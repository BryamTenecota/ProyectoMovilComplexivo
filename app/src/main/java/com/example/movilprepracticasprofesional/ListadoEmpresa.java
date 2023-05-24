package com.example.movilprepracticasprofesional;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import modelos.Empresa;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListadoEmpresa extends Fragment {
    private ListView mjsonListView;
    private List<Empresa> mEmpresaList;
    private ArrayAdapter<String> mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empresas, container, false);

        mjsonListView = view.findViewById(R.id.jsonListView);
        MostrarJson();



        ImageButton buscarButton = view.findViewById(R.id.btnIcono);
        buscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText searchText = view.findViewById(R.id.searchEditText);
                String textoBusqueda = searchText.getText().toString();
                buscar(textoBusqueda);
            }
        });

        EditText searchText = view.findViewById(R.id.searchEditText);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No se requiere ninguna acción antes de que el texto cambie
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No se requiere ninguna acción mientras el texto cambia
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String searchText = editable.toString();
                buscar(searchText);
            }
        });

        return view;
    }



    private void MostrarJson() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.getBaseUrl() + "empresa/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios service = retrofit.create(Servicios.class);
        Call<List<Empresa>> call = service.getEmpresa();

        call.enqueue(new Callback<List<Empresa>>() {
            @Override
            public void onResponse(Call<List<Empresa>> call, Response<List<Empresa>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                mEmpresaList = response.body();
                List<String> dataList = new ArrayList<>();
                for (Empresa empresa : mEmpresaList) {
                    String content = "";
                    content += "Nombre: " + empresa.getNombreEmpresa() + "\n\n";
                    content += "Teléfono: " + empresa.getNumeroTelefono() + "\n\n";
                    content += "Correo: " + empresa.getCorreo() + "\n\n";
                    content += "Ciudad: " + empresa.getCiudad() + "\n\n";
                    content += "Dirección: " + empresa.getDireccion() ;
                    dataList.add(content);
                }

                mAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_empresa, dataList) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        if (convertView == null) {
                            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_empresa, parent, false);
                        }

                        ImageView imageView = convertView.findViewById(R.id.item_image);
                        TextView itemNombre = convertView.findViewById(R.id.item_nombreemp);
                        Button boton = convertView.findViewById(R.id.btnubi);

                        String content = getItem(position);

                        imageView.setImageResource(R.drawable.empresaic);
                        itemNombre.setText(" " + content );

                        // Configurar el OnClickListener para el botón
                        final String direccionEmpresa = mEmpresaList.get(position).getDireccion();

                        // Configurar el OnClickListener para el botón
                        boton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Mostrar la dirección en un Toast
                                searchInGoogleMaps(direccionEmpresa);
                            }
                        });


                        return convertView;
                    }
                };


                mjsonListView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Empresa>> call, Throwable t) {
                // Manejar el error de la llamada aquí
            }
        });
    }

    private void searchInGoogleMaps(String address) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(getActivity(), "La aplicación de Google Maps no está instalada.", Toast.LENGTH_SHORT).show();
        }
    }
    private void buscar(String texto) {
        List<String> resultados = new ArrayList<>();
        for (Empresa empresa : mEmpresaList) {
            if (empresa.getNombreEmpresa().toLowerCase().contains(texto.toLowerCase())) {
                String content = "";
                content += "Nombre: " + empresa.getNombreEmpresa() + "\n\n";
                content += "Teléfono: " + empresa.getNumeroTelefono() + "\n\n";
                content += "Correo: " + empresa.getCorreo() + "\n\n";
                content += "Ciudad: " + empresa.getCiudad() +"\n\n";
                content += "Direccion: " + empresa.getDireccion() ;
                resultados.add(content);
            }
        }
        mAdapter.clear();
        mAdapter.addAll(resultados);
        mAdapter.notifyDataSetChanged();

        if (texto.isEmpty()) {
            mostrarListaCompleta();
        }
    }

    private void mostrarListaCompleta() {
        List<String> dataList = new ArrayList<>();
        for (Empresa empresa : mEmpresaList) {
            String content = "";
            content += "Nombre: " + empresa.getNombreEmpresa() + "\n\n";
            content += "Teléfono: " + empresa.getNumeroTelefono() + "\n\n";
            content += "Correo: " + empresa.getCorreo() + "\n\n";
            content += "Ciudad: " + empresa.getCiudad() +"\n\n" ;
            content += "Direccion: " + empresa.getDireccion() ;
            dataList.add(content);
        }

        mAdapter.clear();
        mAdapter.addAll(dataList);
        mAdapter.notifyDataSetChanged();
    }
}