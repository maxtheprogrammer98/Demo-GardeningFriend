package com.example.gardeningfrienddemo;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Cultivos_RecyclerViewAdapter extends RecyclerView.Adapter<Cultivos_RecyclerViewAdapter.MyViewHolder> {
    // atributos
    private final cltDetalles interfazCultivos;
    Context context;
    ArrayList<cultivosModels> cultModel;

    // constrcutor
    public Cultivos_RecyclerViewAdapter(Context context, ArrayList<cultivosModels> cultModelo, cltDetalles interfazCultivos){
        this.context = context;
        this.cultModel = cultModelo;
        this.interfazCultivos = interfazCultivos;
    }

    @NonNull
    @Override
    public Cultivos_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // "inflates" el layout con los estilos / modelos definidos
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cultivos_recycler, parent, false);

        return new Cultivos_RecyclerViewAdapter.MyViewHolder(view, interfazCultivos);
    }

    @Override
    public void onBindViewHolder(@NonNull Cultivos_RecyclerViewAdapter.MyViewHolder holder, int position) {
        // asigna un valor numerico representado la posicion de cada tarjeta
        // y se añade el texto dinamico de cada carac
        holder.nombre.setText(cultModel.get(position).getNombre());
        holder.temperatura.setText(cultModel.get(position).getTemperatura());
        holder.estacion.setText(cultModel.get(position).getEstacion());
        holder.region.setText(cultModel.get(position).getRegion());
        // se carga el icono del cultivo (a traves de su URL)
        Glide.with(context)
                .load(cultModel.get(position).getImg())
                .into(holder.icono);

    }

    @Override
    public int getItemCount() {
        // solo cuenta cuandos modelos se han pasado al recyclerview
        return cultModel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // es como una especie de 'oncreate'

        // atributos del modelo / tarjeta
        ImageView icono;
        TextView nombre, temperatura, estacion, region;

        //constructor
        public MyViewHolder(@NonNull View itemView, cltDetalles interfazCultivos){
            super(itemView);
            // se identifican los atributos de las tarjetas
            icono = itemView.findViewById(R.id.ic_cultivo);
            nombre = itemView.findViewById(R.id.titulo_cult_card);
            temperatura = itemView.findViewById(R.id.titulo_temp_card);
            estacion = itemView.findViewById(R.id.titulo_est_card);
            region = itemView.findViewById(R.id.titulo_reg_card);

            //se agrega un "click event listener" a cada elemento
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    if(interfazCultivos != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            interfazCultivos.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
