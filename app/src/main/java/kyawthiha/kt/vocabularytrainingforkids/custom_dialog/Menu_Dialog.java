package kyawthiha.kt.vocabularytrainingforkids.custom_dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.core.app.BundleCompat;
import androidx.navigation.NavController;

import kyawthiha.kt.vocabularytrainingforkids.R;

public class Menu_Dialog extends Dialog implements DialogInterface.OnClickListener {
    Context c;
    Activity a;
    private NavController navController;
    private CardView btn_learning,btn_writing,btn_multiplechoice,btn_flashcard;
    ImageView btn_cancel;
    private String type;
    public Menu_Dialog(final Context context,NavController navController,String type) {
        super(context);
        this.c=context;
        this.navController=navController;
        this.type=type;



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_menu);
        btn_learning=findViewById(R.id.btn_learning);
        btn_writing=findViewById(R.id.btn_writing);
        btn_multiplechoice=findViewById(R.id.btn_multiplechoice);
        btn_flashcard=findViewById(R.id.btn_flashcard);
        btn_cancel=findViewById(R.id.btn_cancel);
        btn_learning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Bundle bundle = new Bundle();
                bundle.putString("category_name", type);
                navController.navigate(R.id.action_to_learning,bundle);

            }
        });

        btn_writing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Bundle bundle = new Bundle();
                bundle.putString("category_name", type);
                navController.navigate(R.id.action_to_writing,bundle);

            }
        });

        btn_multiplechoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Bundle bundle = new Bundle();
                bundle.putString("category_name", type);
                navController.navigate(R.id.action_to_multiplechoice,bundle);

            }
        });

        btn_flashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Bundle bundle = new Bundle();
                bundle.putString("category_name", type);
                navController.navigate(R.id.action_to_flashcard,bundle);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
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
