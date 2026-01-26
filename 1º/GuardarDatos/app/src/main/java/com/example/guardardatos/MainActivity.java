package com.example.guardardatos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView resultado;
    EditText numeroA;
    EditText numeroB;
    Button sumar,raiz;




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

        numeroA = findViewById(R.id.number_a_input);
        numeroB = findViewById(R.id.number_b_input);
        resultado = findViewById(R.id.sum_label);
        sumar = findViewById(R.id.add_button);
        raiz = findViewById(R.id.get_root_button);

        sumar.setOnClickListener(v -> sumar());

        raiz.setOnClickListener(v -> raiz());

    }

    private void raiz() {
        Integer dato = Integer.parseInt(resultado.getText().toString());
        Intent intent = new Intent(this, RaizActivity.class);
        intent.putExtra("dato",dato);
        startActivity(intent);
    }

    private void sumar() {
        Integer sum = Integer.parseInt(numeroA.getText().toString()) +
                Integer.parseInt(numeroB.getText().toString());
        resultado.setText(String.valueOf(sum));
    }
}