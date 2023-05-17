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
import at.favre.lib.crypto.bcrypt.BCrypt;
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
                .baseUrl("http://192.168.0.158:8080/api/user/")
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
                    //String encryptedPassword = ps;
                    //String plainPassword = txtPasword.getText().toString();
                    //boolean passwordMatch = BCrypt.verifyer().verify(plainPassword.toCharArray(), encryptedPassword).verified;
                    if(txtUser.getText().toString().equals(us) /*&& passwordMatch*/){
                        for(Roles roles: user.getRoles()){
                            rol = roles.getRolnombre();
                            if(rol.equals("ROLE_ESTUDIANTE")){
                                sesionIniciada = true;
                                Intent vntmenustudiante=new Intent(MainActivity.this,bienvenida.class);
                                startActivity(vntmenustudiante);
                                Usuario.rol=rol;
                                Toast.makeText(MainActivity.this, rol, Toast.LENGTH_SHORT).show();

                            }

                            if(rol.equals("ROLE_TUTORACADEMICO")){
                                sesionIniciada = true;
                                Intent vntmenustudiante=new Intent(MainActivity.this,bienvenida.class);
                                startActivity(vntmenustudiante);
                                Usuario.rol=rol;
                                Toast.makeText(MainActivity.this, rol, Toast.LENGTH_SHORT).show();

                            }
                            if(rol.equals("ROLE_TUTOREMPRESARIAL")){
                                sesionIniciada = true;
                                Intent vntmenustudiante=new Intent(MainActivity.this,bienvenida.class);
                                startActivity(vntmenustudiante);
                                Usuario.rol=rol;
                                Toast.makeText(MainActivity.this, rol, Toast.LENGTH_SHORT).show();

                            }
                            if(rol.equals("ROLE_CORDINADOR")){
                                Intent vntmenustudiante=new Intent(MainActivity.this,bienvenida.class);
                                startActivity(vntmenustudiante);
                                Usuario.rol=rol;
                                Toast.makeText(MainActivity.this, rol, Toast.LENGTH_SHORT).show();

                            }
                            if(rol.equals("ROLE_RESPONSABLEPP")){
                                Intent vntmenustudiante=new Intent(MainActivity.this,bienvenida.class);
                                startActivity(vntmenustudiante);
                                Usuario.rol=rol;
                                Toast.makeText(MainActivity.this, rol, Toast.LENGTH_SHORT).show();

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