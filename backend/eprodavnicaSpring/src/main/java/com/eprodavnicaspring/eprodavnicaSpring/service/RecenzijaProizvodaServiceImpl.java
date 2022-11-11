package com.eprodavnicaspring.eprodavnicaSpring.service;

import com.eprodavnicaspring.eprodavnicaSpring.model.RecenzijaProizvodaModel;
import com.eprodavnicaspring.eprodavnicaSpring.repository.RecenzijaProizvodaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class RecenzijaProizvodaServiceImpl implements RecenzijaProizvodaService{
    @Autowired
    private RecenzijaProizvodaRepository recenzijaProizvodaRepository;

    @Override
    public List<RecenzijaProizvodaModel> getRecenzijeForProizvod(int proizvod_id){
        return recenzijaProizvodaRepository.getRecenzijeForProizvod(proizvod_id);
    }

    @Override
    public RecenzijaProizvodaModel insertRecenzijuProizvoda(RecenzijaProizvodaModel recenzijaProizvodaModel){
        RecenzijaProizvodaModel isAlreadyInserted = this.findRecenziju(recenzijaProizvodaModel.getKorisnik_id(),recenzijaProizvodaModel.getProizvod_id());
        if(isAlreadyInserted==null) {
            System.out.println("Nije Pronadjena recenzija za taj proizvod");
            return recenzijaProizvodaRepository.save(recenzijaProizvodaModel);
        }
        else{
            System.out.println("Pronadjena recenzija za taj proizvod,Updejtujem");
            recenzijaProizvodaModel.setRecenzija_proizvoda_id(isAlreadyInserted.getRecenzija_proizvoda_id());
            return updateRecenzijuProizvoda(recenzijaProizvodaModel);
        }
    }
    @Override
    public RecenzijaProizvodaModel findRecenziju(int korisnik_id,int proizvod_id){
        return recenzijaProizvodaRepository.getRecenzijaProizvodaModelByKorisnik_idAndProizvod_id(korisnik_id,proizvod_id);
    }
    @Override
    public RecenzijaProizvodaModel updateRecenzijuProizvoda(RecenzijaProizvodaModel recenzijaProizvodaModel){
        return recenzijaProizvodaRepository.save(recenzijaProizvodaModel);
    }
    @Override
    public void deleteByRecenzijaProizvodaId(int korisnik_id,int proizvod_id){
        recenzijaProizvodaRepository.deleteByRecenzijaProizvodaId(korisnik_id,proizvod_id);
    }
}
