package com.example.parcial2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SeleccionUsuario extends AppCompatActivity {

    RadioGroup rbgOpcion;
    RadioButton rbtEstudiante, rbtMaestro, rbtSiu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_usuario);
        InicializarControles();

    }

    private void InicializarControles() {
        this.rbgOpcion = (RadioGroup) findViewById(R.id.RadioTipo);
        this.rbtEstudiante = (RadioButton) findViewById(R.id.RaEstudianteRegistro);
        this.rbtMaestro = (RadioButton) findViewById(R.id.RaMaestroRegistro);
        this.rbtSiu = (RadioButton) findViewById(R.id.RaSIU);

    }


    public void IngresarTipodeusuario(View view){

        try{



            String cedula = ObtenerCedula();
            SharedPreferences pref = getSharedPreferences("maestro", Context.MODE_PRIVATE);
            SharedPreferences pref2 = getSharedPreferences("estudiante",Context.MODE_PRIVATE);

            int opc = this.rbgOpcion.getCheckedRadioButtonId();
            String tipoUsuario;
            switch(opc){
                case R.id.RaEstudiante:

                    if(pref.contains(1+cedula)){
                        Toast.makeText(this, "Usted No es Estudiante", Toast.LENGTH_SHORT).show();
                    }else{
                        tipoUsuario = "2";
                        RecibirDatos2(tipoUsuario,cedula);
                    }



                    //startActivity(new Intent(getApplicationContext(), notas.class));
                    break;

                case R.id.RaMaestro:

                    if(pref2.contains(1+cedula)){
                        Toast.makeText(this, "Usted No es Maestro", Toast.LENGTH_SHORT).show();
                    }else{
                        tipoUsuario = "1";
                        RecibirDatos(tipoUsuario,cedula);
                        // startActivity(new Intent(getApplicationContext(), notas.class));
                    }

                    break;

                case R.id.RaSIU:
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://matricula.utp.ac.pa"));
                    startActivity(i);

                    break;

            }



        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void RecibirDatos2(String tipoUsuario, String cedula) {
        Intent i=getIntent();
        String user;

        user = i.getStringExtra("user2");


        Toast.makeText(this, "username : "+user, Toast.LENGTH_SHORT).show();
        EnviarDatos(user, tipoUsuario, cedula);
    }

    private String ObtenerCedula() {
        Intent i = getIntent();
        return i.getStringExtra("id");
    }


    private void RecibirDatos(String tipoUsuario, String cedula ) {
       Intent i=getIntent();
       String user;

       user = i.getStringExtra("user");


        Toast.makeText(this, "username : "+user, Toast.LENGTH_SHORT).show();
       EnviarDatos(user, tipoUsuario, cedula);

    }

    private void EnviarDatos(String user, String tipoUsuario, String cedula) {
        Intent i = new Intent(this, notas.class);

        if(tipoUsuario.equals("1")){
            i.putExtra("user", user);
        }else if (tipoUsuario.equals("2")){
            i.putExtra("user2", user);
        }

        i.putExtra("usertype", tipoUsuario);
        i.putExtra("id",cedula);
        Toast.makeText(this, "username : "+user, Toast.LENGTH_SHORT).show();
        startActivity(i);

    }
}