package com.kubistalipowska.ticketsystem;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.kubistalipowska.ticketsystem.entities.ItemEntity;
import com.kubistalipowska.ticketsystem.entities.SongEntity;

import java.util.ArrayList;
import java.util.List;


public class SelectSongDialog extends Dialog implements
        View.OnClickListener {

    public Context c;
    public Dialog d;
    public Button  no;
    public EditText et;
    ListView mySongsView;
    String album_name;
    ExpandableListAdapter adapter;

    public SelectSongDialog(Context a,String album_name,ExpandableListAdapter adapter) {
        super(a);
        // TODO Auto-getenerated constructor stub
        this.c = a;
        this.album_name = album_name;
        this.adapter  =adapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_select_song);
        no = (Button) findViewById(R.id.btn_add_item_cancel);
        no.setOnClickListener(this);
        mySongsView = (ListView) findViewById(R.id.listViewSelectSong);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                c,
                android.R.layout.simple_list_item_1,
                DatabaseAccess.getInstance(c ).getSongsForAlbumTo(album_name) );
        mySongsView.setAdapter(arrayAdapter);
        mySongsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ContentValues cv = new ContentValues();
                cv.put(DatabaseAccess.FIELD_ALBUM, album_name);
               String song_name = (String) adapterView.getItemAtPosition(i);
                DatabaseAccess.getInstance(c).update(DatabaseAccess.TABLE_SONGS, cv, DatabaseAccess.FIELD_SONG_NAME,song_name );
                //tv.setText(et.getText().toString());
                adapter.putSong(new SongEntity(album_name,0,"",song_name),album_name);
                dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_item_cancel:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}