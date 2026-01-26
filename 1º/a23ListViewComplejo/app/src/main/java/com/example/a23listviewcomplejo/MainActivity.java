package com.example.a23listviewcomplejo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ListView listVIew;
    Button button;

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

        listVIew = findViewById(R.id.listView);
        button = findViewById(R.id.button);

        EquipoRepository equipoRepository = new EquipoRepository();
        AdaptadorEquipo adaptadorEquipo =
                new AdaptadorEquipo(this,R.layout.equipo,equipoRepository.getLista());
        listVIew.setAdapter(adaptadorEquipo);
        button.setOnClickListener(view -> {
            TextView nombre = findViewById(R.id.nombre);
            TextView ciudad = findViewById(R.id.ciudad);
            TextView pais = findViewById(R.id.pais);
            TextView fundacion = findViewById(R.id.fundacion);
            ImageView imagen = findViewById(R.id.imageView);
            Equipo equipo = new Equipo(nombre.getText().toString(),
                    ciudad.getText().toString(),
                    pais.getText().toString() ,
                    Integer.parseInt(fundacion.getText().toString()),
                    R.drawable.avatar_7);
            adaptadorEquipo.addEquipo(equipo);
        });

    }
}