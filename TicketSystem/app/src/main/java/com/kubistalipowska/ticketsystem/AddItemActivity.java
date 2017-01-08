package com.kubistalipowska.ticketsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddItemActivity extends Activity {

    @BindView(R.id.input_item_main) EditText etMain;
    @BindView(R.id.input_item_secondary) EditText etSecond;
    @BindView(R.id.input_item_third) EditText etThird;
    @BindView(R.id.btn_add_item_confirm) Button btnConfirm;
    @BindView(R.id.btn_add_item_cancel) Button btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        ButterKnife.bind(this);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concfrim();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });

        int id  = getIntent().getIntExtra("id",0);
        switch (id){
            case R.id.btn_add_song:
                etMain.setHint("Title");
                etSecond.setHint("Length");
                etThird.setHint("Genre");
                break;
            // TODO zaimplementowac reszte
        }

    }

    /**
     *  save new item to databse
     */
    private void concfrim() {
        // TODO implement adding item to  database
        setResult(Activity.RESULT_OK);
        finish();
    }

    private void cancel(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }


}
