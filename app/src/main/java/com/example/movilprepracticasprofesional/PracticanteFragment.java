package com.example.movilprepracticasprofesional;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;

import Servicios.Servicios;
import modelos.UserSingleton;
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
        MostrarJson();

        return rootView;
    }


    /*private void  MostrarJson(){
        int id = UserSingleton.getIdUsuario();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.18.39:8080/api/solicitudPractica/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios servie= retrofit.create(Servicios.class);
        Call<List<Object[]>> call= servie.getSolporTuto(id);

        call.enqueue(new Callback<List<Object[]>>() {
            @Override
            public void onResponse(Call<List<Object[]>> call, Response<List<Object[]>> response) {
                if(!response.isSuccessful()){
                    mjsonText.setText("Codigo: "+response.code());
                    return;
                }
                List<Object[]> lista= response.body();
                for(Object[] object: lista){
                        String content = "";
                        String nombresol = (String) object[0];
                        String fechae = (String) object[1];
                        String fechaa = (String) object[2];

                        content += "Nombre Solicitud: " + nombresol + "\n";
                        content += "Fecha de Envio: " + fechae + "\n";
                        content += "Fecha de Aceptación: " + fechaa + "\n";

                        mjsonText.append(content);
                    }

            }

            @Override
            public void onFailure(Call<List<Object[]>> call, Throwable t) {
                mjsonText.setText(t.getMessage());
            }

        });
    }*/

    private void  MostrarJson(){
        int id = UserSingleton.getIdUsuario();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.18.39:8080/api/solicitudPractica/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios servie= retrofit.create(Servicios.class);
        Call<List<Object[]>> call= servie.getSolporTutoAceptadas(id);

        call.enqueue(new Callback<List<Object[]>>() {
            @Override
            public void onResponse(Call<List<Object[]>> call, Response<List<Object[]>> response) {
                if(!response.isSuccessful()){
                    mjsonText.setText("Codigo: "+response.code());
                    return;
                }
                List<Object[]> lista= response.body();
                for(Object[] object: lista){
                    String content = "";
                    String nombresol = (String) object[0];
                    String fechae = (String) object[1];
                    String fechaa = (String) object[2];

                    content += "Nombre Solicitud: " + nombresol + "\n";
                    content += "Fecha de Envio: " + fechae + "\n";
                    content += "Fecha de Aceptación: " + fechaa + "\n";

                    mjsonText.append(content);
                }
            }
            @Override
            public void onFailure(Call<List<Object[]>> call, Throwable t) {
                mjsonText.setText(t.getMessage());
            }

        });
    }

    /*private void  MostrarJson(){


        int id = UserSingleton.getIdUsuario();
        Toast.makeText(getActivity(), "ID de Usuario: " + id, Toast.LENGTH_SHORT).show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.18.39:8080/api/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios servie= retrofit.create(Servicios.class);
        Call<List<Object[]>> call= servie.getUsuariosPorTutorEmpresarial(id);

        call.enqueue(new Callback<List<Object[]>>() {
            @Override
            public void onResponse(Call<List<Object[]>> call, Response<List<Object[]>> response) {
                if(!response.isSuccessful()){
                    mjsonText.setText("Codigo: "+response.code());
                    return;
                }
                List<Object[]> lista = response.body();
                for (Object[] object : lista) {
                    String content = "";
                    String nombre = (String) object[0];
                    String apellido = (String) object[1];
                    String cedula = (String) object[2];

                    content += "Nombre: " + nombre + "\n";
                    content += "Apellido: " + apellido + "\n";
                    content += "Cédula: " + cedula + "\n";

                    mjsonText.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Object[]>> call, Throwable t) {
                mjsonText.setText(t.getMessage());
            }

        });

    }*/
}
