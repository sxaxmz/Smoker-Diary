package stackstagingcom.firstwebpage3_com.smoker_diary;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.SharedPreferences;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.DateTimeConstants;

import java.util.Locale;
import java.util.TimeZone;

public class jobService extends JobService {

    //SharedPreference references
    public static final String SHARED_PREFS = "sharedPref";
    public static final String CHECK = "checks";


    //LoadData variables
    private String checks;
    String dayName;

    public int dailyChecks = 0;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        saveData();
        loadData();
        doBackgroundWork(jobParameters);
        return true;
    }

    public void doBackgroundWork (final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                DateTime now = DateTime.now();
                DateTime atMidnight = new DateTime(TimeZone.getDefault()).withTimeAtStartOfDay();
                DateTime latest = atMidnight.plusMinutes(1);

                if (new Interval(atMidnight, latest).contains(now)){
                    dailyChecks = 0;
                }

                LocalDate currentDate = LocalDate.now();

                String dateString = currentDate.toString("dd/MMM/yyyy");



                LocalDate tomorrow = currentDate.plusDays(1);
                String tomorrowDate = tomorrow.toString("ddMMyyyy");
                int dateTomorrow = Integer.parseInt(tomorrowDate);

                LocalDate.Property pDoW = tomorrow.dayOfWeek();
                dayName = pDoW.getAsText(Locale.getDefault());



                MainActivity.dateToday = 0;

                if (dailyChecks == 0) {

                    if (MainActivity.dateToday == dateTomorrow) {

                        //Add new Item to ListView Array
                        MainActivity.dateName.add(dayName);

                    } else {

                        MainActivity.dateToday = dateTomorrow;
                        ++MainActivity.days;

                    }
                    jobFinished(params, true);
                    dailyChecks = 1;
                }
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }

    public void saveData () {
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(CHECK, Integer.toString(dailyChecks));

        editor.apply();

    }

    public void loadData () {
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        checks = sp.getString(CHECK, "0");

        dailyChecks = Integer.parseInt(checks);

    }
}
