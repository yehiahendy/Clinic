package com.example.clinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button login,Register ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        login = findViewById(R.id.btnLog);
        login.setOnClickListener(view -> {
            Intent intenLogin = new Intent(Home.this,LoginActivity.class);
            startActivity(intenLogin);
        });
        Register = findViewById(R.id.btnRegister);
        Register.setOnClickListener(view -> {

            Intent intentReg = new Intent(Home.this, Register.class);
            startActivity(intentReg);
        });
    }
}