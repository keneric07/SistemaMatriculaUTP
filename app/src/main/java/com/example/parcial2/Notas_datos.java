package com.example.parcial2;


public class Notas_datos {

    private String materia;
    private String semestre;
    private String nota;
    private int Check;
    private int imgMateria;



    public Notas_datos(String materia, String semestre,String nota) {
        this.materia=materia;
        this.semestre=semestre;
        this.nota=nota;

    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getCheck() {
        return Check;
    }

    public void setCheck(int check) {
        Check = check;
    }

    public int getImgMateria() {
        return imgMateria;
    }

    public void setImgMateria(int imgMateria) {
        this.imgMateria = imgMateria;
    }
}
