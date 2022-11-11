package com.vaniijo.mongostat.model;

import jdk.jfr.Unsigned;
import lombok.Data;

@Data
public class StatReturnModel {
    private Long brojPorudzbina;
    private Double ukupanPrihod;
    private Double ukupnaKolicina;
}
