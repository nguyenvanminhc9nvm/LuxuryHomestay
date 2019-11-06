package com.minhnv.luxuryhomestay.ui.main.adapter.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.widget.ANImageView;
import com.minhnv.luxuryhomestay.R;
import com.minhnv.luxuryhomestay.data.model.Homestay;

public class StaggeredHomeStayViewHolder extends RecyclerView.ViewHolder{
    private TextView tvName,tvAddress;
    private ANImageView imgView;
    private Button btnDetail;
    private StaggeredHomeStayViewHolder(@NonNull View itemView) {
        super(itemView);
        tvAddress = itemView.findViewById(R.id.tvAddressHomeStayHot);
        tvName = itemView.findViewById(R.id.tvNameHomStayHot);
        imgView = itemView.findViewById(R.id.imgHomeStayHot);
        btnDetail = itemView.findViewById(R.id.btnDetailHomeStayHot);
    }
    public void bind(Homestay homestay, StaggeredPriceViewHolder.UserActionListener callBack){
        imgView.setImageUrl(homestay.getImage());
        imgView.setErrorImageResId(R.drawable.uploadfailed);
        imgView.setDefaultImageResId(R.drawable.img_home1);
        tvName.setText(homestay.getName());
        tvAddress.setText(homestay.getAddress());

        btnDetail.setOnClickListener(v -> callBack.onActionViewDetailHsPriceByUser(getAdapterPosition()));
    }
    public static StaggeredHomeStayViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_staggeredgridlayout,parent,false);
        return new StaggeredHomeStayViewHolder(view);
    }


}