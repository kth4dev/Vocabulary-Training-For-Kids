package kyawthiha.kt.vocabularytrainingforkids.ui.appactivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import kyawthiha.kt.vocabularytrainingforkids.R;
import kyawthiha.kt.vocabularytrainingforkids.adapter.Learning_Adapter;
import kyawthiha.kt.vocabularytrainingforkids.data.V_Data;
import kyawthiha.kt.vocabularytrainingforkids.helper.JsonHelper;
import kyawthiha.kt.vocabularytrainingforkids.helper.MyHelper;


public class Learning_fragment extends Fragment {

    public Learning_fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_learning, container, false);
        RecyclerView learning_rv=view.findViewById(R.id.learning_recyclerview);
        ArrayList<V_Data> ary=new ArrayList<>();
        ary= JsonHelper.getData("fruits",getActivity());
        Learning_Adapter adapter=new Learning_Adapter(getContext(),ary);
        learning_rv.setAdapter(adapter);
        learning_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;

    }

}
