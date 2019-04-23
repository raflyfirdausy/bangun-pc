package com.example.zul_mizini.bangunpc1;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zul_mizini.bangunpc1.adapter.RecyclerViewAdapterHasil;
import com.example.zul_mizini.bangunpc1.helper.DetailBarangPilih;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class HasilActivity extends AppCompatActivity {

    private RecyclerView rv_konten;
    private TextView tv_total;
    private Button btn_simpan;
    private boolean wislogin;

    private InterstitialAd interstitialAd;

    private DatabaseReference databaseReference;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
        rv_konten = findViewById(R.id.rv_konten2);
        tv_total = findViewById(R.id.tv_total);
        btn_simpan = findViewById(R.id.btn_simpan);

        RecyclerViewAdapterHasil adapter = new RecyclerViewAdapterHasil(HasilActivity.this, DetailBarangPilih.list);
        rv_konten.setLayoutManager(new LinearLayoutManager(HasilActivity.this));
        adapter.notifyDataSetChanged();
        rv_konten.setAdapter(adapter);

        tv_total.setText("Total : Rp " + DetailBarangPilih.totalHarga);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wislogin){
                    save();
                } else {
                    Toast.makeText(HasilActivity.this, getString(R.string.login_dulu), Toast.LENGTH_LONG).show();
                }
            }
        });

        //cek wis login urung
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            wislogin = true;
        } else {
            wislogin = false;
        }





//        DetailBarangPilih.list.clear();
//        DetailBarangPilih.totalHarga = 0;
    }

    private void save() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HasilActivity.this);
        LayoutInflater inflater = HasilActivity.this.getLayoutInflater();
        View x = inflater.inflate(R.layout.layout_dialog, null);
        final EditText et_namaFile = (EditText) x.findViewById(R.id.et_namaFile);
        et_namaFile.setText(getString(R.string.rakit_baru));
        builder.setView(x)
                .setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String namaFile = et_namaFile.getText().toString();
                        if (namaFile.equalsIgnoreCase("")) {
                            Toast.makeText(HasilActivity.this, getString(R.string.gagal),
                                    Toast.LENGTH_LONG).show();
                        } else {
                            //TODO : kene save maring firebase
                            databaseReference = FirebaseDatabase.getInstance().
                                    getReference()
                                    .child("riwayat")
                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
                            String key = databaseReference.push().getKey();

                            databaseReference.child(key).child("nama_riwayat").setValue(namaFile);
                            databaseReference.child(key).child("total").setValue(DetailBarangPilih.totalHarga);
                            for (int j = 0; j < DetailBarangPilih.list.size(); j++) {
                                Map map = new HashMap();
                                map.put("nama_barang", DetailBarangPilih.list.get(j).getNama_barang());
                                map.put("harga_barang", DetailBarangPilih.list.get(j).getHarga_barang());
                                map.put("gambar_barang", DetailBarangPilih.list.get(j).getGambar_barang());
                                databaseReference.child(key)
                                        .child("barang")
                                        .child("barang-" + j)
                                        .setValue(map);
                            }
                            Toast.makeText(HasilActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_LONG).show();

                            //COPAS INI UNTUK MENAMPILKAN INTERSTITIAL ADMOB
                            InterstitialAd ad = AdManager.getAd();
                            if (ad != null) {
                                ad.show();
                            }
                            //SAMPE SINI YAAA

//                            //Implementasi dan Membuat Objek Interstitial Ads
//                            interstitialAd = new InterstitialAd(HasilActivity.this);
//                            //Masukan ID Unit Iklan Interstitial Kalian Disini
//                            interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
//                            interstitialAd.loadAd(new AdRequest.Builder().build());
//
//                            //Membuat Event/Kejadian Pada Siklus Hidup Interstitial Ads
//                            interstitialAd.setAdListener(new AdListener(){
//                                @Override
//                                public void onAdClosed() {
//                                    super.onAdClosed();
//                                    //Kode disini akan di eksekusi saat Iklan Ditutup
//                                    Toast.makeText(getApplicationContext(), "Iklan Dititup", Toast.LENGTH_SHORT).show();
//                                    //Setelah ditutup, Iklan akan memuat ulang kembali
//                                    interstitialAd.loadAd(new AdRequest.Builder().build());
//                                }
//
//                                @Override
//                                public void onAdFailedToLoad(int i) {
//                                    super.onAdFailedToLoad(i);
//                                    //Kode disini akan di eksekusi saat Iklan Gagal Dimuat
//                                    Toast.makeText(getApplicationContext(), "Iklan Gagal Dimuat", Toast.LENGTH_SHORT).show();
//                                }
//
//                                @Override
//                                public void onAdLeftApplication() {
//                                    super.onAdLeftApplication();
//                                    //Kode disini akan di eksekusi saat Pengguna Meniggalkan Aplikasi/Membuka Aplikasi Lain
//                                    Toast.makeText(getApplicationContext(), "Iklan Ditinggalkan", Toast.LENGTH_SHORT).show();
//                                }
//
//                                @Override
//                                public void onAdOpened() {
//                                    super.onAdOpened();
//                                    //Kode disini akan di eksekusi saat Pengguna Mengklik Iklan
//                                    Toast.makeText(getApplicationContext(), "Iklan Diklik", Toast.LENGTH_SHORT).show();
//                                }
//
//                                @Override
//                                public void onAdLoaded() {
//                                    super.onAdLoaded();
//                                    //Kode disini akan di eksekusi saat Iklan Selesai Dimuat
//                                    Toast.makeText(getApplicationContext(), "Iklan Selesai Dimuat", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//
//                            //Setelah Iklan Selesai dimuat, pada saat Button di Klik, iklan akan muncul
////                            showAds.setOnClickListener(new View.OnClickListener() {
////                                @Override
////                                public void onClick(View view) {
////                                    if(interstitialAd.isLoaded()){
////                                        interstitialAd.show();
////                                    }
////                                }
////                            });


                            finish();
                        }
                    }
                })
                .setNegativeButton("Batal", null)
                .create()
                .show();


    }

    public void onBackPressed() {
        DetailBarangPilih.totalHarga = 0;
        Intent i = new Intent(HasilActivity.this, Activity_pilihprocessor.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
        startActivity(i);
        finish();
    }
}
