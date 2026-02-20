package com.example.ejercicio2b;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.*;

public class MainActivity extends AppCompatActivity {

    EditText etTexto;
    Button btnLeer, btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTexto = findViewById(R.id.etTexto);
        btnLeer = findViewById(R.id.btnLeer);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Permiso almacenamiento
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);

        btnLeer.setOnClickListener(v -> leerRaw());
        btnGuardar.setOnClickListener(v -> guardarExterno());
    }

    // 📖 LEER grupoB.txt desde RAW
    private void leerRaw() {
        try {
            InputStream is = getResources().openRawResource(R.raw.grupob);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder texto = new StringBuilder();
            String linea;

            while ((linea = br.readLine()) != null) {
                texto.append(linea).append("\n");
            }

            br.close();
            etTexto.setText(texto.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 💾 GUARDAR EN ALMACENAMIENTO EXTERNO
    private void guardarExterno() {
        try {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            File carpeta = new File(Environment.getExternalStorageDirectory(), "Mis Textos");
            if (!carpeta.exists()) carpeta.mkdirs();

            File fichero = new File(carpeta, "Bgrupo.txt");

            FileOutputStream fos = new FileOutputStream(fichero);
            fos.write(etTexto.getText().toString().getBytes());
            fos.close();

            Toast.makeText(this, "Guardado en: " + fichero.getAbsolutePath(), Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}