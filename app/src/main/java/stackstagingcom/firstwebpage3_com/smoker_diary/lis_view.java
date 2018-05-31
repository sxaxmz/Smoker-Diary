package stackstagingcom.firstwebpage3_com.smoker_diary;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by user_PC on 5/19/2018.
 */

public class lis_view extends AppCompatActivity  {

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

    String numOfCig;
    String cigTime;

    TextView smokedQty;
    TextView smokedSince;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        Intent intent = getIntent();
        numOfCig = intent.getStringExtra("numOfCig");
        cigTime = intent.getStringExtra("timeStamp");

        ArrayList<items> itemsList = new ArrayList<>();
        //adding the values in the ArrayList
        itemsList.add(new items(numOfCig, cigTime));

        myRV = findViewById(R.id.RV);
        myRV.setHasFixedSize(true);
        myRVLM = new LinearLayoutManager(this);
        myRVA = new myRVA(itemsList);

        myRV.setLayoutManager(myRVLM);
        myRV.setAdapter(myRVA);




    }

    public void loadData () {
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        textt = sp.getString(TEXT, "0");
        timeStamp = sp.getString(lastSmoked, "-D -H -M -S");
    }

    public void updateView (){
        smokedQty.setText(textt);
        smokedSince.setText(timeStamp);
    }


    }

