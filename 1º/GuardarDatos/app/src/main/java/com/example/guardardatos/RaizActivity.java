package com.example.guardardatos;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RaizActivity extends AppCompatActivity {

    TextView raiz;
    Button cerrar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raiz);

        raiz = findViewById(R.id.res_raiz);
        Integer recibo = getIntent().getIntExtra("dato", 0);
        raiz.setText(String.valueOf(Math.sqrt(recibo)));

        cerrar = findViewById(R.id.button);
        cerrar.setOnClickListener(v -> finish());


        cerrar = findViewById(R.id.button);
        cerrar.setOnClickListener(v -> finish());

    }
}
