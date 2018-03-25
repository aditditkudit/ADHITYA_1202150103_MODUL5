package com.example.android.adhitya_1202150103_modul5;

/**
 * Created by Tavs on 25/03/2018.
 */

//Class untuk model


public class Model {
    //Iniasi Variabel yang digunakan untuk model
    String toDo, desk, prior;

    //Membuat Konstruktor
    public Model(String toDo, String desk, String prior) {
        this.toDo = toDo;
        this.desk = desk;
        this.prior = prior;
    }
    // Membuat Setter dan getter
    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public String getDesk() {
        return desk;
    }

    public void setDesk(String desk) {
        this.desk = desk;
    }

    public String getPrior() {
        return prior;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }




}










