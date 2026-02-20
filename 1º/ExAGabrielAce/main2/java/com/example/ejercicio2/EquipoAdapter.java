package com.example.ejercicio2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EquipoAdapter extends RecyclerView.Adapter<EquipoAdapter.Holder> {

    public interface OnEquipoClick {
        void click(Equipo e);
    }

    ArrayList<Equipo> lista;
    OnEquipoClick listener;

    public EquipoAdapter(ArrayList<Equipo> l, OnEquipoClick ls) {
        lista = l;
        listener = ls;
    }

    public static class Holder extends RecyclerView.ViewHolder {
        ImageView imgLogo;
        TextView txtNombre;
        TextView txtCiudadYear;

        public Holder(View v) {
            super(v);
            imgLogo = v.findViewById(R.id.imgLogoItem);
            txtNombre = v.findViewById(R.id.txtNombreItem);
            txtCiudadYear = v.findViewById(R.id.txtCiudadYearItem);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v1 = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_equipo, parent, false);
        return new Holder(v1);
    }

    @Override
    public void onBindViewHolder(Holder h, int pos) {
        Equipo e = lista.get(pos);
        h.txtNombre.setText(e.nombre);
        h.txtCiudadYear.setText(e.ciudad + " - " + e.year);
        h.imgLogo.setImageResource(e.logo);

        h.itemView.setOnClickListener(v -> listener.click(e));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    // Añadir equipo
    public void addEquipo(Equipo e) {
        lista.add(e);
        notifyItemInserted(lista.size() - 1); // anima la inserción
    }

    // Borrar último equipo
    public void removeLast() {
        if (!lista.isEmpty()) {
            int lastPos = lista.size() - 1;
            lista.remove(lastPos);
            notifyItemRemoved(lastPos);
        }
    }
}
