package com.eprodavnicaspring.eprodavnicaSpring.controller;


import com.eprodavnicaspring.eprodavnicaSpring.model.KorisnikModel;
import com.eprodavnicaspring.eprodavnicaSpring.model.ProizvodModel;
import com.eprodavnicaspring.eprodavnicaSpring.service.ProizvodService;
import com.eprodavnicaspring.eprodavnicaSpring.service.ProizvodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("proizvod")
public class Proizvod {
    @Autowired
    private ProizvodServiceImpl proizvodService;

    @GetMapping("findall")
    @CrossOrigin(origins = "*") //Vraca sve proizvode iz baze
    public List<ProizvodModel> findAll(){ return proizvodService.findAll(); }

    @GetMapping("filtersearch")
    @CrossOrigin(origins = "*")// pretrazivanje proizvoda po kategoriji i ceni
    public List<ProizvodModel> filterSearch(int kategorija_proizvoda_id,float cenaMin,float cenaMax){return proizvodService.filterSearch(kategorija_proizvoda_id, cenaMin, cenaMax);}

    @GetMapping("filtersearchprice")
    @CrossOrigin(origins = "*")// pretrazivanje proizvoda po kategoriji i ceni
    public List<ProizvodModel> filterSearch(float cenaMin,float cenaMax){return proizvodService.filterSearch(cenaMin, cenaMax);}
}
