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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import Servicios.Servicios;
import modelos.BaseUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentConvocatoria extends Fragment {
    private ListView mjsonListView;
    private List<Object[]> mObjectList;
    private ArrayAdapter<Object[]> mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_convocatoria, container, false);

        mjsonListView = rootView.findViewById(R.id.jsonListView);
        MostrarJson();

        EditText searchEditText = rootView.findViewById(R.id.searchEditText);
        ImageButton searchButton = rootView.findViewById(R.id.btnIcono);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = searchEditText.getText().toString();
                buscarConvocatoria(searchText);
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
                buscarConvocatoria(searchText);
            }
        });

        return rootView;
    }

    private void MostrarJson() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.getBaseUrl() + "convocatorias/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios service = retrofit.create(Servicios.class);
        Call<List<Object[]>> call = service.getConvocatorias();

        call.enqueue(new Callback<List<Object[]>>() {
            @Override
            public void onResponse(Call<List<Object[]>> call, Response<List<Object[]>> response) {
                if (!response.isSuccessful()) {
                    // Manejar el código de respuesta no exitoso aquí
                    return;
                }

                mObjectList = response.body();
                mAdapter = new ArrayAdapter<Object[]>(getActivity(), R.layout.list_convocatorias, mObjectList) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        if (convertView == null) {
                            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_convocatorias, parent, false);
                        }

                        ImageView itemImage = convertView.findViewById(R.id.item_image);
                        TextView itemnombreCon = convertView.findViewById(R.id.item_nombreCon);
                        TextView itemnombreSoli = convertView.findViewById(R.id.item_nombreSoli);
                        TextView itemfechaenvio = convertView.findViewById(R.id.item_fechaenvio);
                        TextView itemfechaacep = convertView.findViewById(R.id.item_fechaacep);

                        Object[] object = getItem(position);
                        String nombreCon = (String) object[0];
                        String nombreSoli = (String) object[1];
                        String fechaenvio = (String) object[2];
                        String fechaacep = (String) object[3];

                        itemnombreCon.setText("Nombre Convocatoria: " + nombreCon + "\n");
                        itemnombreSoli.setText("Nombre Solicitud: " + nombreSoli + "\n");
                        itemfechaenvio.setText("Fecha Envio: " + fechaenvio + "\n");
                        itemfechaacep.setText("Fecha Aceptación: " + fechaacep + "\n");
                        itemImage.setImageResource(R.drawable.convoca);

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

    private void buscarConvocatoria(String searchText) {
        if (searchText.isEmpty()) {
            mAdapter.clear();
            mAdapter.addAll(mObjectList);
            mAdapter.notifyDataSetChanged();
            return;
        }

        List<Object[]> filteredList = new ArrayList<>();
        for (Object[] object : mObjectList) {
            String nombreCon = (String) object[0];

            if (nombreCon.toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(object);
            }
        }

        mAdapter.clear();
        mAdapter.addAll(filteredList);
        mAdapter.notifyDataSetChanged();
    }
}
