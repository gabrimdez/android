package com.example.a48misdiscos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etNombre, etArtista, etGenero, etDuracion, etAnio;
    Button btnAnadir, btnModificar, btnBorrar;
    RecyclerView recyclerViewMusica;

    MusicaDbHelper dbHelper;
    ArrayList<Disco> listaDiscos;
    DiscoAdapter adapter;

    int idSeleccionado = -1; // Para modificar/borrar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Referencias UI
        etNombre = findViewById(R.id.etNombre);
        etArtista = findViewById(R.id.etArtista);
        etGenero = findViewById(R.id.etGenero);
        etDuracion = findViewById(R.id.etDuracion);
        etAnio = findViewById(R.id.etAnio);

        btnAnadir = findViewById(R.id.btnAnadir);
        btnModificar = findViewById(R.id.btnModificar);
        btnBorrar = findViewById(R.id.btnBorrar);

        recyclerViewMusica = findViewById(R.id.recyclerViewMusica);
        recyclerViewMusica.setLayoutManager(new LinearLayoutManager(this));

        // BD y lista
        dbHelper = new MusicaDbHelper(this);
        listaDiscos = new ArrayList<>();

        // Adapter con listener
        adapter = new DiscoAdapter(listaDiscos, disco -> {
            idSeleccionado = disco.getId();
            etNombre.setText(disco.getNombre());
            etArtista.setText(disco.getArtista());
            etGenero.setText(disco.getGenero());
            etDuracion.setText(String.valueOf(disco.getDuracion()));
            etAnio.setText(String.valueOf(disco.getAnio()));
        });
        recyclerViewMusica.setAdapter(adapter);

        cargarLista();

        // Botón Añadir
        btnAnadir.setOnClickListener(v -> {
            insertarDisco();
            cargarLista();
        });

        // Botón Modificar
        btnModificar.setOnClickListener(v -> {
            if (idSeleccionado == -1) {
                Toast.makeText(this, "Selecciona un disco primero", Toast.LENGTH_SHORT).show();
                return;
            }

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(MusicaContrato.COL_NOMBRE, etNombre.getText().toString());
            values.put(MusicaContrato.COL_ARTISTA, etArtista.getText().toString());
            values.put(MusicaContrato.COL_GENERO, etGenero.getText().toString());
            values.put(MusicaContrato.COL_DURACION, Integer.parseInt(etDuracion.getText().toString()));
            values.put(MusicaContrato.COL_ANIO, Integer.parseInt(etAnio.getText().toString()));

            db.update(
                    MusicaContrato.NOMBRE_TABLA,
                    values,
                    MusicaContrato.COL_ID + "=?",
                    new String[]{String.valueOf(idSeleccionado)}
            );

            idSeleccionado = -1;
            limpiarCampos();
            cargarLista();
        });

        // Botón Borrar
        btnBorrar.setOnClickListener(v -> {
            if (idSeleccionado == -1) {
                Toast.makeText(this, "Selecciona un disco primero", Toast.LENGTH_SHORT).show();
                return;
            }

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.delete(
                    MusicaContrato.NOMBRE_TABLA,
                    MusicaContrato.COL_ID + "=?",
                    new String[]{String.valueOf(idSeleccionado)}
            );

            idSeleccionado = -1;
            limpiarCampos();
            cargarLista();
        });
    }

    private void insertarDisco() {
        if (etNombre.getText().toString().isEmpty()) {
            Toast.makeText(this, "El nombre es obligatorio", Toast.LENGTH_SHORT).show();
            return;
        }

        dbHelper.insertarCancion(
                etNombre.getText().toString(),
                etArtista.getText().toString(),
                etGenero.getText().toString(),
                Integer.parseInt(etDuracion.getText().toString()),
                Integer.parseInt(etAnio.getText().toString())
        );

        limpiarCampos();
    }

    private void cargarLista() {
        listaDiscos.clear();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                MusicaContrato.NOMBRE_TABLA,
                null,
                null,
                null,
                null,
                null,
                MusicaContrato.COL_ANIO + " DESC"
        );

        while (cursor.moveToNext()) {
            listaDiscos.add(new Disco(
                    cursor.getInt(cursor.getColumnIndexOrThrow(MusicaContrato.COL_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MusicaContrato.COL_NOMBRE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MusicaContrato.COL_ARTISTA)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MusicaContrato.COL_GENERO)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(MusicaContrato.COL_DURACION)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(MusicaContrato.COL_ANIO))
            ));
        }

        cursor.close();
        adapter.notifyDataSetChanged();
    }

    private void limpiarCampos() {
        etNombre.setText("");
        etArtista.setText("");
        etGenero.setText("");
        etDuracion.setText("");
        etAnio.setText("");
    }
}
