package com.eprodavnicaspring.eprodavnicaSpring.controller;

import com.eprodavnicaspring.eprodavnicaSpring.model.RecenzijaProizvodaModel;
import com.eprodavnicaspring.eprodavnicaSpring.service.RecenzijaProizvodaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("recenzijaproizvoda")
public class RecenzijaProizvoda {
    @Autowired
    private RecenzijaProizvodaServiceImpl recenzijaProizvodaService;

    @GetMapping("getrecenzijeforproizvod")
    @CrossOrigin(origins = "*")
    public List<RecenzijaProizvodaModel> getRecenzijeForProizvod(int proizvod_id){
        return recenzijaProizvodaService.getRecenzijeForProizvod(proizvod_id);
    }

    @PostMapping("insertrecenzijuproizvoda")//JSON podatke poslati u body
    @CrossOrigin(origins = "*")
    public RecenzijaProizvodaModel insertRecenzijuProizvoda(@RequestBody RecenzijaProizvodaModel recenzijaProizvodaModel){
        return recenzijaProizvodaService.insertRecenzijuProizvoda(recenzijaProizvodaModel);
    }
}
