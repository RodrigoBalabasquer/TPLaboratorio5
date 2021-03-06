package com.example.alumno.tplaboratoriov;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyOnItemClick, Handler.Callback,SearchView.OnQueryTextListener {
    MyAdapter adapter;
    List<Item> items;
    List<Item> itemsBackUp;
    Handler handler;
    public static final int TEXTO = 1;
    public static final int IMAGEN = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        itemsBackUp = new ArrayList<>();

        RecyclerView rvPersona = (RecyclerView) super.findViewById(R.id.rvPersonas);
        handler = new Handler(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvPersona.setLayoutManager(layoutManager);

        adapter = new MyAdapter(items,this,handler);
        rvPersona.setAdapter(adapter);

        MiCatalogo catalogo = new MiCatalogo(this);
        catalogo.show(getSupportFragmentManager(),"dialogo");
    }

    public void Cargar(List<String> urls){
        this.items = new ArrayList<Item>();
        this.itemsBackUp = new ArrayList<Item>();

        adapter.items = new ArrayList<Item>();
        adapter.notifyDataSetChanged();

        for(String url: urls){
            MyThread hilo = new MyThread(handler,url,TEXTO,0);
            hilo.start();
        }
        //MyThread hilo = new MyThread(handler,url,TEXTO,0);
        //hilo.start();
    }

    @Override
    public void onItemClick(View v,int position) {
        Item item = this.items.get(position);
        Log.d("Nueva persona",item.toString());

        Intent i = new Intent(this,AnotherActivity.class);

        i.putExtra("link",item.getLink());
        i.putExtra("title",item.getTitle());
        this.startActivity(i);

    }

    @Override
    public boolean handleMessage(Message msg) {
        if(msg.arg1 == TEXTO){
            this.items = this.adapter.SetItems((List<Item>) msg.obj,false);
            adapter.notifyDataSetChanged();
            this.itemsBackUp = this.items;
        }
        else{
            this.adapter.SetImagen((byte[])msg.obj,msg.arg2);
            adapter.notifyItemChanged(msg.arg2);
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem mi = menu.findItem(R.id.opcion1);
        SearchView sv = (SearchView)mi.getActionView();
        sv.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        if(menuItem.getItemId() == R.id.opcion1){
            Log.d("Click Menu","Se hizo click en el menu "+menuItem.getTitle());
        }
        else if(menuItem.getItemId() == R.id.opcion2){
            MiCatalogo catalogo = new MiCatalogo(this);
            catalogo.show(getSupportFragmentManager(),"dialogo");
        }
        return  super.onOptionsItemSelected(menuItem);
    }

    @Override
    public boolean onQueryTextSubmit(String newText) {
        Log.d("Enter",newText);

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d("Escribo",newText);
        if(newText.length() > 3) {
            this.filtrar(newText);
            this.items = adapter.SetItems(this.items,true);
            adapter.notifyDataSetChanged();
        }
        else{
            this.items = this.itemsBackUp;
            adapter.SetItems(this.itemsBackUp,true);
            adapter.notifyDataSetChanged();
        }
        return false;
    }

    public void filtrar(String filtro){
        this.items = new ArrayList<Item>();

        for (Item item: this.itemsBackUp) {
            Log.d("Titulo",item.getTitle());
            if(item.getTitle().toLowerCase().contains(filtro.toLowerCase())){
                Log.d("Filro","oka");
                this.items.add(item);
            }
        }
    }
}
