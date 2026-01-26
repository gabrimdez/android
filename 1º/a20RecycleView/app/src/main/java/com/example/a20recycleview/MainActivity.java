package com.example.a20recycleview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    com.example.a20recycleview.MiAdaptador miAdaptador;
    ArrayList<String> nameList;

    EditText editTextPais;
    Button buttonAgregar, buttonBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nameList = PaisRepositorio.obtenerPaises();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        miAdaptador = new MiAdaptador(nameList);
        recyclerView.setAdapter(miAdaptador);

        editTextPais = findViewById(R.id.editTextPais);
        buttonAgregar = findViewById(R.id.buttonAgregar);

        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevoPais = editTextPais.getText().toString().trim();
                if (nuevoPais.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Ingrese el nombre de un país", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (nameList.contains(nuevoPais)) {
                    Toast.makeText(MainActivity.this, "El país ya está en la lista", Toast.LENGTH_SHORT).show();
                    return;
                }
                nameList.add(nuevoPais);
                miAdaptador.notifyDataSetChanged();
                editTextPais.setText("");
            }
        });

    }
}