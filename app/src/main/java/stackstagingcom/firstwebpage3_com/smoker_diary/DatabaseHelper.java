package stackstagingcom.firstwebpage3_com.smoker_diary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    public static final String tableName = "cigarette";
    public static final String COL1 = "ID";
    public static final String COL2 = "numOfCig";
    public static final String COL3 = "timeStamp";

    public DatabaseHelper(Context context) {
        super(context, tableName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + tableName + "("+COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL2 +" VARCHAR, "+COL3+" VARCHAR)";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public boolean addData (String numOfCig, String timeStamp){
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2, numOfCig);
        cv.put(COL3, timeStamp);

        Log.d(TAG, " addData: Adding " + numOfCig + " to "+ tableName);

        Long result = db.insert(tableName, null, cv);

        //-1 data inserted incorrectly
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData (){
        SQLiteDatabase db =  this.getWritableDatabase();
        String query = "SELECT "+COL2+" , "+COL3+" FROM "+tableName;
        Cursor data = db.rawQuery(query, null);
        return  data;
    }
}
