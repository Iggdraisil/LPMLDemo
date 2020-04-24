package com.olezhko.lpmldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button signInButton;
    EditText emailEditText;
    EditText passwordEditText;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initializing UI
        setContentView(R.layout.activity_login);
        signInButton = findViewById(R.id.sign_in);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        checkBox = findViewById(R.id.slave);
        //setting onClickListener to execute following code as user clicks button
        signInButton.setOnClickListener(view -> {
            //getting data from editTexts and checkbox
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            boolean slave = checkBox.isChecked();
            //checking if password and email are proper enough
            if (password.length() < 9 && email.length() < 10) {
                Toast.makeText(getApplicationContext(),"Довше строчи", Toast.LENGTH_LONG).show();
            } else {
                //checking checkbox value
                if (!slave) {
                    Toast.makeText(getApplicationContext(),"стань рабом, тоді впустим", Toast.LENGTH_LONG).show();
                    return;
                }
                //creating intent
                Intent signInIntent = new Intent(LoginActivity.this, MainActivity.class);
                //adding our emil and password into intent to receive it in MainActivity
                signInIntent.putExtra("userData", new String[]{email, password});
                //starting MainActivity with this intent
                startActivity(signInIntent);
            }
        });
    }
}
