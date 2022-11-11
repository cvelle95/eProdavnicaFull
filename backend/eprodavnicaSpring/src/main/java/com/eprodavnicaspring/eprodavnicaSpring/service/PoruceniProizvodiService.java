package com.eprodavnicaspring.eprodavnicaSpring.service;

import com.eprodavnicaspring.eprodavnicaSpring.model.PoruceniProizvodiModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PoruceniProizvodiService {
    public List<PoruceniProizvodiModel> getPoruceniProizvodiForUser(int korisnik_id);
    public PoruceniProizvodiModel addPoruceniProizvod(PoruceniProizvodiModel poruceniProizvod);
    public ResponseEntity<Object> deletePoruceniProizvodiModelById(int id);
    public PoruceniProizvodiModel getPoruceniProizvodiModelById(int id);
}
