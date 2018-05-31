package stackstagingcom.firstwebpage3_com.smoker_diary;

/**
 * Created by user_PC on 5/31/2018.
 */

public class items {

    private String num_of_cig;
    private String timeStamp;

    public items(String num_of_cig, String timeStamp) {
        this.num_of_cig = num_of_cig;
        this.timeStamp = timeStamp;
    }

    public String getNumOfCig (){
        return num_of_cig;
    }

    public String getTimeStamp (){
        return timeStamp;
    }
}
