package com.example.zul_mizini.bangunpc1;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zul_mizini.bangunpc1.adapter.RecyclerViewAdapterHasil;
import com.example.zul_mizini.bangunpc1.helper.DetailbarangpilihModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {
    private String id;
    private RecyclerView rv_konten;
    private TextView tv_total;
    private Button btn_kembali;
    private DatabaseReference databaseReference;
    private ArrayList<DetailbarangpilihModel> list = new ArrayList<>();
    private String isiPesan;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        id = (String) b.get("id");

        rv_konten = findViewById(R.id.rv_konten3);
        tv_total = findViewById(R.id.tv_total2);
        btn_kembali = findViewById(R.id.btn_kembali);
        btn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, MainActivity.class));
                finish();
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("riwayat")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(id);

        final StringBuilder pesan = new StringBuilder();

        list.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
                Date date = new Date();
                pesan.append(dataSnapshot.child("nama_riwayat").getValue(String.class));
                pesan.append("\n");
                pesan.append(dateFormat.format(date));
                pesan.append("\n\n");
                tv_total.setText("Total : Rp " + String.valueOf(dataSnapshot.child("total").getValue(Long.class)));
//                showDialog(dataSnapshot.child("nama_riwayat").getValue(String.class));
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    for (DataSnapshot DS : ds.getChildren()) {
//                        showDialog(DS.child("nama_barang").getValue(String.class));
                        DetailbarangpilihModel data = new DetailbarangpilihModel();
                        data.setNama_barang(DS.child("nama_barang").getValue(String.class));
                        data.setGambar_barang(DS.child("gambar_barang").getValue(String.class));
                        data.setHarga_barang(DS.child("harga_barang").getValue(String.class));
                        list.add(data);
//                        showDialog(dataSnapshot.child("nama_riwayat").getValue(String.class));
//                        showDialog(dataSnapshot.child("nama_riwayat").getValue(String.class));
                        pesan.append(index+1);
                        pesan.append(". ");
                        pesan.append(DS.child("nama_barang").getValue(String.class));
                        pesan.append("\n");
                        pesan.append("Rp ");
                        pesan.append(DS.child("harga_barang").getValue(String.class));
                        pesan.append("\n\n");
                        index++;
                    }
                }
                pesan.append("Total Rp ");
                pesan.append(dataSnapshot.child("total").getValue(Long.class));
                isiPesan = pesan.toString();
                index = 0;
//                showDialog(list.toString());
                RecyclerViewAdapterHasil adapter = new RecyclerViewAdapterHasil(DetailActivity.this, list);
                rv_konten.setLayoutManager(new LinearLayoutManager(DetailActivity.this));
                rv_konten.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void showDialog(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        LayoutInflater inflater = DetailActivity.this.getLayoutInflater();

        builder.setPositiveButton("OK", null)
                .setNegativeButton("Batal", null)
                .setMessage(s)
                .create()
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Intent  sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setPackage("com.google.android.gm");
                sharingIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"Jatmikocahyoaji@yahoo.com"});
                sharingIntent.setType("text/plain");
                String shareBodyText = "Check it out. Your message goes here";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Pesan Rakitan (menunggu balasan konfirmasi selanjutnya)");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, isiPesan);
                startActivity(Intent.createChooser(sharingIntent, "Pilih Aplikasi"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
