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
import modelos.Convenio;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ListadoConvenio extends Fragment {
    private TextView mjsonText;
    private ListView mjsonListView;
    private List<Convenio> mConvenioList;
    private ArrayAdapter<String> mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_convenios, container, false);

        mjsonListView = rootView.findViewById(R.id.jsonListView);
        MostrarJson();

        EditText searchEditText = rootView.findViewById(R.id.searchEditText);
        ImageButton searchButton = rootView.findViewById(R.id.btnIcono);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = searchEditText.getText().toString();
                buscarConvenio(searchText);
            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
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
                if (searchText.isEmpty()) {
                    mostrarListaCompleta();
                }
            }
        });

        return rootView;
    }

    private void MostrarJson() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.getBaseUrl() + "convenio/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios service = retrofit.create(Servicios.class);
        Call<List<Convenio>> call = service.getConvenio();

        call.enqueue(new Callback<List<Convenio>>() {
            @Override
            public void onResponse(Call<List<Convenio>> call, Response<List<Convenio>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                mConvenioList = response.body();
                List<String> dataList = new ArrayList<>();
                for (Convenio convenio : mConvenioList) {
                    String content = "";

                    content += "Descripcion: " + convenio.getDescripcion() + "\n\n";
                    content += "Fecha de elaboración: " + convenio.getFecha_elaboracion() + "\n\n";
                    content += "Número de convenio: " + convenio.getNumero_convenio() + "\n\n";
                    content += "Número ITV: " + convenio.getNumero_itv() + "\n\n";

                    dataList.add(content);
                }

                mAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_convenio, dataList) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        if (convertView == null) {
                            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_convenio, parent, false);
                        }

                        ImageView imageView = convertView.findViewById(R.id.item_image);
                        TextView itemDescripcion = convertView.findViewById(R.id.item_descripcionc);

                        String content = getItem(position);

                        imageView.setImageResource(R.drawable.convenio);
                        itemDescripcion.setText(" " + content + "\n");
                        return convertView;
                    }
                };

                mjsonListView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Convenio>> call, Throwable t) {
                // Manejar el error de la llamada aquí
            }
        });
    }

    private void buscarConvenio(String searchText) {
        List<String> filteredList = new ArrayList<>();
        for (Convenio convenio : mConvenioList) {
            if (convenio.getNumero_convenio().toLowerCase().contains(searchText.toLowerCase())) {
                String content = "";
                content += "Descripcion: " + convenio.getDescripcion() + "\n\n";
                content += "Fecha de elaboración: " + convenio.getFecha_elaboracion() + "\n\n";
                content += "Número de convenio: " + convenio.getNumero_convenio() + "\n\n";
                content += "Número ITV: " + convenio.getNumero_itv() + "\n\n";

                filteredList.add(content);
            }
        }

        mAdapter.clear();
        mAdapter.addAll(filteredList);
        mAdapter.notifyDataSetChanged();

        if (searchText.isEmpty()) {
            mostrarListaCompleta();
        }
    }

    private void mostrarListaCompleta() {
        List<String> dataList = new ArrayList<>();
        for (Convenio convenio : mConvenioList) {
            String content = "";

            content += "Descripcion: " + convenio.getDescripcion() + "\n\n";
            content += "Fecha de elaboración: " + convenio.getFecha_elaboracion() + "\n\n";
            content += "Número de convenio: " + convenio.getNumero_convenio() + "\n\n";
            content += "Número ITV: " + convenio.getNumero_itv() + "\n\n";

            dataList.add(content);
        }

        mAdapter.clear();
        mAdapter.addAll(dataList);
        mAdapter.notifyDataSetChanged();
    }
}
