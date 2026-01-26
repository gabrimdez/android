package com.example.ejercicio2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    EquipoAdapter adapter;
    ImageView img;
    TextView txt;
    Button btnAddEquipo, btnDeleteEquipo;
    ArrayList<Equipo> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = findViewById(R.id.recyclerEquipos);
        img = findViewById(R.id.imgSeleccionado);
        txt = findViewById(R.id.txtSeleccionado);
        btnAddEquipo = findViewById(R.id.btnAddEquipo);
        btnDeleteEquipo = findViewById(R.id.btnDeleteEquipo);

        lista.add(new Equipo("Baskonia","Vitoria",1952,R.drawable.baskonia));
        lista.add(new Equipo("Žalgiris","Kaunas",1944,R.drawable.zalgiris));
        lista.add(new Equipo("Panathinaikos","Atenas",1922,R.drawable.panathinaikos));
        lista.add(new Equipo("Maccabi","Tel Aviv",1932,R.drawable.maccabi));
        lista.add(new Equipo("CSKA","Moscú",1923,R.drawable.cska));

        adapter = new EquipoAdapter(lista, e -> {
            img.setImageResource(e.logo);
            txt.setText(e.nombre);
        });

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        // Añadir un equipo nuevo
        btnAddEquipo.setOnClickListener(v -> {
            adapter.addEquipo(
                    new Equipo("Nuevo Equipo", "Ciudad X", 2025, R.drawable.baskonia) // pon un logo por defecto que tengas
            );
        });

        // Borrar el último equipo
        btnDeleteEquipo.setOnClickListener(v -> {
            adapter.removeLast();
        });
    }
}
