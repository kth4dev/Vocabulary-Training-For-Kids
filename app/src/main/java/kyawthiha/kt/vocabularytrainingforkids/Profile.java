
package kyawthiha.kt.vocabularytrainingforkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kyawthiha.kt.vocabularytrainingforkids.helper.MyHelper;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        final EditText username=findViewById(R.id.et_username);
        Button next=findViewById(R.id.btn_goto_main);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UN=username.getText().toString();
                if(UN.equals("")){
                    Toast.makeText(v.getContext(),"Please Enter your name",Toast.LENGTH_LONG).show();
                }
                else {
                    MyHelper.saveString(v.getContext(),"USER_NAME",UN);

                    MyHelper.saveString(v.getContext(),"HS_WFRUITS","0");
                    MyHelper.saveString(v.getContext(),"HS_LFRUITS","0");
                    MyHelper.saveString(v.getContext(),"HS_FFRUITS","0");
                    MyHelper.saveString(v.getContext(),"HS_MFRUITS","0");

                    MyHelper.saveString(v.getContext(),"HS_WANIMALS","0");
                    MyHelper.saveString(v.getContext(),"HS_LANIMALS","0");
                    MyHelper.saveString(v.getContext(),"HS_FANIMALS","0");
                    MyHelper.saveString(v.getContext(),"HS_MANIMALS","0");

                    MyHelper.saveString(v.getContext(),"HS_WSCHOOL","0");
                    MyHelper.saveString(v.getContext(),"HS_LSCHOOL","0");
                    MyHelper.saveString(v.getContext(),"HS_FSCHOOL","0");
                    MyHelper.saveString(v.getContext(),"HS_MSCHOOL","0");

                    MyHelper.saveString(v.getContext(),"HS_WFOODS","0");
                    MyHelper.saveString(v.getContext(),"HS_LFOODS","0");
                    MyHelper.saveString(v.getContext(),"HS_FFOODS","0");
                    MyHelper.saveString(v.getContext(),"HS_MFOODS","0");

                    MyHelper.saveString(v.getContext(),"HS_WVEHICLES","0");
                    MyHelper.saveString(v.getContext(),"HS_LVEHICLES","0");
                    MyHelper.saveString(v.getContext(),"HS_FVEHICLES","0");
                    MyHelper.saveString(v.getContext(),"HS_MVEHICLES","0");
                    Intent intent = new Intent(Profile.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
