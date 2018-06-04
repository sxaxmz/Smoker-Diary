package stackstagingcom.firstwebpage3_com.smoker_diary;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by user_PC on 5/19/2018.
 */

public class lis_view extends AppCompatActivity  {

    public static final String TAG = "list_view";

    //SharedPreference references
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String Average = "average";
    public static final String lastSmoked = "lastSmoked";

    //LoadData variables
    private String textt;
    private String timeStamp;
    private String AverageSmoked;


    private RecyclerView myRV;
    private RecyclerView.Adapter myRVA;
    private RecyclerView.LayoutManager myRVLM;

    DatabaseHelper myDB;

    String numOfCig;
    String cigTime;

    TextView smokedQty;
    TextView smokedSince;
    ArrayList<items> itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        /**
        Intent intent = getIntent();
        numOfCig = intent.getStringExtra("numOfCig");
        cigTime = intent.getStringExtra("timeStamp");
         **/

        myDB = new DatabaseHelper(this);

        itemsList = new ArrayList<>();

        myRV = findViewById(R.id.RV);

        /**
        //adding the values in the ArrayList
        itemsList.add(new items(numOfCig, cigTime));

        myRV.setHasFixedSize(true);
        myRVLM = new LinearLayoutManager(this);
        myRVA = new myRVA(itemsList);

        myRV.setLayoutManager(myRVLM);
        myRV.setAdapter(myRVA);
         **/


        fillListView();

    }

    public void loadData () {
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        textt = sp.getString(TEXT, "0");
        timeStamp = sp.getString(lastSmoked, "-D -H -M -S");
    }

    public void updateView (){
        numOfCig = textt;
        cigTime = timeStamp;
        smokedQty.setText(numOfCig);
        smokedSince.setText(cigTime);
    }

    public void fillListView (){
        Log.d(TAG, "populateListView: Displaying data in the ListView");

        // get data
        Cursor data = myDB.getData();

        // append data
        while (data.moveToNext()){
            //get value from db in column 1 (numOfCig) , column 2 (timeStamp)
            //add it to arrayList
            itemsList.add(new items (data.getString(0), data.getString(1)));
        }

        myRV.setHasFixedSize(true);
        myRVLM = new LinearLayoutManager(this);
        myRVA = new myRVA(itemsList);

        myRV.setLayoutManager(myRVLM);
        myRV.setAdapter(myRVA);
    }


    }

