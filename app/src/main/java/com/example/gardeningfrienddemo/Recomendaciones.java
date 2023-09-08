package com.example.gardeningfrienddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Recomendaciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendaciones);
        // identificando boton
        Button buscarBtn = findViewById(R.id.buscar_btn);
    }

    public void abrirActCultivos(View view){
        //identificando text inputs
        EditText inputTemp = findViewById(R.id.input_temperatura);
        EditText inputEst = findViewById(R.id.input_estacion);
        EditText inputReg = findViewById(R.id.input_region);
        // inicializando nueva actividad
        Intent intent = new Intent(this, Cultivos.class);
        // TODO: AGREGAR propiedades putExtra para pasar actividaes a la nueva act.
        startActivity(intent);
    }


}