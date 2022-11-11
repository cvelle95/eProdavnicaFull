package com.eprodavnicaspring.eprodavnicaSpring.repository;

import com.eprodavnicaspring.eprodavnicaSpring.model.KorisnikModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KorisnikRepository extends JpaRepository<KorisnikModel,Long> {
    @Query(nativeQuery = true, value = "SELECT k.* FROM korisnik k INNER JOIN omiljene_kategorije_proizvoda o on k.korisnik_id = o.korisnik_id WHERE k.username = :username")
    KorisnikModel findByUsernameSql(@Param("username")String username);
    KorisnikModel findKorisnikModelByUsername(String username);


    KorisnikModel getById(int id);
}
