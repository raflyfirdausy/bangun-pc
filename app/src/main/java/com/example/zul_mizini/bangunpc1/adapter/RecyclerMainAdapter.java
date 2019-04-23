package com.example.zul_mizini.bangunpc1.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.example.zul_mizini.bangunpc1.DetailActivity;
import com.example.zul_mizini.bangunpc1.R;
import com.example.zul_mizini.bangunpc1.helper.DetailbarangpilihModel;

import java.util.List;

public class RecyclerMainAdapter extends RecyclerView.Adapter<RecyclerMainAdapter.MyViewHolder>  {

    private Context mContext;
    private List<DetailbarangpilihModel> mData;

    public RecyclerMainAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.riwayat_item, viewGroup, false);
        return new RecyclerMainAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.tv_namariwayat.setText(mData.get(i).getNama_barang());
        final int index = i;
        myViewHolder.ll_dasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, mData.get(i).getId_barang(), Toast.LENGTH_LONG).show();
                Intent i = new Intent(mContext, DetailActivity.class);
                i.putExtra("id", mData.get(index).getId_barang());
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;

        TextView tv_namariwayat;
        LinearLayout ll_dasar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_namariwayat = itemView.findViewById(R.id.tv_namariwayat);
            ll_dasar = itemView.findViewById(R.id.ll_dasar);
        }
    }
}
