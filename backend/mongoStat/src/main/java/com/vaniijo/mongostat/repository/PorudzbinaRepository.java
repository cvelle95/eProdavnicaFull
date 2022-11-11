package com.vaniijo.mongostat.repository;

import com.vaniijo.mongostat.model.PorudzbinaModel;
import com.vaniijo.mongostat.model.StatReturnModel;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//
@Repository
public interface PorudzbinaRepository extends MongoRepository<PorudzbinaModel,Integer> {
    @Query(value ="{ 'godina': ?0 , 'mesec': ?1 }",count = true)
    Long porudzbineCount(int godina,int mesec);

    @Aggregation(pipeline = {
            "{$match: { 'godina': ?0 , 'mesec': ?1 }}",
            "{$group: { _id: '', total: {$sum: $cena_porudzbine}}}"
    })
    Double getUkupanPrihod(int godina,int mesec);

    @Query(value ="{ 'godina': ?0 , 'mesec': ?1 , 'proizvod_id': ?2 }",count = true)
    Long PorudzbineForProductCount(int godina,int mesec,int porudzbinaId);

    @Aggregation(pipeline = {
            "{$match: { 'godina': ?0 , 'mesec': ?1 , 'proizvod_id': ?2 }}",
            "{$group: { _id: '', total: {$sum: $cena_porudzbine}}}"
    })
    Double getUkupanPrihodForProduct(int godina,int mesec,Integer productId);

    @Aggregation(pipeline = {
            "{$match: { 'godina': ?0 , 'mesec': ?1 }}",
            "{$group: { _id: '', total: {$sum: $kolicina}}}"
    })
    Double getUkupnaKolicina(int godina,int mesec);

    @Aggregation(pipeline = {
            "{$match: { 'godina': ?0 , 'mesec': ?1 , 'proizvod_id': ?2 }}",
            "{$group: { _id: '', total: {$sum: $kolicina}}}"
    })
    Double getUkupnaKolicinaForProduct(int godina,int mesec,Integer productId);

    @Query(value ="{ 'godina': ?0 }")
    List<PorudzbinaModel> getAllByGodina(int godina);

    @Aggregation(pipeline = {
            "{$match: { 'godina': ?0 , 'mesec': ?1 , 'proizvod_id': ?2 }}",
            "{$group: { _id: '', ukupnaKolicina: {$sum: $kolicina}, ukupanPrihod: {$sum: $cena_porudzbine}}}"
    })
    StatReturnModel getStatDataAll(int godina,int mesec,Integer productId);


}
