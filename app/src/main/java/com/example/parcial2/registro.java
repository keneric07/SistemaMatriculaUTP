package com.example.parcial2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Map;

public class registro extends AppCompatActivity {
    EditText Correo, password, cedula, user;
    RadioGroup tipoUsuario;
    private SharedPreferences pref;
    private ArrayList<String> datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        inicializarControles();
        leerSharePreferences();
    }

    private void leerSharePreferences() {
        pref=getSharedPreferences("usuarios", Context.MODE_PRIVATE);
        Map<String,?> claves =pref.getAll();
        for(Map.Entry<String,?> element :claves.entrySet()){
            datos.add(element.getKey()+element.getValue().toString());

        }
    }

    private void inicializarControles() {
        this.Correo = (EditText) findViewById(R.id.txtEmailRegistro);
        this.password = (EditText) findViewById(R.id.txtPasswordRegistro);
        this.tipoUsuario = (RadioGroup) findViewById(R.id.RadioTipoRegistro);
        this.cedula =(EditText) findViewById(R.id.txtCedulaRegistro);
        this.user=(EditText) findViewById(R.id.txtUserRegistro);
    }
    public void RegistrarUsuario(View view){
        try {
            String correo = this.Correo.getText().toString(), password = this.password.getText().toString(), cedula = this.cedula.getText().toString(), user = this.user.getText().toString();
            int rbtSeleccion = this.tipoUsuario.getCheckedRadioButtonId();
            switch (rbtSeleccion){

                case R.id.RaMaestroRegistro:
                        pref = getSharedPreferences("maestro",Context.MODE_PRIVATE);

                    if(pref.contains(1+cedula) ){

                        Toast.makeText(this, "Usuario Existe", Toast.LENGTH_SHORT).show();

                    }else{

                        Toast.makeText(this, "Usuario NO Existe", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor elemento=pref.edit();
                        elemento.putString(1+cedula,cedula);
                        elemento.putString(2+cedula,password);
                        elemento.putString(3+cedula,user);
                        elemento.putString(4+cedula,correo);
                        elemento.putString(5+cedula,""+rbtSeleccion);
                        elemento.commit();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                    break;
                case R.id.RaEstudianteRegistro:
                    pref = getSharedPreferences("estudiante",Context.MODE_PRIVATE);

                    if(pref.contains(1+cedula) ){

                        Toast.makeText(this, "Usuario Existe", Toast.LENGTH_SHORT).show();

                    }else{

                        Toast.makeText(this, "Usuario NO Existe", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor elemento=pref.edit();
                        elemento.putString(1+cedula,cedula);
                        elemento.putString(2+cedula,password);
                        elemento.putString(3+cedula,user);
                        elemento.putString(4+cedula,correo);
                        elemento.putString(5+cedula,""+rbtSeleccion);
                        elemento.commit();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                    break;
                default:
                    break;

            }
        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}
