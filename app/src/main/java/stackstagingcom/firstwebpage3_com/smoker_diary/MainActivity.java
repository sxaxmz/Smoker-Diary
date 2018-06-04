package stackstagingcom.firstwebpage3_com.smoker_diary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;

    //SharedPreference references
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String Average = "average";
    public static final String lastSmoked = "lastSmoked";

    //LoadData variables
    private String textt;
    private String timeStamp;
    private String AverageSmoked;

    //UI components
    Button btnAddSmoked;
    TextView smokedQty;
    TextView smokedSince;
    TextView smokedAverage;

    //Variables
    int average;
    int numberOfCig;
    int previousCig;
    int days = 0;

    String cigTime;

    Date startDateValue = new Date();
    Date endDateValue = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showListView();
                /**Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();**/
            }
        });

        smokedQty = (TextView)findViewById(R.id.qty1);
        btnAddSmoked = (Button)findViewById(R.id.btnSmoked);
        smokedSince = (TextView)findViewById(R.id.smokedSince);
        smokedAverage = (TextView)findViewById(R.id.smokedAverage);


        btnAddSmoked.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                addSmoked();
                addData(Integer.toString(numberOfCig), cigTime);
            }
        });

        loadData();
        updateView();
    }

    public void addData (String newCig, String timeStamp){
        boolean insertData = myDB.addData(newCig, timeStamp);

        if (insertData){
            Toast.makeText(this, "Inserted  successfully",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Something went wrong",Toast.LENGTH_SHORT).show();
        }
    }

    public void addSmoked () {
        numberOfCig = 1 + numberOfCig;
        calcAverage(numberOfCig);
        smokedQty.setText(String.valueOf(numberOfCig));

        if (numberOfCig == 0) {
            smokedSince.setText("0 minutes");
        }

        previousCig = numberOfCig;

        Toast.makeText(this, "Added!", Toast.LENGTH_SHORT).show();

        saveData();
        lastCig();

    }

    public void calcAverage (int numOfCig) {
        if (days == 0) {
            smokedAverage.setText(String.valueOf(numOfCig));
            average = Integer.parseInt(smokedAverage.getText().toString());
        } else {
            //lifetime average cigarette
            average = numOfCig / days;
            smokedAverage.setText(String.valueOf(average));
        }
    }

    public void showListView () {
        Intent listView = new Intent(this, lis_view.class);
        listView.putExtra("numOfCig", String.valueOf(numberOfCig));
        listView.putExtra("timeStamp", String.valueOf(cigTime));
        startActivity(listView);
    }

    public void saveData () {
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(TEXT, String.valueOf(numberOfCig));
        editor.putString(Average, String.valueOf(average));
        editor.putString(lastSmoked, smokedSince.getText().toString());

        editor.apply();

    }

    public void loadData () {
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        textt = sp.getString(TEXT, "0");
        AverageSmoked = sp.getString(Average, "0");
        timeStamp = sp.getString(lastSmoked, "-D -H -M -S");
        numberOfCig = Integer.parseInt(textt);
    }

    public void updateView (){
        smokedQty.setText(textt);
        smokedSince.setText(timeStamp);
        smokedAverage.setText(AverageSmoked);
    }

    public void lastCig () {
        startDateValue.getTime();
        endDateValue.setTime(1527672629000L);

        printDifference(startDateValue, endDateValue);

        endDateValue = startDateValue;

    }


    public void printDifference(Date startDate, Date endDate){

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();


        //1 minute = 60 seconds
        //1 hour = 60 x 60 = 3600
        //1 day = 3600 x 24 = 86400

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        smokedSince.setText(
                Long.toString(Math.abs(elapsedDays)) +" D " + Long.toString(Math.abs(elapsedHours)) + " H " +
                        Long.toString(Math.abs(elapsedMinutes)) + " M " + Long.toString(Math.abs(elapsedSeconds)) + " S "
        );

        //need modification
        cigTime = Long.toString(Math.abs(elapsedHours)) + " : " + Long.toString(Math.abs(elapsedMinutes));



        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays,
                elapsedHours, elapsedMinutes, elapsedSeconds);

    }

}
