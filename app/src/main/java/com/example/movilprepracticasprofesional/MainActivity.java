package com.example.movilprepracticasprofesional;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Servicios.Servicios;
import modelos.Roles;
import modelos.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@SuppressLint("WrongViewCast")
public class MainActivity extends AppCompatActivity {

    private TextView txtUser;
    private TextView txtPasword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUser=findViewById(R.id.username);
        txtPasword=findViewById(R.id.password);
    }


    public void logueado(View view) {
        MostrarJson();
    }

    private void  MostrarJson(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.5.94:8080/api/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

            Servicios servi= retrofit.create(Servicios.class);
            Call<List<Usuario>> call= servi.getUsuarios();
            call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(!response.isSuccessful()){
                    return;
                }
                List<Usuario> list= response.body();
                boolean sesionIniciada = false;

                for (Usuario user: list){
                    String ps="";
                    String us="";
                    String rol="";
                    us +=user.getCorreo();
                    ps +=user.getContrasenia();

                    Toast.makeText(MainActivity.this, ps, Toast.LENGTH_SHORT).show();
                    if(txtUser.getText().toString().equals(us)){
                        for(Roles roles: user.getRoles()){
                            rol += roles.getRolnombre();
                            Toast.makeText(MainActivity.this, rol, Toast.LENGTH_SHORT).show();
                            Usuario.rol=rol;
                            if(rol.equals("ROLE_ESTUDIANTE")){

                                sesionIniciada = true;
//                                Toast.makeText(MainActivity.this, "Entrastes como estudiante", Toast.LENGTH_SHORT).show();
                                Intent vntmenustudiante=new Intent(MainActivity.this,bienvenida.class);
                                startActivity(vntmenustudiante);

                            }
                            if(rol.equals("ROLE_TUTORACADEMICO")){
                                sesionIniciada = true;

                                Toast.makeText(MainActivity.this, "Entrastes como Tutor Empresarial", Toast.LENGTH_SHORT).show();

                            }
                            if(rol.equals("ROLE_TUTOREMPRESARIAL")){
                                sesionIniciada = true;

                                Toast.makeText(MainActivity.this, "Entrastes como Tutor Ecargado", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                }
                if (!sesionIniciada) {
                    Toast.makeText(MainActivity.this, "No se pudo iniciar seccion", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}