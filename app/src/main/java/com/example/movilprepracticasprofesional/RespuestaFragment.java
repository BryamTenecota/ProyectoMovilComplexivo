package com.example.movilprepracticasprofesional;


import android.content.Context;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Servicios.Servicios;
import modelos.Empresa;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RespuestaFragment extends Fragment {
    Adaptadoremp adaptadoremp;
    RecyclerView recyclerView;
    private TextView mjsonText;
    ArrayList<Empresa> empresaArrayList;
    List<Empresa> empresa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_respuesta, container, false);
        recyclerView = view.findViewById(R.id.listRecyclerviewemp);
        empresaArrayList = new ArrayList<>();
        //cargarmos la lista

        cargarlista();
        return view;
    }

    public void init() {

        empresa = new ArrayList<>();
        empresa.add(new Empresa(9, "ASTUDILLO TOCACHI MANUEL EDUARDO", "TECNOLOGÍA SUPERIOR EN DESARROLLO DE SOFTWARE", "eduardoeat10@gmail.com", ""));

        //ListAdapter listAdapter = new ListAdapter(practicantes, );

    }

    public void cargarlista() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.18.39:8080/api/empresa/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Servicios servie = retrofit.create(Servicios.class);
        Call<List<Empresa>> call = servie.getEmpresa();
        call.enqueue(new Callback<List<Empresa>>() {
            @Override
            public void onResponse(Call<List<Empresa>> call, Response<List<Empresa>> response) {
                if(response.isSuccessful()){
                    empresa = response.body();
                    adaptadoremp = new Adaptadoremp( empresaArrayList, getActivity().getApplicationContext());
                    recyclerView.setAdapter(adaptadoremp);
                }
            }

            @Override
            public void onFailure(Call<List<Empresa>> call, Throwable t) {
                // Manejar el error en caso de fallo de la llamada
            }
        });
    }
}

   /* public void mostrarData(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptadoremp = new Adaptadoremp(empresaArrayList, getContext());
        recyclerView.setAdapter(adaptadoremp);
    }*/




   /* private void  MostrarJson(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.18.39:8080/api/empresa/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios servie= retrofit.create(Servicios.class);
        Call<List<Empresa>> call= servie.getEmpresa();

        call.enqueue(new Callback<List<Empresa>>() {
            @Override
            public void onResponse(Call<List<Empresa>> call, Response<List<Empresa>> response) {
                if(!response.isSuccessful()){
                    mjsonText.setText("Codigo: "+response.code());
                    return;
                }
                List<Empresa> lista= response.body();
                for(Empresa empresa: lista){
                    String content="";
                    content+="Idnpresa: "+empresa.getIdempresa()+"\n";
                    content+="Nombre: "+empresa.getNombreempresa()+"\n";
                    content+="Telefono: "+empresa.getNumerotelefono()+"\n";
                    content+="Descripcion: "+empresa.getDescripcion()+"\n";
                    content+="Descripcion: "+empresa.getDescripcion()+"\n";
                    mjsonText.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Empresa>> call, Throwable t) {
                mjsonText.setText(t.getMessage());
            }

        });
    }

    }*/