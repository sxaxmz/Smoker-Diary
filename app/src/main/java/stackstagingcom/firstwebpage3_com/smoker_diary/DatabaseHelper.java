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
    public static final String COL4 = "dayDate";

    String date;


    public DatabaseHelper(Context context) {
        super(context, tableName, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + tableName + "("+COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL2 +" VARCHAR, "+COL3+" VARCHAR, "+COL4+" VARCHAR)";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldV, int newV) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ tableName);
        onCreate(sqLiteDatabase);
    }

    public boolean addData (String numOfCig, String timeStamp, String dateOfDay){
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2, numOfCig);
        cv.put(COL3, timeStamp);
        cv.put(COL4, dateOfDay);

        Log.d(TAG, " addData: Adding " + numOfCig + " and "+dateOfDay+" to "+ tableName);

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
        String query = "SELECT "+COL2+" , "+COL3+"  , "+ COL4 +" FROM "+tableName;
        Cursor data = db.rawQuery(query, null);
        return  data;
    }

    public boolean addDate (long date) {
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL4, date);

        Log.d(TAG, " addData: Adding date to "+ tableName);

        Long result = db.insert(tableName, null, cv);

        //-1 data inserted incorrectly
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor getDate (){
        SQLiteDatabase db =  this.getWritableDatabase();
        String query = "SELECT "+COL2+ " FROM "+tableName ;
        Cursor data = db.rawQuery(query, null);
        return  data;
    }

    public Cursor getDateList (){
        SQLiteDatabase db =  this.getWritableDatabase();
        String query = "SELECT "+COL2+ ", "+COL4+" FROM "+tableName;
        Cursor data = db.rawQuery(query, null);
        return  data;
    }



}
