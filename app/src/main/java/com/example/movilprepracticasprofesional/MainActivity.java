package com.example.movilprepracticasprofesional;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import Servicios.Servicios;
import at.favre.lib.crypto.bcrypt.BCrypt;
import modelos.Roles;
import modelos.UserSingleton;
import modelos.Usuario;
import modelos.bienvenida;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@SuppressLint("WrongViewCast")
public class MainActivity extends AppCompatActivity {

    private TextView txtUser;
    private TextView txtPasword;

    int idus = 0;

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
                int id = 0;
                for (Usuario user: list){
                    String ps="";
                    String us="";
                    String rol="";
                    us +=user.getCorreo();
                    ps +=user.getContrasenia();
                    id = user.getIdUsuario();
                     // Reemplaza esto con la id capturada

                    String encryptedPassword = ps;
                    String plainPassword = txtPasword.getText().toString();
                    boolean passwordMatch = BCrypt.verifyer().verify(plainPassword.toCharArray(), encryptedPassword).verified;
                    if(txtUser.getText().toString().equals(us) && passwordMatch){

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
                                Intent vntmenustudiante=new Intent(MainActivity.this, bienvenida.class);
                                startActivity(vntmenustudiante);
                                Usuario.rol=rol;
                                Toast.makeText(MainActivity.this, rol, Toast.LENGTH_SHORT).show();

                            }
                            if(rol.equals("ROLE_TUTOREMPRESARIAL")){
                                idus = id;
                                UserSingleton.setIdUsuario(idus);
                                sesionIniciada = true;
                                Intent vntmenustudiante=new Intent(MainActivity.this,bienvenida.class);
                                startActivity(vntmenustudiante);
                                Usuario.rol=rol;
                                Toast.makeText(MainActivity.this, rol, Toast.LENGTH_SHORT).show();
                                Toast.makeText(MainActivity.this, "ID de Usuario: " + idus, Toast.LENGTH_SHORT).show();

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