package com.eprodavnicaspring.eprodavnicaSpring.service;

import com.eprodavnicaspring.eprodavnicaSpring.model.KorisnikModel;
import com.eprodavnicaspring.eprodavnicaSpring.model.OmiljeneKategorijeProizvodaModel;
import com.eprodavnicaspring.eprodavnicaSpring.repository.KorisnikRepository;
import com.eprodavnicaspring.eprodavnicaSpring.repository.OmiljeneKategorijeProizvodaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class KorisnikServiceImpl implements KorisnikService{
    @Autowired
    public KorisnikRepository korisnikRepository;

    @Autowired
    public OmiljeneKategorijeProizvodaRepository omiljeneKategorijeProizvodaRepository;

    @Override
    public KorisnikModel findByUsernameSql(String username) { return korisnikRepository.findByUsernameSql(username);}

    @Override
    public boolean isUsernameRegistered(String username){
        KorisnikModel user = findByUsernameSql(username);
        if(user == null){
            return false;
        }
        return true;
    }
    @Override
    public KorisnikModel insertNewKorisnik(KorisnikModel korisnikModel){
        return korisnikRepository.save(korisnikModel);
    }
    @Override
    public KorisnikModel updateKorisnik(KorisnikModel korisnikModel){
        KorisnikModel korisnikModelChanges = korisnikModel;
        KorisnikModel korisnikForUdate = korisnikRepository.findKorisnikModelByUsername(korisnikModelChanges.getUsername());
        korisnikForUdate.setEmail(korisnikModelChanges.getEmail());
        korisnikForUdate.setTelefon(korisnikModelChanges.getTelefon());
        korisnikForUdate.setAdresa(korisnikModelChanges.getAdresa());
        korisnikForUdate.setPassword(korisnikModelChanges.getPassword());
        return korisnikRepository.save(korisnikForUdate);
    }

    @Override
    public OmiljeneKategorijeProizvodaModel addOmiljenuKategorijuProizvoda(OmiljeneKategorijeProizvodaModel omiljeneKategorijeProizvodaModel){
        return omiljeneKategorijeProizvodaRepository.save(omiljeneKategorijeProizvodaModel);
    }

}
