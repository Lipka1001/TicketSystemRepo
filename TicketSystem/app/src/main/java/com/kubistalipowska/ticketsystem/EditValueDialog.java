package com.kubistalipowska.ticketsystem;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class EditValueDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Context c;
    public Dialog d;
    public Button yes, no;

    public EditValueDialog(Context a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_edit_text);
        yes = (Button) findViewById(R.id.btn_add_item_confirm);
        no = (Button) findViewById(R.id.btn_add_item_cancel);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_item_confirm:
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