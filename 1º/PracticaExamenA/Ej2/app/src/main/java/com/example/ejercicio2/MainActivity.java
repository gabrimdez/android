package com.example.ejercicio2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    CountryAdapter adapter;
    ImageView imgSelectedFlag;
    TextView txtSelectedName;
    Button btnAdd, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = findViewById(R.id.recyclerCountries);
        imgSelectedFlag = findViewById(R.id.imgSelectedFlag);
        txtSelectedName = findViewById(R.id.txtSelectedName);

        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);

        ArrayList<Country> list = new ArrayList<>();
        list.add(new Country("Argentina","Buenos Aires","43.800.000",R.drawable.argentina));
        list.add(new Country("Brasil","Brasilia","208.500.000", R.drawable.brasil));
        list.add(new Country("Uruguay","Montevideo","3.445.000",R.drawable.uruguay));
        list.add(new Country("Colombia","Bogotá","49.590.000",R.drawable.colombia));

        adapter = new CountryAdapter(list, c -> {
            txtSelectedName.setText(c.getName());
            imgSelectedFlag.setImageResource(c.getFlagRes());
        });

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            adapter.addCountry(new Country("Nuevo País", "Capital X", "1M", R.drawable.default_flag));
        });

        btnDelete.setOnClickListener(v -> {
            adapter.removeLast();
        });
    }
}
