package com.eprodavnicaspring.eprodavnicaSpring.repository;

import com.eprodavnicaspring.eprodavnicaSpring.model.KorisnikModel;
import com.eprodavnicaspring.eprodavnicaSpring.model.ProizvodModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProizvodRepository extends JpaRepository<ProizvodModel,Long> {
    @Query(nativeQuery = true, value =
            "SELECT p.* FROM proizvod p WHERE p.kategorija_proizvoda_id = :kategorija_proizvoda_id AND p.cena BETWEEN :cenaMin AND :cenaMax")
    List<ProizvodModel> filterSearch(@Param("kategorija_proizvoda_id")int kategorija_proizvoda_id, float cenaMin, float cenaMax);

    @Query(nativeQuery = true, value = "SELECT p.* FROM proizvod p WHERE p.cena BETWEEN :cenaMin AND :cenaMax")
    List<ProizvodModel> filterSearch(float cenaMin, float cenaMax);
}
