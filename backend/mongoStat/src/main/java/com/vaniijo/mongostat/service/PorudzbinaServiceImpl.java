package com.vaniijo.mongostat.service;

import com.vaniijo.mongostat.model.PorudzbinaModel;
import com.vaniijo.mongostat.model.StatReturnModel;
import com.vaniijo.mongostat.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service()
public class PorudzbinaServiceImpl implements PorudzbinaService{
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public List<PorudzbinaModel> getAllPoruzbinaByYear(Integer year) {
        return porudzbinaRepository.getAllByGodina(year);
    }

    @Override
    public PorudzbinaModel addNewPorudzbina(PorudzbinaModel porudzbina) {
        porudzbina.setCreatedAt(new Date(System.currentTimeMillis()));
        porudzbina.setId(sequenceGeneratorService.getSequenceNumber(PorudzbinaModel.SEQUENCE_NAME));
        porudzbina.setGodina(porudzbina.getCreatedAt().getYear()+1900);//daje razliku od 1900-te
        porudzbina.setMesec(porudzbina.getCreatedAt().getMonth()+1);//od 0 - 11
        return porudzbinaRepository.save(porudzbina);
    }

    @Override
    public List<PorudzbinaModel> addNewMockPorudzbina(PorudzbinaModel porudzbina, int godina, int mesec,int ponavljanja) {
        List<PorudzbinaModel>porudzbine = new ArrayList<PorudzbinaModel>();
        porudzbina.setGodina(godina);
        porudzbina.setMesec(mesec);

        for(int i=0;i<ponavljanja;i++) {
            PorudzbinaModel por = porudzbina;
            por.setCreatedAt(new Date(System.currentTimeMillis()));
            por.setId(sequenceGeneratorService.getSequenceNumber(PorudzbinaModel.SEQUENCE_NAME));
            porudzbinaRepository.save(por);
            porudzbine.add(por);
        }
        return porudzbine;
    }

    @Override
    public StatReturnModel getStatByYearAndMonth(int godina, int mesec) {
        StatReturnModel zaPovrat = new StatReturnModel();
        zaPovrat.setBrojPorudzbina(porudzbinaRepository.porudzbineCount(godina,mesec));
        zaPovrat.setUkupanPrihod(porudzbinaRepository.getUkupanPrihod(godina,mesec));
        zaPovrat.setUkupnaKolicina(porudzbinaRepository.getUkupnaKolicina(godina,mesec));

        return zaPovrat;
    }

    @Override
    public StatReturnModel getStatForProduct(int godina, int mesec, Integer productId) {
        StatReturnModel stat = new StatReturnModel();
        stat.setBrojPorudzbina(porudzbinaRepository.PorudzbineForProductCount(godina,mesec,productId));
        stat.setUkupanPrihod(porudzbinaRepository.getUkupanPrihodForProduct(godina,mesec,productId));
        stat.setUkupnaKolicina(porudzbinaRepository.getUkupnaKolicinaForProduct(godina,mesec,productId));
        return stat;
    }
}
