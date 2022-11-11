package com.eprodavnicaspring.eprodavnicaSpring.repository;

import com.eprodavnicaspring.eprodavnicaSpring.model.KorisnikModel;
import com.eprodavnicaspring.eprodavnicaSpring.model.PoruceniProizvodiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PoruceniProizvodiRepository extends JpaRepository<PoruceniProizvodiModel,Long> {
    @Query(nativeQuery = true, value = "SELECT i.* FROM poruceni_proizvodi i INNER JOIN proizvod p on i.proizvod_id = p.proizvod_id WHERE i.korisnik_id = :korisnik_id")
    List<PoruceniProizvodiModel> getPoruceniProizvodiForUser(@Param("korisnik_id")int korisnik_id);

    void deletePoruceniProizvodiModelById(int id);

    PoruceniProizvodiModel getPoruceniProizvodiModelById(int id);
}
