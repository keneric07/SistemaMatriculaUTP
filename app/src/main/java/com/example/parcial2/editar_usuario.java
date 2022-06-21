package com.example.parcial2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editar_usuario extends AppCompatActivity {
    EditText new_password;
    EditText new_username;
    Button confirmar_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        this.Incializar();



    }

    private void Incializar() {
        new_password = (EditText) findViewById(R.id.nueva_pass);
        new_username = (EditText) findViewById(R.id.nuevo_name);
        confirmar_edit = (Button) findViewById(R.id.confirmar_edit);


    }





    public void guardarUsuario(View view ) {
        String npass = this.new_password.getText().toString();
        String nuser = this.new_username.getText().toString();



        try {

            /*
            SharedPreferences nuevo = getSharedPreferences("nuevo",MODE_PRIVATE);
            SharedPreferences.Editor edit = nuevo.edit();
            edit.putString("name",nuser);
            edit.putString("pass",npass);

*/          SharedPreferences pref = getSharedPreferences("maestro", MODE_PRIVATE);
            SharedPreferences pref2 = getSharedPreferences("estudiante", MODE_PRIVATE);
            Intent i = getIntent();
            String cedula =i.getStringExtra("id");




            if(cedula.equals(pref.getString(1+cedula, ""))){
                String ps= pref.getString(2+cedula,"");
                String us = pref.getString(3+cedula,"");

                if(!ps.equals(nuser) && !us.equals(npass)){
                    SharedPreferences.Editor editor = pref.edit();
                    Toast.makeText(this, "Listo, usuario editado.", Toast.LENGTH_SHORT).show();

                    editor.putString(2 + cedula, npass);
                    editor.putString(3 + cedula, nuser);
                    editor.commit();
                    EnviarDatos(cedula, nuser, npass);
                }else{
                    Toast.makeText(this, "mi loco, ponte otros datos, que esto son los mismo...", Toast.LENGTH_SHORT).show();
                }


            }else if(cedula.equals(pref2.getString(1+cedula, ""))){
                String ps= pref2.getString(2+cedula,"");
                String us = pref2.getString(3+cedula,"");

                if(!ps.equals(nuser) && !us.equals(npass)){
                    SharedPreferences.Editor editor = pref2.edit();
                    Toast.makeText(this, "Listo, usuario editado.", Toast.LENGTH_SHORT).show();

                    editor.putString(2 + cedula, npass);
                    editor.putString(3 + cedula, nuser);
                    editor.commit();
                    EnviarDatos(cedula, nuser, npass);

                }else{
                    Toast.makeText(this, "mi loco, ponte otros datos, que esto son los mismo...", Toast.LENGTH_SHORT).show();
                }

            }


            /*
            SharedPreferences pref = getSharedPreferences("estudiante", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();


            String ps= pref.getString(2+cedula,npass );
            String us = pref.getString(3+cedula,nuser);



            if (!ps.equals(nuser) && !us.equals(npass)) {

                Toast.makeText(this, "Listo, usuario editado.", Toast.LENGTH_SHORT).show();

                editor.putString(2 + cedula, npass);
                editor.putString(3 + cedula, nuser);
                editor.commit();
                startActivity(new Intent(getApplicationContext(), notas.class));
                RecibirDatos();


            } else {
                Toast.makeText(this, "mi loco, ponte otros datos, que esto son los mismo...", Toast.LENGTH_SHORT).show();
            }

             */

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }







    public void EnviarDatos(String cedula, String user, String pass){
        Intent i = new Intent(this,MainActivity.class);
        i.putExtra("user",user);
        i.putExtra("id",cedula);
        i.putExtra("pass",pass);
        startActivity(i);
    }

}