package com.example.alumno.tplaboratoriov;

import android.app.Activity;
import android.util.Log;
import android.view.View;

/**
 * Created by alumno on 06/06/2019.
 */

public class MyListenerView implements View.OnClickListener {
    public MainActivity activity;
    public MyListenerView(MainActivity activity){
        this.activity = activity;
    }
    @Override
    public void onClick(View v) {
        /*if(v.getId() == R.id.cat1){
            this.activity.Cambiar("https://tn.com.ar/rss.xml",ENoticias.TN);
            Log.d("Catalogo", "1");
        }
        if(v.getId() == R.id.cat2){
            this.activity.Cambiar("https://www.clarin.com/rss/lo-ultimo/",ENoticias.CLARIN);
            Log.d("Catalogo", "2");
        }*/
    }
}
