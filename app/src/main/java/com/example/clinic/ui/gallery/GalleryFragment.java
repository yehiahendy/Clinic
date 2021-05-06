package com.example.clinic.ui.gallery;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.clinic.LoginActivity;
import com.example.clinic.R;
import com.example.clinic.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    TextView address,gender,phone,name,email,date,resdate,restime;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        name = root.findViewById(R.id.txtName);
        email = root.findViewById(R.id.txtEmail);
        address = root.findViewById(R.id.txtAdrr);
        gender = root.findViewById(R.id.txtGender);
        phone = root.findViewById(R.id.txtph);
        date = root.findViewById(R.id.txtBirthDay);
        //resdate = root.findViewById(R.id.resdate);
        ///////////////////////////////////////////////////////////////////////////////////////////////
        name.setText(LoginActivity.Name);
        email.setText(LoginActivity.mail);
        address.setText(LoginActivity.address);
        gender.setText(LoginActivity.gender);
        phone.setText(LoginActivity.phone);
        date.setText(LoginActivity.birth);
        ////////////////////////////////////////////////////////////////////////////////////////////
       /* database db = new database();
        Connection conn = db.ConnectDB();
        ResultSet rs = db.RunSearch("select * from  Reservation where ReservationID  = '"+LoginActivity.id+"'");
        try {
            if (rs.next())
            {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                String strDate = dateFormat.format(rs.getDate(2));

                resdate.setText(strDate);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
*/
        //Toast.makeText(getActivity(),LoginActivity.mail,Toast.LENGTH_LONG).show();

        return root;
    }
}