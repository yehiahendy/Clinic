package com.example.clinic;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clinic.ui.home.HomeFragment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.example.clinic.ui.home.HomeFragment;

public class Reservation extends AppCompatActivity {
    EditText txtdate,txttime,txttype,txtdetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        final Calendar c = Calendar.getInstance();
        final  int year = c.get(Calendar.YEAR);
        final  int month = c.get(Calendar.MONTH)+1;
        final  int day = c.get(Calendar.DAY_OF_MONTH);
        final  int hour = c.get(Calendar.HOUR);
        final  int min = c.get(Calendar.MINUTE);
        txttype  = findViewById(R.id.txttype);
        txtdetails = findViewById(R.id.txtdetails);
        txtdate = findViewById(R.id.txtdate);
        txttime = findViewById(R.id.txttime);
        txttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timmpic = new TimePickerDialog(Reservation.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        txttime.setText(i+":"+i1);
                    }
                },hour,min,true);
                timmpic.setTitle("Choose time");
                timmpic.show();
            }
        });
        txtdate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                DatePickerDialog datepic = new DatePickerDialog(Reservation.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int Year, int Month, int Day) {
                        txtdate.setText(Year + "/" + Month + "/"+Day);
                    }
                },year,month,day);
                datepic.setTitle("Choose Date");
                datepic.show();
            }
        });
    }

    public void click(View view) throws SQLException {
        database db = new database();
        Connection conn = db.ConnectDB();
        if (txtdate.getText().toString().isEmpty())
        {
            txtdate.setError("Please enter the reservation date");
        }
        else
        {
            if(txttime.getText().toString().isEmpty())
            {
                txttime.setError("Please enter the reservation time");
            }
            else {
                if (txttype.getText().toString().isEmpty())
                {
                    txttype.setError("Please enter the reservation time");
                }
                else
                {
                    if (txtdetails.getText().toString().isEmpty())
                    {
                        txtdetails.setError("Please enter the reservation details");
                    }
                    else {

                        if (conn == null)
                        {

                            Toast.makeText(this,"Please check your internet connection",Toast.LENGTH_LONG);
                        }
                        else
                        {
                            ResultSet rs = db.RunSearch("select * from  Reservation where ReservationTime = '"+txttime.getText()+"' and ReservationDate = '"+txtdate.getText()+"'");
                            if (rs.next())
                            {
                                Toast.makeText(this,"This time is not avaliable",Toast.LENGTH_LONG).show();
                            }
                            else {
                                String msg = db.RUNDML("insert into  Reservation values('" + txtdate.getText() + "','" + txtdetails.getText() + "','" + txttime.getText() + "','" + txttype.getText() + "')");

                                if (msg.equals("Ok")) {
                                    int id = 0;
                                    rs = db.RunSearch("select  ReservationID from Reservation where ReservationTime = '" + txttime.getText() + "'");
                                    if (rs.next()) {

                                        id = rs.getInt(1);
                                    }
                                    String msg2 = db.RUNDML("update Patient set PatientRes  = '" + id + "' where phone  = '" + LoginActivity.phone + "';");
                                    if (msg2.equals("Ok")) {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(Reservation.this);
                                        alert.setTitle("Reservation");
                                        alert.setMessage("Your reservation for doctror "+ HomeFragment.doctorName + " is done");
                                        alert.setPositiveButton("Go to main user", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                startActivity(new Intent(Reservation.this, userActivity.class));
                                            }
                                        });
                                        alert.setNegativeButton("Thanks", null);
                                        alert.create().show();

                                    }
                                }
                            }
                        }

                    }
                }
            }
        }



    }
}