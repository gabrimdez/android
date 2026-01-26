package com.example.ejercicio2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    ArrayList<Country> countries;
    OnCountryClick listener;

    public interface OnCountryClick {
        void onClick(Country c);
    }

    public CountryAdapter(ArrayList<Country> countries, OnCountryClick listener) {
        this.countries = countries;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtCapital, txtPopulation;
        ImageView imgFlag;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtCapital = itemView.findViewById(R.id.txtCapital);
            txtPopulation = itemView.findViewById(R.id.txtPopulation);
            imgFlag = itemView.findViewById(R.id.imgFlag);
        }
    }

    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_country, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CountryAdapter.ViewHolder holder, int position) {
        Country c = countries.get(position);

        holder.txtName.setText(c.getName());
        holder.txtCapital.setText("Capital: " + c.getCapital());
        holder.txtPopulation.setText("Habitantes: " + c.getPopulation());
        holder.imgFlag.setImageResource(c.getFlagRes());

        holder.itemView.setOnClickListener(v -> listener.onClick(c));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public void addCountry(Country c) {
        countries.add(c);
        notifyItemInserted(countries.size() - 1);
    }

    public void removeLast() {
        if (!countries.isEmpty()) {
            countries.remove(countries.size() - 1);
            notifyDataSetChanged();
        }
    }
}
