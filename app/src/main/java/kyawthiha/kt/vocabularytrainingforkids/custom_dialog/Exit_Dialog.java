package kyawthiha.kt.vocabularytrainingforkids.custom_dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;

import kyawthiha.kt.vocabularytrainingforkids.R;

public class Exit_Dialog extends Dialog implements DialogInterface.OnClickListener {
    Context c;
    private NavController navController;
    private Button btn_yes,btn_no;
    private int mode;
    private Activity activity;
    public Exit_Dialog(final Context context,Activity activity, NavController navController,int mode) {
        super(context);
        this.c=context;
        this.mode=mode;
        this.navController=navController;
        this.activity=activity;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_exit);
        btn_yes=findViewById(R.id.btn_yes);
        btn_no=findViewById(R.id.btn_no);
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mode==1){
                    navController.navigate(R.id.action_to_home);
                }
                else{
                    activity.finish();
                }

                dismiss();
            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });




    }//oncreate



    @Override
    public void onClick(DialogInterface dialog, int which) {

    }



}
