package com.example.a21rviewcomplejo;

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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MiAdaptador miAdaptador;
    List<ItemData> nameList;

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

        nameList = DataRepository.obtenerAvatar();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        miAdaptador = new MiAdaptador(nameList);
        recyclerView.setAdapter(miAdaptador);

        editTextPais = findViewById(R.id.editTextPais);
        buttonAgregar = findViewById(R.id.buttonAgregar);

        buttonAgregar.setOnClickListener(v ->  {
            ItemData name = new ItemData("Avatar ADD","Descripcion ADD",R.drawable.avatar_11);
            nameList.add(name);

        });

    }
}