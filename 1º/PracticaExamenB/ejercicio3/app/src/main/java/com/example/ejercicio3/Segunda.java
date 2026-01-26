package com.example.ejercicio3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Segunda extends AppCompatActivity {

    int gal, pin;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.segunda);

        TextView t1 = findViewById(R.id.txtGal);
        TextView t2 = findViewById(R.id.txtPin);
        Button conv = findViewById(R.id.btnConvertir);
        Button vol = findViewById(R.id.btnVolver);

        gal = getIntent().getIntExtra("gal", 0);
        pin = getIntent().getIntExtra("pin", 0);

        t1.setText("Galones: " + gal);
        t2.setText("Pintas: " + pin);

        conv.setOnClickListener(v -> {
            double res = gal * 4.5461 + pin * 0.5863;
            Intent i = new Intent();
            i.putExtra("litros", res);
            setResult(RESULT_OK, i);
            finish();
        });

        vol.setOnClickListener(v -> finish());
    }
}
