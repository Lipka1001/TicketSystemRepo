package com.kubistalipowska.ticketsystem;

import android.app.Activity;
import android.content.ContentValues;
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
    private String[] keys ;
    private String table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        ButterKnife.bind(this);

        keys = getIntent().getStringArrayExtra("keys");
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


        table  = getIntent().getStringExtra("table");

        // zakaldamy ze zawsze sa 3 itemy ( bedzie mozna to zmienic
        etMain.setHint(keys[0]);
        etSecond.setHint(keys[1]);
        etThird.setHint(keys[2]);

    }

    /**
     *  save new item to databse
     */
    private void concfrim() {
        // TODO implement adding item to  database
        ContentValues cv = new ContentValues();
        cv.put(keys[0],etMain.getText().toString());
        cv.put(keys[1],etSecond.getText().toString());
        cv.put(keys[2],etThird.getText().toString());
        DatabaseAccess.getInstance(this).insert(table,cv);
        setResult(Activity.RESULT_OK);
        finish();
    }

    private void cancel(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }


}
