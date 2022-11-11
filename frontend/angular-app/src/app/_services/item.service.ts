import { HttpClient,HttpParams,HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ItemModel } from '../_models/item.model';
import { RatingItemModel } from '../_models/ratingItem.model';

@Injectable(
  //providedIn: 'root'
  )
export class ItemService {
  proizvodi: any;

  constructor(private http: HttpClient) { }

////////////////////////////////////////////
  public findAll() :any{
    var items : ItemModel[] = [];
    var url="http://localhost:8080/proizvod/findall";
    this.http.get(url).toPromise().then(data => {
     for (let key in data){
       if (data.hasOwnProperty(key)){
         items.push(data[key]);
       }
     }
    })
    console.log(items)
    return items;
  }

////////////////////////////////////////////////////
  public filterSearch(categoryId: number,priceMin: any,priceMax: any) :any{
    var items : ItemModel[] = [];
    var url: string;
    
    var params:any;

    if(isNaN(priceMax) || priceMax > 999999 || priceMax == "" || priceMax==null){
      priceMax = 999999;
    }

    if(priceMin < 1 || isNaN(priceMin) || priceMin == ""){
      priceMin = 0;
    }

    if(isNaN(categoryId)){
      params = new HttpParams().set('cenaMin', priceMin)
    .set('cenaMax', priceMax);
    url = "http://localhost:8080/proizvod/filtersearchprice";
    }
    else{
      params = new HttpParams().set('kategorija_proizvoda_id', categoryId).set('cenaMin', priceMin)
    .set('cenaMax', priceMax);
    url = "http://localhost:8080/proizvod/filtersearch";
    }
    console.log(params," Parametri")
    this.http.get(url,{params}).toPromise().then(data => {
     for (let key in data){
       if (data.hasOwnProperty(key)){
         items.push(data[key]);
       }
     }
    })
    console.log(items)
    return items;
  }
  /////////////////////////////////////////////////

  //////////////////////////////////////////////////

  public getRatingForItem(id:number) :any{
    var ratings : RatingItemModel[] = [];
    const params1 = new HttpParams().set("proizvod_id",id);

    var url ="http://localhost:8080/recenzijaproizvoda/getrecenzijeforproizvod";

    this.http.get(url,{params:params1}).toPromise().then(data => {
      for (let key in data){
        if (data.hasOwnProperty(key)){
          ratings.push(data[key]);
        }
      }
     })
     console.log(ratings)
     return ratings;

  }

/////////////////////////////////////////////////////
  public insertUserRating(rating: number,comment: string,userId:number,itemId:number,name:string):any{
    const param = {"ocena":rating,"komentar":comment,"korisnik_id":userId,"proizvod_id":itemId,"ime":name}
    this.http.post("http://localhost:8080/recenzijaproizvoda/insertrecenzijuproizvoda",param).toPromise().then(data => {
      console.log(data);
    });

  }
}
