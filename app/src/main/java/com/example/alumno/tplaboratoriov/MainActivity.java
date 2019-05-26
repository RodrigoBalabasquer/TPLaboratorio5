package com.example.alumno.tplaboratoriov;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyOnItemClick, Handler.Callback {
    MyAdapter adapter;
    List<Item> items;
    public static final int TEXTO = 1;
    public static final int IMAGEN = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();

        RecyclerView rvPersona = (RecyclerView) super.findViewById(R.id.rvPersonas);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvPersona.setLayoutManager(layoutManager);

        Handler handler = new Handler(this);

        adapter = new MyAdapter(items,this,handler);
        rvPersona.setAdapter(adapter);

        MyThread hilo = new MyThread(handler,"https://tn.com.ar/rss.xml",TEXTO,0);
        hilo.start();
    }
    @Override
    public void onItemClick(View v,int position) {
        Item item = this.items.get(position);
        Log.d("Nueva persona",item.toString());

        Intent i = new Intent(this,AnotherActivity.class);

        i.putExtra("link",item.getLink());
        this.startActivity(i);

    }

    @Override
    public boolean handleMessage(Message msg) {
        if(msg.arg1 == TEXTO){
            this.items = this.adapter.SetPersonas((List<Item>) msg.obj);
            adapter.notifyDataSetChanged();
        }
        else{
            this.adapter.SetImagenPer((byte[])msg.obj,msg.arg2);
            adapter.notifyItemChanged(msg.arg2);
        }
        return false;
    }
}
