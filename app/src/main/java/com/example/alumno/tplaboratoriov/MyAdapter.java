package com.example.alumno.tplaboratoriov;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by alumno on 02/05/2019.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    List<Item> items;
    private MyOnItemClick listener;
    private Handler myHanler;
    public MyAdapter(List<Item> personas,MyOnItemClick listener,Handler myHanler){
        this.items = personas;
        this.listener = listener;
        this.myHanler = myHanler;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recy,parent,false);

        MyViewHolder myViewHoleder = new MyViewHolder(v,listener);
        return myViewHoleder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Aca se debe llamar al hilo para obtener la imagen
        Item i = this.items.get(position);

        holder.tvTitle.setText(i.getTitle());
        holder.tvDescription.setText(i.getDescription());
        holder.tvLink.setText(i.getLink());

        if(!i.getProcesar()){
            MyThread hilo2 = new MyThread(this.myHanler,i.getImage(),2,position);
            i.setProcesar(true);
            hilo2.start();
        }
        else{
            if(i.getImagenValue() != null){
                holder.viewImagen.setImageBitmap(BitmapFactory.decodeByteArray(i.getImagenValue(),0,i.getImagenValue().length));
            }
        }

        holder.setPosition(position);
    }
    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public List<Item> SetPersonas(List<Item> pers){
        this.items = pers;
        return this.items;
    }
    public void SetImagenPer(byte[] imagen,int position){
        Item p = this.items.get(position);
        p.setImagenValue(imagen);
    }

}
