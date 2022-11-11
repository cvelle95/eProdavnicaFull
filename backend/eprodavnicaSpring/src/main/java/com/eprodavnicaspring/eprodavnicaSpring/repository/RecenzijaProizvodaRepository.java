package com.eprodavnicaspring.eprodavnicaSpring.repository;

import com.eprodavnicaspring.eprodavnicaSpring.model.PoruceniProizvodiModel;
import com.eprodavnicaspring.eprodavnicaSpring.model.RecenzijaProizvodaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecenzijaProizvodaRepository extends JpaRepository<RecenzijaProizvodaModel,Long> {
    @Query(nativeQuery = true, value = "SELECT r.* FROM recenzija_proizvoda r WHERE r.proizvod_id = :proizvod_id")
    List<RecenzijaProizvodaModel> getRecenzijeForProizvod(@Param("proizvod_id")int proizvod_id);

    @Query(nativeQuery = true, value = "SELECT r.* FROM recenzija_proizvoda r WHERE r.korisnik_id = :korisnik_id AND r.proizvod_id = :proizvod_id")
    RecenzijaProizvodaModel getRecenzijaProizvodaModelByKorisnik_idAndProizvod_id(int korisnik_id,int proizvod_id);

    @Query(nativeQuery = true, value = "DELETE FROM recenzija_proizvoda r WHERE r.korisnik_id = :korisnik_id AND r.proizvod_id = :proizvod_id")
    void deleteByRecenzijaProizvodaId(int korisnik_id,int proizvod_id);
}
