package com.alvaropedrajas.calculonotas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class ResultadoActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        View v;
        Intent intent = this.getIntent();
        ArrayList<Double> notas = (ArrayList<Double>) intent.getSerializableExtra("notas");
        TextView tv_notas = (TextView) findViewById(R.id.tv_nota);
        Double aux=media(notas);
        if(!aux.isNaN()) {
            tv_notas.setText(aux.toString());
        }else{
            Context context = getApplicationContext();
            Toast.makeText(context, "¡La operación es incorrecta!", Toast.LENGTH_SHORT).show();
        }
        Button btnEnd = (Button) findViewById(R.id.btnEnd);
        btnEnd.setOnClickListener(this);
    }

    public Double media(ArrayList<Double> notas){
        Double res = 0.0;
        for (Double n: notas){
            res+=n;
        }
        return res/notas.size();
    }

    public void salir(View v){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setMessage("¿Está seguro de que desea salir?");
        alerta.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ResultadoActivity.this.finish();
            }
        });
        alerta.setNegativeButton(android.R.string.no, null);
        alerta.show();
    }

    @Override
    public void onClick(View v) {
        salir(v);
    }
}
