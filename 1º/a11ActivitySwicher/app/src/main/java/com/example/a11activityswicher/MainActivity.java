package com.example.a11activityswicher;

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

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editText1;
    EditText editText2;
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

        button = findViewById(R.id.button);
        editText1 = findViewById(R.id.editTextNumber);
        editText2 = findViewById(R.id.editTextNumber3);


        button.setOnClickListener(v -> cambio());

        Intent intent = getIntent();
        Integer suma = intent.getIntExtra("suma", 0);
        textView = findViewById(R.id.resultado);
        textView.setText(String.valueOf(suma));
    }

    private void cambio() {
        Intent intent = new Intent(this, SecondActivity.class);

        if (editText1.getText().toString().isEmpty())
            editText1.setText("0");
        if (editText2.getText().toString().isEmpty())
            editText2.setText("0");
        Integer num1 = Integer.parseInt(editText1.getText().toString());
        Integer num2 = Integer.parseInt(editText2.getText().toString());


        intent.putExtra("num1", num1);
        intent.putExtra("num2", num2);

        startActivity(intent);

    }
}
