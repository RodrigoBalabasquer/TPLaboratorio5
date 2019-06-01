package com.example.alumno.tplaboratoriov;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class AnotherActivity extends AppCompatActivity implements  View.OnClickListener {

    Button b = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("asdda","Se hizo click en el menu ");
        setContentView(R.layout.activity_another);
        Intent i = getIntent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(i.getStringExtra("title"));
        actionBar.setDisplayHomeAsUpEnabled(true);

        //b = (Button) this.findViewById(R.id.btn);
        //b.setOnClickListener(this);
        //b.setText(i.getStringExtra("link"));
        WebView wv = (WebView)super.findViewById(R.id.web);
        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);

        wv.loadUrl(i.getStringExtra("link"));
    }
    @Override
    public void onClick(View v) {
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu2,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        if(menuItem.getItemId() == R.id.opcion1){
            Log.d("Click Menu","Se hizo click en el menu "+menuItem.getTitle());
        }
        else  if(menuItem.getItemId() == android.R.id.home){
            this.finish();
        }
        return  super.onOptionsItemSelected(menuItem);
    }
}
