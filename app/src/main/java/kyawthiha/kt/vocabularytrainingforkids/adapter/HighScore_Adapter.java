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
import java.util.Locale;

import kyawthiha.kt.vocabularytrainingforkids.R;
import kyawthiha.kt.vocabularytrainingforkids.data.V_Data;
import kyawthiha.kt.vocabularytrainingforkids.helper.DataBaseHelper;
import kyawthiha.kt.vocabularytrainingforkids.helper.MyHelper;


public class HighScore_Adapter extends RecyclerView.Adapter<HighScore_Adapter.ViewHolder> {
    ArrayList<V_Data> ary=new ArrayList<>();
    Context context;

    public HighScore_Adapter(Context c, ArrayList<V_Data> aryy){
        ary.clear();
        ary.addAll(aryy);
        this.context=c;
        this.notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_highscore,viewGroup,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
            final V_Data cdata=ary.get(i);






    }

    @Override
    public int getItemCount() {
        return ary.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }


}
