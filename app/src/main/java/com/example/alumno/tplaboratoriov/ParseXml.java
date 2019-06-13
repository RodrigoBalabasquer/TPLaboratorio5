package com.example.alumno.tplaboratoriov;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.text.SimpleDateFormat;
/**
 * Created by alumno on 02/05/2019.
 */

public class ParseXml {

    public static List<Item> parseXml(String listaItem) {
        List<Item> ListaItem = new ArrayList<>();
        XmlPullParser xml = Xml.newPullParser();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            xml.setInput(new StringReader(listaItem));
            Item p = null;
            int event = xml.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {

                if (event == XmlPullParser.START_TAG) {
                    if("item".equals(xml.getName()))//obtenemos el nombre del tag sobre el cual estamos parado
                    {
                        p = new Item();
                    }
                    if("title".equals(xml.getName())){
                        if(p != null){
                            p.setTitle(xml.nextText());
                        }
                    }
                    if("pubDate".equals(xml.getName())){
                        if(p != null){
                            String[] fechaArray = xml.nextText().split(" ");
                            int mont = 0;
                            switch (fechaArray[2]){
                                case "Jan":
                                    mont = 0;
                                    break;
                                case "Feb":
                                    mont = 1;
                                    break;
                                case "Mar":
                                    mont = 2;
                                    break;
                                case "Apr":
                                    mont = 3;
                                    break;
                                case "May":
                                    mont = 4;
                                    break;
                                case "Jun":
                                    mont = 5;
                                    break;
                                case "Jul":
                                    mont = 6;
                                    break;
                                case "Aug":
                                    mont = 7;
                                    break;
                                case "Sep":
                                    mont = 8;
                                    break;
                                case "Oct":
                                    mont = 9;
                                    break;
                                case "Nov":
                                    mont = 10;
                                    break;
                                case "Dec":
                                    mont = 11;
                                    break;
                            }
                            String[] hora = fechaArray[4].split(":");
                            Date fecha = new Date(Integer.parseInt(fechaArray[3]),mont,Integer.parseInt(fechaArray[1])
                                    ,Integer.parseInt(hora[0]),Integer.parseInt(hora[1]),Integer.parseInt(hora[2]));
                            p.setFechaDate(fecha);
                            //p.setFecha(format.format(fecha));
                            p.setFecha(fecha.getYear()+"-"+(fecha.getMonth()+1)+"-"+fechaArray[1]+" "+fecha.getHours()+":"+fecha.getMinutes()+":"+fecha.getSeconds());
                        }
                    }
                    if("description".equals(xml.getName())){
                        if(p != null){
                            p.setDescription(xml.nextText());
                        }
                    }
                    if("link".equals(xml.getName())){
                        if(p != null){
                            p.setLink(xml.nextText());
                        }
                    }
                    if("image".equals(xml.getName())){
                        if(p != null){
                            p.setImage(xml.nextText());
                        }
                    }
                    if("enclosure".equals(xml.getName())){
                        if(p != null){
                            p.setImage((String) xml.getAttributeValue(null,"url"));
                        }
                    }
                }
                else if(event == XmlPullParser.END_TAG && "item".equals(xml.getName())){
                    ListaItem.add(p);
                }

                event = xml.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  ListaItem;
    }

}
