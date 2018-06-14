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

public class myRVADate extends RecyclerView.Adapter<myRVADate.viewHolder> {

    private ArrayList<dayDateItems> itemsArray;
    private onItemClickListener mListener;

    public class viewHolder extends RecyclerView.ViewHolder {

        public TextView numOfCig;
        public TextView timeStamp;

        public viewHolder(View itemView) {
            super(itemView);
            numOfCig = itemView.findViewById(R.id.txtCig);
            timeStamp = itemView.findViewById(R.id.txtTimeStamp);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                 if (mListener != null){
                     int position = getAdapterPosition();
                     if (position != RecyclerView.NO_POSITION){
                         mListener.onItemClick(position);
                     }
                 }
                }
                });

        }

    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener (onItemClickListener Listener){
        mListener = Listener;
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
        dayDateItems currentItem = itemsArray.get(position);
        holder.numOfCig.setText(currentItem.getDayDate());
        holder.timeStamp.setText((currentItem.getNumOfCig()));
    }



    @Override
    public int getItemCount() {
        return itemsArray.size();
    }



}
