package com.kubistalipowska.ticketsystem;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "AdminPanelActivity";

    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_signup) Button _signupButton;
    @BindView(R.id.btn_back_login) Button _loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });


        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }


    public void signup() {
        Log.d(TAG, "Signup");



        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);


        String login = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        ContentValues myCV = new ContentValues();
        myCV.put(DatabaseAccess.FIELD_LOGIN, login);
        myCV.put(DatabaseAccess.FIELD_PASSWORD, password);
        DatabaseAccess.getInstance(this).insert(DatabaseAccess.TABLE_ACCOUNTS, myCV);
        DatabaseAccess.getInstance(this).setUser(login);
        CustomUtils.hideKeyboard(this);
        onSignupSuccess();
    }



    public void onSignupSuccess() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("login", "");
        setResult(Activity.RESULT_OK, resultIntent);
        finish();

    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), R.string.fail, Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty()) {
            _emailText.setError(getString(R.string.epmty_login));
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty()) {
            _passwordText.setError(getString(R.string.pswrd_empty));
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if(valid){
            valid = DatabaseAccess.getInstance(this).checkLogin(email);
        }

        return valid;
    }
}
