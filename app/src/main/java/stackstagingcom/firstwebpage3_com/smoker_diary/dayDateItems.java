package stackstagingcom.firstwebpage3_com.smoker_diary;

public class dayDateItems {

    private String dayDate;
    private String numOfCig;


    public dayDateItems (String day_item, String numOfCig) {
        this.dayDate = day_item;
        this.numOfCig = numOfCig;
    }

    public String getDayDate () {
        return dayDate;
    }

    public String getNumOfCig (){
        return numOfCig;
    }
}
