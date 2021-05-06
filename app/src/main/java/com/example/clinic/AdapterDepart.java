package com.example.clinic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterDepart extends ArrayAdapter<Doctors> {

    Context c;
    ArrayList<Doctors> ass;

    public AdapterDepart(Context context, ArrayList<Doctors> cont) {
        super(context, R.layout.doctorslayout,cont);
        c=context;
        ass=cont;
    }

    class Holder
    {
        ImageView imgdept;
        TextView txtname;
        TextView txtdescreption;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        Doctors data = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

        Holder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {

            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.doctorslayout, parent, false);

            viewHolder.txtname = (TextView) convertView.findViewById(R.id.textView);

           viewHolder.imgdept = (ImageView) convertView.findViewById(R.id.imageView2);
            viewHolder.txtdescreption = (TextView) convertView.findViewById(R.id.textView3);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (Holder) convertView.getTag();
        }
        PicassoClient.downloadImage(c,data.getLogo(),viewHolder.imgdept);
        viewHolder.txtname.setText(data.getName());
        viewHolder.txtdescreption.setText(data.getDescription());

        // Return the completed view to render on screen
        return convertView;
    }


}
