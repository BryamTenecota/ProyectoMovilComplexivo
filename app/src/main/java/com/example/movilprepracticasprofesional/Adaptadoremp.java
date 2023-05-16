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

public class Adaptadoremp extends RecyclerView.Adapter<Adaptadoremp.ViewHolder> implements View.OnClickListener{
    private ArrayList<ListElement> mData;
    private LayoutInflater mInflater;
    //listener
    private View.OnClickListener listener;

    public Adaptadoremp(ArrayList<ListElement> itemList, Context context){
        this.mInflater=LayoutInflater.from(context);
        this.mData=itemList;
    }

    @NonNull
    @Override
    public Adaptadoremp.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= mInflater.inflate(R.layout.list_empresa,parent,false);
        view.setOnClickListener(this);
        return new Adaptadoremp.ViewHolder(view);
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(final Adaptadoremp.ViewHolder holder, int position) {
        String titulo= mData.get(position).getTitle();
        String mensaje= mData.get(position).getDescripcion();
        String state= mData.get(position).getStatus();
        int image= mData.get(position).getImagenid();

       // holder.title.setText(titulo);
      //  holder.descripcion.setText(mensaje);
        holder.status.setText(state);
        holder.iconImage.setImageResource(image);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }
    public void SetItems(ArrayList<ListElement>items){ mData = items;}

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView rucEmpresa, nombreEmpresa,status;
        public ViewHolder(View itemView){
            super(itemView);
            iconImage= itemView.findViewById(R.id.iconImageView);
            rucEmpresa=itemView.findViewById(R.id.txtTitle);
            nombreEmpresa=itemView.findViewById(R.id.txtDescripcion);
            status=itemView.findViewById(R.id.txtStatus);
        }

    }

}
