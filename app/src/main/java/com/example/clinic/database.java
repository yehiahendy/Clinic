package com.example.clinic;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class database {
    Connection conn = null;
    public Connection ConnectDB()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:jtds:sqlserver://sql5078.site4now.net/DB_A7087C_ClinicDb","DB_A7087C_ClinicDb_admin","clinic@123");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    //using add , update , delete
    public String RUNDML(String stmt)
    {
        try {
            Statement cmd=conn.createStatement();
            cmd.executeUpdate(stmt);
            return "Ok";
        } catch (SQLException e) {
            e.printStackTrace();
            return  e.getMessage();
        }
    }
    public ResultSet RunSearch(String st)
    {
        try {
            Statement stm=conn.createStatement();
            ResultSet rs=stm.executeQuery(st);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
