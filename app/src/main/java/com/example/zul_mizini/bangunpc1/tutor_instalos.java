package com.example.zul_mizini.bangunpc1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class tutor_instalos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_instalos);
        ImageView imageView = (ImageView) findViewById(R.id.imageViewio);
        Picasso.get().load("https://raw.githubusercontent.com/zulkifli-mizini/kelompok2/master/trios.png").into(imageView);


    }
}
