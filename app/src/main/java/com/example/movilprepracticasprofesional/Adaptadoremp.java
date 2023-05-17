package com.example.movilprepracticasprofesional;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import modelos.Empresa;

public class Adaptadoremp extends RecyclerView.Adapter<Adaptadoremp.ViewHolder> implements View.OnClickListener{
    private ArrayList<Empresa> mData;
    private LayoutInflater mInflater;
    //listener
    private View.OnClickListener listener;

    public Adaptadoremp(ArrayList<Empresa> itemList, Context context){
        this.mInflater=LayoutInflater.from(context);
        this.mData=itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= mInflater.inflate(R.layout.list_elementemp,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String nombreempresa = mData.get(position).getNombreempresa();
        String numerotelefono = mData.get(position).getNumerotelefono();
        String correo = mData.get(position).getCorreo();
        String descripcion = mData.get(position).getDescripcion();

        holder.nombreempresa.setText(nombreempresa);
        holder.numerotelefono.setText(numerotelefono);
        holder.correo.setText(correo);
        holder.descripcion.setText(descripcion);
    }




    @Override
    public int getItemCount() {
        return mData.size();
    }
    public void SetItems(ArrayList<Empresa>items){ mData = items;}

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView nombreempresa,numerotelefono,correo,descripcion;
        public ViewHolder(View itemView){
            super(itemView);
            iconImage= itemView.findViewById(R.id.iconImageView);
            nombreempresa=itemView.findViewById(R.id.txtnombreempresa);
            numerotelefono=itemView.findViewById(R.id.txtnumerotelefono);
            correo=itemView.findViewById(R.id.txtcorreo);
            descripcion=itemView.findViewById(R.id.txtdescripcioneemp);

        }

    }


}