package com.example.ejercicio1b;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTitulo, etDirector, etPais, etGenero, etAnio;
    Button btnInsertar, btnModificar, btnBorrar;
    ListView listView;

    DBHelper dbHelper;
    SQLiteDatabase db;

    ArrayList<String> listaPeliculas;
    ArrayAdapter<String> adapter;

    int idSeleccionado = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitulo = findViewById(R.id.etTitulo);
        etDirector = findViewById(R.id.etDirector);
        etPais = findViewById(R.id.etPais);
        etGenero = findViewById(R.id.etGenero);
        etAnio = findViewById(R.id.etAnio);

        btnInsertar = findViewById(R.id.btnInsertar);
        btnModificar = findViewById(R.id.btnModificar);
        btnBorrar = findViewById(R.id.btnBorrar);
        listView = findViewById(R.id.listView);

        dbHelper = new DBHelper(this);

        listaPeliculas = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaPeliculas);
        listView.setAdapter(adapter);

        listarPeliculas();

        // INSERTAR
        btnInsertar.setOnClickListener(v -> insertar());

        // MODIFICAR
        btnModificar.setOnClickListener(v -> modificar());

        // BORRAR
        btnBorrar.setOnClickListener(v -> borrar());

        // SELECCIONAR DE LISTVIEW
        listView.setOnItemClickListener((parent, view, position, id) -> cargarRegistro(position));
    }

    private void insertar() {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("titulo", etTitulo.getText().toString());
        values.put("director", etDirector.getText().toString());
        values.put("pais", etPais.getText().toString());
        values.put("genero", etGenero.getText().toString());
        values.put("anio", Integer.parseInt(etAnio.getText().toString()));

        db.insert("peliculas", null, values);
        Toast.makeText(this, "Insertado", Toast.LENGTH_SHORT).show();

        limpiar();
        listarPeliculas();
    }

    private void modificar() {
        if (idSeleccionado == -1) return;

        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("titulo", etTitulo.getText().toString());
        values.put("director", etDirector.getText().toString());
        values.put("pais", etPais.getText().toString());
        values.put("genero", etGenero.getText().toString());
        values.put("anio", Integer.parseInt(etAnio.getText().toString()));

        db.update("peliculas", values, "id=?", new String[]{String.valueOf(idSeleccionado)});
        Toast.makeText(this, "Modificado", Toast.LENGTH_SHORT).show();

        limpiar();
        listarPeliculas();
    }

    private void borrar() {
        if (idSeleccionado == -1) return;

        db = dbHelper.getWritableDatabase();
        db.delete("peliculas", "id=?", new String[]{String.valueOf(idSeleccionado)});
        Toast.makeText(this, "Borrado", Toast.LENGTH_SHORT).show();

        limpiar();
        listarPeliculas();
    }

    private void listarPeliculas() {
        listaPeliculas.clear();
        db = dbHelper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM peliculas", null);

        while (c.moveToNext()) {
            int id = c.getInt(0);
            String titulo = c.getString(1);
            String director = c.getString(2);
            String pais = c.getString(3);
            String genero = c.getString(4);
            int anio = c.getInt(5);

            listaPeliculas.add(id + " - " + titulo + " (" + anio + ")");
        }

        c.close();
        adapter.notifyDataSetChanged();
    }

    private void cargarRegistro(int position) {
        String item = listaPeliculas.get(position);
        idSeleccionado = Integer.parseInt(item.split(" - ")[0]);

        db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM peliculas WHERE id=" + idSeleccionado, null);

        if (c.moveToFirst()) {
            etTitulo.setText(c.getString(1));
            etDirector.setText(c.getString(2));
            etPais.setText(c.getString(3));
            etGenero.setText(c.getString(4));
            etAnio.setText(String.valueOf(c.getInt(5)));
        }

        c.close();
    }

    private void limpiar() {
        etTitulo.setText("");
        etDirector.setText("");
        etPais.setText("");
        etGenero.setText("");
        etAnio.setText("");
        idSeleccionado = -1;
    }
}