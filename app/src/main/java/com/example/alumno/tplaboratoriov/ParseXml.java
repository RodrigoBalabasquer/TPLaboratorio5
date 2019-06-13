package com.example.alumno.tplaboratoriov;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;


import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
/**
 * Created by alumno on 02/05/2019.
 */

public class ParseXml {

    public static List<Item> parseXml(String listaItem)throws XmlPullParserException, IOException, ParseException, ParseException {
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
                            DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
                            Date date = formatter.parse(xml.nextText());

                            p.setFechaDate(date);

                            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String fechaString = f.format(date);
                            p.setFecha(fechaString);
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
