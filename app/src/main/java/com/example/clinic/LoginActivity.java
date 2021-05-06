package com.example.clinic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity {
    EditText user,password;
    Button login;
    CheckBox remember;
    public static String Name,mail,gender,phone,address,birth ;
    public  static int id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.txtuser);
        password = findViewById(R.id.txtLoginPass);
        login = findViewById(R.id.btnsign);
        remember = findViewById(R.id.chLog);
        SharedPreferences sh= getSharedPreferences("clinicSh",MODE_PRIVATE);
        String st = sh.getString("PhEm",null);

        if (st != null)
        {
            Name = sh.getString("name",null);
            mail = sh.getString("Email",null);
            gender = sh.getString("Gender",null);
            phone = sh.getString("Phone",null);
            address = sh.getString("Address",null);
            birth = sh.getString("Date",null);
            id = Integer.parseInt(sh.getString("resid", String.valueOf(0)));
            startActivity(new Intent(LoginActivity.this,userActivity.class));
        }

    }

    public void login(View view) {

        if (user.getText().toString().isEmpty())
        {
            user.setError("Enter your phone number/Email");
            user.requestFocus();
        }else
        {
            if (password.getText().toString().isEmpty())
            {
                password.setError("Please enter your password");
                password.requestFocus();
            }
            else
            {
                database db = new database();
                Connection conn = db.ConnectDB();
                if (conn == null)
                {
                    Toast.makeText(this,"Please check your internet connection",Toast.LENGTH_LONG).show();
                }
                else
                {
                    ResultSet result = db.RunSearch("select * from  Patient where (phone='"+user.getText()+"' or Email='"+user.getText()+"') and password='"+password.getText()+"'");
                    try {
                        if (result.next())
                        {
                            Name = result.getString(1);
                            mail = result.getString(4);
                            gender = result.getString(6);
                            phone = result.getString(5);
                            address = result.getString(3);
                            birth = result.getString(2);
                            id = result.getInt(9);
                            if(remember.isChecked())
                            {
                                getSharedPreferences("clinicSh",MODE_PRIVATE).edit().putString("PhEm",user.getText().toString())
                                        .putString("name",result.getString(1).toString())
                                        .putString("Date",result.getString(2).toString())
                                        .putString("Address",result.getString(3).toString())
                                        .putString("Email",result.getString(4).toString())
                                        .putString("Phone",result.getString(5).toString())
                                        .putString("Gender",result.getString(6).toString())
                                        .putString("resid", String.valueOf(result.getInt(9)))
                                        .commit();
                            }

                            startActivity(new Intent(LoginActivity.this,userActivity.class));

                        }else
                        {
                            AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                            alert.setTitle("Login...");
                            alert.setMessage("Invalid user/password");
                            alert.setPositiveButton("Create new account", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(LoginActivity.this,Register.class));
                                }
                            });
                            alert.setNegativeButton("Try again",null);
                            alert.create().show();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }

        }
    }
}