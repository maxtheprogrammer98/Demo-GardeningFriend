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

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Cultivos extends AppCompatActivity implements cltDetalles {

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
        Cultivos_RecyclerViewAdapter adapter = new Cultivos_RecyclerViewAdapter(this,modelsCultivos,this);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    public void volverReco(View view){
        Intent intent = new Intent(this, Recomendaciones.class);
        startActivity(intent);
    }

    private void addModelsCultivos(){
        // instancia que representa la BD de FireBase
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // flag que se activa si hay resultados que coinciden con la busqueda
        boolean resultados = false;

        // 1 - se extraen los documentos de firebase
        //db.collection("cultivos")
         //       .get()


        // 2 - los documentos son añadidos a un nuevo array


        // si ningun resultado coincide con la busqueda
        if(!resultados){
            //mensaje de error
            Toast.makeText(this, "ningun cultivo coincide con los parametros seleccionados, lo sentimos", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemClick(int position) {
        // 1 - se crea intent para dirigir al user a la pantalla con info extra:
        Intent intent = new Intent(Cultivos.this, cultivos_detalles.class);

        // 2 - se pasan las propiedades necesarias:
        intent.putExtra("NOMBRE_CULTIVO", modelsCultivos.get(position).getNombre());
        intent.putExtra("INFO_CULTIVO", modelsCultivos.get(position).getInfo());

        // 3 - se inicialza la nueva actividad (intent)
        startActivity(intent);
    }
}