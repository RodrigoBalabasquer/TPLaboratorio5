package com.example.alumno.tplaboratoriov;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        String text = i.getLink().split("//")[1].split("/")[0];
        Log.d("link",text);
        holder.tvLink.setText(text);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat parser = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");

        if(i.getFecha() == "" || i.getFecha() == null){
            Date fecha = new Date();
            holder.tvFecha.setText(format.format(fecha));
        }
        else{
            try {
                Date fecha = parser.parse(i.getFecha());
                holder.tvFecha.setText(format.format(fecha));
            } catch (Exception e) {
                holder.tvFecha.setText(e.getMessage());
            }
        }

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

    public List<Item> SetItems(List<Item> items,boolean update){
        if(update)
            this.items = items;
        else{
            for(Item item: items){
                this.items.add(item);
            }
        }
        return this.items;
    }
    public void SetImagen(byte[] imagen,int position){
        Item p = this.items.get(position);
        p.setImagenValue(imagen);
    }
    @Override
    public int getItemCount() {
        return this.items.size();
    }


}
