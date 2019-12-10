package kyawthiha.kt.vocabularytrainingforkids.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import static android.content.Context.MODE_PRIVATE;

public class MyHelper {

    public static Drawable getImageResource(Context context, String name){
        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(name, "drawable",
                context.getPackageName());
        return resources.getDrawable(resourceId);
    }

    public static String getSavingString(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences("ALL_DATA",MODE_PRIVATE);
        String value =sharedPreferences.getString(key,null);
        return value;
    }
    public static void  saveString(Context context,String key,String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences("ALL_DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }
}
