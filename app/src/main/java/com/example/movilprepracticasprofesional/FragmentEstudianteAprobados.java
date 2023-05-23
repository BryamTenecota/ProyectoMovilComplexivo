package com.example.movilprepracticasprofesional;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import Servicios.Servicios;
import modelos.BaseUrl;
import modelos.UserSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentEstudianteAprobados extends Fragment {
    private ListView mjsonListView;
    private List<Object[]> mObjectList;
    private ArrayAdapter<Object[]> mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_estudiantes_aprobados, container, false);

        mjsonListView = rootView.findViewById(R.id.jsonListView);
        MostrarJson();

        EditText searchEditText = rootView.findViewById(R.id.searchEditText);
        ImageButton searchButton = rootView.findViewById(R.id.btnIcono);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = searchEditText.getText().toString();
                buscarEstudiante(searchText);
            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No se requiere acción antes de cambiar el texto
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No se requiere acción mientras se cambia el texto
            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchText = s.toString();
                buscarEstudiante(searchText);
            }
        });

        return rootView;
    }

    private void MostrarJson() {
        int id = UserSingleton.getIdUsuario();
        Toast.makeText(getActivity(), "ID de Usuario: " + id, Toast.LENGTH_SHORT).show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.getBaseUrl() + "user/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios service = retrofit.create(Servicios.class);
        Call<List<Object[]>> call = service.getEstudiantesByrespo(id);

        call.enqueue(new Callback<List<Object[]>>() {
            @Override
            public void onResponse(Call<List<Object[]>> call, Response<List<Object[]>> response) {
                if (!response.isSuccessful()) {
                    // Manejar el código de respuesta no exitoso aquí
                    return;
                }

                mObjectList = response.body();
                mAdapter = new ArrayAdapter<Object[]>(getActivity(), R.layout.list_estudiantesaprobados, mObjectList) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        if (convertView == null) {
                            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_estudiantesaprobados, parent, false);
                        }

                        ImageView itemImage = convertView.findViewById(R.id.item_image);
                        TextView itemcedulaest = convertView.findViewById(R.id.item_cedulaest);
                        TextView itemnombrest= convertView.findViewById(R.id.item_nombreest);
                        TextView itemapellidoest = convertView.findViewById(R.id.item_apellidoest);
                        TextView itemcarreraest = convertView.findViewById(R.id.item_carreraest);

                        Object[] object = getItem(position);
                        String cedulaest = (String) object[0];
                        String nombreest = (String) object[1];
                        String apellidoest = (String) object[2];
                        String carreraest = (String) object[3];

                        itemcedulaest.setText("Cédula: " + cedulaest + "\n");
                        itemnombrest.setText("Nombres: " + nombreest + "\n");
                        itemapellidoest.setText("Apellidos: " + apellidoest + "\n");
                        itemcarreraest.setText("Carrera: " + carreraest + "\n");
                        itemImage.setImageResource(R.drawable.estuapr);

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

    private void buscarEstudiante(String searchText) {
        if (searchText.isEmpty()) {
            mAdapter.clear();
            mAdapter.addAll(mObjectList);
            mAdapter.notifyDataSetChanged();
            return;
        }

        List<Object[]> filteredList = new ArrayList<>();
        for (Object[] object : mObjectList) {
            String cedulaest = (String) object[0];

            if (cedulaest.toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(object);
            }
        }

        mAdapter.clear();
        mAdapter.addAll(filteredList);
        mAdapter.notifyDataSetChanged();
    }
}
