package kyawthiha.kt.vocabularytrainingforkids.custom_dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;

import kyawthiha.kt.vocabularytrainingforkids.R;

public class Menu_Dialog extends Dialog implements DialogInterface.OnClickListener {
    Context c;
    Activity a;
    NavController navController;
    CardView btn_learning,btn_writing;
    public Menu_Dialog(final Context context,NavController navController) {
        super(context);
        this.c=context;
        this.navController=navController;



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_menu);
        btn_learning=findViewById(R.id.btn_learning);
        btn_writing=findViewById(R.id.btn_writing);
        btn_learning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                navController.navigate(R.id.action_to_learning);

            }
        });

        btn_writing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                navController.navigate(R.id.action_to_writing);

            }
        });




    }//oncreate



    @Override
    public void onClick(DialogInterface dialog, int which) {

    }



}
