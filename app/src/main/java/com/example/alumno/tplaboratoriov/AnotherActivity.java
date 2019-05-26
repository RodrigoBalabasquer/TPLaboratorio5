package com.example.alumno.tplaboratoriov;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class AnotherActivity extends AppCompatActivity implements  View.OnClickListener {

    Button b = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        b = (Button) this.findViewById(R.id.btn);
        b.setOnClickListener(this);

        Intent i = getIntent();
        b.setText(i.getStringExtra("link"));
    }
    @Override
    public void onClick(View v) {
        this.finish();
    }
}
