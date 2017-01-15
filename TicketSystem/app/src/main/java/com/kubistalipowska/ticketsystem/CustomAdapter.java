package com.kubistalipowska.ticketsystem;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kubistalipowska.ticketsystem.entities.ItemEntity;

import java.util.ArrayList;

class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<ItemEntity[]> data;
    String table_name;
    private static LayoutInflater inflater = null;

    public CustomAdapter(Context context,  ArrayList<ItemEntity[]>  data,String table_name) {
        // TODO change parameter to TICKETENTITY
        this.context = context;
        this.data = data;
        this.table_name = table_name;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null) {
            vi = inflater.inflate(R.layout.tictek_item, null);
            final ItemEntity[] items = data.get(position);

            TextView text = (TextView) vi.findViewById(R.id.tv_ticket_title);
            text.setText(items[0].getValue());
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeValue(view,items[0]);
                }
            });

            text = (TextView) vi.findViewById(R.id.tv_ticket_date);
            text.setText(items[1].getValue());
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeValue(view,items[1]);
                }
            });
        }
        return vi;
    }

    void changeValue(View view, ItemEntity item){
        EditValueDialog cdd = new EditValueDialog(context,item,table_name);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
    }
}