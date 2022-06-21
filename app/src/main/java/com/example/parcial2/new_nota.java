package com.example.parcial2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class new_nota extends AppCompatActivity {

    EditText Cedula, Semestre;
    Spinner spnMaterias;
    Spinner spnNotas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_nota);
        inicializarControles();

        spnNotas = (Spinner) findViewById(R.id.spnNotas);
        String []opciones={"A","B","C","D","F"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);
        spnNotas.setAdapter(adapter);

        spnMaterias = (Spinner) findViewById(R.id.spMaterias);
        String []opciones2={"Base_De_datos", "Desarrollo_de_Software", "Calculo"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones2);
        spnMaterias.setAdapter(adapter2);


    }

    private void inicializarControles() {
        this.spnMaterias = (Spinner) findViewById(R.id.spMaterias);
        this.spnNotas = (Spinner) findViewById(R.id.spnNotas);
        this.Semestre = (EditText) findViewById(R.id.txtSemestreRegi);
        this.Cedula = (EditText) findViewById(R.id.txtcedulaEsRe);
    }






    public void NuevaCalificacion(View view){
        try{
            String semestre = this.Semestre.getText().toString();
            String cedula = this.Cedula.getText().toString();
            String selec=this.spnNotas.getSelectedItem().toString();
            String selec2=this.spnMaterias.getSelectedItem().toString();

            SharedPreferences pref = getSharedPreferences("notas",Context.MODE_PRIVATE);
            SharedPreferences pref2 = getSharedPreferences("estudiante",Context.MODE_PRIVATE);
            if(pref2.contains(1+cedula)){
                if(pref.contains("M"+cedula+selec2)) {
                    Toast.makeText(this, "Este estudiante ya tiene Nota con esta materia", Toast.LENGTH_SHORT).show();
                }else{

                SharedPreferences.Editor elemento=pref.edit();

                elemento.putString("M"+cedula+selec2, selec2);
                elemento.putString("N"+cedula+selec2, selec);
                elemento.putString("S"+cedula+selec2, semestre);
                Alerta dialogAlerta =new Alerta();
                dialogAlerta.show(getSupportFragmentManager(), "TAG");
                elemento.commit();
                startActivity(new Intent(getApplicationContext(), notas.class));
                finish();

                    Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();

                }

            }else{
                Toast.makeText(this, "Usuario No existe", Toast.LENGTH_SHORT).show();

            }



        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }




}