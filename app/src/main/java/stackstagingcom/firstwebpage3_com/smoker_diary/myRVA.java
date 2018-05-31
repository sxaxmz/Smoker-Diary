package stackstagingcom.firstwebpage3_com.smoker_diary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user_PC on 5/31/2018.
 */

public class myRVA extends RecyclerView.Adapter<myRVA.viewHolder> {

    private ArrayList<items> itemsArray;

    public static class viewHolder extends RecyclerView.ViewHolder {

        public TextView numOfCig;
        public TextView timeStamp;

        public viewHolder(View itemView) {
            super(itemView);
            numOfCig = itemView.findViewById(R.id.txtCig);
            timeStamp = itemView.findViewById(R.id.txtTimeStamp);


        }

    }

    public myRVA (ArrayList<items> itemsArrayList) {
        itemsArray = itemsArrayList;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        viewHolder vh = new viewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        items currentItem = itemsArray.get(position);
        holder.numOfCig.setText(currentItem.getNumOfCig());
        holder.timeStamp.setText((currentItem.getTimeStamp()));
    }


    @Override
    public int getItemCount() {
        return itemsArray.size();
    }



}
