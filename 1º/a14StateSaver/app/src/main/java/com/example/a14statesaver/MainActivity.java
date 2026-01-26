package com.example.a14statesaver;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    static final String KEY_COUNTER = "COUNTER";
    private int mCounter = 0;
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

        if (savedInstanceState != null) {
            mCounter = savedInstanceState.getInt(KEY_COUNTER);
            ((TextView) findViewById(R.id.textViewCounter)).setText("Contador: " + mCounter);
        }

        button = findViewById(R.id.button);

        button.setOnClickListener(v -> onClickCounter(v));
    }

    public void onClickCounter(View view) {
        mCounter++;
        ((TextView) findViewById(R.id.textViewCounter)).setText("Contador: " + Integer.toString(mCounter));
    }
    /*
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNTER,mCounter);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCounter = savedInstanceState.getInt(KEY_COUNTER);
    }
     */
}
