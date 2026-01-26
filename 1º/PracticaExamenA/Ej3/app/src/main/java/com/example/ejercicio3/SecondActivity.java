package com.example.ejercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class SecondActivity extends AppCompatActivity {

    TextView txtYardas, txtPies, txtMetros;
    Button btnConvertir, btnRegresar;

    int yardas, pies;
    Double resultadoMetros = null; // null = no calculado todavía

    private static final double METROS_POR_YARDA = 0.91440;
    private static final double METROS_POR_PIE = 0.30480;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtYardas = findViewById(R.id.txtYardas);
        txtPies = findViewById(R.id.txtPies);
        txtMetros = findViewById(R.id.txtMetros);
        btnConvertir = findViewById(R.id.btnConvertir);
        btnRegresar = findViewById(R.id.btnRegresar);

        // Recoger los extras (si no vienen, usar 0)
        yardas = getIntent().getIntExtra("yardas", 0);
        pies = getIntent().getIntExtra("pies", 0);

        txtYardas.setText("Yardas: " + yardas);
        txtPies.setText("Pies: " + pies);

        btnConvertir.setOnClickListener(v -> {
            convertir();
        });

        btnRegresar.setOnClickListener(v -> {
            // Si no se ha convertido, convertir ahora (evita devolver texto vacío)
            if (resultadoMetros == null) {
                convertir();
            }

            if (resultadoMetros != null) {
                Intent data = new Intent();
                data.putExtra("resultado_double", resultadoMetros);
                // también añadimos una cadena legible
                String cadena = String.format(Locale.US, "%d yardas y %d pies son %.4f metros.", yardas, pies, resultadoMetros);
                data.putExtra("resultado_string", cadena);

                setResult(RESULT_OK, data);
            } else {
                // en caso improbable de fallo
                setResult(RESULT_CANCELED);
            }
            finish();
        });
    }

    private void convertir() {
        // Validaciones por seguridad (pies entre 0 y 2)
        if (pies < 0 || pies > 2) {
            Toast.makeText(this, "Valor de pies inválido (0–2)", Toast.LENGTH_SHORT).show();
            return;
        }
        if (yardas < 0) {
            Toast.makeText(this, "Valor de yardas inválido (>=0)", Toast.LENGTH_SHORT).show();
            return;
        }

        double metrosYardas = yardas * METROS_POR_YARDA;
        double metrosPies = pies * METROS_POR_PIE;
        resultadoMetros = metrosYardas + metrosPies;

        // Texto con 4 decimales (p. ej. 5,1816 -> 5.1816)
        String texto = String.format(Locale.US, "%d yardas y %d pies son %.4f metros.", yardas, pies, resultadoMetros);
        txtMetros.setText(texto);
    }
}
