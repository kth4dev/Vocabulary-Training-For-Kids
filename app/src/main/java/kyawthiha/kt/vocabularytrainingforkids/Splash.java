package kyawthiha.kt.vocabularytrainingforkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import kyawthiha.kt.vocabularytrainingforkids.helper.MyHelper;

public class Splash extends AppCompatActivity {
    private boolean mIsBackButtonPressed;
    private static final int SPLASH_DURATION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (!mIsBackButtonPressed) {
                    if(MyHelper.getSavingString(getApplicationContext(),"USER_NAME")==null){
                        Intent intent = new Intent(Splash.this, Profile.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Intent intent = new Intent(Splash.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }

                }
            }

        }, SPLASH_DURATION);
    }
    @Override
    public void onBackPressed() {
        // set the flag to true so the next activity won't start up
        mIsBackButtonPressed = true;
        super.onBackPressed();

    }
}
