package com.example.zul_mizini.bangunpc1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.zul_mizini.bangunpc1.amd.Activity_amd_am3;
import com.example.zul_mizini.bangunpc1.amd.Activity_amd_am4;
import com.example.zul_mizini.bangunpc1.amd.Activity_amd_mb_am3;
import com.example.zul_mizini.bangunpc1.amd.Activity_amd_mb_am4;
import com.example.zul_mizini.bangunpc1.amd.Activity_amd_mb_tr4;
import com.example.zul_mizini.bangunpc1.amd.Activity_amd_tr4;
import com.example.zul_mizini.bangunpc1.intel.Activity_intel_lga1150;
import com.example.zul_mizini.bangunpc1.intel.Activity_intel_lga1151;
import com.example.zul_mizini.bangunpc1.intel.Activity_intel_lga2066;
import com.example.zul_mizini.bangunpc1.intel.Activity_intel_mb_lga1150;
import com.example.zul_mizini.bangunpc1.intel.Activity_intel_mb_lga1151;
import com.example.zul_mizini.bangunpc1.intel.Activity_intel_mb_lga2066;
import com.example.zul_mizini.bangunpc1.komponen.Activity_casing;
import com.example.zul_mizini.bangunpc1.komponen.Activity_cpucooler;
import com.example.zul_mizini.bangunpc1.komponen.Activity_fan;
import com.example.zul_mizini.bangunpc1.komponen.Activity_hddssd;
import com.example.zul_mizini.bangunpc1.komponen.Activity_optical;
import com.example.zul_mizini.bangunpc1.komponen.Activity_psu;
import com.example.zul_mizini.bangunpc1.komponen.Activity_vga;
import com.example.zul_mizini.bangunpc1.ram.Activity_ddr4;
import com.example.zul_mizini.bangunpc1.ram.Main2Activity;

import java.util.List;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.MyViewHolder> {
    RequestOptions options;
    private Context mContext;
    private List<barangModel> mData;
    private View.OnClickListener onClickListener;
    private int selectedItem = -1;


    public recyclerViewAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.barang_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.tv_namaBarang.setText(mData.get(i).getTitle());
        myViewHolder.tv_hargaBarang.setText("Rp " + mData.get(i).getPrice());

        Glide.with(mContext)
                .load(mData.get(i).getProduct_image())
                .apply(options)
                .into(myViewHolder.iv_gambarItem);

        myViewHolder.linearItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mData.get(i).getPrice(), Toast.LENGTH_LONG).show();

            }
        });


        if(selectedItem == i){
            myViewHolder.ll_dasar.setBackgroundColor(mContext.getResources().getColor(R.color.bg_selected));
        } else {
            myViewHolder.ll_dasar.setBackgroundColor(mContext.getResources().getColor(R.color.transparan));
        }

        myViewHolder.linearItem.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                selectedItem = i;
                notifyDataSetChanged();


                Activity_intel_lga2066.setDataSementara(mData,i);
                Activity_intel_mb_lga2066.setDataSementara(mData,i);
                Activity_ddr4.setDataSementara(mData,i);

                Activity_hddssd.setDataSementara(mData,i);
                Activity_cpucooler.setDataSementara(mData,i);
                Activity_fan.setDataSementara(mData,i);
                Activity_optical.setDataSementara(mData,i);
                Activity_vga.setDataSementara(mData,i);
                Activity_psu.setDataSementara(mData,i);
                Activity_casing.setDataSementara(mData,i);


                //              amd
                Activity_amd_tr4.updateHarga(Integer.parseInt(mData.get(i).getPrice()));
                Activity_amd_am3.updateHarga(Integer.parseInt(mData.get(i).getPrice()));
                Activity_amd_mb_am3.updateHarga(Integer.parseInt(mData.get(i).getPrice()));
                Activity_amd_am4.updateHarga(Integer.parseInt(mData.get(i).getPrice()));
                Activity_amd_mb_am4.updateHarga(Integer.parseInt(mData.get(i).getPrice()));
                Activity_amd_mb_tr4.updateHarga(Integer.parseInt(mData.get(i).getPrice()));

//                intel
                Activity_intel_lga1150.updateHarga(Integer.parseInt(mData.get(i).getPrice()));
                Activity_intel_lga1151.updateHarga(Integer.parseInt(mData.get(i).getPrice()));
                Activity_intel_mb_lga1150.updateHarga(Integer.parseInt(mData.get(i).getPrice()));
                Activity_intel_lga2066.updateHarga(Integer.parseInt(mData.get(i).getPrice()));
                Activity_intel_mb_lga2066.updateHarga(Integer.parseInt(mData.get(i).getPrice()));
                Activity_intel_mb_lga1151.updateHarga(Integer.parseInt(mData.get(i).getPrice()));

//                komponen
                Activity_casing.updateHarga(Integer.parseInt(mData.get(i).getPrice()));
                Activity_cpucooler.updateHarga(Integer.parseInt(mData.get(i).getPrice()));
                Activity_fan.updateHarga(Integer.parseInt(mData.get(i).getPrice()));
                Activity_hddssd.updateHarga(Integer.parseInt(mData.get(i).getPrice()));
                Activity_optical.updateHarga(Integer.parseInt(mData.get(i).getPrice()));
                Activity_psu.updateHarga(Integer.parseInt(mData.get(i).getPrice()));
                Activity_vga.updateHarga(Integer.parseInt(mData.get(i).getPrice()));

//                ram
                Activity_ddr4.updateHarga(Integer.parseInt(mData.get(i).getPrice()));
                Main2Activity.updateHarga(Integer.parseInt(mData.get(i).getPrice()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_gambarItem;
        TextView tv_namaBarang;
        TextView tv_hargaBarang;
        LinearLayout linearItem;
        LinearLayout ll_dasar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_gambarItem = itemView.findViewById(R.id.iv_gambarItem);
            tv_namaBarang = itemView.findViewById(R.id.tv_namaBarang);
            tv_hargaBarang = itemView.findViewById(R.id.tv_hargaBarang);
            linearItem = itemView.findViewById(R.id.linearItem);
            ll_dasar = itemView.findViewById(R.id.ll_dasar);
        }
    }

}
