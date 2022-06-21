package com.example.parcial2;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NotasAdapter extends ArrayAdapter {
    //Declaramos variable como List
    public List<Notas_datos> opciones = new ArrayList<>();

    // Creamos constructor
    public NotasAdapter(Context context, List<Notas_datos>datos) {
        super(context, R.layout.view_notas, datos);

        this.opciones=datos;
    }

    // Mandamos datos los elementos de View
    public View getView(int position, View view, ViewGroup vg){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.view_notas,null);

        TextView materia = (TextView) item.findViewById(R.id.txtMateria);
        materia.setText(opciones.get(position).getMateria());

        TextView semestre = (TextView) item.findViewById(R.id.txtSemestre);
        semestre.setText(opciones.get(position).getSemestre());

        TextView nota = (TextView) item.findViewById(R.id.txtCalificacion);
        nota.setText(opciones.get(position).getNota());

        ImageView check = (ImageView) item.findViewById(R.id.imgNota);
        check.setImageResource(opciones.get(position).getCheck());
        String notita = nota.getText().toString();

        ImageView Materia_img = (ImageView) item.findViewById(R.id.imgMateria);
        Materia_img.setImageResource(opciones.get(position).getImgMateria());

        String Materias = materia.getText().toString();
        if(notita.equals("A") || notita.equals("B") || notita.equals("C")){
            check.setImageResource(R.drawable.check1);
        }else if (notita.equals("F") || notita.equals("D")){
            check.setImageResource(R.drawable.check2);
        }
        if(Materias.equals("Base_De_datos")){
            Materia_img.setImageResource(R.drawable.base_de_datos_img);
        }else if(Materias.equals("Desarrollo_de_Software")){
            Materia_img.setImageResource(R.drawable.desarrollo_software_img);

        }else if(Materias.equals("Calculo")){
            Materia_img.setImageResource(R.drawable.calculo_img);

        }

        return (item);
    }


}
