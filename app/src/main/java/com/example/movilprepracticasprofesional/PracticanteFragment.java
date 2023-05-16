package com.example.movilprepracticasprofesional;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;

import java.util.List;

import Servicios.Servicios;
import modelos.Empresa;
import modelos.Practica;
import modelos.SolicitudPractica;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PracticanteFragment extends Fragment {
    private TextView mjsonText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_practicante, container, false);

        mjsonText = rootView.findViewById(R.id.jsonText);
        /*MostrarJson();*/

        return rootView;
    }

    /*private void  MostrarJson(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.18.39:8080/api/practica/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios servie= retrofit.create(Servicios.class);
        Call<List<Practica>> call= servie.getPractica();

        call.enqueue(new Callback<List<Practica>>() {
            @Override
            public void onResponse(Call<List<Practica>> call, Response<List<Practica>> response) {
                if(!response.isSuccessful()){
                    mjsonText.setText("Codigo: "+response.code());
                    return;
                }
                List<Practica> lista= response.body();
                for(Practica practica: lista){
                    String content="";
                    content+="Hora Inicio: "+practica.getHoraInicio()+"\n";
                    content+="Hora Salida: "+practica.getHoraSalida()+"\n";

                    mjsonText.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Practica>> call, Throwable t) {
                mjsonText.setText(t.getMessage());
            }

        });*/

    /*private void  MostrarJson(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.18.39:8080/api/solicitudPractica/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios servie= retrofit.create(Servicios.class);
        Call<List<SolicitudPractica>> call= servie.getSolicitudPractica();

        call.enqueue(new Callback<List<SolicitudPractica>>() {
            @Override
            public void onResponse(Call<List<SolicitudPractica>> call, Response<List<SolicitudPractica>> response) {
                if(!response.isSuccessful()){
                    mjsonText.setText("Codigo: "+response.code());
                    return;
                }
                List<SolicitudPractica> lista= response.body();
                for(SolicitudPractica solicitudPractica: lista){
                    if (!solicitudPractica.isEstadoSolicitud()) {
                        String content = "";
                        content += "Nombre Solicitud: " + solicitudPractica.getNombreSolicitud() + "\n";
                        content += "Numero de Estudiantes: " + solicitudPractica.getNumeroEstudiantes() + "\n";
                        content += "Fecha de Envio: " + solicitudPractica.getFechaEnvioSolicitud() + "\n";

                        mjsonText.append(content);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SolicitudPractica>> call, Throwable t) {
                mjsonText.setText(t.getMessage());
            }

        });
    }*/

    /*private void  MostrarJson(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.18.39:8080/api/solicitudPractica/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios servie= retrofit.create(Servicios.class);
        Call<List<SolicitudPractica>> call= servie.getSolicitudPractica();

        call.enqueue(new Callback<List<SolicitudPractica>>() {
            @Override
            public void onResponse(Call<List<SolicitudPractica>> call, Response<List<SolicitudPractica>> response) {
                if(!response.isSuccessful()){
                    mjsonText.setText("Codigo: "+response.code());
                    return;
                }
                List<SolicitudPractica> lista= response.body();
                for(SolicitudPractica solicitudPractica: lista){
                    if (solicitudPractica.isEstadoSolicitud()) {
                        String content = "";
                        content += "Nombre Solicitud: " + solicitudPractica.getNombreSolicitud() + "\n";
                        content += "Numero de Estudiantes: " + solicitudPractica.getNumeroEstudiantes() + "\n";
                        content += "Fecha de Aceptacion: " + solicitudPractica.getFechaAceptacion() + "\n";

                        mjsonText.append(content);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SolicitudPractica>> call, Throwable t) {
                mjsonText.setText(t.getMessage());
            }

        });
    }*/
}
