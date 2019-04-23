package com.example.zul_mizini.bangunpc1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class paketpc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paketpc);
        ImageView imageView = (ImageView) findViewById(R.id.imageViewpkpc);
        Picasso.get().load("https://raw.githubusercontent.com/zulkifli-mizini/kelompok2/master/pgpc.png").into(imageView);
    }
}
