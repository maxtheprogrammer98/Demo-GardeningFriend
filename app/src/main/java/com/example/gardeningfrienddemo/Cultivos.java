package com.example.gardeningfrienddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Cultivos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultivos);

        // se reciben los valores que se pasaron via 'putExtra':
        String valorTemperatura = getIntent().getStringExtra("valTemperatura");
        String valorEstacion = getIntent().getStringExtra("valEstacion");
        String valorRegion = getIntent().getStringExtra("valRegion");

        // se identifican los elementos de la interfaz a modificar:
        TextView temperatura = findViewById(R.id.temperatura_selec);
        TextView estacion = findViewById(R.id.estacion_selec);
        TextView region = findViewById(R.id.region_selec);

        // se agregan los valores ingresados en la act anterior:
        temperatura.append(valorTemperatura);
        estacion.append(valorEstacion);
        region.append(valorRegion);
    }

    public void volverReco(View view){
        Intent intent = new Intent(this, Recomendaciones.class);
        startActivity(intent);
    }
}