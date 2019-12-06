package kyawthiha.kt.vocabularytrainingforkids.custom_dialog;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import kyawthiha.kt.vocabularytrainingforkids.R;
import kyawthiha.kt.vocabularytrainingforkids.ui.home.HomeFragment;

public class Menu_Dialog extends Dialog implements DialogInterface.OnClickListener {
    Context c;
    Activity a;
    NavController navController;
    public Menu_Dialog(final Context context,NavController navController) {
        super(context);
        this.c=context;
        this.navController=navController;



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_dialog);
        CardView btn_learning=findViewById(R.id.btn_learning);
        btn_learning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                navController.navigate(R.id.action_to_learning);

            }
        });



    }



    @Override
    public void onClick(DialogInterface dialog, int which) {

    }



}
