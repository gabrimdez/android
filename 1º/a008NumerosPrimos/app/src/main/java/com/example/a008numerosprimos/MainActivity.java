package com.example.a008numerosprimos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //globals
    EditText editTextNumber;
    Button button;
    TextView textView;


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

        
        editTextNumber = findViewById(R.id.editTextNumber);

        button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Integer ordinal = Integer.parseInt(editTextNumber.getText().toString());
            if (ordinal <= 1000) {
                calcular(Integer.parseInt(editTextNumber.getText().toString()));
            } else {
                Toast.makeText(this, "NO PUEDO CALCULAR EL NUMERO PRIMO PARA DICHO ORDINAL", Toast.LENGTH_SHORT).show();
                editTextNumber.setText("0");
            }
        });


        textView = findViewById(R.id.resultado);

    }


    public void calcular(Integer ordinal) {
        if (ordinal <= 0) {
            throw new IllegalArgumentException("La posición debe ser un entero positivo.");
        }
        int contador = 0;
        int numero = 2;
        while (contador < ordinal) {
            if (esPrimo(numero)) {
                contador++;
            }
            numero++;
            //Toast.makeText(this,"Numero: "+numero,Toast.LENGTH_LONG).show();
        }
        textView.setText(Integer.toString(numero));
    }

    public boolean esPrimo(int numero) {
        if (numero <= 1) return false;
        for (int i = 2; i <= numero / 2; i++) {
            if (numero % i == 0) return false;
        }
        return true;
    }
}