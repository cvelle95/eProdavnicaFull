package com.eprodavnicaspring.eprodavnicaSpring.controller;

import com.eprodavnicaspring.eprodavnicaSpring.model.PoruceniProizvodiModel;
import com.eprodavnicaspring.eprodavnicaSpring.service.PoruceniProizvodiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("poruceniproizvodi")
public class PoruceniProizvodi {
    @Autowired
    private PoruceniProizvodiServiceImpl poruceniProizvodiService;

    @GetMapping("getporuceniproizvodiforuser")
    @CrossOrigin(origins = "*")
    public List<PoruceniProizvodiModel> getPoruceniProizvodiForUser(int korisnik_id){
        return poruceniProizvodiService.getPoruceniProizvodiForUser(korisnik_id);
    }

    @PostMapping("addporuceniproizvod")
    @CrossOrigin(origins = "*")
    public PoruceniProizvodiModel addPoruceniProizvod(@RequestBody PoruceniProizvodiModel poruceniProizvod){
        return poruceniProizvodiService.addPoruceniProizvod(poruceniProizvod);
    }

    @GetMapping("deleteporudzbinu")
    @CrossOrigin(origins = "*")
    @Transactional
    public void deletePoruceniProizvodiModelById(int id){
        poruceniProizvodiService.deletePoruceniProizvodiModelById(id);
    }

}
