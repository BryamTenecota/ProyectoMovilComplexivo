package com.example.movilprepracticasprofesional;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import Servicios.Servicios;
import modelos.BaseUrl;
import modelos.UserSingleton;
import modelos.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentPerfilU extends Fragment {

    private TextView nombreTextView;
    private TextView apellidoTextView;
    private TextView cedulaTextView;
    private TextView correoTextView;
    private TextView carreraTextView;
    private Usuario mUsuario;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfilu, container, false);
        nombreTextView = view.findViewById(R.id.PNombres);
        apellidoTextView = view.findViewById(R.id.PApellidos);
        cedulaTextView = view.findViewById(R.id.PCedula);
        correoTextView = view.findViewById(R.id.PCorreo);
        carreraTextView = view.findViewById(R.id.PCarrera);
        MostrarJson();
        return view;
    }

    private void MostrarJson() {
        int id = UserSingleton.getIdUsuario();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.getBaseUrl() + "user/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios service = retrofit.create(Servicios.class);
        Call<Usuario> call = service.getUsuariosbyId(id);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (!response.isSuccessful()) {
                    // Manejar el código de respuesta no exitoso aquí
                    return;
                }

                mUsuario = response.body();

                if (mUsuario != null) {
                    // Establece los valores en los TextView correspondientes
                    nombreTextView.setText( mUsuario.getNombres());
                    apellidoTextView.setText(mUsuario.getApellidos());
                    cedulaTextView.setText(mUsuario.getCedula());
                    correoTextView.setText(mUsuario.getCorreo());
                    carreraTextView.setText(mUsuario.getCarrera());
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                // Manejar el fallo de la llamada aquí
            }
        });
    }
}
