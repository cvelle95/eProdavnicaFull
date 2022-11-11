package com.vaniijo.mongostat.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "porudzbine")
@Data
public class PorudzbinaModel {
    @Transient // ne perzistiraj polje u bazu
    public static final String SEQUENCE_NAME = "user_sequnce";

    @Id
    private int id;

    private Float kolicina;
    private Float cena_porudzbine;
    private String status;
    private Integer korisnik_id;
    private Integer proizvod_id;
    private Integer godina;
    private Integer mesec;
    private Date createdAt;

}
