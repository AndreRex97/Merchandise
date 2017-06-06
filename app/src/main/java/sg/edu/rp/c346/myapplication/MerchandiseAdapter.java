package sg.edu.rp.c346.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 15017103 on 6/6/2017.
 */

public class MerchandiseAdapter extends ArrayAdapter<Merchandise>{
    Context context;
    int layoutResourceId;
    ArrayList<Merchandise> merchandiseList = null;

    public MerchandiseAdapter(Context context, int resource, ArrayList<Merchandise> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.merchandiseList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MerchandiseHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new MerchandiseHolder();
            holder.itemName = (TextView)row.findViewById(R.id.tvItemName);
            holder.price = (TextView)row.findViewById(R.id.tvPrice);

            row.setTag(holder);
        }
        else
        {
            holder = (MerchandiseHolder) row.getTag();
        }

        Merchandise merchandise = merchandiseList.get(position);
        holder.itemName.setText("Item Name: " + merchandise.getItemName());
        holder.price.setText("Price: " + merchandise.getPrice().toString());
        return row;
    }

    static class MerchandiseHolder
    {
        TextView itemName;
        TextView price;
    }
}
