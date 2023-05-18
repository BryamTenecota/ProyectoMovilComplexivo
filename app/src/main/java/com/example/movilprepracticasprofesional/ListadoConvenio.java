package com.example.movilprepracticasprofesional;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import Servicios.Servicios;
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

        return rootView;
    }

    private void  MostrarJson(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.18.39:8080/api/convenio/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios servie= retrofit.create(Servicios.class);
        Call<List<Convenio>> call= servie.getConvenio();

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
                        TextView itemdescripcionc = convertView.findViewById(R.id.item_descripcionc);

                        String content = getItem(position);


                        imageView.setImageResource(R.drawable.convenio); // Reemplaza "user" con el nombre de tu imagen
                        itemdescripcionc.setText(" " + content + "\n");
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


                     }
            /*@Override
            public void onResponse(Call<List<Convenio>> call, Response<List<Convenio>> response) {
                if(!response.isSuccessful()){
                    mjsonText.setText("Codigo: "+response.code());
                    return;
                }
                List<Convenio> lista= response.body();
                for(Convenio empresa: lista){
                    String content="";
                    content+="Descripcion: "+empresa.getDescripcion()+"\n";
                    content+="Fecha de elaboración: "+empresa.getFecha_elaboracion()+"\n";
                    content+="Número de convenio: "+empresa.getNumero_convenio()+"\n";
                    content+="Número ITV: "+empresa.getNumero_itv()+"\n";
                    mjsonText.append(content);
                }

            mAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_empresa, lista) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    if (convertView == null) {
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_empresa, parent, false);
                    }


                    ImageView imageView = convertView.findViewById(R.id.item_image);
                    TextView itemnombre = convertView.findViewById(R.id.item_nombreemp);

                    String content = getItem(position);



                    imageView.setImageResource(R.drawable.empresaic); // Reemplaza "user" con el nombre de tu imagen
                    itemnombre.setText("Nombre: "+content+ "\n");
                    return convertView;
                }
            };

                mjsonListView.setAdapter(mAdapter);
        }

            @Override
            public void onFailure(Call<List<Convenio>> call, Throwable t) {
                mjsonText.setText(t.getMessage());
            }

        });
    }
}*/

