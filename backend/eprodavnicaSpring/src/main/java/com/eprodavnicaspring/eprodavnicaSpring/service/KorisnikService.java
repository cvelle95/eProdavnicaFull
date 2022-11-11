package com.eprodavnicaspring.eprodavnicaSpring.service;


import com.eprodavnicaspring.eprodavnicaSpring.model.KorisnikModel;
import com.eprodavnicaspring.eprodavnicaSpring.model.OmiljeneKategorijeProizvodaModel;

public interface KorisnikService {
    public KorisnikModel findByUsernameSql(String username);
    public boolean isUsernameRegistered(String username);
    public KorisnikModel insertNewKorisnik(KorisnikModel korisnikModel);
    public KorisnikModel updateKorisnik(KorisnikModel korisnikModel);
    public OmiljeneKategorijeProizvodaModel addOmiljenuKategorijuProizvoda(OmiljeneKategorijeProizvodaModel omiljeneKategorijeProizvodaModel);

}
