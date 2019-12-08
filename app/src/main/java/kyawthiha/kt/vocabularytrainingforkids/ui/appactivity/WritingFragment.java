package kyawthiha.kt.vocabularytrainingforkids.ui.appactivity;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

import kyawthiha.kt.vocabularytrainingforkids.R;
import kyawthiha.kt.vocabularytrainingforkids.data.V_Data;
import kyawthiha.kt.vocabularytrainingforkids.helper.JsonHelper;
import kyawthiha.kt.vocabularytrainingforkids.helper.MyHelper;

public class WritingFragment extends Fragment {
    TextView tv_ture_score,tv_false_score,tv_meaning,tv_indicator,tv_finalresult;
    EditText et_answer;
    Button btn_next;
    ImageView iv_question_img;
    LinearLayout ll_resultboard;
    ArrayList<V_Data> question_ary=new ArrayList<>();

    int cuerrent_question=1;
    int current_index=0;
    int true_scorboard=0;
    int false_scoreboard=0;
    int question_size=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_writing, container, false);
        setUpUI(view);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(btn_next.getText().toString().equalsIgnoreCase("next")){
                    next();
                }
                else if(btn_next.getText().toString().equalsIgnoreCase("submit")){
                    submit();
                }

            }
        });
        return view;
    }

    private void next(){
        check_question();
        cuerrent_question+=1;
        current_index+=1;
        if(cuerrent_question==question_size){
            btn_next.setText("Submit");
        }

        insertData();
    }
    private void submit(){
     check_question();
     insertScore();
     ll_resultboard.setVisibility(View.VISIBLE);
     btn_next.setEnabled(false);
     tv_finalresult.setText(tv_finalresult.getText().toString()+true_scorboard+"/"+question_size);





    }
    private void check_question(){
        String user_answer=et_answer.getText().toString();
        String true_ans=question_ary.get(current_index).getTrueAns();
        if(user_answer.equalsIgnoreCase(true_ans)){
            true_scorboard+=1;
        }
        else{
            false_scoreboard+=1;
        }
    }

    private void setUpUI(View view){
        tv_ture_score=view.findViewById(R.id.tv_ture_score);
        tv_false_score=view.findViewById(R.id.tv_false_score);
        tv_indicator=view.findViewById(R.id.tv_windicator);
        tv_meaning=view.findViewById(R.id.tv_wmeaning);
        et_answer=view.findViewById(R.id.et_wanswer);
        btn_next=view.findViewById(R.id.btn_wnext);
        iv_question_img=view.findViewById(R.id.iv_wimg);
        tv_finalresult=view.findViewById(R.id.tv_wfinalresult);
        ll_resultboard=view.findViewById(R.id.ll_wresultboard);
        question_ary= JsonHelper.getData("fruits",getActivity());
        question_size=question_ary.size();
        insertData();
    }


    private void insertData(){
        insertScore();
        tv_ture_score.setText(true_scorboard+"");
        tv_false_score.setText(false_scoreboard+"");
        iv_question_img.setImageDrawable(MyHelper.getImageResource(getContext(),question_ary.get(current_index).getTrueAns().toLowerCase()));
        tv_meaning.setText(question_ary.get(current_index).getMeaning());
        tv_indicator.setText(cuerrent_question+"/"+question_size);
        et_answer.setText("");
    }
    private void insertScore(){
        tv_ture_score.setText(true_scorboard+"");
        tv_false_score.setText(false_scoreboard+"");
    }



}
