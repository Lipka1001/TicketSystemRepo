package com.kubistalipowska.ticketsystem;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerActivity extends Activity {

    @BindView(R.id.btn_back_log_out) Button btnLogOut;
    @BindView(R.id.listViewTickets) ListView ticketsListView;
    @BindView(R.id.listViewConcerts) ListView concertsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        ButterKnife.bind(this);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });
      /*  ticketsListView.setAdapter(new CustomAdapter(this, new String[]{"data1",
                "data2"})); */

        TabHost host = (TabHost)findViewById(R.id.tabHost1);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tabTickets);
        spec.setIndicator("My Tickets");
        host.addTab(spec);



        //Tab 2
        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tabConcerts);
        spec.setIndicator("Concerts");
        host.addTab(spec);

      /*  concertsListView.setAdapter(new CustomAdapter(this, new String[]{"data1",
                "data2"})); */

    }

    public void logOut(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }



}
