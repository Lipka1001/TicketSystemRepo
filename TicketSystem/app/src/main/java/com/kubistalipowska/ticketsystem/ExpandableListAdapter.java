package com.kubistalipowska.ticketsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kubistalipowska.ticketsystem.entities.AlbumEntity;
import com.kubistalipowska.ticketsystem.entities.ItemEntity;
import com.kubistalipowska.ticketsystem.entities.SongEntity;

public class ExpandableListAdapter  extends BaseExpandableListAdapter {

    private Context _context;
    private ArrayList<AlbumEntity> data; // header titles
    // child data in format of header title, child title
    private HashMap<String, ArrayList<SongEntity>> songs;
    public ExpandableListAdapter instance;

    public ExpandableListAdapter(Context context, ArrayList<AlbumEntity> data,
                                 HashMap<String, ArrayList<SongEntity>> songs) {
            ArrayList<SongEntity> list  ;
           for(String key: songs.keySet()) {
                  list = songs.get(key);
                  list.add(new SongEntity(key,0,"STUB","STUB"))            ;
           }
        this._context = context;
        this.songs = songs;
        this.data = data;
        instance =this;
    }

    public void putSong(SongEntity song,String album){
               ArrayList<SongEntity> list =   songs.get(album);
        SongEntity stub =    list.get(list.size() - 1);
        list.remove(list.size() - 1);
        list.add(song);
        list.add(stub);
        notifyDataSetChanged();
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
          AlbumEntity album = (AlbumEntity)this.data.get(groupPosition);
        return this.songs.get(album.getName()).get(childPosititon);
    }

    @Override
    public long getChildId(int getChildrenCountv, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final SongEntity song = (SongEntity)getChild(groupPosition, childPosition);
        LayoutInflater infalInflater;

        if (convertView == null || childPosition == getChildrenCount(groupPosition) -2) {
            if (childPosition == getChildrenCount(groupPosition)- 1) {
                infalInflater   = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.expandable_list_add_item, null);
                Button button = (Button) convertView
                        .findViewById(R.id.expandalbe_list_add_song);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       AlbumEntity album  = (AlbumEntity)getGroup(groupPosition);
                        SelectSongDialog cdd = new SelectSongDialog(_context,album.getName(),instance);
                        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        cdd.show();
                    }
                });

            }else {
                infalInflater   = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.expandable_list_item, null);
                TextView txtListChild = (TextView) convertView
                        .findViewById(R.id.tv_expandalbe_list_item);

                txtListChild.setText(song.getName());
            }
        }


        return convertView;
    }



    @Override
    public int getChildrenCount(int groupPosition) {
        AlbumEntity album = (AlbumEntity)this.data.get(groupPosition);
        return this.songs.get(album.getName()).size() ;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.data.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.data.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        AlbumEntity album  =  (AlbumEntity)getGroup(groupPosition);
        String headerTitle = album.getName();
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.expandable_item_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.input_expandable_header);
       /* lblListHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditValueDialog cdd = new EditValueDialog(_context);
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();
            }
        }); */
        lblListHeader.setFocusable(false);
        lblListHeader.setText(headerTitle);

        Button btnDelete = (Button) convertView
                .findViewById(R.id.btn_expandalbe_list_delete_group);
        btnDelete.setFocusable(false);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}