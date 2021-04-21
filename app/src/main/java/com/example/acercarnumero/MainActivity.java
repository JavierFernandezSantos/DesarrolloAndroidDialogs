package com.example.acercarnumero;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button boton1;
    Button boton2;
    Button boton3;
    Button boton4;
    Button boton5;
    Button boton6;
    Button boton7;
    Button boton8;
    Button boton9;
    Button boton0;
    ArrayList<Button> listaBotones;
    TextView texto;
    int numeroRamdom;
    int intentos;
    AlertDialog dialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton0=(Button) findViewById(R.id.button0);
        boton1=(Button) findViewById(R.id.button1);
        boton2=(Button) findViewById(R.id.button2);
        boton3=(Button) findViewById(R.id.button3);
        boton4=(Button) findViewById(R.id.button4);
        boton5=(Button) findViewById(R.id.button5);
        boton6=(Button) findViewById(R.id.button6);
        boton7=(Button) findViewById(R.id.button7);
        boton8=(Button) findViewById(R.id.button8);
        boton9=(Button) findViewById(R.id.button9);
        texto=(TextView) findViewById(R.id.textoNumeros);
        listaBotones=new ArrayList<>();
        listaBotones.add(boton0);
        listaBotones.add(boton1);
        listaBotones.add(boton2);
        listaBotones.add(boton3);
        listaBotones.add(boton4);
        listaBotones.add(boton5);
        listaBotones.add(boton6);
        listaBotones.add(boton7);
        listaBotones.add(boton8);
        listaBotones.add(boton9);
        texto.setText("HOLA");
        dialogo=createSimpleDialog();
        nuevoJuego();
        Evento ebertoOnclick=new Evento();
        for (Button v:listaBotones){
            v.setOnClickListener(ebertoOnclick);
        }

    }
    class Evento implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(listaBotones.indexOf(v)==numeroRamdom){
                texto.setText("Es el número correcto");
            }else if(listaBotones.indexOf(v)>numeroRamdom){
                texto.setText("Te has pasado");
                intentos++;
            }else{
                texto.setText("Te has quedado corto");
                intentos++;
            }
            if(intentos==3){
                texto.setText("Has perdido");

                dialogo.show();
                nuevoJuego();
            }
        }
    }

    public int generarNumeroAleatorio(){

        numeroRamdom = (int)(Math.random()*10);
        return numeroRamdom;
    }

    public void nuevoJuego(){
        intentos=0;
        numeroRamdom=generarNumeroAleatorio();
    }

    public AlertDialog createSimpleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Fin del juego")
                .setMessage("¿Quieres continuar?")
                .setPositiveButton("Si",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                texto.setText("Intentalo de nuevo");
                            }
                        })
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

        return builder.create();
    }
}