package com.eprodavnicaspring.eprodavnicaSpring.model;


import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.*;

@Entity
@Table(name = "poruceni_proizvodi")
@Data
public class PoruceniProizvodiModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poruceni_proizvodi_id")
    private int id;

    @Column(name = "kolicina")
    private float kolicina;

    @Column(name = "cena_porudzbine")
    private float cena_porudzbine;

    //@Column(name = "proizvod_id")
    //private int proizvod_id;

    @Column(name = "status")
    String status;

    @Column(name = "korisnik_id")
    private int korisnik_id;

    @Column(name = "proizvod_id")
    private int proizvod_id;

    @ManyToOne
    @JoinColumn(name = "proizvod_id",insertable = false,updatable = false)
    private ProizvodModel proizvod;
}
