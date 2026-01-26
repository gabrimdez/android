package com.example.a21rviewcomplejo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.ViewHolder> {

    private List<ItemData> nameList;

    public MiAdaptador(List<ItemData> nameList) {
        this.nameList = nameList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ficha, parent, false);

        return new ViewHolder(view);
    }

    public void removeItem(int position){
        nameList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ItemData item = nameList.get(position);
        final String superior = item.getTextoSuperior();
        final String inferior = item.getTextoInferior();
        final int idImagen = item.getIdImagen();

        holder.textViewSuperior.setText(superior);
        holder.textViewInferior.setText(inferior);
        holder.imageView.setImageResource(idImagen);
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                removeItem(holder.getAdapterPosition());
            }
        });
    }

    public void addItem(ItemData item) {
        //item = new ItemData("Avatar ADD","Descripcion ADD",R.drawable.avatar_11);
        nameList.add(item);
        notifyItemInserted(nameList.size()-1);
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textViewSuperior;
        TextView textViewInferior;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
            textViewSuperior = itemView.findViewById(R.id.textViewSuperior);
            textViewInferior = itemView.findViewById(R.id.textViewInferior);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}