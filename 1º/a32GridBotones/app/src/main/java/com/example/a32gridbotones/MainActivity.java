package com.example.a32gridbotones;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.gridlayout.widget.GridLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    GridLayout grid;


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

        grid = findViewById(R.id.grid);
        populateButtons();


    }


    public void populateButtons() {

        final int FILAS = 6;
        final int COLUMNAS = 3;
        final int NUM_BOTONES = FILAS * COLUMNAS;

        grid.setRowCount(FILAS);
        grid.setColumnCount(COLUMNAS);
        Button button;

        for (int i = 0; i < NUM_BOTONES; i++) {
            button = new Button(this);

            if (i != 17) {
                button.setText("Boton " + (i + 1));
                button.setOnClickListener(v -> changeColor(v));
            } else {
                button.setText("Setear Botones");
                button.setOnClickListener(v -> resetButtons(v));
            }

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();

            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);

            params.width = 0;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;

            button.setLayoutParams(params);

            grid.addView(button);

        }
    }

    private void changeColor(View view) {
        Button button = (Button) view;
        button.setBackgroundColor(randomero());
    }

    private int randomero() {
        Random random = new Random();

        int rojo = random.nextInt(256);
        int verde = random.nextInt(256);
        int azul = random.nextInt(256);
        return Color.rgb(rojo, verde, azul);
    }

    private void resetButtons( View view) {
        grid.removeAllViews();
        populateButtons();
    }

}