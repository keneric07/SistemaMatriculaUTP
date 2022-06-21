package com.example.parcial2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.parcial2.NotasAdapter;
import com.example.parcial2.Notas_datos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class notas extends AppCompatActivity {
    TextView User, materia,semestre,nota;
    ListView lstNotas;
    Button Agregar;

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        mostrarPerfil();
        inicializarControles();

    }


    private void inicializarControles() {
        this.materia = (TextView) findViewById(R.id.txtMateria);
        this.semestre = (TextView) findViewById(R.id.txtSemestre);
        this.nota = (TextView) findViewById(R.id.txtCalificacion);

        lstNotas=(ListView)findViewById(R.id.lstNotas);
        cargarNotas();

    }

    private void cargarNotas() {
        List<Notas_datos>cargarnotas =this.obtenerNotas();
        NotasAdapter adapter = new NotasAdapter(this,cargarnotas);
        lstNotas.setAdapter(adapter);
    }


    private List<Notas_datos> obtenerNotas() {
        //variables globales
        List<Notas_datos>obtenerNotas=new ArrayList<Notas_datos>();
        SharedPreferences pref=getSharedPreferences("notas",MODE_PRIVATE);

        try{

            //1. Recibir dos parametros desde la SeleccionUsuario.class - cedula y tipo de usuario
            Intent i = getIntent();
            String cedulaRecibida=i.getStringExtra("id");
            String typeUserRecib=i.getStringExtra("usertype"); // digamos que recibimos de tipo Maestro

            switch (typeUserRecib){
                case "1":

                    Map<String,?> clavesM = pref.getAll();
                    ArrayList listadoGeneral = new ArrayList();

                    for(Map.Entry<String,?> ele : clavesM.entrySet()){
                        String cedulaMap=ele.getKey().substring(1);

                        if(listadoGeneral.contains(cedulaMap)){
                        } else {
                            listadoGeneral.add(cedulaMap);
                        }

                    }

                    for(int p=0; p<listadoGeneral.size();p++) {
                        String materiaMap=pref.getString("M"+listadoGeneral.get(p),"");
                        String semestreMap=pref.getString("S"+listadoGeneral.get(p),"");
                        String notaMap=pref.getString("N"+listadoGeneral.get(p),"");

                        obtenerNotas.add(new Notas_datos(materiaMap,semestreMap,notaMap));
                    }
                    break;


                case "2":

                    Map<String,?> claves = pref.getAll();
                    ArrayList listadoE = new ArrayList();

                    for(Map.Entry<String,?> ele : claves.entrySet()){
                        String cedulaMap=ele.getKey().substring(1);
                        System.out.println("cedulaMap = "+cedulaMap);

                        if(listadoE.contains(cedulaMap)){
                            System.out.println("Ya contiene la cedula: "+cedulaMap);
                        } else if(cedulaMap.contains(cedulaRecibida)) {
                            listadoE.add(cedulaMap);
                        }

                        System.out.println("listadoE = "+listadoE);
                    }

                    for(int p=0; p<listadoE.size();p++) {
                        String materiaMap=pref.getString("M"+listadoE.get(p),"");
                        String semestreMap=pref.getString("S"+listadoE.get(p),"");
                        String notaMap=pref.getString("N"+listadoE.get(p),"");

                        obtenerNotas.add(new Notas_datos(materiaMap,semestreMap,notaMap));

                    }

                    break;
            }

        }catch (Exception e){

        }
        return obtenerNotas;
    }


    private void mostrarPerfil() {
        Intent i = getIntent();

        String tipo = i.getStringExtra("usertype");
        // String tipo="2";
        Toast.makeText(this, tipo, Toast.LENGTH_SHORT).show();
        //this.ValidacionElementos(tipo);
        this.User = (TextView) findViewById(R.id.tltUserName);
        this.Agregar = (Button) findViewById(R.id.btnModificar);


        if(tipo.equals("1")) {
            String user = i.getStringExtra("user");
            this.User.setText(user);
            Agregar.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Entro en 1 :"+user, Toast.LENGTH_SHORT).show();

        }else if(tipo.equals("2")){
            String user = i.getStringExtra("user2");
            Agregar.setVisibility(View.INVISIBLE);
            this.User.setText(user);
            Toast.makeText(this, "Entro en 2 : "+user, Toast.LENGTH_SHORT).show();

        }

    }




/*
    private void mostrarPerfil() {
        Intent i = getIntent();
        String user = i.getStringExtra("user");
        String id = i.getStringExtra("id");
        this.User = (TextView) findViewById(R.id.tltUserName);
        this.User.setText(user);
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void NuevaCalificacion(View view){
        startActivity(new Intent(getApplicationContext(), new_nota.class));
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mLogout:
                Toast.makeText(this, "Ha cerrado Sesion", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;

            default:
                return true;
        }
        return true;
    }


    public void irEditar(View view){
        Intent i = getIntent();
        String cedula = i.getStringExtra("id");
        EnviarDatos(cedula);

    }

    private void EnviarDatos(String cedula) {
        Intent i = new Intent(this, editar_usuario.class);
        i.putExtra("id", cedula);
        startActivity(i);

    }
}