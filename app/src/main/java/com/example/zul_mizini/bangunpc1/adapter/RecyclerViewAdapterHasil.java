package com.example.zul_mizini.bangunpc1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.zul_mizini.bangunpc1.R;
import com.example.zul_mizini.bangunpc1.barangModel;
import com.example.zul_mizini.bangunpc1.helper.DetailBarangPilih;
import com.example.zul_mizini.bangunpc1.helper.DetailbarangpilihModel;
import com.example.zul_mizini.bangunpc1.recyclerViewAdapter;

import java.util.List;

public class RecyclerViewAdapterHasil  extends RecyclerView.Adapter<RecyclerViewAdapterHasil.MyViewHolder> {

    RequestOptions options;
    private Context mContext;
    private List<DetailbarangpilihModel> mData;

    public RecyclerViewAdapterHasil(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_tentang);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.hasil_item, viewGroup, false);
        return new RecyclerViewAdapterHasil.MyViewHolder(view);
    }

    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        String jenis[] = {"Processor",
                "Motherboard",
                "RAM",
                "Hardisk",
                "CPU Cooler",
                "FAN",
                "Optical Drive",
                "VGA Card",
                "Power Supply",
                "Casing"};
        myViewHolder.tv_jenis.setText(jenis[i]);

        if (isNullOrEmpty(mData.get(i).getNama_barang())) {
            myViewHolder.tv_namaBarang.setText("Tidak Memilih Barang");
            myViewHolder.tv_hargaBarang.setText("Rp 0");
            myViewHolder.iv_gambarItem.setBackgroundResource(R.drawable.ic_tentang);
        } else {
            myViewHolder.tv_namaBarang.setText(mData.get(i).getNama_barang());
            myViewHolder.tv_hargaBarang.setText("Rp " + mData.get(i).getHarga_barang());

            Glide.with(mContext)
                    .load(mData.get(i).getGambar_barang())
                    .apply(options)
                    .into(myViewHolder.iv_gambarItem);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
//        return 10;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_gambarItem;
        TextView tv_namaBarang;
        TextView tv_hargaBarang;
        TextView tv_jenis;
        LinearLayout linearItem;
        LinearLayout ll_dasar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_gambarItem = itemView.findViewById(R.id.iv_gambarItem);
            tv_namaBarang = itemView.findViewById(R.id.tv_namaBarang);
            tv_hargaBarang = itemView.findViewById(R.id.tv_hargaBarang);
            tv_jenis = itemView.findViewById(R.id.tv_jenis);
            linearItem = itemView.findViewById(R.id.linearItem);
            ll_dasar = itemView.findViewById(R.id.ll_dasar);
        }
    }
}
