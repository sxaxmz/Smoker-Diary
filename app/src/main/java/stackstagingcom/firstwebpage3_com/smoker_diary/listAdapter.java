package stackstagingcom.firstwebpage3_com.smoker_diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by user_PC on 5/19/2018.
 */

public class listAdapter extends BaseAdapter {

    ArrayList<listComponent> listItem  = new ArrayList<listComponent>();



    listAdapter(ArrayList<listComponent> listItem) {
        this.listItem = listItem;
    }


    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int position) {
        return listItem.get(position).timeStamp;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.items,null);


        TextView num_cig = (TextView)view.findViewById(R.id.txtCig);
        TextView timeStamp = (TextView)view.findViewById(R.id.txtTimeStamp);

        num_cig.setText(String.valueOf(listItem.get(position).num_of_cig));
        timeStamp.setText(String.valueOf(listItem.get(position).timeStamp));

        return view;
    }
    
}
