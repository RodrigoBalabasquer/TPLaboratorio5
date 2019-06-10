package com.example.alumno.tplaboratoriov;

import java.util.Date;

/**
 * Created by alumno on 02/05/2019.
 */

public class Item {
    private  String title;
    private  String description;
    private  String image;
    private  String link;
    private boolean procesar;
    private byte[] imagenValue;
    private String fecha;
    public Item(){
        this.procesar = false;
    }

    public Item(String title, String description, String image,String link,String fecha){
        this.title = title;
        this.description = description;
        this.image = image;
        this.link = link;
        this.procesar = false;
        this.fecha = fecha;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setProcesar(boolean procesar) {
        this.procesar = procesar;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setImagenValue(byte[] imagenValue) {
        this.imagenValue = imagenValue;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

    public boolean getProcesar() {
        return procesar;
    }

    public byte[] getImagenValue() {
        return imagenValue;
    }

    public String getFecha() { return  fecha;}
}
