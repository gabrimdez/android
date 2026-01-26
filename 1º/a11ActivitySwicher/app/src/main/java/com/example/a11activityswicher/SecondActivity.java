package com.example.a11activityswicher;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    Button button;
    TextView textView1;
    TextView textView2;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        button = findViewById(R.id.button);
        textView1 = findViewById(R.id.textView3);
        textView2 = findViewById(R.id.textView4);

        Intent intent = getIntent();
        Integer num1 = intent.getIntExtra("num1",0);
        Integer num2 = intent.getIntExtra("num2",0);
        textView1.setText(String.valueOf(num1));
        textView2.setText(String.valueOf(num2));


        button.setOnClickListener(v -> cambioSecond(num1,num2));
    }

    private void cambioSecond(int num1, int num2){
        Intent intent = new Intent(this, MainActivity.class);

        Integer suma = num1 +num2;
        intent.putExtra("suma",suma);
        startActivity(intent);
    }


}
