package com.example.movilprepracticasprofesional;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import Servicios.Servicios;
import modelos.Convocatorias;
import modelos.Empresa;
import modelos.UserSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConvocatoriasFragment extends Fragment {
    private ListView mjsonListView;
    private List<Convocatorias> Convocatoriaslist;
    private ArrayAdapter<String> mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_convocatorias, container, false);

        mjsonListView = view.findViewById(R.id.mjsonListView);

        MostrarJson();

        return view;
    }

    private void  MostrarJson() {
        int id = UserSingleton.getIdUsuario();
        Toast.makeText(getActivity(), "ID de Usuario: " + id, Toast.LENGTH_SHORT).show();

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
                    return;
                }
                Convocatoriaslist = response.body();
                List<String> dataList = new ArrayList<>();
                        for (Convocatorias convocatorias : Convocatoriaslist) {
                            String content = "";
                            content += "nombreConvocatorias: " + convocatorias.getNombreConvocatoria() + "\n";
                            content += "fechaPublicacion: " + convocatorias.getFechaPublicacion() + "\n";
                            content += "fechaExpiracion: " + convocatorias.getFechaExpiracion() + "\n";
                            content += "estadoConvocatoria: " + convocatorias.isEstadoConvocatoria() + "\n";
//
                            dataList.add(content);
                        }
                        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.list_content, dataList);
                        mjsonListView.setAdapter(mAdapter);
            }



            @Override
            public void onFailure(Call<List<Convocatorias>> call, Throwable t) {
            }
        });
    }
}
