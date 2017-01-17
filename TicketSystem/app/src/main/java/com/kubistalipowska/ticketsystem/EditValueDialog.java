package com.kubistalipowska.ticketsystem;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kubistalipowska.ticketsystem.entities.ItemEntity;

public class EditValueDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Context c;
    public Dialog d;
    public Button yes, no;
    public EditText et;
    ItemEntity item;
    String table_name;
    String old_value;
    BaseAdapter tv;

    public EditValueDialog(Context a,ItemEntity item, String table_name,BaseAdapter tv) {
        super(a);
        // TODO Auto-getenerated constructor stub
        this.c = a;
        this.table_name = table_name;
        this.item = item;
        this.tv = tv;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_edit_text);
        yes = (Button) findViewById(R.id.btn_add_item_confirm);
        no = (Button) findViewById(R.id.btn_add_item_cancel);
        et = (EditText) findViewById(R.id.input_dialog);
        et.setText(item.getValue());
        et.setHint(item.getFielnd_name());
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_item_confirm:
                ContentValues cv = new ContentValues();
                cv.put(item.getFielnd_name(), et.getText().toString());
                DatabaseAccess.getInstance(c).update(table_name, cv,item.getFielnd_name(), item.getValue());
                //tv.setText(et.getText().toString());
                item.setValue(et.getText().toString());
                tv.notifyDataSetChanged();
                dismiss();
                break;
            case R.id.btn_add_item_cancel:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}