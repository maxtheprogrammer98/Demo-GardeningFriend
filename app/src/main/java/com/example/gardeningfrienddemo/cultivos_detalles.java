package com.example.gardeningfrienddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class cultivos_detalles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultivos_detalles);

        // elementos interfaz
        TextView textView_titulo = findViewById(R.id.titl_cultivo);
        TextView textView_temperatura = findViewById(R.id.temp_cultivo);
        TextView textView_estacion = findViewById(R.id.est_cultivo);
        TextView textView_region = findViewById(R.id.region_cultivo);

        // 1 - se reciben los valores de la pantalla anterior via intent
        String nombreCultivo = getIntent().getStringExtra("NOMBRE_CULTIVO");
        String tempCultivo = getIntent().getStringExtra("TEMPERATURA_CULTIVO");
        String estacionCultivo = getIntent().getStringExtra("ESTACION_CULTIVO");
        String regionCultivo = getIntent().getStringExtra("REGION_CULTIVO");

        // 2 - estos nuevos valores e interpolan a la plantilla XML
        textView_titulo.setText(nombreCultivo);
        textView_temperatura.append(tempCultivo);
        textView_estacion.append(estacionCultivo);
        textView_region.append(regionCultivo);

    }

    //public void volverRecom(View view){
        // 1 - se crea intent para volver a "cultivos (recomendaciones)"
    //    Intent intent = new Intent(this, Cultivos.class);
        // 2 - se inicializa dicha actividad
    //    startActivity(intent);
    //}
}