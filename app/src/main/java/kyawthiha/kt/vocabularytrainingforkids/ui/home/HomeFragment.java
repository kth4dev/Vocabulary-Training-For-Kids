package kyawthiha.kt.vocabularytrainingforkids.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import kyawthiha.kt.vocabularytrainingforkids.R;
import kyawthiha.kt.vocabularytrainingforkids.custom_dialog.Menu_Dialog;

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
                Menu_Dialog sent = new Menu_Dialog(getContext());
                sent.setCancelable(false);
                sent.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                sent.show();
            }
        });
        category_animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return root;
    }


}