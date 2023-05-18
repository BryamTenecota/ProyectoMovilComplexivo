package com.example.movilprepracticasprofesional;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import Servicios.Servicios;
import modelos.BaseUrl;
import modelos.UserSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentConvoctoriasDis extends Fragment {
    private ListView mjsonListView;
    private List<Object[]> mObjectList;
    private ArrayAdapter<Object[]> mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_convocatoriadis, container, false);

        mjsonListView = rootView.findViewById(R.id.jsonListView);
        MostrarJson();

        return rootView;
    }

    private void MostrarJson() {
        String nombre_carrera = UserSingleton.getNombre_carrera();
        Toast.makeText(getActivity(), "Carrera: " + nombre_carrera, Toast.LENGTH_SHORT).show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.getBaseUrl() + "practica/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios service = retrofit.create(Servicios.class);
        Call<List<Object[]>> call = service.getConvocatoriaDisp(nombre_carrera);

        call.enqueue(new Callback<List<Object[]>>() {
            @Override
            public void onResponse(Call<List<Object[]>> call, Response<List<Object[]>> response) {
                if (!response.isSuccessful()) {
                    // Manejar el código de respuesta no exitoso aquí
                    return;
                }

                mObjectList = response.body();
                mAdapter = new ArrayAdapter<Object[]>(getActivity(), R.layout.list_convocatoriadis, mObjectList) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        if (convertView == null) {
                            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_convocatoriadis, parent, false);
                        }

                        ImageView itemImage = convertView.findViewById(R.id.item_image);
                        TextView itemnombreConvo= convertView.findViewById(R.id.item_nombreConvo);
                        TextView itemfechap = convertView.findViewById(R.id.item_fechap);
                        TextView itemfechaex = convertView.findViewById(R.id.item_fechaex);

                        Object[] object = getItem(position);
                        String nombreConvo = (String) object[0];
                        String fechap = (String) object[1];
                        String fechaex = (String) object[2];

                        // Asignar los datos al diseño de elementos de lista
                        itemnombreConvo.setText("Nombre Convocatoria: " + nombreConvo + "\n");
                        itemfechap.setText("Fecha publicación: " + fechap + "\n");
                        itemfechaex.setText("Fecha Expiración: " + fechaex + "\n");
                        itemImage.setImageResource(R.drawable.convo); // Reemplaza "imagen" con el nombre de tu imagen

                        return convertView;
                    }
                };

                mjsonListView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Object[]>> call, Throwable t) {
                // Manejar el fallo de la llamada aquí
            }
        });
    }
}