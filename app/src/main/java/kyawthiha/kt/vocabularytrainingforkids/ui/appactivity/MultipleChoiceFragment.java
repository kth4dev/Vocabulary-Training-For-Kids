package kyawthiha.kt.vocabularytrainingforkids.ui.appactivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kyawthiha.kt.vocabularytrainingforkids.R;
import kyawthiha.kt.vocabularytrainingforkids.custom_dialog.Exit_Dialog;
import kyawthiha.kt.vocabularytrainingforkids.custom_dialog.Result_Dialog;
import kyawthiha.kt.vocabularytrainingforkids.data.V_Data;
import kyawthiha.kt.vocabularytrainingforkids.helper.JsonHelper;
import kyawthiha.kt.vocabularytrainingforkids.helper.MyHelper;


public class MultipleChoiceFragment extends Fragment {
    private  TextView tv_ture_score,tv_false_score,tv_meaning,tv_indicator;
    private  Button  place_one,place_two,place_three,place_four;
    private ImageView iv_question_img,iv_wroung_symbol,iv_correct_symbol;
    private  ArrayList<V_Data> question_ary=new ArrayList<>();
    private Button btn_next;
    private LinearLayout all_btn;
    private  int current_question=1;
    private  int current_index=0;
    private  int true_scorboard=0;
    private  int false_scoreboard=0;
    private int question_size=0;

    private String ctype;
    public MultipleChoiceFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctype=getArguments().getString("category_name");
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
        View view= inflater.inflate(R.layout.fragment_multiple_choice, container, false);
        setUpUI(view);
        place_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u_ans=place_one.getText().toString();
                    next(u_ans);


            }
        });
        place_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u_ans=place_two.getText().toString();
                    next(u_ans);

            }
        });
        place_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u_ans=place_three.getText().toString();
                    next(u_ans);
            }
        });
        place_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u_ans=place_four.getText().toString();
                    next(u_ans);

            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_next.getText().toString().equalsIgnoreCase("next")){
                    insertData();
                }else{
                    Result_Dialog result_dialog=new Result_Dialog(Navigation.findNavController(getView()),getContext(),true_scorboard,question_size,"m"+ctype);
                    result_dialog.show();
                    result_dialog.setCancelable(false);
                }

            }
        });

        return view;
    }


    private void setUpUI(View view){
        all_btn=view.findViewById(R.id.all_btn_m);
        btn_next=view.findViewById(R.id.btn_mnext);
        iv_correct_symbol=view.findViewById(R.id.iv_mcorrect_symbol);
        iv_wroung_symbol=view.findViewById(R.id.iv_mwrong_symbol);
        tv_ture_score=view.findViewById(R.id.tv_mture_score);
        tv_false_score=view.findViewById(R.id.tv_mfalse_score);
        tv_indicator=view.findViewById(R.id.tv_mindicator);
        tv_meaning=view.findViewById(R.id.tv_mmeaning);
        place_one=view.findViewById(R.id.place_one);
        place_two=view.findViewById(R.id.place_two);
        place_three=view.findViewById(R.id.place_three);
        place_four=view.findViewById(R.id.place_four);
        iv_question_img=view.findViewById(R.id.iv_mimg);
        question_ary= JsonHelper.getData(ctype,getActivity());
        question_size=question_ary.size();
        insertData();
        insertScore();
    }
    private void next(String user_ans){
        all_btn.setVisibility(View.GONE);
        btn_next.setVisibility(View.VISIBLE);
        checkAnswer(user_ans);
        current_question+=1;
        current_index+=1;
    }

    private void checkAnswer(String user_ans) {
        if(user_ans.equalsIgnoreCase(question_ary.get(current_index).getTrueAns())){
            true_scorboard+=1;
            iv_correct_symbol.setVisibility(View.VISIBLE);
        }
        else{
            false_scoreboard+=1;
            iv_wroung_symbol.setVisibility(View.VISIBLE);
        }
        insertScore();
    }

    private void insertData(){
        if(current_question==question_size){
            btn_next.setText("Submit");
        }
        all_btn.setVisibility(View.VISIBLE);
        btn_next.setVisibility(View.GONE);
        iv_correct_symbol.setVisibility(View.GONE);
        iv_wroung_symbol.setVisibility(View.GONE);
        tv_indicator.setText(current_question+"/"+question_size);
        iv_question_img.setImageDrawable(MyHelper.getImageResource(getContext(),question_ary.get(current_index).getTrueAns().toLowerCase()));
        tv_meaning.setText(question_ary.get(current_index).getMeaning());
        List<String> choices_data=new ArrayList<>() ;
        choices_data.add(question_ary.get(current_index).getTrueAns());
        choices_data.add(question_ary.get(current_index).getFalseAns1());
        choices_data.add(question_ary.get(current_index).getFalseAns2());
        choices_data.add(question_ary.get(current_index).getFalseAns3());
        Collections.shuffle(choices_data);
        place_one.setText(choices_data.get(0));
        place_two.setText(choices_data.get(1));
        place_three.setText(choices_data.get(2));
        place_four.setText(choices_data.get(3));
    }
    private void insertScore(){
        tv_ture_score.setText(true_scorboard+"");
        tv_false_score.setText(false_scoreboard+"");
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
