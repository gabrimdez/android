package com.example.a16primerlayout;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //carga el boton a la izquierda
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        boton = findViewById(R.id.button);
        boton.setOnClickListener(v -> cargaLayout2());
    }


    private void cargaLayout2() {
        setContentView(R.layout.activity_main2);
        Button boton = findViewById(R.id.button);
        boton.setOnClickListener(v -> cargaLayout1());
    }

    private void cargaLayout1() {
        setContentView(R.layout.activity_main);
        Button boton = findViewById(R.id.button);
        boton.setOnClickListener(v -> cargaLayout2());
    }
}