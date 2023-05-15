package com.example.movilprepracticasprofesional;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import Servicios.Servicios;
import modelos.Empresa;
import modelos.Practica;
import modelos.TutorEmpresarial;
import modelos.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class   ConvocatoriaFragment extends Fragment {

    private TextView mjsonText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_convocatoria, container, false);

        mjsonText = rootView.findViewById(R.id.jsonText);
        MostrarJson();

        return rootView;
    }

    private void  MostrarJson(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.110:8080/api/practica/")

                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios servie= retrofit.create(Servicios.class);
        Call <List<Practica>> call= servie.getPractica();
        call.enqueue(new Callback<List<Practica>>() {
            @Override
            public void onResponse(Call<List<Practica>> call, Response<List<Practica>> response) {
                if(!response.isSuccessful()){
                    mjsonText.setText("codigo: "+response.code());
                    return;
                }
                List<Practica> list= response.body();
                for (Practica practica: list){
                    String content= "";
                    content +="Hora Inicio: "+practica.getHoraInicio()+"\n";
                    content +="Hora Fin: "+practica.getHoraFin()+"\n";
                    for(Usuario usuario: practica.getUsuarios()){
                        content += "Nombre Practicante: "+usuario.getNombres()+"\n";
                        content += "Apellido Practicante: "+usuario.getApellidos()+"\n";
                    }
                    for(TutorEmpresarial tutorEmpresarial: practica.getTutorEmpresarials()){
                        for(Empresa empresa: tutorEmpresarial.getEmpresas()) {
                            content += "Empresa: " + empresa.getNombreEmpresa() + "\n";
                        }
                    }
                    mjsonText.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Practica>> call, Throwable t) {
                mjsonText.setText(t.getMessage());
            }
        });

    }
}
