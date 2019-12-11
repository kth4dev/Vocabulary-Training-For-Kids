package kyawthiha.kt.vocabularytrainingforkids.custom_dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;

import kyawthiha.kt.vocabularytrainingforkids.R;
import kyawthiha.kt.vocabularytrainingforkids.helper.MyHelper;

public class Result_Dialog extends Dialog implements DialogInterface.OnClickListener {
    Context c;
    Activity a;
    int result;
    String type;
    int question_size;
    private NavController navController;
    Button btn_gohome,btn_again;

    public Result_Dialog(NavController navController,final Context context,int result,int question_size,String type) {
        super(context);
        this.c=context;
        this.result=result;
        this.type=type;
        this.question_size=question_size;
        this.navController=navController;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_result);
        TextView finalresult=findViewById(R.id.tv_finalresult);
        TextView tv_hs=findViewById(R.id.tv_highscore);
        TextView tv_welcome=findViewById(R.id.result_welcome);
        tv_welcome.setText("Hi, "+MyHelper.getSavingString(c,"USER_NAME"));
        finalresult.setText(finalresult.getText().toString()+result+"/"+question_size);
        String key_name="HS_"+type.toUpperCase();
        String current_hs= MyHelper.getSavingString(c,key_name);
        Log.d("mgmgtest", current_hs);
        if(Integer.parseInt(current_hs)<result){
            MyHelper.saveString(c,key_name,String.valueOf(result));
        }
        tv_hs.setText(tv_hs.getText().toString()+MyHelper.getSavingString(c,key_name)+"/"+question_size);
        btn_gohome=findViewById(R.id.btn_gohome);
        btn_gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_to_home);
                dismiss();
            }
        });
        btn_again=findViewById(R.id.btn_playagain);
        btn_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Bundle bundle = new Bundle();
              bundle.putString("category_name", type.substring(1,type.length()));
              dismiss();
              if(type.substring(0,1).equalsIgnoreCase("w")){
                  navController.navigate(R.id.action_to_wrefresh,bundle);
              }
              else   if(type.substring(0,1).equalsIgnoreCase("l")){
                  navController.navigate(R.id.action_to_lrefresh,bundle);
              }
              else   if(type.substring(0,1).equalsIgnoreCase("f")){
                  navController.navigate(R.id.action_to_frefresh,bundle);
              }
              else   if(type.substring(0,1).equalsIgnoreCase("m")){
                  navController.navigate(R.id.action_to_mrefresh,bundle);
              }


            }
        });
    }//oncreate



    @Override
    public void onClick(DialogInterface dialog, int which) {

    }



}
