package com.example.movilprepracticasprofesional;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

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

    private ListView mjsonListView;
    private List<Empresa> mEmpresaList;
    private ArrayAdapter<String> mAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_respuesta, container, false);

        mjsonListView = view.findViewById(R.id.jsonListView);
        MostrarJson();

        return view;
    }

   private void  MostrarJson(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.158:8080/api/empresa/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios servie= retrofit.create(Servicios.class);
        Call<List<Empresa>> call= servie.getEmpresa();

        call.enqueue(new Callback<List<Empresa>>() {
            @Override
            public void onResponse(Call<List<Empresa>> call, Response<List<Empresa>> response) {
                if(!response.isSuccessful()){

                    return;
                }
                mEmpresaList = response.body();
                List<String> dataList = new ArrayList<>();
                for(Empresa empresa: mEmpresaList){
                    String content="";
                    content+="Nombre: "+empresa.getNombreEmpresa()+"\n";
                    content+="Telefono: "+empresa.getNumeroTelefono()+"\n";
                    content+="Correo: "+empresa.getCorreo()+"\n";
                    content+="Descripcion: "+empresa.getDescripcion()+"\n";

                    dataList.add(content);
                }

                mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, dataList);
                mjsonListView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Empresa>> call, Throwable t) {

            }

        });
    }
    }