package com.vaniijo.mongostat.controller;

import com.vaniijo.mongostat.model.PorudzbinaModel;
import com.vaniijo.mongostat.model.StatReturnModel;
import com.vaniijo.mongostat.repository.PorudzbinaRepository;
import com.vaniijo.mongostat.service.PorudzbinaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("porudzbina")
@CrossOrigin(origins = "*")
public class Porudzbina {
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;
    @Autowired
    private PorudzbinaServiceImpl porudzbinaService;

    @GetMapping("getall")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getAllPorudzbinaForYear(Integer year){
        List<PorudzbinaModel> porudzbine = porudzbinaRepository.findAll();
        if(porudzbine.size()>0){
            return new ResponseEntity<List<PorudzbinaModel>>(porudzbine, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("0 results found",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("addnewporudzbina")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> addNewPorudzbina(@RequestBody PorudzbinaModel porudzbina){
        try{
            PorudzbinaModel por = porudzbinaService.addNewPorudzbina(porudzbina);
            return new ResponseEntity<PorudzbinaModel>(por,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("addNewMockPorudzbina")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> addNewMockPorudzbina(@RequestBody PorudzbinaModel porudzbina,int godina,int mesec,int ponavljanja){
        try{
            List<PorudzbinaModel> por = porudzbinaService.addNewMockPorudzbina(porudzbina,godina,mesec,ponavljanja);
            return new ResponseEntity<List<PorudzbinaModel>>(por,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("getStatByYearAndMonth")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getStatByYearAndMounth(int godina,int mesec) {
        try {
            StatReturnModel stat = porudzbinaService.getStatByYearAndMonth(godina, mesec);
            return new ResponseEntity<StatReturnModel>(stat, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

        @GetMapping("getStatForProduct")
        @CrossOrigin(origins = "*")
        public ResponseEntity<?> getStatForProduct(int godina,int mesec,Integer productId){
            try {
                StatReturnModel stat = porudzbinaService.getStatForProduct(godina,mesec,productId);
                return new ResponseEntity<StatReturnModel>(stat,HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

            }
    }

    @GetMapping("getStatForProduct2")
    @CrossOrigin(origins = "*")
    public List<StatReturnModel> getStatForProduct(int godina,Integer productId) {
        ArrayList<StatReturnModel> stats= new ArrayList<StatReturnModel>();
        for(int i=1;i<=12;i++){
            StatReturnModel stat = porudzbinaService.getStatForProduct(godina, i, productId);
            stats.add(stat);
        }
        return stats;

    }

    @GetMapping("getStatByYearAndMonth2")
    @CrossOrigin(origins = "*")
    public List<StatReturnModel> getStatByYearAndMounth(int godina) {
        ArrayList<StatReturnModel> stats= new ArrayList<StatReturnModel>();
        for(int i=1;i<=12;i++){
            StatReturnModel stat = porudzbinaService.getStatByYearAndMonth(godina, i);
            stats.add(stat);
        }
        return stats;

    }

    @GetMapping("getStatDataAll")
    @CrossOrigin(origins = "*")
    public List<StatReturnModel> getStatDataAll(int godina, int product_id) {
        ArrayList<StatReturnModel> stats= new ArrayList<StatReturnModel>();
        for(int i=1;i<=12;i++){
            StatReturnModel stat = porudzbinaRepository.getStatDataAll(godina, i, product_id);
            stats.add(stat);
        }
        return stats;

    }

}
