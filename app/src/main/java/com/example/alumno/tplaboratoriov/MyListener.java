package com.example.alumno.tplaboratoriov;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alumno on 06/06/2019.
 */

public class MyListener implements DialogInterface.OnClickListener {
    public MainActivity activity;
    public List<CheckBox> opciones;

    public MyListener(MainActivity a, List<CheckBox> o){
        this.activity = a;
        this.opciones = o;
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {
        SharedPreferences prefs = this.activity.getSharedPreferences("catalogo", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = prefs.edit();

        if(which == -1){
            List<String> urls = new ArrayList<String>();
            for(CheckBox ch : this.opciones){
                if(ch.isChecked()){
                    Log.d("Check", (String)ch.getText());
                    urls.add((String) ch.getText());
                    editor.putBoolean((String)ch.getText(),ch.isChecked());
                }
                else{
                    editor.putBoolean((String)ch.getText(),ch.isChecked());
                }
            }
            if(urls.size() > 0)
                this.activity.Cargar(urls);
            editor.commit();
        }
        //Log.d("Click","Click en "+which);
    }
}
