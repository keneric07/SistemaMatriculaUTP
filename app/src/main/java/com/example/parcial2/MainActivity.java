package com.example.parcial2;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    EditText Cedula, password;
    List<String> datos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarlizarControles();
    }

    private void iniciarlizarControles() {
        this.Cedula = (EditText) findViewById(R.id.txtCedula);
        this.password =(EditText) findViewById(R.id.txtPassword);
        // this.tipoUsuario = (RadioGroup) findViewById(R.id.RadioTipo);

    }

    public void IrRegistro(View v){
        startActivity(new Intent(getApplicationContext(), registro.class));
    }

    public void validarUsuario (View view){
        try {
            String cedula=this.Cedula.getText().toString(), password=this.password.getText().toString();

            SharedPreferences pref = getSharedPreferences("maestro",Context.MODE_PRIVATE);
            SharedPreferences pref2 = getSharedPreferences("estudiante",Context.MODE_PRIVATE);
            if(!cedula.equals("") && !password.equals("") || !cedula.equals("") || !password.equals("")){

                if (pref.contains(1+cedula)){
                    String ps=pref.getString(2+cedula, password);
                    if(ps.equals(password)){
                        Toast.makeText(this, "login exitoso" , Toast.LENGTH_SHORT).show();
                        String user = pref.getString(3+cedula, "");

                        EnviarDatos(user, cedula);


                    }else{
                        Toast.makeText(this, "contraseña erronea" , Toast.LENGTH_SHORT).show();

                    }
                }else if(pref2.contains(1+cedula)){
                    String ps=pref2.getString(2+cedula, password);
                    if(ps.equals(password)){
                        Toast.makeText(this, "login exitoso" , Toast.LENGTH_SHORT).show();
                        String user = pref2.getString(3+cedula, "");
                        EnviarDatos2(user, cedula);
                    }else{
                        Toast.makeText(this, "contraseña erronea" , Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "EL usario no existe" , Toast.LENGTH_SHORT).show();
                }
            }
        }catch(Exception e){
            Toast.makeText(this, e.getMessage() , Toast.LENGTH_SHORT).show();
        }
    }

    private void EnviarDatos2(String user, String cedula) {
        Intent i = new Intent(this, SeleccionUsuario.class);
        i.putExtra("user2", user);
        i.putExtra("id", cedula);
        startActivity(i);
    }

    private void EnviarDatos(String user, String cedula) {
        Intent i = new Intent(this, SeleccionUsuario.class);
        i.putExtra("user", user);
        i.putExtra("id", cedula);
        startActivity(i);
    }
}