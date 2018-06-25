package stackstagingcom.firstwebpage3_com.smoker_diary;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.joda.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;

    //SharedPreference references
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String Average = "average";
    public static final String lastSmoked = "lastSmoked";
    public static final String cigFirst = "firstCig";
    public static final String lastSmokedCig = "lastSmokedCigLong";
    public static final String dayz = "days";
    public static final String todayDates = "dateToday";

    //LoadData variables
    private String textt;
    private String timeStamp;
    private String AverageSmoked;
    private String firsttCig;
    private String lastSmokedCigg;
    private String day;
    private String dates;

    //UI components
    Button btnAddSmoked;
    TextView smokedQty;
    TextView smokedSince;
    TextView smokedAverage;
    TextView txtCreator;

    //Variables
    int average;
    int numberOfCig;
    int firstCig;
    int previousCig;
    public static int days = 3;
    public static int dateToday = 0;

    String cigTime;
    String dateTxt;

    Date startDateValue = new Date();
    Date endDateValue = new Date();

    long startDateValueLong;
    long lastSmokedCigLong;

    ArrayList<dayDateItems> itemsList;

    public static ArrayList<String> dateName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showListView();
            }
        });

        fab.setImageResource(R.drawable.ic_history);

        smokedQty = (TextView)findViewById(R.id.qty1);
        btnAddSmoked = (Button)findViewById(R.id.btnSmoked);
        smokedSince = (TextView)findViewById(R.id.smokedSince);
        smokedAverage = (TextView)findViewById(R.id.smokedAverage);
        txtCreator = (TextView)findViewById(R.id.txtCreator);

        txtCreator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, webView.class);
                startActivity(intent);
            }
        });



        btnAddSmoked.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                addSmoked(view);
                //addData(Integer.toString(numberOfCig), cigTime, Integer.toString(days));
                addData(Integer.toString(numberOfCig), cigTime, dateTxt);
            }
        });

        loadData();
        updateView();
        //scheduleJob();
    }

    protected void onResume () {
        super.onResume();
        lastCig();
    }

    public void addData (String newCig, String timeStamp, String dateOfDay){
        boolean insertData = myDB.addData(newCig, timeStamp, dateOfDay);

        if (insertData){
            //Replaced with snackbar
            //Toast.makeText(this, "Inserted  successfully",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Something went wrong",Toast.LENGTH_SHORT).show();
        }
    }

    public void addSmoked (View view) {
        numberOfCig = 1 + numberOfCig;
        calcAverage(numberOfCig);
        smokedQty.setText(String.valueOf(numberOfCig));

        if (numberOfCig == 0) {
            smokedSince.setText("0 minutes");
        }

        previousCig = numberOfCig;


        //Toast.makeText(this, "Added!", Toast.LENGTH_SHORT).show();
        Snackbar.make(view, "Added!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();

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
        Intent listView = new Intent(MainActivity.this, lis_view.class);
        startActivity(listView);
    }

    public void saveData () {
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(TEXT, String.valueOf(numberOfCig));
        editor.putString(Average, String.valueOf(average));
        editor.putString(lastSmoked, smokedSince.getText().toString());
        editor.putString(cigFirst, Integer.toString(firstCig));
        editor.putString(lastSmokedCig, Long.toString(lastSmokedCigLong));
        editor.putString(dayz, Integer.toString(days));
        editor.putString(todayDates, Integer.toString(dateToday));

        editor.apply();

    }

    public void loadData () {
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        textt = sp.getString(TEXT, "0");
        AverageSmoked = sp.getString(Average, "0");
        timeStamp = sp.getString(lastSmoked, "-D -H -M -S");
        firsttCig = sp.getString(cigFirst , "0");
        lastSmokedCigg = sp.getString(lastSmokedCig, "0");
        day = sp.getString(dayz, "0");
        dates = sp.getString(todayDates, "0");

        numberOfCig = Integer.parseInt(textt);
        firstCig = Integer.parseInt(firsttCig);
        lastSmokedCigLong = Long.parseLong(lastSmokedCigg);
        days = Integer.parseInt(day);
        dateToday = Integer.parseInt(dates);
    }

    public void updateView (){
        smokedQty.setText(textt);
        smokedSince.setText(timeStamp);
        smokedAverage.setText(AverageSmoked);
    }

    public void lastCig () {
        //endDateValue.setTime(1527672629000L); //test purpose


        //startDateValue is the date of the recent cig
        //endDateValue is the value of the last cig


        startDateValueLong = startDateValue.getTime();
        startDateValue.setTime(startDateValueLong);

        System.out.print(" firstCig: "+firstCig+" ");


        //firstCig is the very first cig in the app,
        //used to calc/define lastCig time
        if (firstCig == 0) {
            endDateValue.setTime(0);
            firstCig = 1;
        } else if (firstCig == 1){
            endDateValue.setTime(lastSmokedCigLong);
        }

            printDifference(startDateValue, endDateValue);
    }


    public void printDifference(Date startDate, Date endDate){

        //milliseconds
        long different;
        different = endDate.getTime() - startDate.getTime(); //modify



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

        timeStamp = Long.toString(Math.abs(elapsedDays)) +" D " + Long.toString(Math.abs(elapsedHours)) + " H " +
                Long.toString(Math.abs(elapsedMinutes)) + " M " + Long.toString(Math.abs(elapsedSeconds)) + " S ";

        smokedSince.setText(timeStamp);

        System.out.print("start: "+ startDate);
        System.out.print(" end: "+ endDate);

        lastSmokedCigLong = startDate.getTime();

        //Modification
        //cigTime = Long.toString(Math.abs(elapsedHours)) + " : " + Long.toString(Math.abs(elapsedMinutes));

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        sdf.setTimeZone(TimeZone.getDefault());
        cigTime = sdf.format(startDate);

        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MMM/yyyy");
        sdf2.setTimeZone(TimeZone.getDefault());
        dateTxt = sdf2.format(startDate);

        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays,
                elapsedHours, elapsedMinutes, elapsedSeconds);

    }

    /**
    public void newDay () {


        LocalDate currentDate = LocalDate.now();
        LocalDate tomorrow = currentDate.plusDays(1);
        String dateString = currentDate.toString("dd/MMM/yyyy");
        txtView.setText(dateString);

        String tomorrowDate = tomorrow.toString("ddMMyyyy");

        int dateTomorrow = Integer.parseInt(tomorrowDate);
        dateToday = 0;


        //This stmt must run once a day _AlarmManager_ || _SchedulerManager_
        if ( dateToday == dateTomorrow ) {

            //Add new Item to ListView Array
            Toast.makeText(this, "new Day", Toast.LENGTH_SHORT).show(); //Test

            dateName.add(dateString);
            } else {

            Toast.makeText(this, "not yet", Toast.LENGTH_SHORT).show(); //Test

            dateToday = dateTomorrow;
            ++days;
            }


            }
     **/

    public void scheduleJob (){
        ComponentName cn = new ComponentName(this, jobService.class);
        JobInfo info = new JobInfo.Builder(1, cn).setPeriodic(60 * 60 * 1000).build();

        JobScheduler js = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
        int codeResult = js.schedule(info);

        if (codeResult == JobScheduler.RESULT_SUCCESS){
            Log.d("jobScheduler", "Job success");
        } else {
            Log.d("JobScheduler", "Job failed");
        }
    }



}


