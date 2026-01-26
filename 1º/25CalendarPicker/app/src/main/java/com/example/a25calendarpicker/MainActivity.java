package com.example.a25calendarpicker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    CalendarView calendarView;
    EditText editTextdata;
    Button button;
    EditText editTextText;

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

        calendarView = findViewById(R.id.calendarView);
        editTextdata = findViewById(R.id.editTextDate);
        editTextText = findViewById(R.id.editTextText);
        button = findViewById(R.id.button);
        calendarView.setOnDateChangeListener((view, year, month, daOfMonth) -> {
            String date = daOfMonth + "/" + (month + 1) + "/" + year;
            editTextdata.setText(date);
        });

        button.setOnClickListener(v -> {
            String date = editTextText.getText().toString();
            editTextdata.setText(date);
            String[] parts = date.split("/");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date parseDate;
            try {
                parseDate = sdf.parse(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            calendarView.setDate(parseDate.getTime());
        });
    }
}