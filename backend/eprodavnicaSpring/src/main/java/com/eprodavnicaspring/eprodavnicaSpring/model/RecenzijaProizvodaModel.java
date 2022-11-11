package com.eprodavnicaspring.eprodavnicaSpring.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "recenzija_proizvoda")
@Data
public class RecenzijaProizvodaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recenzija_proizvoda_id")
    private int recenzija_proizvoda_id;

    @Column(name = "ocena")
    private float ocena;

    @Column(name = "komentar")
    private String komentar;

    @Column(name = "korisnik_id")
    private int korisnik_id;

    @Column(name = "proizvod_id")
    private int proizvod_id;

    @Column (name = "ime")
    private String ime;
}
