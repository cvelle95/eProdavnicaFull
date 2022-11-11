package com.vaniijo.mongostat.service;


import com.vaniijo.mongostat.model.PorudzbinaModel;
import com.vaniijo.mongostat.model.StatReturnModel;

import java.util.List;

public interface PorudzbinaService {
    public List<PorudzbinaModel> getAllPoruzbinaByYear(Integer year);
    public PorudzbinaModel addNewPorudzbina(PorudzbinaModel porudzbina);
    public List<PorudzbinaModel> addNewMockPorudzbina(PorudzbinaModel porudzbina,int godina,int mesec,int ponavljanja);
    public StatReturnModel getStatByYearAndMonth(int godina,int mesec);
    public StatReturnModel getStatForProduct(int godina,int mesec, Integer productId);
}
