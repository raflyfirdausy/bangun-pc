package com.example.zul_mizini.bangunpc1.intel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.zul_mizini.bangunpc1.R;
import com.example.zul_mizini.bangunpc1.barangModel;
import com.example.zul_mizini.bangunpc1.helper.DetailBarangPilih;
import com.example.zul_mizini.bangunpc1.helper.DetailbarangpilihModel;
import com.example.zul_mizini.bangunpc1.ram.Main2Activity;
import com.example.zul_mizini.bangunpc1.recyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Activity_intel_mb_lga1150 extends AppCompatActivity {


    private RecyclerView rv_konten;
    private static TextView tv_harga;
    private Button btn_next;
    private JsonArrayRequest jsonArrayRequest;
    private RequestQueue requestQueue;
    private List<barangModel> list = new ArrayList<>();
    private String BASE_URL = "http://jatmikom.store/";
    private String URL_API = BASE_URL + "api/product/kondisi/id/16";
    private String URL_GAMBAR = BASE_URL + "attachments/shop_images/";
    private static int HargaAwal = DetailBarangPilih.totalHarga;
    private static int UpdateHarga = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddr4);
        Activity_intel_mb_lga1150.this.setTitle("Pilih Motherboard Socket LGA 1150");

        rv_konten = findViewById(R.id.rv_konten);
        tv_harga = findViewById(R.id.tv_harga);
        btn_next = findViewById(R.id.btn_next);
        //   tv_harga.setText(URL_API);
        ambil_dataOnline();

        tv_harga.setText(String.valueOf(DetailBarangPilih.totalHarga));


    }

    private void ambil_dataOnline() {
        jsonArrayRequest = new JsonArrayRequest(URL_API, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        barangModel barangModel = new barangModel();
                        barangModel.setProduct_id(jsonObject.getString("product_id"));
                        barangModel.setTitle(jsonObject.getString("title"));
                        barangModel.setPrice(jsonObject.getString("price"));
                        barangModel.setProduct_image(URL_GAMBAR + jsonObject.getString("product_image"));
                        list.add(barangModel);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                recyclerViewAdapter adapter = new recyclerViewAdapter(Activity_intel_mb_lga1150.this, list);
                rv_konten.setLayoutManager(new LinearLayoutManager(Activity_intel_mb_lga1150.this));
                rv_konten.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Activity_intel_mb_lga1150.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue = Volley.newRequestQueue(Activity_intel_mb_lga1150.this);
        requestQueue.add(jsonArrayRequest);
    }

    public static void updateHarga(int harga) {
        try {
            UpdateHarga = DetailBarangPilih.totalHarga + harga;
            tv_harga.setText("Rp " + UpdateHarga);
        } catch (Exception e) {
            Log.d("Exeption", e.getMessage());
        }

    }

    public static void setDataSementara(List<barangModel> x, int posisi){
        DetailBarangPilih.namaBarangSementara = x.get(posisi).getTitle();
        DetailBarangPilih.hargabarangSementara = x.get(posisi).getPrice();
        DetailBarangPilih.gambarbarangSementara = x.get(posisi).getProduct_image();
    }

    public void pdlanjut(View v) {
        DetailBarangPilih.totalHarga = UpdateHarga;


        int index = 1;
        if(index >= DetailBarangPilih.list.size()){
            //kosong alias null
            DetailbarangpilihModel data = new DetailbarangpilihModel();
            data.setNama_barang(DetailBarangPilih.namaBarangSementara);
            data.setHarga_barang(DetailBarangPilih.hargabarangSementara);
            data.setGambar_barang(DetailBarangPilih.gambarbarangSementara);
            DetailBarangPilih.list.add(data);

        } else {
            //wis ana datane
            DetailBarangPilih.list.get(index).setNama_barang(DetailBarangPilih.namaBarangSementara);
            DetailBarangPilih.list.get(index).setHarga_barang(DetailBarangPilih.hargabarangSementara);
            DetailBarangPilih.list.get(index).setGambar_barang(DetailBarangPilih.gambarbarangSementara);
        }

        DetailBarangPilih.namaBarangSementara = null;
        DetailBarangPilih.hargabarangSementara = null;

        Intent i = new Intent(Activity_intel_mb_lga1150.this, Main2Activity.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
        startActivity(i);
        finish();
    }

    public void onBackPressed() {
        DetailBarangPilih.totalHarga = 0;
        finish();
    }
}