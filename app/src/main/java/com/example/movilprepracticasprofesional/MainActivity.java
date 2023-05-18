package com.example.movilprepracticasprofesional;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.List;

import Notificaciones.AlarmReceiver;
import Notificaciones.AlarmReceiver2;
import Servicios.Servicios;
import at.favre.lib.crypto.bcrypt.BCrypt;
import modelos.Roles;
import modelos.UserSingleton;
import modelos.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@SuppressLint("WrongViewCast")
public class MainActivity extends AppCompatActivity {
    private int alarmID = 1;
    private int alarmID2 = 1;
    private SharedPreferences settings;
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;
    private AlarmManager alarmManager2;
    private PendingIntent alarmIntent2;
    int idus = 0;
    String nombre_carrera="";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private TextView txtUser;
    private TextView txtPasword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUser=findViewById(R.id.username);
        txtPasword=findViewById(R.id.password);
        // Notificación
        settings = getSharedPreferences(getString(R.string.title), Context.MODE_PRIVATE);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(this, alarmID, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Servicio de conversación
        settings = getSharedPreferences(getString(R.string.title), Context.MODE_PRIVATE);
        alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent2 = new Intent(this, AlarmReceiver2.class); // Reemplaza "AnotherAlarmReceiver" con el nombre de tu clase BroadcastReceiver para el segundo servicio
        alarmIntent2 = PendingIntent.getBroadcast(this, alarmID2, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
    }



    public void logueado(View view) {
        MostrarJson();
//        if(txtUser.getText().toString().equals("carlos") && txtPasword.getText().toString().equals("12345")){
//            Intent vtn=new Intent(MainActivity.this,bienvenidadocente.class);
//            startActivity(vtn);
//        }else if(txtUser.getText().toString().equals("wosita") && txtPasword.getText().toString().equals("12345")){
//            Intent vtn=new Intent(MainActivity.this,bienbenidocoordinador.class);
//            startActivity(vtn);
//        }else if(txtUser.getText().toString().equals("empresa") && txtPasword.getText().toString().equals("12345")){
//            Intent vtn=new Intent(MainActivity.this,bienvenidaempresa.class);
//            startActivity(vtn);
//        }else{
//
//        }
    }

    private void  MostrarJson(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.18.39:8080/api/user/")
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
                    String carrera="";
                    int id = 0;
                    us +=user.getCorreo();
                    ps +=user.getContrasenia();
                    id = user.getIdUsuario();
                    carrera=user.getCarrera();
                    String encryptedPassword = ps;
                    String plainPassword = txtPasword.getText().toString();
                    boolean passwordMatch = BCrypt.verifyer().verify(plainPassword.toCharArray(), encryptedPassword).verified;
                    if(txtUser.getText().toString().equals(us) && passwordMatch){
                        for(Roles roles: user.getRoles()){
                            rol += roles.getRolnombre();
                            if(rol.equals("ROLE_ESTUDIANTE")){
                                idus = id; nombre_carrera=carrera;
                                sesionIniciada = true;
                                Intent vntmenustudiante=new Intent(MainActivity.this,bienvenida.class);
                                startActivity(vntmenustudiante);
                                Usuario.rol=rol;
                                Toast.makeText(MainActivity.this, rol, Toast.LENGTH_SHORT).show();
                                MostrarNotiAceptacion();
                                MostrarNotiConvo();

                            }

                            if(rol.equals("ROLE_TUTORACADEMICO")){
                                sesionIniciada = true;
                                Intent vntmenustudiante=new Intent(MainActivity.this,bienvenidadocente.class);
                                startActivity(vntmenustudiante);
                                Usuario.rol=rol;
                                Toast.makeText(MainActivity.this, rol, Toast.LENGTH_SHORT).show();

                            }

                            if(rol.equals("ROLE_TUTOREMPRESARIAL")){
                                idus = id;
                                UserSingleton.setIdUsuario(idus);
                                sesionIniciada = true;
                                Intent vntmenustudiante=new Intent(MainActivity.this,bienvenidaempresa.class);
                                startActivity(vntmenustudiante);
                                Usuario.rol=rol;
                                Toast.makeText(MainActivity.this, rol, Toast.LENGTH_SHORT).show();

                            }
                            Toast.makeText(MainActivity.this, rol, Toast.LENGTH_SHORT).show();
                            if(rol.equals("ROLE_CORDINADOR")){
                                sesionIniciada = true;
                                Intent vntmenustudiante=new Intent(MainActivity.this,bienbenidocoordinador.class);
                                startActivity(vntmenustudiante);
                                Usuario.rol=rol;
                                Toast.makeText(MainActivity.this, rol, Toast.LENGTH_SHORT).show();

                            }

                            if(rol.equals("ROLE_RESPONSABLEPP")){
                                sesionIniciada = true;
                                Intent vntmenustudiante=new Intent(MainActivity.this,bienvenidadresponsable.class);
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




    ///notify

    private void MostrarNotiAceptacion() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.5.94:8080/api/practica/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Servicios apiSoli = retrofit.create(Servicios.class);
        Call<Boolean> call = apiSoli.getEstadoPorUsuario(idus);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Boolean estadosoli = response.body();
                    if (estadosoli) {
                        Calendar today = Calendar.getInstance();
                        today.add(Calendar.SECOND, 5);
                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, today.getTimeInMillis(), alarmIntent);
                    }
                } else {
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
            }
        });
    }

    private void MostrarNotiConvo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.5.94:8080/api/practica/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Servicios apiConvocatoria = retrofit.create(Servicios.class);
        Call<Boolean> call = apiConvocatoria.getConvocatoriaPorCarrera(nombre_carrera);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Boolean estadoConvo = response.body();
                    if (estadoConvo) {
                        Calendar today2 = Calendar.getInstance();
                        today2.add(Calendar.SECOND, 5);
                        alarmManager2.setExact(AlarmManager.RTC_WAKEUP, today2.getTimeInMillis(), alarmIntent2);
                    }
                } else {
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }
}