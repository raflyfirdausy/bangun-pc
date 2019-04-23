package com.example.zul_mizini.bangunpc1.amd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.zul_mizini.bangunpc1.Activity_pilihprocessor;
import com.example.zul_mizini.bangunpc1.R;
import com.example.zul_mizini.bangunpc1.intel.Activity_pilihsoketintel;

public class Activity_pilihsoketamd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihsoketamd);
        Activity_pilihsoketamd.this.setTitle("Pilih Socket AMD");
    }

    public void tr4 (View v){
        Intent i = new Intent(Activity_pilihsoketamd.this, Activity_amd_tr4.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
        startActivity(i);
    finish();}

    public void am4(View v){
        Intent i = new Intent(Activity_pilihsoketamd.this, Activity_amd_am4.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
        startActivity(i);
    finish();}

    public void am3(View v){
        Intent i = new Intent(Activity_pilihsoketamd.this, Activity_amd_am3.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
        startActivity(i);
    finish();}
}
