package com.example.a40preferencias;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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

    private final String NAME="NAME";
    private EditText mEditTextName;

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


        TextView textView = (TextView) findViewById(R.id.textView);


        button = findViewById(R.id.button);
        mEditTextName = findViewById(R.id.editTextName);

        ponNombre();


        button.setOnClickListener(v -> {
            String nombre = mEditTextName.getText().toString();

            SharedPreferences preferencias =  getSharedPreferences("preferencias",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();

            editor.putString("nombre", nombre);
            editor.apply();
            Toast.makeText(this, "Preferencias guardadas", Toast.LENGTH_SHORT).show();
        });

    }

    public void ponNombre() {
        SharedPreferences preferences = getSharedPreferences("preferencias", MODE_PRIVATE);
        String recupera = preferences.getString("nombre", "");
        mEditTextName.setHint(recupera);
    }
}