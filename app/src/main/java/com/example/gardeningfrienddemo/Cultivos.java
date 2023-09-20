package com.example.gardeningfrienddemo;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.ktx.Firebase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

public class Cultivos extends AppCompatActivity implements cltDetalles {

    // se generan los modelos
    ArrayList<cultivosModels> modelsCultivos = new ArrayList<>();
    //int[] imagenesCultivos = {R.mipmap.ic_aceituna, R.mipmap.ic_calabaza, R.mipmap.ic_cebolla, R.mipmap.ic_lechuga};

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

        // 1 - se extraen los documentos de firebase
        db.collection("cultivos")
                // se realiza una get request para acceder a todos los documentos
                .get()
                // este evento valida si la peticion se dio exitosamente
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            // la request se realizo exitosamente
                            Toast success = Toast.makeText(Cultivos.this, "peticion exitosa", Toast.LENGTH_SHORT);
                            success.show();

                            for (QueryDocumentSnapshot document : task.getResult()){
                                // se guardan las propiedades en variables para despues instanciar
                                String nombre = document.getString("nombre");
                                String temperatura = document.getString("temperatura");
                                String estacion = document.getString("estacion");
                                String region = document.getString("region");
                                String icono = document.getString("icono");
                                String info = document.getString("informacion");

                                // se crea un nuevo cult en base a la info recibida
                                cultivosModels cultivo = new cultivosModels(nombre,temperatura,estacion,region,info,icono);

                                // se agrega al array (si coincide con los parametros del user)
                                if(cultivo.temperatura.equals(valorTemperatura) && cultivo.estacion.equals(valorEstacion) && cultivo.region.equals(valorRegion)){
                                    modelsCultivos.add(cultivo);
                                }
                            }
                        } else {
                            Toast.makeText(Cultivos.this, "ha ocurrido un error al conectar con la BD", Toast.LENGTH_SHORT).show();
                        }

                        //mensaje en caso de que ningun cultivo coincida con los pametros del user
                        if(modelsCultivos.size() == 0){
                            Toast.makeText(Cultivos.this, "ningun cultivo coincide con los parametros brindados", Toast.LENGTH_SHORT).show();
                        } else{
                            // probando si los cultivos se agregan al arraylist
                            int cant = modelsCultivos.size();
                            String cant_text = Integer.toString(cant);
                            Toast.makeText(Cultivos.this, "cantidad cultivos " + cant_text, Toast.LENGTH_SHORT).show();
                        }

                    }
                });

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