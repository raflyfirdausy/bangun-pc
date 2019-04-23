package com.example.zul_mizini.bangunpc1.intel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.zul_mizini.bangunpc1.R;
import com.example.zul_mizini.bangunpc1.amd.Activity_amd_am3;
import com.example.zul_mizini.bangunpc1.amd.Activity_amd_am4;
import com.example.zul_mizini.bangunpc1.amd.Activity_amd_tr4;
import com.example.zul_mizini.bangunpc1.amd.Activity_pilihsoketamd;

public class Activity_pilihsoketintel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihsoketintel);

        Activity_pilihsoketintel.this.setTitle("Pilih Socket Intel");
    }
    public void lga2066 (View v){
        Intent i = new Intent(Activity_pilihsoketintel.this, Activity_intel_lga2066.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
        startActivity(i);
    finish();}

    public void lga1151 (View v){
        Intent i = new Intent(Activity_pilihsoketintel.this, Activity_intel_lga1151.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
        startActivity(i);
    finish();}

    public void lga1150(View v){
        Intent i = new Intent(Activity_pilihsoketintel.this, Activity_intel_lga1150.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
        startActivity(i);
    finish();}
}
