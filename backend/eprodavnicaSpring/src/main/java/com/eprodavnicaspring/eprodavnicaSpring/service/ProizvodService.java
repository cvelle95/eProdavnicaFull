package com.eprodavnicaspring.eprodavnicaSpring.service;

import com.eprodavnicaspring.eprodavnicaSpring.model.ProizvodModel;

import java.util.List;

public interface ProizvodService {
    public List<ProizvodModel> findAll();
    public List<ProizvodModel> filterSearch(int kategorija_proizvoda_id,float cenaMin,float cenaMax);
    public List<ProizvodModel> filterSearch(float cenaMin,float cenaMax);
}
