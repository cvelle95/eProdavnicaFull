package com.eprodavnicaspring.eprodavnicaSpring.model;

import com.eprodavnicaspring.eprodavnicaSpring.repository.ProizvodRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "proizvod")
@Data
public class ProizvodModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proizvod_id")
    private Long id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "opis")
    private String opis;

    @Column(name = "cena")
    private float cena;

    @Column(name = "kategorija_proizvoda_id")
    private int kategorija_proizvoda_id;

    @Column(name = "proizvodjac")
    private String proizvodjac;

    @Column(name = "cover_image_url")
    private String coverImage;

    @Column(name = "zemlja_porekla")
    private String zemljaPorekla;


}
