package stackstagingcom.firstwebpage3_com.smoker_diary;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;


/**
 * Created by user_PC on 5/19/2018.
 */

public class lis_view extends AppCompatActivity  {

    ListView smokedList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        smokedList = (ListView)findViewById(R.id.listSmoked);

        Intent intent = getIntent();
        String numOfCig = intent.getStringExtra("numOfCig");
        String cigTime = intent.getStringExtra("timeStamp");


    }
    }
