package kyawthiha.kt.vocabularytrainingforkids.helper;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import kyawthiha.kt.vocabularytrainingforkids.data.V_Data;

public class JsonHelper {
    public static String loadjsonfromAsset(Activity a){
        String json=null;
        try {
            InputStream is=a.getAssets().open("mmvocabulary.json");
            int size=is.available();
            byte[] buffer=new byte[size];
            is.read(buffer);
            is.close();
            json=new String(buffer,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    public static ArrayList<V_Data> getData(String name,Activity activity) {
        JSONObject reader= null;
        ArrayList<V_Data> rarray=new ArrayList<>();
        try {
            reader = new JSONObject(loadjsonfromAsset(activity));
            JSONArray getarray=reader.getJSONArray(name);
            Log.d("ktest", name+getarray.length()+"");
            for(int i=0;i<getarray.length();i++){
                JSONObject obj=getarray.getJSONObject(i);
                V_Data ndata=new V_Data(obj.getString("true_ans"),obj.getString("false_ans_1"),obj.getString("false_ans_2"),obj.getString("false_ans_3"),obj.getString("meaning"));
                rarray.add(ndata);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rarray;
    }


}
