package com.example.gardeningfrienddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.Serializable;

public class Recomendaciones extends AppCompatActivity {

    //contiene el texto de la opc seleccionada
    String tempSelec;
    String estSelec;
    String regSelec;
    // flags para verificar que cada grupo haya sido chequeado
    boolean flagTemp;
    boolean flagEst;
    boolean flagReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendaciones);
        // identificando boton
        Button buscarBtn = findViewById(R.id.buscar_btn);
    }

    public void btnTempClicked(View view){
        // var elem selec
        RadioButton btnSelec;
        // se verifica que un elem fue selec:
        flagTemp = ((RadioButton) view).isChecked();
        // se identifica que btn fue seleccionado
        if (view.getId() == R.id.rango0) {
            btnSelec = findViewById(R.id.rango0);
            tempSelec = btnSelec.getText().toString();

        } else if (view.getId() == R.id.rango1){
            btnSelec = findViewById(R.id.rango1);
            tempSelec = btnSelec.getText().toString();

        } else if (view.getId() == R.id.rango2){
            btnSelec = findViewById(R.id.rango2);
            tempSelec = btnSelec.getText().toString();

        } else if (view.getId() == R.id.rango3){
            btnSelec = findViewById(R.id.rango3);
            tempSelec = btnSelec.getText().toString();

        } else if (view.getId() == R.id.rango4){
            btnSelec = findViewById(R.id.rango3);
            tempSelec = btnSelec.getText().toString();

        } else {
            tempSelec = "default";
        }

        // muestra la opc selec
        Toast avisoSelec = Toast.makeText(this, "seleccionaste: " + tempSelec, Toast.LENGTH_SHORT);
        avisoSelec.show();
    }

    public void btnEstacClicked(View view){
        // var elem selec
        RadioButton btnSelec;
        // se verifica que un elem fue selec:
        flagEst = ((RadioButton) view).isChecked();
        // se identifica que btn fue seleccionado
        if(view.getId() == R.id.opc_verano){
            btnSelec = findViewById(R.id.opc_verano);
            estSelec = btnSelec.getText().toString();

        } else if (view.getId() == R.id.opc_otono){
            btnSelec = findViewById(R.id.opc_otono);
            estSelec = btnSelec.getText().toString();

        } else if (view.getId() == R.id.opc_invierno){
            btnSelec = findViewById(R.id.opc_invierno);
            estSelec = btnSelec.getText().toString();

        } else if (view.getId() == R.id.opc_primavera){
            btnSelec = findViewById(R.id.opc_primavera);
            estSelec = btnSelec.getText().toString();

        } else {
            estSelec = "default";
        }

        //muestra la opc seleccionada
        Toast avisoSelec = Toast.makeText(this, "seleccionaste: " + estSelec, Toast.LENGTH_SHORT);
        avisoSelec.show();

    }

    public void btnRegClicked(View view){
        // var elem selec
        RadioButton btnSelec;
        // se verifica que un elem fue selec:
        flagReg = ((RadioButton) view).isChecked();
        // se identifica que btn fue seleccionado
        if (view.getId() == R.id.reg_norte){
            btnSelec = findViewById(R.id.reg_norte);
            regSelec = btnSelec.getText().toString();

        } else if (view.getId() == R.id.reg_centro){
            btnSelec = findViewById(R.id.reg_centro);
            regSelec = btnSelec.getText().toString();

        } else if (view.getId() == R.id.reg_cuyo){
            btnSelec = findViewById(R.id.reg_cuyo);
            regSelec = btnSelec.getText().toString();

        } else if (view.getId() == R.id.reg_patagonica){
            btnSelec = findViewById(R.id.reg_patagonica);
            regSelec = btnSelec.getText().toString();

        } else {
            regSelec = "default";
        }

        // este aviso muestra la seleccion del user
        Toast avisoSelec = Toast.makeText(this,"seleccionaste: " + regSelec, Toast.LENGTH_SHORT);
        avisoSelec.show();
    }

    public void abrirActCultivos(View view){
        // 1 - se crea intent
        Intent intent = new Intent(Recomendaciones.this, Cultivos.class);

        // aviso de error en caso que no se haya selec ninguna opcion
        Toast errorCampos = Toast.makeText(this,"debes seleccionar una opcion en cada caso", Toast.LENGTH_SHORT);

        // 2 - se pasan los val necesarios via put extra
        intent.putExtra("valTemperatura", tempSelec);
        intent.putExtra("valEstacion", estSelec);
        intent.putExtra("valRegion", regSelec);

        // 3 - se inicializa intent (si cada parametro fue especificado)
        if (flagTemp && flagEst && flagReg){
            startActivity(intent);
        } else {
            errorCampos.show();
        }
    }


}