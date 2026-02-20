package com.example.a51brsmsdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    EditText etNumero, etMensaje;
    Button btnEnviar;
    TextView tvRecibido;

    private static final int SMS_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etNumero = findViewById(R.id.etNumero);
        etMensaje = findViewById(R.id.etMensaje);
        btnEnviar = findViewById(R.id.btnEnviar);
        tvRecibido = findViewById(R.id.tvRecibido);

        pedirPermisos();
        btnEnviar.setOnClickListener(v -> enviarSMS());

        // Por si la activity se abre desde el receptor
        procesarIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        procesarIntent(intent);
    }

    private void procesarIntent(Intent intent) {
        if (intent != null && intent.hasExtra("remitente")) {
            String remitente = intent.getStringExtra("remitente");
            String mensaje = intent.getStringExtra("mensaje");

            tvRecibido.setText(
                    "De: " + remitente + "\nMensaje: " + mensaje
            );
        }
    }

    private void pedirPermisos() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            Manifest.permission.SEND_SMS,
                            Manifest.permission.RECEIVE_SMS
                    },
                    SMS_PERMISSION_CODE
            );
        }
    }

    private void enviarSMS() {
        String numero = etNumero.getText().toString();
        String mensaje = etMensaje.getText().toString();

        if (numero.isEmpty() || mensaje.isEmpty()) {
            Toast.makeText(this, "Completa número y mensaje", Toast.LENGTH_SHORT).show();
            return;
        }

        SmsManager.getDefault()
                .sendTextMessage(numero, null, mensaje, null, null);

        Toast.makeText(this, "SMS enviado", Toast.LENGTH_SHORT).show();
    }
}
