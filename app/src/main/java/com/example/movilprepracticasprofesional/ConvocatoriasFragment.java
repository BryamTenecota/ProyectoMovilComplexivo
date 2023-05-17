package com.example.movilprepracticasprofesional;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

import Servicios.Servicios;
import modelos.Convocatorias;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConvocatoriasFragment extends Fragment {
    private TextView mjsonText;
    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_convocatoria, container, false);

        mjsonText = rootView.findViewById(R.id.jsonText);

        MostrarJson();

        return rootView;
    }*/

    private void  MostrarJson() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.158:8080/api/convocatorias/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios servie = retrofit.create(Servicios.class);
        Call<List<Convocatorias>> call = servie.getConvocatorias();
        call.enqueue(new Callback<List<Convocatorias>>() {

            @Override
            public void onResponse(Call<List<Convocatorias>> call, Response<List<Convocatorias>> response) {
                if (!response.isSuccessful()) {
                    mjsonText.setText("codigo: " + response.code());
                    return;
                }
                List<Convocatorias> list = response.body();
                for (Convocatorias convocatorias : list) {
                    String content = "";
                    content += "nombreConvocatorias: " + convocatorias.getNombreConvocatoria() + "\n";
                    content += "fechaPublicacion: " + convocatorias.getFechaPublicacion() + "\n";
                    content += "fechaExpiracion: " + convocatorias.getFechaExpiracion() + "\n";
                    content += "estadoConvocatoria: " + convocatorias.isEstadoConvocatoria() + "\n";
//
                    mjsonText.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Convocatorias>> call, Throwable t) {
                mjsonText.setText(t.getMessage());
            }
        });
    }
}
