package kyawthiha.kt.vocabularytrainingforkids.ui.appactivity;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import kyawthiha.kt.vocabularytrainingforkids.R;
import kyawthiha.kt.vocabularytrainingforkids.custom_dialog.Exit_Dialog;
import kyawthiha.kt.vocabularytrainingforkids.custom_dialog.Result_Dialog;
import kyawthiha.kt.vocabularytrainingforkids.data.V_Data;
import kyawthiha.kt.vocabularytrainingforkids.helper.JsonHelper;
import kyawthiha.kt.vocabularytrainingforkids.helper.MyHelper;
import me.myatminsoe.mdetect.MDetect;
import me.myatminsoe.mdetect.Rabbit;


public class ListeningFragment extends Fragment  implements   TextToSpeech.OnInitListener  {
    ProgressDialog progress;
    private TextToSpeech tts;

    private TextView tv_ture_score,tv_false_score,tv_indicator,tv_meaning;
    private Button btn_next;
    private ImageView iv_question_img,iv_wroung_symbol,iv_correct_symbol;
    private CardView btn_correct,btn_wrong,btn_sound;
    private ArrayList<V_Data> question_ary=new ArrayList<>();

    private int current_question=1;
    private int current_index=0;
    private int true_scorboard=0;
    private int false_scoreboard=0;
    private int question_size=0;
    private String current_ans,ctype;
    private LinearLayout all_btn;
    public ListeningFragment() {
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

        View view=inflater.inflate(R.layout.fragment_listening, container, false);
        tts = new TextToSpeech(getContext(), this);

        showProgressDialog();
        setUpUI(view);
        btn_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut(current_ans);
            }
        });
        btn_correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all_btn.setVisibility(View.GONE);
                btn_next.setVisibility(View.VISIBLE);
                if(question_ary.get(current_index).getTrueAns().equals(current_ans)){
                    //increase mark
                    true_ans();
                }else{
                    wrong_ans();
                }
                if(current_question==question_size){
                    btn_next.setText("Submit");
                }



            }
        });
        btn_wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all_btn.setVisibility(View.GONE);
                btn_next.setVisibility(View.VISIBLE);
                if(!question_ary.get(current_index).getTrueAns().equals(current_ans)){
                    //increase mark
                    true_ans();
                }else{

                    wrong_ans();
                }
                if(current_question==question_size){
                    btn_next.setText("Submit");
                }



            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_next.getText().toString().equals("Submit")){
                    Result_Dialog result_dialog=new Result_Dialog(Navigation.findNavController(getView()),getContext(),true_scorboard,question_size,"l"+ctype);
                    result_dialog.show();
                    result_dialog.setCancelable(false);
                }else{
                    current_index+=1;
                    current_question+=1;
                    insertData();
                }


            }
        });

        return view;
    }
    public void wrong_ans(){
        iv_wroung_symbol.setVisibility(View.VISIBLE);
        false_scoreboard+=1;
        MyHelper.wroungSound(getContext());
        insertScore();


    }

    public void true_ans(){
        iv_correct_symbol.setVisibility(View.VISIBLE);
        MyHelper.correctSound(getContext());
        true_scorboard+=1;
        insertScore();
    }
    private void setUpUI(View view){
        tv_ture_score=view.findViewById(R.id.tv_llture_score);
        tv_false_score=view.findViewById(R.id.tv_llfalse_score);
        tv_indicator=view.findViewById(R.id.tv_llindicator);

        iv_question_img=view.findViewById(R.id.iv_llimg);
        iv_correct_symbol=view.findViewById(R.id.iv_llcorrect_symbol);
        iv_wroung_symbol=view.findViewById(R.id.iv_llwrong_symbol);
        tv_meaning=view.findViewById(R.id.tv_llmeaning);

        btn_correct=view.findViewById(R.id.btn_iv_true);
        btn_sound=view.findViewById(R.id.btn_iv_sound);
        btn_wrong=view.findViewById(R.id.btn_iv_false);

        btn_next=view.findViewById(R.id.btn_llnext);
        all_btn=view.findViewById(R.id.ll_all_btn);

        question_ary= JsonHelper.getData(ctype,getActivity());
        Collections.shuffle(question_ary);
        question_size=question_ary.size();
        insertScore();
        insertData();
    }

    private void insertData(){
        all_btn.setVisibility(View.VISIBLE);
        btn_next.setVisibility(View.GONE);
        iv_correct_symbol.setVisibility(View.GONE);
        iv_wroung_symbol.setVisibility(View.GONE);

        iv_question_img.setImageDrawable(MyHelper.getImageResource(getContext(),question_ary.get(current_index).getTrueAns().toLowerCase()));
        if(MDetect.INSTANCE.isUnicode()){
            tv_meaning.setText(question_ary.get(current_index).getMeaning());
        }else{
            tv_meaning.setText(Rabbit.uni2zg(question_ary.get(current_index).getMeaning()));
        }
        tv_indicator.setText(current_question+"/"+question_size);
        List<String> choices_data=new ArrayList<>() ;
        choices_data.add(question_ary.get(current_index).getTrueAns());
        choices_data.add(question_ary.get(current_index).getFalseAns1());
        choices_data.add(question_ary.get(current_index).getFalseAns2());
        choices_data.add(question_ary.get(current_index).getFalseAns3());
      /*  Random rand = new Random();
        int random_num = rand.nextInt(4);*/
        Collections.shuffle(choices_data);
        current_ans=choices_data.get(0);
        speakOut(choices_data.get(0));

    }
    private void insertScore(){
        tv_ture_score.setText(true_scorboard+"");
        tv_false_score.setText(false_scoreboard+"");
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            tts.setLanguage(Locale.US);
            speakOut(current_ans);
            progress.dismiss();

        } else {
            Log.e("TTS", "Initilization Failed");
        }
    }

    private void showProgressDialog(){
        progress = new ProgressDialog(getContext());
        progress.setMessage("Loading.Please wait...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();
        progress.setCancelable(false);
    }
    private void speakOut(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
