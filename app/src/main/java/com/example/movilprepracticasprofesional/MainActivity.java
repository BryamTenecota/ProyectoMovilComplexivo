package com.example.movilprepracticasprofesional;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Notificaciones.AlarmReceiver;
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
    private int alarmID = 1;
    private SharedPreferences settings;
    private boolean estadoAceptacionPPP = true;
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;
    int idus = 0;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private TextView txtUser;
    private TextView txtPasword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUser = findViewById(R.id.username);
        txtPasword = findViewById(R.id.password);

        ///notify
        settings = getSharedPreferences(getString(R.string.title), Context.MODE_PRIVATE);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(this, alarmID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }


    public void logueado(View view) {

        MostrarJson();
      //  MostrarNoti();
    }


    private void MostrarJson() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.18.23:8080/api/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios servi = retrofit.create(Servicios.class);
        Call<List<Usuario>> call = servi.getUsuarios();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<Usuario> list = response.body();
                boolean sesionIniciada = false;
                String ps = "";
                String us = "";
                String rol = "";
                int id = 0;
                for (Usuario user : list) {
                    us = user.getCorreo();
                    ps = user.getContrasenia();
                    id = user.getIdUsuario();
                    if (txtUser.getText().toString().equals(us)) {
                        for (Roles roles : user.getRoles()) {
                            rol = roles.getRolnombre();
                        }
                        if (rol.equals("ROLE_ESTUDIANTE")) {
                            idus = id;
                            Toast.makeText(MainActivity.this, "Este es el id del usuario: " + idus, Toast.LENGTH_SHORT).show();
                            sesionIniciada = true;
                          //  Toast.makeText(MainActivity.this, "cEste es el id del usuario: " + us, Toast.LENGTH_SHORT).show();
                            Intent vntmenustudiante = new Intent(MainActivity.this, bienvenida.class);
                            startActivity(vntmenustudiante);
                            MostrarNoti();
                        }
                        if (rol.equals("ROLE_TUTORACADEMICO")) {
                            sesionIniciada = true;

                            //  Toast.makeText(MainActivity.this, "Entrastes como Tutor Empresarial", Toast.LENGTH_SHORT).show();

                        }
                        if (rol.equals("ROLE_TUTOREMPRESARIAL")) {
                            sesionIniciada = true;

                            Toast.makeText(MainActivity.this, "Entrastes como Tutor Ecargado", Toast.LENGTH_SHORT).show();

                        }
                        break;
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

    private void MostrarNoti() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.18.23:8080/api/practica/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Servicios apiInterface = retrofit.create(Servicios.class);
        Call<Boolean> call = apiInterface.getEstadoPorUsuario(idus);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Boolean estado = response.body();
               //     Toast.makeText(MainActivity.this, "El estado de la soli es: " + estado, Toast.LENGTH_SHORT).show();
                  //    if (estado) {
                    //    Calendar today = Calendar.getInstance();
                      //  int hour = today.get(Calendar.HOUR_OF_DAY);
                     //   int minute = today.get(Calendar.MINUTE) + 1;
                      //  if (minute >= 60) {
                        //    minute = 0;
                          //  hour += 1;
                        //}
                        //today.set(Calendar.HOUR_OF_DAY, hour);
                        //today.set(Calendar.MINUTE, minute);
                        //today.set(Calendar.SECOND, 0);
                        //alarmManager.setExact(AlarmManager.RTC_WAKEUP, today.getTimeInMillis(), alarmIntent);
                    //}
                    if (estado) {
                        Calendar today = Calendar.getInstance();
                        today.add(Calendar.SECOND, 10);
                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, today.getTimeInMillis(), alarmIntent);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "No vale", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
              //  Toast.makeText(MainActivity.this, "No se conecta", Toast.LENGTH_SHORT).show();
            }
        });
    }
}