package com.example.ejercicio1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button[] botones = new Button[20];
    Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout grid = findViewById(R.id.gridLayout1);

        for (int i = 0; i < 20; i++) {
            Button b = new Button(this);
            char c = (char) ('A' + r.nextInt(26));
            b.setText(String.valueOf(c));
            int index = i;
            b.setOnClickListener(v -> b.setText("67 X"));
            grid.addView(b);
            botones[i] = b;
        }

        Button reset = findViewById(R.id.btnReset);
        reset.setOnClickListener(v -> {
            for (Button b : botones) {
                char c = (char) ('A' + r.nextInt(26));
                b.setText(String.valueOf(c));
            }
        });
    }
}
