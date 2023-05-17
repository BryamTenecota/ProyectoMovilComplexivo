package modelos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.movilprepracticasprofesional.ConvocatoriaFragment;
import com.example.movilprepracticasprofesional.HomeFragment;
import com.example.movilprepracticasprofesional.InfoFragment;
import com.example.movilprepracticasprofesional.MainActivity;
import com.example.movilprepracticasprofesional.NotificacionFragment;
import com.example.movilprepracticasprofesional.PracticanteFragment;
import com.example.movilprepracticasprofesional.R;
import com.example.movilprepracticasprofesional.RespuestaFragment;
import com.google.android.material.navigation.NavigationView;

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

            case R.id.nav_convocatoria:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConvocatoriaFragment()).commit();
                Toast.makeText(this, Usuario.rol, Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_informacion:
                if(Usuario.rol.equals("ROLE_TUTORACADEMICO")){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InfoFragment()).commit();
                }
                Toast.makeText(this, "No se puede mostrar este elemento", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_notification_:

//                if(Usuario.rol.equals("ROLE_TUTORACADEMICO")){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NotificacionFragment()).commit();
//                }
//                Toast.makeText(this, "No se puede mostrar este elemento", Toast.LENGTH_SHORT).show();

                break;

            case R.id.nav_respuesta:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RespuestaFragment()).commit();
                break;

            case R.id.nav_practicante:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PracticanteFragment()).commit();
                break;
            case R.id.nav_exit:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                Intent logout=new Intent(bienvenida.this, MainActivity.class);
                startActivity(logout);
                break;
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