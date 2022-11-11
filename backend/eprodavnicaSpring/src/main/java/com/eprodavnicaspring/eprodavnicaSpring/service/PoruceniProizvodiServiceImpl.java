package com.eprodavnicaspring.eprodavnicaSpring.service;

import com.eprodavnicaspring.eprodavnicaSpring.model.PoruceniProizvodiModel;
import com.eprodavnicaspring.eprodavnicaSpring.repository.PoruceniProizvodiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class PoruceniProizvodiServiceImpl implements PoruceniProizvodiService{
    @Autowired
    private PoruceniProizvodiRepository poruceniProizvodiRepository;

    @Override
    public List<PoruceniProizvodiModel> getPoruceniProizvodiForUser(int korisnik_id){
        return poruceniProizvodiRepository.getPoruceniProizvodiForUser(korisnik_id);
    }
    @Override
    public PoruceniProizvodiModel addPoruceniProizvod(PoruceniProizvodiModel poruceniProizvod){
        return poruceniProizvodiRepository.save(poruceniProizvod);
    }
    @Override
    public PoruceniProizvodiModel getPoruceniProizvodiModelById(int id){
        return poruceniProizvodiRepository.getPoruceniProizvodiModelById(id);
    }
    @Override
    public ResponseEntity<Object> deletePoruceniProizvodiModelById(int id){
        PoruceniProizvodiModel order = this.getPoruceniProizvodiModelById(id);
        if(!order.getStatus().equals("active")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            poruceniProizvodiRepository.deletePoruceniProizvodiModelById(id);
            return new ResponseEntity<>(HttpStatus.OK);

        }
    }
}
