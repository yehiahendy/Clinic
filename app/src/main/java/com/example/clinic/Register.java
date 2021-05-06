package com.example.clinic;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.sql.Connection;
import java.util.Calendar;

public class Register extends AppCompatActivity {

    EditText name,Mail,pass,confirmPass,phone,address,birthDate;
    RadioButton male;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.txtName);
        birthDate = findViewById(R.id.txtBirthDay);
        Mail = findViewById(R.id.txtMail);
        pass = findViewById(R.id.txtPass);
        confirmPass = findViewById(R.id.txtConPass);
        phone = findViewById(R.id.txtPhone);
        register = findViewById(R.id.btnSignUp);
        male = findViewById(R.id.radioMale);
        address = findViewById(R.id.txtAdr);
        final Calendar c = Calendar.getInstance();
        final  int year = c.get(Calendar.YEAR);
        final  int month = c.get(Calendar.MONTH)+1;
        final  int day = c.get(Calendar.DAY_OF_MONTH);

        Mail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String em= "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
                if(Mail.getText().toString().matches(em))
                    ;
                else
                    Mail.setError("Email is not valid");
            }
        });

        phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b == true )
                {
                    ;;
                } else
                {
                    if (phone.getText().toString().length() < 11)
                        phone.setError("must be 11 digit");
                }
            }
        });

        birthDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                DatePickerDialog datepic = new DatePickerDialog(Register.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int Year, int Month, int Day) {
                        birthDate.setText(Year + "/" + (Month+1) + "/"+Day);
                    }
                },year,month,day);
                datepic.setTitle("Choose Date");
                datepic.show();
            }
        });
    }

    public void click(View view) {

        String gender ;
        if(male.isChecked())
            gender = "male";
        else
            gender = "Female";
        if (name.getText().toString().isEmpty())
        {
            name.setError("Please enter your name");
            name.requestFocus();
        }
        else
        {
            if(Mail.getText().toString().isEmpty())
            {
                Mail.setError("Please enter your Email");
                Mail.requestFocus();
            }else
            {
                if(phone.getText().toString().isEmpty())
                {
                    phone.setError("Please enter your Phone number");
                    phone.requestFocus();
                }
                else
                {
                    if(pass.getText().toString().isEmpty())
                    {
                        pass.setError("Please enter your Password");
                        pass.requestFocus();
                    }
                    else
                    {
                        if(confirmPass.getText().toString().isEmpty())
                        {
                            confirmPass.setError("Please enter your Password");
                            confirmPass.requestFocus();
                        }
                        else
                        {
                            if (pass.getText().toString().equals(confirmPass.getText().toString()))
                            {
                                database db = new database() ;
                                Connection conn = db.ConnectDB();
                                if (conn == null)
                                {
                                    Toast.makeText(this,"Check your internet connection",Toast.LENGTH_LONG).show();
                                }else
                                {
                                    String msg = db.RUNDML("insert into Patient values('"+name.getText()+"','"+birthDate.getText()+"','"+address.getText()+"','"+Mail.getText()+"','"+phone.getText()+"','"+gender+"','"+pass.getText()+"','')");///complete
                                    if (msg.equals("Ok")) {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(Register.this);
                                        alert.setTitle("Congratulations");
                                        alert.setMessage(" your account has been created");
                                        alert.setPositiveButton("go to login ", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                startActivity(new Intent(Register.this,LoginActivity.class));
                                            }
                                        });
                                        alert.setNegativeButton("Thanks",null);
                                        alert.create().show();
                                    }
                                    else if(msg.contains("UF"))
                                    {
                                        phone.setError("This phone number is already used");
                                        phone.requestFocus();
                                    }
                                    else if (msg.contains("UEP"))
                                    {
                                        Mail.setError("This Email is already used");
                                        Mail.requestFocus();
                                    }
                                    else
                                    {
                                        Toast.makeText(this,"Error is"+msg,Toast.LENGTH_LONG);
                                    }


                                }

                            }
                            else
                            {
                                confirmPass.setError("Password must match");
                                confirmPass.requestFocus();
                            }

                        }

                    }
                }
            }
        }


    }

}