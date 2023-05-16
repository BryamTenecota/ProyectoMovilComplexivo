package com.example.movilprepracticasprofesional;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;

import Servicios.Servicios;
import modelos.Convocatorias;
import modelos.TutorEmpresarial;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListTutorEmpresarial extends Fragment {

    private TextView mjsonText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_tutempresarial, container, false);

        mjsonText = rootView.findViewById(R.id.jsonText);
        MostrarJson();

        return rootView;
    }

    private void  MostrarJson() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.18.39:8080//api/convocatorias/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios servie = retrofit.create(Servicios.class);
        Call<List<TutorEmpresarial>> call = servie.getTutorEmpresarial();
        call.enqueue(new Callback<List<TutorEmpresarial>>() {

            @Override
            public void onResponse(Call<List<TutorEmpresarial>> call, Response<List<TutorEmpresarial>> response) {
                if (!response.isSuccessful()) {
                    mjsonText.setText("codigo: " + response.code());
                    return;
                }
                List<TutorEmpresarial> list = response.body();
                for (TutorEmpresarial tutorEmpresarial : list) {
                    String content = "";
                    content += "cargo: " + tutorEmpresarial.getCargo() + "\n";
                    content += "usuario: " + tutorEmpresarial.getUsuario() + "\n";
                    content += "empresa: " + tutorEmpresarial.getEmpresa() + "\n";
                    mjsonText.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<TutorEmpresarial>> call, Throwable t) {
                mjsonText.setText(t.getMessage());
            }
        });
    }
}
