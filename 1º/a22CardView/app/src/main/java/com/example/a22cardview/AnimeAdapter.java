package com.example.a22cardview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Creado por Hermosa Programación
 */
public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {
    private final List<Anime> items;

    public static class AnimeViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre;
        public TextView visitas;

        public AnimeViewHolder(View v) {
            super(v);
            imagen = v.findViewById(R.id.imagen);
            nombre = v.findViewById(R.id.nombre);
            visitas = v.findViewById(R.id.visitas);
        }
    }

    public AnimeAdapter(List<Anime> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.anime_card, viewGroup, false);
        return new AnimeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder viewHolder, int i) {
        viewHolder.imagen.setImageResource(items.get(i).getImagen());
        viewHolder.nombre.setText(items.get(i).getNombre());
        // Usar recurso de string con placeholder
        viewHolder.visitas.setText(viewHolder.itemView.getContext()
                .getString(R.string.visitas_format, items.get(i).getVisitas()));
    }
}
