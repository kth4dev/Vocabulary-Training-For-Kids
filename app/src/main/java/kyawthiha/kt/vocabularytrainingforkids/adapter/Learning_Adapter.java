package kyawthiha.kt.vocabularytrainingforkids.adapter;


import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import kyawthiha.kt.vocabularytrainingforkids.R;
import kyawthiha.kt.vocabularytrainingforkids.data.V_Data;
import kyawthiha.kt.vocabularytrainingforkids.helper.DataBaseHelper;
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
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
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

            boolean favcheck=false;
            final DataBaseHelper db=new DataBaseHelper(context);
            ArrayList<V_Data> allfavproduct=db.getAllFavWords();
            for(int k=0;k<allfavproduct.size();k++){
            if(allfavproduct.get(k).getTrueAns().equals(cdata.getTrueAns())){
                favcheck=true;
                break;
            } }
            if(favcheck){
                favourited(viewHolder);
            }
            else {
                un_favourite(viewHolder);
            }
            viewHolder.btn_fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(viewHolder.fav_before.getVisibility()==View.VISIBLE){
                        boolean result=db.insertData(cdata.getTrueAns(),cdata.getMeaning());
                        if(result){
                            Toast.makeText(v.getContext(),"Add to Favourite list!",Toast.LENGTH_SHORT).show();
                            favourited(viewHolder);
                        }
                        else{
                            Toast.makeText(v.getContext(),"Fail!",Toast.LENGTH_LONG).show();
                            un_favourite(viewHolder);
                        }
                    }
                    else{
                        db.removeData(cdata.getTrueAns());
                        notifyDataSetChanged();
                        Toast.makeText(v.getContext(),"Remove from Favourite list!",Toast.LENGTH_LONG).show();
                        un_favourite(viewHolder);
                    }
                }
            });






    }
    private void favourited(ViewHolder viewHolder){
        viewHolder.fav_after.setVisibility(View.VISIBLE);
        viewHolder.fav_before.setVisibility(View.GONE);
    }
    private void un_favourite(ViewHolder viewHolder){
        viewHolder.fav_after.setVisibility(View.GONE);
        viewHolder.fav_before.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return ary.size();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            tts.setLanguage(Locale.US);
        } else {
            Log.e("TTS", "Initilization Failed");
        }
    }

    private void speakOut(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,sound,fav_before,fav_after;
        TextView title,meaning;
        RelativeLayout btn_fav;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.iv_limg);
            title=itemView.findViewById(R.id.tv_ltitle);
            sound=itemView.findViewById(R.id.iv_lsound);
            meaning=itemView.findViewById(R.id.tv_lmeaning);
            fav_before=itemView.findViewById(R.id.fav_before);
            fav_after=itemView.findViewById(R.id.fav_after);
            btn_fav=itemView.findViewById(R.id.btn_fav);

        }
    }


}
