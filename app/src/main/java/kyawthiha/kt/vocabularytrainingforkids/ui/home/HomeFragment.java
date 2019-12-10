package kyawthiha.kt.vocabularytrainingforkids.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import kyawthiha.kt.vocabularytrainingforkids.R;
import kyawthiha.kt.vocabularytrainingforkids.custom_dialog.Menu_Dialog;
import kyawthiha.kt.vocabularytrainingforkids.helper.MyHelper;

public class HomeFragment extends Fragment{

    private CardView category_fruits,category_animals;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        category_fruits=root.findViewById(R.id.category_fruits);
        category_animals=root.findViewById(R.id.category_animals);
        category_fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuDialog(v,"fruits");
            }
        });
        category_animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuDialog(v,"animals");
            }
        });
        return root;
    }

    private void menuDialog(View view,String c_name){
        Menu_Dialog menu_dialog = new Menu_Dialog(getContext(), Navigation.findNavController(view),c_name);
        menu_dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        menu_dialog.setCancelable(false);
        menu_dialog.show();
    }


}