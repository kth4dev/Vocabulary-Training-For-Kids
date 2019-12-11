package kyawthiha.kt.vocabularytrainingforkids.ui.appactivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import kyawthiha.kt.vocabularytrainingforkids.R;
import kyawthiha.kt.vocabularytrainingforkids.adapter.Learning_Adapter;
import kyawthiha.kt.vocabularytrainingforkids.custom_dialog.Exit_Dialog;
import kyawthiha.kt.vocabularytrainingforkids.data.V_Data;
import kyawthiha.kt.vocabularytrainingforkids.helper.JsonHelper;
import kyawthiha.kt.vocabularytrainingforkids.helper.MyHelper;


public class LearningFragment extends Fragment {

    public LearningFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                Exit_Dialog exit_dialog=new Exit_Dialog(getContext(),getActivity(),  Navigation.findNavController(getView()),1);
                exit_dialog.show();
                exit_dialog.setCancelable(false);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_learning, container, false);
        RecyclerView learning_rv=view.findViewById(R.id.learning_recyclerview);
        ArrayList<V_Data> ary=new ArrayList<>();
        ary= JsonHelper.getData(getArguments().getString("category_name"),getActivity());
        Learning_Adapter adapter=new Learning_Adapter(getContext(),ary);
        learning_rv.setAdapter(adapter);
        learning_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;

    }

}
