package com.example.zul_mizini.bangunpc1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class tutorrakitpc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorrakitpc);
        ImageView imageView = (ImageView) findViewById(R.id.imageViewrp);
        Picasso.get().load("https://raw.githubusercontent.com/zulkifli-mizini/kelompok2/master/trpc.png").into(imageView);
    }
}
