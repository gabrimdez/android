package com.example.ejercicio3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtYardas, edtPies;
    Button btnEnviar;
    TextView txtResultado;

    private static final int REQ_CODE_SECOND = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtYardas = findViewById(R.id.edtYardas);
        edtPies = findViewById(R.id.edtPies);
        btnEnviar = findViewById(R.id.btnEnviar);
        txtResultado = findViewById(R.id.txtResultado);

        btnEnviar.setOnClickListener(v -> {
            String sY = edtYardas.getText().toString().trim();
            String sP = edtPies.getText().toString().trim();

            if (sY.isEmpty() || sP.isEmpty()) {
                Toast.makeText(this, "Introduce ambos valores", Toast.LENGTH_SHORT).show();
                return;
            }

            int yardas;
            int pies;
            try {
                yardas = Integer.parseInt(sY);
                pies = Integer.parseInt(sP);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Introduce números enteros válidos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (yardas < 0 || pies < 0) {
                Toast.makeText(this, "Los valores no pueden ser negativos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (pies > 2) {
                Toast.makeText(this, "Los pies no pueden ser mayores que 2", Toast.LENGTH_LONG).show();
                return;
            }

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("yardas", yardas);
            intent.putExtra("pies", pies);

            // startActivityForResult sigue funcionando; si usas API moderna puedes migrar a ActivityResult
            startActivityForResult(intent, REQ_CODE_SECOND);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE_SECOND) {
            if (resultCode == RESULT_OK && data != null) {
                // Intent puede traer tanto string legible como double numérico
                if (data.hasExtra("resultado_double")) {
                    double res = data.getDoubleExtra("resultado_double", Double.NaN);
                    // mostramos con 4 decimales
                    txtResultado.setText(String.format("Resultado: %.4f m", res));
                } else if (data.hasExtra("resultado_string")) {
                    String s = data.getStringExtra("resultado_string");
                    txtResultado.setText(s);
                } else {
                    txtResultado.setText("No hay resultado retornado");
                }
            } else {
                // El usuario canceló o no hubo resultado
                Toast.makeText(this, "Operación cancelada o sin resultado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
