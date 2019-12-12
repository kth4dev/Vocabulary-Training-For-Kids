package kyawthiha.kt.vocabularytrainingforkids.ui.appactivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import kyawthiha.kt.vocabularytrainingforkids.R;
import kyawthiha.kt.vocabularytrainingforkids.custom_dialog.Exit_Dialog;
import kyawthiha.kt.vocabularytrainingforkids.custom_dialog.Result_Dialog;
import kyawthiha.kt.vocabularytrainingforkids.data.V_Data;
import kyawthiha.kt.vocabularytrainingforkids.helper.JsonHelper;
import kyawthiha.kt.vocabularytrainingforkids.helper.MyHelper;
import me.myatminsoe.mdetect.MDetect;
import me.myatminsoe.mdetect.Rabbit;

public class WritingFragment extends Fragment {
    private TextView tv_ture_score,tv_false_score,tv_meaning,tv_indicator;
    private EditText et_answer;
    private Button btn_next;
    private ImageView iv_question_img,iv_wroung_symbol,iv_correct_symbol;
    private ArrayList<V_Data> question_ary=new ArrayList<>();

    private int cuerrent_question=1;
    private int current_index=0;
    private int true_scorboard=0;
    private int false_scoreboard=0;
    private int question_size=0;
    private String ctype;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctype=getArguments().getString("category_name");

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                Exit_Dialog exit_dialog=new Exit_Dialog(getContext(),getActivity(),  Navigation.findNavController(getView()),1);
                exit_dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                exit_dialog.show();
                exit_dialog.setCancelable(false);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
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
                    current_index+=1;
                    cuerrent_question+=1;
                    next();
                }
                else if(btn_next.getText().toString().equalsIgnoreCase("submit")){
                    submit();
                }
                else if(btn_next.getText().toString().equalsIgnoreCase("confirm")){
                    checkAnswer();
                    if(cuerrent_question==question_size){
                        btn_next.setText("Submit");
                    }




                }

            }
        });
        return view;
    }

    private void next(){
        insertData();
    }
    private void submit(){
     Result_Dialog result_dialog=new Result_Dialog(Navigation.findNavController(getView()),getContext(),true_scorboard,question_size,"w"+ctype);
     result_dialog.show();
     result_dialog.setCancelable(false);
    }
    private void checkAnswer(){
        String user_answer=et_answer.getText().toString();
        String true_ans=question_ary.get(current_index).getTrueAns();
        if(user_answer.equalsIgnoreCase(true_ans)){
            true_scorboard+=1;
            iv_correct_symbol.setVisibility(View.VISIBLE);
            MyHelper.correctSound(getContext());
        }
        else{
            false_scoreboard+=1;
            iv_wroung_symbol.setVisibility(View.VISIBLE);
            MyHelper.wroungSound(getContext());
        }
        insertScore();
        btn_next.setText("Next");
        et_answer.setEnabled(false);
    }

    private void setUpUI(View view){
        tv_ture_score=view.findViewById(R.id.tv_ture_score);
        tv_false_score=view.findViewById(R.id.tv_false_score);
        tv_indicator=view.findViewById(R.id.tv_windicator);

        iv_question_img=view.findViewById(R.id.iv_wimg);
        iv_correct_symbol=view.findViewById(R.id.iv_wcorrect_symbol);
        iv_wroung_symbol=view.findViewById(R.id.iv_wwrong_symbol);
        tv_meaning=view.findViewById(R.id.tv_wmeaning);
        et_answer=view.findViewById(R.id.et_wanswer);
        btn_next=view.findViewById(R.id.btn_wnext);

        question_ary= JsonHelper.getData(ctype,getActivity());
        Collections.shuffle(question_ary);
        question_size=question_ary.size();
        insertData();
        insertScore();
    }


    private void insertData(){
        insertScore();
        unvisible();
        tv_ture_score.setText(true_scorboard+"");
        tv_false_score.setText(false_scoreboard+"");
        iv_question_img.setImageDrawable(MyHelper.getImageResource(getContext(),question_ary.get(current_index).getTrueAns().toLowerCase()));
       if(MDetect.INSTANCE.isUnicode()){
           tv_meaning.setText(question_ary.get(current_index).getMeaning());
       }else{
           tv_meaning.setText(Rabbit.uni2zg(question_ary.get(current_index).getMeaning()));
       }



        et_answer.setText("");
    }
    private void insertScore(){
        tv_ture_score.setText(true_scorboard+"");
        tv_false_score.setText(false_scoreboard+"");
        tv_indicator.setText(cuerrent_question+"/"+question_size);
    }
    private void unvisible(){
        btn_next.setText("Confirm");
        iv_correct_symbol.setVisibility(View.GONE);
        iv_wroung_symbol.setVisibility(View.GONE);
        et_answer.setEnabled(true);

    }




}
