package com.eprodavnicaspring.eprodavnicaSpring.service;

import com.eprodavnicaspring.eprodavnicaSpring.model.RecenzijaProizvodaModel;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecenzijaProizvodaService {
    public List<RecenzijaProizvodaModel> getRecenzijeForProizvod(int proizvod_id);
    public RecenzijaProizvodaModel insertRecenzijuProizvoda(RecenzijaProizvodaModel recenzijaProizvodaModel);
    public RecenzijaProizvodaModel updateRecenzijuProizvoda(RecenzijaProizvodaModel recenzijaProizvodaModel);
    public RecenzijaProizvodaModel findRecenziju(int korisnik_id,int proizvod_id);
    public void deleteByRecenzijaProizvodaId(int korisnik_id,int proizvod_id);
}
