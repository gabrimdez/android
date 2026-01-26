package com.example.ejercicio3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText g, p;
    Button enviar;
    TextView txt;

    private final ActivityResultLauncher<Intent> resultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            double litros = result.getData().getDoubleExtra("litros", 0);
                            txt.setText("Resultado: " + litros);
                        }
                    });

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        g = findViewById(R.id.edtGal);
        p = findViewById(R.id.edtPin);
        enviar = findViewById(R.id.btnEnviar);
        txt = findViewById(R.id.txtResultado);

        enviar.setOnClickListener(v -> {
            String sGal = g.getText().toString().trim();
            String sPin = p.getText().toString().trim();

            if (sGal.isEmpty() || sPin.isEmpty()) {
                Toast.makeText(this, "Completa ambos campos", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int gal = Integer.parseInt(sGal);
                int pin = Integer.parseInt(sPin);

                Intent i = new Intent(this, Segunda.class);
                i.putExtra("gal", gal);
                i.putExtra("pin", pin);
                resultLauncher.launch(i);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Solo números válidos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
