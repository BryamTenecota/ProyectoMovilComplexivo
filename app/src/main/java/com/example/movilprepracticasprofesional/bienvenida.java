package com.example.movilprepracticasprofesional;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import modelos.UserSingleton;
import modelos.Usuario;

public class bienvenida extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);




        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout= findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView nombreTextView = headerView.findViewById(R.id.nameEst);
        TextView apellidoTextView = headerView.findViewById(R.id.lastnameEst);
        String nombreEst = UserSingleton.getNombres();
        nombreTextView.setText(nombreEst);
        String apellidoEst = UserSingleton.getApellidos();
        apellidoTextView.setText(apellidoEst);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();
        Toast.makeText(this, Usuario.rol, Toast.LENGTH_SHORT).show();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_view:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;

            case R.id.nav_anexos:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentAnexos()).commit();
                Uri uri = Uri.parse("https://drive.google.com/drive/folders/1vD5YU0nRh920S1pJHpRXeQWsqEPtXYYn?usp=sharing");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;

            case R.id.nav_informacion:
//                if(Usuario.rol.equals("ROLE_TUTORACADEMICO")){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InfoFragment()).commit();
//                }
                break;
            case R.id.nav_convocatoria_disponible:
//                if(Usuario.rol.equals("ROLE_TUTORACADEMICO")){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentConvoctoriasDis()).commit();
//                }
                break;



            case R.id.nav_exit:
                Toast.makeText(bienvenida.this, "", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(bienvenida.this);
                builder.setTitle("Confirmación");
                builder.setMessage("¿Desea salir de la aplicación?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Cerrar sesión
                        // ...

                        // Redirigir a la vista de inicio de sesión
                        Intent logout = new Intent(bienvenida.this, MainActivity.class);
                        startActivity(logout);

                        // Finalizar la actividad actual
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // No hacer nada, simplemente cerrar el diálogo
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();




        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}