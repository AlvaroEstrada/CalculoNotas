package com.alvaropedrajas.calculonotas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<Double> notas;
    EditText et_nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_nota = (EditText) findViewById(R.id.et_nota);
        Button btnSave = (Button) findViewById(R.id.btnSave);
        Button btnCalc = (Button) findViewById(R.id.btnCalc);
        btnSave.setOnClickListener(this);
        btnCalc.setOnClickListener(this);

        notas = new ArrayList<>();
    }

    public void guardar(){
        notas.add(Double.parseDouble(et_nota.getText().toString()));
        et_nota.setText("");
        et_nota.requestFocus();
    }

    public void calculos(){
        Intent intent = new Intent(this, ResultadoActivity.class);
        intent.putExtra("notas", notas);
        this.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:
                String getNum = et_nota.getText().toString();
                String empty = "";
                if (!Objects.equals(getNum, empty)){
                    guardar();
                }else{
                    Toast.makeText(v.getContext(), "¡Es necesario algún número!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnCalc:
                if (notas!=null && notas.size()!=0){
                    calculos();
                }else{
                    Toast.makeText(v.getContext(), "¡No hay numeros guardados para calcular!", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
