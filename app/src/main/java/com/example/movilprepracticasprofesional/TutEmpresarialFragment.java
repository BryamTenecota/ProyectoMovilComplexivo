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
import modelos.Convocatorias;
import modelos.TutorEmpresarial;
import modelos.UserSingleton;
import modelos.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TutEmpresarialFragment extends Fragment {
    private ListView mjsonListView;
    private List<Object[]> mObjectList;
    private ArrayAdapter<Object[]> mAdapter;

    private TextView mjsonText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tutempresarial, container, false);
        //rootView = inflater.inflate(R.layout.fragment_convocatoria, container, false);
        mjsonListView = rootView.findViewById(R.id.mjsonListView);
        MostrarJson();

        return rootView;
    }

    private void  MostrarJson() {
        int id = UserSingleton.getIdUsuario();
        Toast.makeText(getActivity(), "ID de Usuario: " + id, Toast.LENGTH_SHORT).show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.158:8080/api/tutorEmp/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios service = retrofit.create(Servicios.class);
        Call<List<Object[]>> call = service.getInfoTutorEmpresarial();

        call.enqueue(new Callback<List<Object[]>>() {
            @Override
            public void onResponse(Call<List<Object[]>> call, Response<List<Object[]>> response) {
                if (!response.isSuccessful()) {
                    // Manejar el código de respuesta no exitoso aquí
                    return;
                }

                mObjectList = response.body();
                mAdapter = new ArrayAdapter<Object[]>(getActivity(), R.layout.list_tutempresarial, mObjectList) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        if (convertView == null) {
                            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_tutempresarial, parent, false);
                        }

                        ImageView itemImage = convertView.findViewById(R.id.item_image);
                        TextView itemnombreempresa = convertView.findViewById(R.id.txtnombreempresa);
                        TextView itemnombres = convertView.findViewById(R.id.txtNombres);
                        TextView itemcargo = convertView.findViewById(R.id.txtCargo);
                        TextView itemnumerocontacto = convertView.findViewById(R.id.txtNumerocontacto);

                        Object[] object = getItem(position);
                        String nombreempresa = (String) object[0];
                        String nombres = (String) object[1];
                        String cargo = (String) object[2];
                        String numerocontacto = (String) object[3];

                        // Asignar los datos al diseño de elementos de lista
                        itemnombreempresa.setText("Nombres: " + nombreempresa + "\n");
                        itemnombres.setText("Apellidos: " + nombres + "\n");
                        itemcargo.setText("Cédula: " + cargo + "\n");
                        itemnumerocontacto.setText("Cédula: " + numerocontacto + "\n");
                        itemImage.setImageResource(R.drawable.usere); // Reemplaza "imagen" con el nombre de tu imagen

                        return convertView;
                    }
                };

                mjsonListView.setAdapter(mAdapter);
            }
            @Override
            public void onFailure(Call<List<Object[]>>call, Throwable t) {
                mjsonText.setText(t.getMessage());
            }
        });
    }
}
