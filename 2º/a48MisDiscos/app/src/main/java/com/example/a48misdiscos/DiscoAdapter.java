package com.example.a48misdiscos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DiscoAdapter extends RecyclerView.Adapter<DiscoAdapter.ViewHolder> {

    public interface OnDiscoClickListener {
        void onDiscoClick(Disco disco);
    }

    private ArrayList<Disco> listaDiscos;
    private OnDiscoClickListener listener;

    public DiscoAdapter(ArrayList<Disco> listaDiscos, OnDiscoClickListener listener) {
        this.listaDiscos = listaDiscos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_musica, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Disco disco = listaDiscos.get(position);

        holder.tvNombre.setText(disco.getNombre());
        holder.tvArtista.setText(disco.getArtista());
        holder.tvGenero.setText(disco.getGenero());
        holder.tvAnio.setText(String.valueOf(disco.getAnio()));

        holder.itemView.setOnClickListener(v -> listener.onDiscoClick(disco));
    }

    @Override
    public int getItemCount() {
        return listaDiscos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvArtista, tvGenero, tvAnio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvItemNombre);
            tvArtista = itemView.findViewById(R.id.tvItemArtista);
            tvGenero = itemView.findViewById(R.id.tvItemGenero);
            tvAnio = itemView.findViewById(R.id.tvItemAnio);
        }
    }
}
