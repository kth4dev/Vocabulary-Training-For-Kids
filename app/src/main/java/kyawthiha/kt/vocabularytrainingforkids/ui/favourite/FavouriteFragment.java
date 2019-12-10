package kyawthiha.kt.vocabularytrainingforkids.ui.favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kyawthiha.kt.vocabularytrainingforkids.R;
import kyawthiha.kt.vocabularytrainingforkids.adapter.Learning_Adapter;
import kyawthiha.kt.vocabularytrainingforkids.data.V_Data;
import kyawthiha.kt.vocabularytrainingforkids.helper.DataBaseHelper;
import kyawthiha.kt.vocabularytrainingforkids.helper.JsonHelper;

public class FavouriteFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_favourite, container, false);
        RecyclerView favourite_rv=root.findViewById(R.id.rv_favourite);
        ArrayList<V_Data> ary=new ArrayList<>();
        DataBaseHelper dataBaseHelper=new DataBaseHelper(getContext());
        ary=dataBaseHelper.getAllFavWords();
        Learning_Adapter adapter=new Learning_Adapter(getContext(),ary);
        favourite_rv.setAdapter(adapter);
        favourite_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }
}