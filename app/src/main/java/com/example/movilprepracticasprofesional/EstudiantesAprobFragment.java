package com.example.movilprepracticasprofesional;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;

import Servicios.Servicios;
import modelos.EstudiantesAprob;
import modelos.TutorEmpresarial;
import modelos.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EstudiantesAprobFragment extends Fragment {

    private TextView mjsonText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_estudiantesaprob, container, false);
        mjsonText = rootView.findViewById(R.id.jsonText);
        MostrarJson();

        return rootView;
    }

    private void  MostrarJson() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.158:8080/api/estudiantepracticante/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios servie = retrofit.create(Servicios.class);
        Call<List<EstudiantesAprob>> call = servie.getEstudianteAprob();
        call.enqueue(new Callback<List<EstudiantesAprob>>() {

            @Override
            public void onResponse(Call<List<EstudiantesAprob>> call, Response<List<EstudiantesAprob>> response) {
                if (!response.isSuccessful()) {
                    mjsonText.setText("codigo: " + response.code());
                    return;
                }
                List<EstudiantesAprob> list = response.body();
                for (EstudiantesAprob estudiantesAprob : list) {
                    String content = "";
                        content += "nombre: " + estudiantesAprob.getCedula() + "\n";
                        content += "cargo: " + estudiantesAprob.getNombre() + "\n";
                        content += "departamento: " + estudiantesAprob.getApellido() + "\n";
                        content += "estado: " + estudiantesAprob.getCorreo() + "\n";
                        content += "numero contacto: " + estudiantesAprob.getCarrera() + "\n";


                        mjsonText.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<EstudiantesAprob>> call, Throwable t) {
                mjsonText.setText(t.getMessage());
            }
        });
    }
}
