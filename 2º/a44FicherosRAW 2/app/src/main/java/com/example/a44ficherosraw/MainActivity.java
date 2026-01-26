package com.example.a44ficherosraw;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configuración Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setContentView(R.layout.activity_main);

        // TextView para el recurso RAW
        TextView textViewRaw = findViewById(R.id.textViewRaw);
        InputStream rawStream = this.getResources().openRawResource(R.raw.raw_text);
        textViewRaw.setText(getText(rawStream));

        // TextView para el recurso Asset
        TextView textViewAsset = findViewById(R.id.textViewAsset);
        try {
            InputStream assetStream = this.getAssets().open("asset_text.txt");
            textViewAsset.setText(getText(assetStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para leer InputStream y devolver String
    private String getText(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (inputStream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append(System.lineSeparator());
                }
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
