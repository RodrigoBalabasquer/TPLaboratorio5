package com.example.alumno.tplaboratoriov;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.*;

/**
 * Created by alumno on 06/06/2019.
 */

public class MiCatalogo extends DialogFragment {
    public MainActivity activity;
    public MiCatalogo(){

    }
    @SuppressLint("ValidFragment")
    public MiCatalogo(MainActivity activity){

        this.activity = activity;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater li = LayoutInflater.from(this.getContext());
        View v = li.inflate(R.layout.dialog,null);
        //CheckBox TN1 = (CheckBox) v.findViewById(R.id.TN1);
        //CheckBox CL1 = (CheckBox) v.findViewById(R.id.CL1);
        List<CheckBox> opciones = new ArrayList<CheckBox>();

        SharedPreferences prefs = this.activity.getSharedPreferences("catalogo", Context.MODE_PRIVATE);

        //TN1.setChecked(prefs.getBoolean((String)TN1.getText(),false));
        //CL1.setChecked(prefs.getBoolean((String)CL1.getText(),false));
        for (View vi : v.getTouchables()){
            CheckBox cb = (CheckBox)vi;
            cb.setChecked(prefs.getBoolean((String)cb.getText(),false));
            opciones.add(cb);
        }


        //opciones.add(TN1);
        //opciones.add(CL1);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Catalogos");
        MyListener ml = new MyListener(this.activity,opciones);
        //MyListenerView mlv = new MyListenerView(this.activity);
        //CAT1.setOnClickListener(mlv);
        //CAT2.setOnClickListener(mlv);
        builder.setView(v);

        //builder.setMessage("Mis catalogos");
        builder.setPositiveButton("Traer",ml);
        builder.setNegativeButton("Cerrar",ml);
        //builder.setNeutralButton("---",ml);
        return builder.create();
    }
}
