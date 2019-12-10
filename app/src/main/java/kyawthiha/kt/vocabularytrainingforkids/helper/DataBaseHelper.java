package kyawthiha.kt.vocabularytrainingforkids.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.IOException;
import java.util.ArrayList;
import kyawthiha.kt.vocabularytrainingforkids.data.V_Data;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String dbname="favproduct.db";
    public static final String tablename="word";

    public DataBaseHelper(Context context){
        super(context,dbname,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+tablename+"(W_name Text PRIMARY KEY,W_meaning Text)");
       }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tablename);
    }
    public int removeData(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        int i=db.delete(tablename,"W_name=?",new String[]{name});
        return i;
    }

    public boolean insertData(String name,String meaning){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("W_name",name);
        contentValues.put("W_meaning",meaning);
        long result=db.insert(tablename,null,contentValues);
        db.close();
        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }

    public ArrayList<V_Data> getAllFavWords(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<V_Data> bary = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + tablename, null);

        if(cursor.moveToFirst()) {
            do {
                bary.add(new V_Data(cursor.getString(0),"","","",cursor.getString(1)));
            }while(cursor.moveToNext());
        }
        cursor.close();
        close();
        return bary;
    }



}
