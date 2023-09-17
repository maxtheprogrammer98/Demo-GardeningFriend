package com.example.gardeningfrienddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class cultivos_detalles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultivos_detalles);

        // elementos interfaz
        TextView textView_titulo = findViewById(R.id.titulo_cultivo_detalles);
        TextView textView_info = findViewById(R.id.info_cultivo_detalles);

        // 1 - se reciben los valores de la pantalla anterior via intent
        String nombreCultivo = getIntent().getStringExtra("NOMBRE_CULTIVO");
        String infoCultivo = getIntent().getStringExtra("INFO_CULTIVO");

        // 2 - estos nuevos valores e interpolan a la plantilla XML
        textView_titulo.setText(nombreCultivo);
        textView_info.setText(infoCultivo);

    }

    public void btn_volver_reco(View view){
        // 1 - se crea el intent
        Intent intent = new Intent(this, Recomendaciones.class);
        // 2 - se redirige al nuevo intent
        startActivity(intent);
    }

    public void btn_volver_cultivos(){
        // se cierra la actividad actual mostrando la act previa
        finish();
    }




}