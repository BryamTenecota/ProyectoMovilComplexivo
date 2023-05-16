package com.example.movilprepracticasprofesional;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import modelos.bienvenida;

@SuppressLint("WrongViewCast")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void logueado(View view) {
        Intent vntmenustudiante=new Intent(MainActivity.this, bienvenida.class);
        startActivity(vntmenustudiante);
    }
  //  Button myButton;{myButton = findViewById(R.id.iconImageView);}




}