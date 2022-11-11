package com.eprodavnicaspring.eprodavnicaSpring.service;

import com.eprodavnicaspring.eprodavnicaSpring.model.ProizvodModel;
import com.eprodavnicaspring.eprodavnicaSpring.repository.ProizvodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service()
public class ProizvodServiceImpl implements ProizvodService{
    @Autowired
    private ProizvodRepository proizvodRepository;

    @Override
    public List<ProizvodModel> findAll(){ return proizvodRepository.findAll(); }

    @Override
    public List<ProizvodModel> filterSearch(int kategorija_proizvoda_id,float cenaMin,float cenaMax){

        return proizvodRepository.filterSearch(kategorija_proizvoda_id, cenaMin, cenaMax);
    }
    @Override
    public List<ProizvodModel> filterSearch(float cenaMin,float cenaMax){

        return proizvodRepository.filterSearch(cenaMin, cenaMax);
    }

}
