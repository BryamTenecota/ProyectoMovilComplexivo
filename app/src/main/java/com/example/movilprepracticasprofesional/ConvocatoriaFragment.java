package com.example.movilprepracticasprofesional;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;

import Servicios.Servicios;
import modelos.Convenio;
import modelos.DetalleConvenio;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class  ConvocatoriaFragment extends Fragment {
    private TextView mjsonText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_convocatoria, container, false);

        //mjsonText = rootView.findViewById(R.id.jsonText);
        MostrarJson();

        return rootView;
    }

    private void  MostrarJson(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.18.39:8080/api/detalleConvenio/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios servie= retrofit.create(Servicios.class);
        Call <List<DetalleConvenio>> call= servie.getDetalleConvenio();
        call.enqueue(new Callback<List<DetalleConvenio>>() {
            @Override
            public void onResponse(Call<List<DetalleConvenio>> call, Response<List<DetalleConvenio>> response) {
                if(!response.isSuccessful()){
                    mjsonText.setText("codigo: "+response.code());
                    return;
                }
                List<DetalleConvenio> list= response.body();
                for (DetalleConvenio detalleConvenio: list){
                    String content= "";
                    content +="Nombre Carrera: "+detalleConvenio.getNombre_carrera()+"\n";
                    for (Convenio convenio : detalleConvenio.getConvenio()) {
                        content += "Numero de Convenio: " + convenio.getNumero_convenio() + "\n";
                    }


                    mjsonText.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<DetalleConvenio>> call, Throwable t) {
                mjsonText.setText(t.getMessage());
            }
        });
    }
    }

