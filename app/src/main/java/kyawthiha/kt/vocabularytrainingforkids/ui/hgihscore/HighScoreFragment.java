package kyawthiha.kt.vocabularytrainingforkids.ui.hgihscore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kyawthiha.kt.vocabularytrainingforkids.R;
import kyawthiha.kt.vocabularytrainingforkids.adapter.HighScore_Adapter;
import kyawthiha.kt.vocabularytrainingforkids.adapter.Learning_Adapter;
import kyawthiha.kt.vocabularytrainingforkids.data.HS_Data;
import kyawthiha.kt.vocabularytrainingforkids.data.V_Data;
import kyawthiha.kt.vocabularytrainingforkids.helper.JsonHelper;
import kyawthiha.kt.vocabularytrainingforkids.helper.MyHelper;

public class HighScoreFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_highscore, container, false);
        RecyclerView rv_highscore=root.findViewById(R.id.rv_highscore);
        ArrayList<HS_Data> ary=new ArrayList<>();
        ary.add(new HS_Data("Fruits", MyHelper.getSavingString(getContext(),"HS_WFRUITS"), MyHelper.getSavingString(getContext(),"HS_LFRUITS"), MyHelper.getSavingString(getContext(),"HS_FFRUITS"), MyHelper.getSavingString(getContext(),"HS_MFRUITS")));
        ary.add(new HS_Data("Animals",
                MyHelper.getSavingString(getContext(),"HS_WANIMALS"),
                MyHelper.getSavingString(getContext(),"HS_LANIMALS"),
                MyHelper.getSavingString(getContext(),"HS_FANIMALS"),
                MyHelper.getSavingString(getContext(),"HS_MANIMALS")));
        ary.add(new HS_Data("School Suplies",
                MyHelper.getSavingString(getContext(),"HS_WSCHOOL"),
                MyHelper.getSavingString(getContext(),"HS_LSCHOOL"),
                MyHelper.getSavingString(getContext(),"HS_FSCHOOL"),
                MyHelper.getSavingString(getContext(),"HS_MSCHOOL")));

        HighScore_Adapter adapter=new HighScore_Adapter(getContext(),ary);
        rv_highscore.setAdapter(adapter);
        rv_highscore.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }
}