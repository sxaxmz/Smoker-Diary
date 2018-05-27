package stackstagingcom.firstwebpage3_com.smoker_diary;

import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnAddSmoked;
    TextView smokedQty;
    TextView smokedSince;
    TextView smokedAverage;
    TextView txtCost;
    ListView smoked;
    int numberOfCig;
    int previousCig;
    int days = 0;
    Date startDateValue = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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

        smoked = (ListView)findViewById(R.id.listSmoked);
        smokedQty = (TextView)findViewById(R.id.qty1);
        btnAddSmoked = (Button)findViewById(R.id.btnSmoked);
        smokedSince = (TextView)findViewById(R.id.smokedSince);
        smokedAverage = (TextView)findViewById(R.id.average);
        txtCost = (TextView)findViewById(R.id.txtCost);


        btnAddSmoked.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                addSmoked();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void timeDifference() {
        long Time = SystemClock.elapsedRealtime();
        long difference = SystemClock.elapsedRealtime() - Time;

        smokedSince.setText(String.valueOf(difference));
    }

    public void addSmoked () {
        numberOfCig = 1 + numberOfCig;
        calcAverage(numberOfCig);
        smokedQty.setText(String.valueOf(numberOfCig));

        if (numberOfCig == 0) {
            smokedSince.setText("0 minutes");
        } else if (previousCig > numberOfCig){
            timeDifference();
        }

        previousCig = numberOfCig;
    }

    public void calcAverage (int numOfCig) {
        if (days == 0) {
            smokedAverage.setText(String.valueOf(numOfCig));
        } else {
            double average = numOfCig / 24;
            smokedAverage.setText(String.valueOf(average));
        }
    }

    public void showListView () {
        Intent intent = new Intent(this, lis_view.class);
        intent.putExtra("numOfCig", numberOfCig);
        startActivity(intent);
    }


}
