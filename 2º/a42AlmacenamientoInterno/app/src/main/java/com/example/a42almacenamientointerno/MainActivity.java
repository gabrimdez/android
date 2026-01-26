package com.example.a42almacenamientointerno;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    //data/data/com.example.a42almacenamientointerno/files/

    EditText editText;
    Button write;
    Button read;
    private final String FILENAME = "testfile.txt";


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

        read = findViewById(R.id.button2);
        write = findViewById(R.id.button);
        editText = findViewById(R.id.editTextText);

        write.setOnClickListener(v -> {
            escribir();
        });

        read.setOnClickListener(v -> {
            leer();
        });

    }

    private void leer() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //leyendo fichero
            FileInputStream fileInputStream = openFileInput(FILENAME);
            if (fileInputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String newLine = null;
                while ((newLine = bufferedReader.readLine()) != null) {
                    stringBuilder.append(newLine).append("\n");
                }
                inputStreamReader.close();
                Toast.makeText(this, "Leido correctamente", Toast.LENGTH_SHORT).show();

            }
            editText.setText(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al leer", Toast.LENGTH_SHORT).show();
        }
    }

    private void escribir() {
        String texto = editText.getText().toString();
        try {
            FileOutputStream fileOutputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            // Convertimos el String a bytes y escribimos
            fileOutputStream.write(texto.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "Guardado correctamente", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
        }
    }
}
