package com.example.zul_mizini.bangunpc1;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.zul_mizini.bangunpc1.R;
import com.example.zul_mizini.bangunpc1.amd.Activity_pilihsoketamd;
import com.example.zul_mizini.bangunpc1.intel.Activity_pilihsoketintel;
import com.example.zul_mizini.bangunpc1.komponen.Activity_casing;
import com.example.zul_mizini.bangunpc1.ram.Main2Activity;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class Activity_pilihprocessor extends AppCompatActivity {

    LinearLayoutCompat linearLayoutCompatt;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihprocessor);
        linearLayoutCompatt = (LinearLayoutCompat) findViewById(R.id.LinearLayouta);
        animationDrawable = (AnimationDrawable) linearLayoutCompatt.getBackground();

        animationDrawable.setEnterFadeDuration(3000);
        animationDrawable.setExitFadeDuration(3000);
        Activity_pilihprocessor.this.setTitle("Pilih Processor");
//        //COPAS INI UNTUK MENAMPILKAN INTERSTITIAL ADMOB
//        InterstitialAd ad = AdManager.getAd();
//        if (ad != null) {
//            ad.show();
//        }
//        //SAMPE SINI YAAA
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (animationDrawable != null && animationDrawable.isRunning()){
            animationDrawable.stop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        AdManager adManager = new AdManager(this,"ca-app-pub-3940256099942544/1033173712");
        adManager.createAd();

        if (animationDrawable != null && !animationDrawable.isRunning()){
            animationDrawable.start();
        }
    }

    public void intelpd(View v){
        Intent i = new Intent(Activity_pilihprocessor.this, Activity_pilihsoketintel.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
        startActivity(i);}

        public void amdpd(View v){
            Intent i = new Intent(Activity_pilihprocessor.this, Activity_pilihsoketamd.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
            startActivity(i);}
}
