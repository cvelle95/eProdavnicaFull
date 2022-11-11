package com.eprodavnicaspring.eprodavnicaSpring.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "kategorija_proizvoda")
@Data
public class KategorijaProizvodaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kategorija_proizvoda_id")
    private Long id;

    @Column(name = "naziv")
    private String naziv;


}
