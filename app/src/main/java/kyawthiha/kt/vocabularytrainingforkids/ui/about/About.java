package kyawthiha.kt.vocabularytrainingforkids.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import kyawthiha.kt.vocabularytrainingforkids.R;

public class About extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_about, container, false);
        CardView share=root.findViewById(R.id.btn_share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharefun();
            }
        });
        CardView feedback=root.findViewById(R.id.btn_feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedbackfun();
            }
        });
        return root;
    }
    public void sharefun() {
        Intent i=new Intent(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=kyawthiha.kt.vocabularytrainingforkids");
        i.setType("text/plain");
        startActivity(Intent.createChooser(i,""));
    }
    public void feedbackfun() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","developer.kyawthiha@gmail.com", null));
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
}