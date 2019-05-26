package com.example.alumno.tplaboratoriov;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
    private MyOnItemClick listener;
    public TextView tvTitle;
    public TextView tvDescription;
    public TextView tvLink;
    public ImageView viewImagen;
    private int position;

    public MyViewHolder(View v,MyOnItemClick listener){
        super(v);
        v.setOnClickListener(this);
        tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        tvLink = (TextView) v.findViewById(R.id.tvLink);
        tvDescription = (TextView) v.findViewById(R.id.tvDescription);
        viewImagen = (ImageView) v.findViewById(R.id.viewImagen);
        this.listener = listener;
    }
    public void setPosition(int position)
    {
        this.position = position;
    }
    @Override
    public void onClick(View v) {
        listener.onItemClick(v,position);

    }
}
