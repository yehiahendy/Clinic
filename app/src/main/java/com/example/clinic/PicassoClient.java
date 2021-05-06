package com.example.clinic;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by YAT on 25/11/2017.
 */

public class PicassoClient {
    static String imgs;



    public static void downloadImage(Context c, String url, ImageView img)
    {

        if(url != null && url.length()>0)
        {

            Picasso.with(c).load(url).resize(150,150).transform(new CropCircleTransformation()).placeholder(R.drawable.doctors).into(img);
        }
        else
        {
            Picasso.with(c).load(R.drawable.doctors).into(img);
        }
    }

}