package stackstagingcom.firstwebpage3_com.smoker_diary;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user_PC on 5/31/2018.
 */

public class myRVADate extends RecyclerView.Adapter<myRVADate.viewHolder> {

    private static final String TAG = "myRVADateList";

    private ArrayList<dayDateItems> itemsArray;

    public class viewHolder extends RecyclerView.ViewHolder {

        public TextView numOfCig;
        public TextView dayDate;

        public viewHolder(View itemView) {
            super(itemView);
            numOfCig = itemView.findViewById(R.id.txtnumOfCig);
            dayDate = itemView.findViewById(R.id.txtDayDate);

        }

    }


    public myRVADate (ArrayList<dayDateItems> itemsArrayList) {
        itemsArray = itemsArrayList;
    }
    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_date_items, parent, false);
        viewHolder vh = new viewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(myRVADate.viewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        dayDateItems currentItem = itemsArray.get(position);
        holder.numOfCig.setText(currentItem.getNumOfCig());
        holder.dayDate.setText((currentItem.getDayDate()));

    }



    @Override
    public int getItemCount() {
        return MainActivity.days;
    }



}
