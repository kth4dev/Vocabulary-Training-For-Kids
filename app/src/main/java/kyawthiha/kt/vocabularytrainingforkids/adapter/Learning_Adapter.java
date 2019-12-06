package kyawthiha.kt.vocabularytrainingforkids.adapter;


import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import kyawthiha.kt.vocabularytrainingforkids.R;
import kyawthiha.kt.vocabularytrainingforkids.data.V_Data;
import kyawthiha.kt.vocabularytrainingforkids.helper.MyHelper;


public class Learning_Adapter extends RecyclerView.Adapter<Learning_Adapter.ViewHolder> implements   TextToSpeech.OnInitListener  {
    ArrayList<V_Data> ary=new ArrayList<>();
    Context context;
    private TextToSpeech tts;
    public Learning_Adapter(Context c, ArrayList<V_Data> aryy){
        ary.clear();
        ary.addAll(aryy);
        this.context=c;
        this.notifyDataSetChanged();
        tts = new TextToSpeech(c, this);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_learningcategoryview,viewGroup,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            final V_Data cdata=ary.get(i);
            viewHolder.img.setImageDrawable(MyHelper.getImageResource(context,cdata.getTrueAns().toLowerCase()));
            viewHolder.title.setText(cdata.getTrueAns());
            viewHolder.meaning.setText(cdata.getMeaning());
            viewHolder.sound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    speakOut(cdata.getTrueAns());
                }
            });





    }

    @Override
    public int getItemCount() {
        return ary.size();
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language is not supported");
            } else {
                    speakOut("");
            }

        } else {
            Log.e("TTS", "Initilization Failed");
        }

    }

    private void speakOut(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,sound;
        TextView title,meaning;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.iv_limg);
            title=itemView.findViewById(R.id.tv_ltitle);
            sound=itemView.findViewById(R.id.iv_lsound);
            meaning=itemView.findViewById(R.id.tv_lmeaning);

        }
    }


}
