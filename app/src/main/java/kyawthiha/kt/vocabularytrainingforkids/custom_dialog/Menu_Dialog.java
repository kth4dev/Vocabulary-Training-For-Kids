package kyawthiha.kt.vocabularytrainingforkids.custom_dialog;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;

import kyawthiha.kt.vocabularytrainingforkids.R;

public class Menu_Dialog extends Dialog implements DialogInterface.OnClickListener {
    Context c;
    Activity a;
    public Menu_Dialog(Context context) {
        super(context);
        this.c=context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_dialog);



    }



    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
