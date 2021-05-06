package com.example.clinic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class doctorSections {
    public List<Doctors> getData()
    {
        List<Doctors> data = new ArrayList<>();
        database db = new database();
        Connection conn = db.ConnectDB();
        ResultSet result = db.RunSearch("Select * From Doctor");
        try {
            while (result.next()) {
                Doctors doctors = new Doctors();
                doctors.setName(result.getString(2));
                doctors.setLogo(result.getString(6));
                doctors.setDescription(result.getString(7));
                data.add(doctors);
            }
        }catch (SQLException e)
        {

        }

    return data;
    }
}
