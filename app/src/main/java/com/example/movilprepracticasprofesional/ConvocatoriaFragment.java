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
import modelos.TutorEmpresarial;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ConvocatoriaFragment extends Fragment {



    private TextView mjsonText;
    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_convocatoria, container, false);

        mjsonText = rootView.findViewById(R.id.jsonText);
        MostrarJson();

        return rootView;
    }*/

    private void  MostrarJson(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.158:8080/api/tutorEmp/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios servie= retrofit.create(Servicios.class);
        Call<List<TutorEmpresarial>> call= servie.getTutorEmpresarial();

        call.enqueue(new Callback<List<TutorEmpresarial>>() {
            @Override
            public void onResponse(Call<List<TutorEmpresarial>> call, Response<List<TutorEmpresarial>> response) {
                if(!response.isSuccessful()){
                    mjsonText.setText("Codigo: "+response.code());
                    return;
                }
                List<TutorEmpresarial> lista= response.body();
                for(TutorEmpresarial tutorEmpresarial: lista){
                    String content="";
                    //content+="Nombre Tutor: "+tutorEmpresarial.getUsuario().getPersonaEmpresa().getPrimer_nombre()+"\n";
                    //content+="Apellido Tutor: "+tutorEmpresarial.getUsuario().getPersonaEmpresa().getPrimer_apellido()+"\n";
                    content+="Empresa: "+tutorEmpresarial.getEmpresa().getNombreEmpresa()+"\n";
                    content+="Cargo: "+tutorEmpresarial.getCargo()+"\n";
                    mjsonText.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<TutorEmpresarial>> call, Throwable t) {
                mjsonText.setText(t.getMessage());
            }

        });
    }
//    private void  MostrarJson(){
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.1.110:8080/api/tutorEmp/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        Servicios servie= retrofit.create(Servicios.class);
//        Call<List<TutorEmpresarial>> call= servie.getTutorEmpresarial();
//
//        call.enqueue(new Callback<List<TutorEmpresarial>>() {
//            @Override
//            public void onResponse(Call<List<TutorEmpresarial>> call, Response<List<TutorEmpresarial>> response) {
//                if(!response.isSuccessful()){
//                    mjsonText.setText("Codigo: "+response.code());
//                    return;
//                }
//                List<TutorEmpresarial> lista= response.body();
//                for(TutorEmpresarial tutorEmpresarial: lista){
//                    String content="";
//                    content+="Nombre Tutor: "+tutorEmpresarial.getUsuario().getPersonaEmpresa().getPrimer_nombre()+"\n";
//                    content+="Apellido Tutor: "+tutorEmpresarial.getUsuario().getPersonaEmpresa().getPrimer_apellido()+"\n";
//                    content+="Empresa: "+tutorEmpresarial.getEmpresa().getNombreEmpresa()+"\n";
//                    content+="Cargo: "+tutorEmpresarial.getCargo()+"\n";
//                    mjsonText.append(content);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<TutorEmpresarial>> call, Throwable t) {
//                mjsonText.setText(t.getMessage());
//            }
//
//        });
//    }
}