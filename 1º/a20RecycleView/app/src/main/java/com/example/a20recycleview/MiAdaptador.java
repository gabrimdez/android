package com.example.a20recycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.ViewHolder> {

    private List<String> nameList;

    public MiAdaptador(List<String> nameList) {
        this.nameList = nameList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        return new ViewHolder(view);
    }

    public void removeItem(int position){
        nameList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = nameList.get(position);
        holder.textView.setText(name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                removeItem(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}