package com.example.gardeningfrienddemo;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Cultivos extends AppCompatActivity {

    // se generan los modelos
    ArrayList<cultivosModels> modelsCultivos = new ArrayList<>();
    int[] imagenesCultivos = {R.mipmap.ic_aceituna, R.mipmap.ic_calabaza, R.mipmap.ic_cebolla, R.mipmap.ic_lechuga};

    //parametros del usuario
    String valorTemperatura;
    String valorEstacion;
    String valorRegion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultivos);

        // se reciben los valores que se pasaron via 'putExtra':
        Intent intentRec = getIntent();
        valorTemperatura = intentRec.getStringExtra("valTemperatura");
        valorEstacion = intentRec.getStringExtra("valEstacion");
        valorRegion = intentRec.getStringExtra("valRegion");

        //se identifica el recyclerview de la activity
        RecyclerView recycler = findViewById(R.id.recycler_cultivos);

        //se inicializa funcion para agregar las tarjetas
        addModelsCultivos();

        // se activa el "adapter" para que pase las tarjetas al recycler
        Cultivos_RecyclerViewAdapter adapter = new Cultivos_RecyclerViewAdapter(this,modelsCultivos);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    public void volverReco(View view){
        Intent intent = new Intent(this, Recomendaciones.class);
        startActivity(intent);
    }

    private void addModelsCultivos(){
        // flag que se activa si hay resultados que coinciden con la busqueda
        boolean resultados = false;

        // 1 - se extraen todos los recursos para instanciar los cultivos
        String[] cultivosNombre = getResources().getStringArray(R.array.cultivos_nombres);
        String[] cultivosTemp = getResources().getStringArray(R.array.culvtivos_temp);
        String[] cultivosEst = getResources().getStringArray(R.array.cultivos_estacion);
        String[] cultivosReg = getResources().getStringArray(R.array.cultivos_region);

        // 2 - se iteran los arrays con la informacion y se generan nuevas instancias
        for (int i = 0; i < cultivosNombre.length; i++) {
            // se valida que coincida con los parametros del usuario
            if(cultivosTemp[i].toString().equals(valorTemperatura) && cultivosEst[i].toString().equals(valorEstacion) && cultivosReg[i].toString().equals(valorRegion)){
                // si es correcto se crean las instancias correspondientes
                modelsCultivos.add(new cultivosModels(cultivosNombre[i], cultivosTemp[i], cultivosEst[i], cultivosReg[i], imagenesCultivos[i]));
                resultados = true;
            }
        }

        // si ningun resultado coincide con la busqueda
        if(!resultados){
            //mensaje de error
            Toast.makeText(this, "ningun cultivo coincide con los parametros seleccionados, lo sentimos", Toast.LENGTH_SHORT).show();
        }

    }
}