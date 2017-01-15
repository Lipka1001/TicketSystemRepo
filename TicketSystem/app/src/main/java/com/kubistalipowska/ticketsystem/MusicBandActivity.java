package com.kubistalipowska.ticketsystem;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class MusicBandActivity extends Activity {
    private static final int REQUEST_ADDITEM= 0;

    @BindView(R.id.btn_back_log_out) Button btnLogOut;
    @BindView(R.id.listViewCrew) ListView crewListView;
    @BindView(R.id.listViewConcerts) ListView concertsListView;
    @BindView(R.id.expandableListViewPlayLists) ExpandableListView playlistsListView;
    @BindView(R.id.listViewSongs) ListView songsListView;
    @BindView(R.id.expandableListViewPlates) ExpandableListView platesListView;
    @BindView(R.id.btn_add_plate) Button btnAddPlate;
    @BindView(R.id.btn_add_crew) Button btnAddCrew;
    @BindView(R.id.btn_add_playlist) Button btnAddPlaylist;
    @BindView(R.id.btn_add_song) Button btnAddSong;
    @BindView(R.id.btn_add_concert) Button btnAddConcert;

    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ExpandableListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_band);

        ButterKnife.bind(this);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });

        btnAddPlate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               addItem(view);
            }
        });

        btnAddPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

        btnAddSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

        btnAddConcert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

        btnAddCrew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Crew");
        spec.setContent(R.id.tabCrew);
        spec.setIndicator("Crew");
        host.addTab(spec);

   //     crewListView.setAdapter(new CustomAdapter(this, new String[]{"crew1", "crew2"}));

        //Tab 2
        spec = host.newTabSpec("Concerts");
        spec.setContent(R.id.tabConcerts);
        spec.setIndicator("Concerts");
        host.addTab(spec);

        //concertsListView.setAdapter(new CustomAdapter(this, new String[]{"data1", "data2"}));

        //Tab 3
        spec = host.newTabSpec("Playlists");
        spec.setContent(R.id.tabPlaylists);
        spec.setIndicator("Playlists");
        host.addTab(spec);

     /*   playlistsListView.setAdapter(new CustomAdapter(this, new String[]{"data1",
                "data2"})); */

        //Tab 4
        spec = host.newTabSpec("Plates");
        spec.setContent(R.id.tabPlate);
        spec.setIndicator("Plates");
        host.addTab(spec);


        // get the listview
        platesListView = (ExpandableListView) findViewById(R.id.expandableListViewPlayLists);

        // preparing list data
        prepareListData();



        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        platesListView.setAdapter(listAdapter);


        //Tab 5
        spec = host.newTabSpec("Songs");
        spec.setContent(R.id.tabSongs);
        spec.setIndicator("Songs");
        host.addTab(spec);

        songsListView.setAdapter(new CustomAdapter(this,DatabaseAccess.getInstance(this)
                .getItems(DatabaseAccess.TABLE_SONGS),DatabaseAccess.TABLE_SONGS));


    }

    private void addItem(View view) {
                Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
                intent.putExtra("type",view.getId());
                startActivityForResult(intent, REQUEST_ADDITEM);
    }

    public void logOut(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ADDITEM) {
            if (resultCode == RESULT_OK) {
            }
        }
    }

    /*
  * Preparing the list data
  */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
}
