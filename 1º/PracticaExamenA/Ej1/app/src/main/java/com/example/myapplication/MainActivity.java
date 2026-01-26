package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private Button btnReset;
    private ArrayList<Button> buttonList = new ArrayList<>();
    private final int ROWS = 7;
    private final int COLS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridLayout);
        btnReset = findViewById(R.id.btnReset);

        createButtons();
        fillRandomNumbers();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillRandomNumbers();
            }
        });
    }

    private void createButtons() {
        for (int i = 0; i < ROWS * COLS; i++) {
            Button b = new Button(this);
            b.setTextSize(18f);

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Button) v).setText("X");
                }
            });

            buttonList.add(b);
            gridLayout.addView(b);
        }
    }

    private void fillRandomNumbers() {
        for (Button b : buttonList) {
            int randomNum = NumberGenerator.randomBetween1and99();
            b.setText(String.valueOf(randomNum));
        }
    }
}
