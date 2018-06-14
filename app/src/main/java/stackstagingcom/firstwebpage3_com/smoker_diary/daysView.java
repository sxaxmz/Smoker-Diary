package stackstagingcom.firstwebpage3_com.smoker_diary;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class daysView extends AppCompatActivity {

    public static final String TAG = "list_view";

    private RecyclerView myRV;
    private RecyclerView.Adapter myRVA;
    private RecyclerView.LayoutManager myRVLM;

    ArrayList<dayDateItems> itemsList;
    ArrayList<dateItems> dayList;
    DatabaseHelper myDB;

    int days;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.days_view);

        myDB = new DatabaseHelper(this);

        itemsList = new ArrayList<>();

        myRV = findViewById(R.id.RVDate);

        fillListView();
        perDay();

    }

    public void fillListView (){
        Log.d(TAG, "populateListView: Displaying data in the ListView");

        // get data
        Cursor data = myDB.getDateList();

        // append data
        while (data.moveToNext()){
            //get value from db in column 1 (numOfCig) , column 2 (dayDate)
            //add it to arrayList
            itemsList.add(new dayDateItems (data.getString(0), data.getString(1)));
        }

        myRV.setHasFixedSize(true);
        myRVLM = new LinearLayoutManager(this);
        myRVA = new myRVADate(itemsList);

        myRV.setLayoutManager(myRVLM);
        myRV.setAdapter(myRVA);
        //myRVA.setOnItemClickListener(daysView.this);
    }

    //get number of entries of dayDate
    public void getDay (){

        // get data
        Cursor data = myDB.getDate();

        // append data
        while (data.moveToNext()){
            //get value from db in column 1 (numOfCig) , column 2 (dayDate)
            //add it to arrayList
            dayList.add(new dateItems (data.getString(0)));
        }

    }

    public void perDay (){
        /**Date time = null;
         SimpleDateFormat sdf = new SimpleDateFormat("hhmm");
         sdf.format(time.getTime());
         Date startDay = null;
         startDay.setTime(0);

         if (time == startDay ){
         smokedQty.setText("0");
         smokedSince.setText("0 minutes");
         ++days;
         } **/

        getDay();

        //Array is the date column from SQlite
        for (int i = 0; i < dayList.size(); i++){
            //setAdapter pass value of the date saved in SQlite
            ++days;
        }

        MainActivity.days = days;
    }
}
