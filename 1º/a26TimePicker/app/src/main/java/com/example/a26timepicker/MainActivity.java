package com.example.a26timepicker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TimePicker timePicker;
    TextView textView;
    TextClock textClock;
    Button button;
    EditText editText;

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

        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        textClock = findViewById(R.id.textClock);
        textView = findViewById(R.id.getTime);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                String time = String.format("%02d:%02d", hourOfDay, minute);
                textView.setText(time);
                textClock.setText(time);
            }
        });
        editText = findViewById(R.id.editTextTime);
        button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            String time = editText.getText().toString();
            textView.setText(time);
            textClock.setText(time);

        });

    }
}