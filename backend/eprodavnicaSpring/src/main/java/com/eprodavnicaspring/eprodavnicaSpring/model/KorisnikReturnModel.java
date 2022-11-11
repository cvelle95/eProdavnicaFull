package com.eprodavnicaspring.eprodavnicaSpring.model;

import java.util.List;

public class KorisnikReturnModel {
    public int id;
    public String ime;
    public String prezime;
    public String email;
    public String telefon;
    public String adresa;
    public String username;
    public String password;

    public KorisnikReturnModel(int id, String ime, String prezime, String email, String telefon, String adresa, String username, String password) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
        this.adresa = adresa;
        this.username = username;
        this.password = password;
    }

    public KorisnikReturnModel(){}
}
