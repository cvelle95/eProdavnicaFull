import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ItemModel } from '../_models/item.model';
import { OrderModel } from '../_models/order.model';
import {StatModel} from '../_models/stat.model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  static shoppingCart: Array<ItemModel> = [];
  constructor(private http:HttpClient) { }

  getOrdersForUser(userId:number):any{
    var orders: OrderModel[] = [];

    const params = new HttpParams().set("korisnik_id",userId);

    var url ="http://localhost:8080/poruceniproizvodi/getporuceniproizvodiforuser";
    this.http.get(url,{params:params}).toPromise().then(data => {
      for (let key in data){
        if (data.hasOwnProperty(key)){
          orders.push(data[key]);
        }
      }
     })
    console.log(orders)
     return orders;
  }

  cancelOrder(id:number){
    const params = new HttpParams().set("id",id);
    var url ="http://localhost:8080/poruceniproizvodi/deleteporudzbinu";
    this.http.get(url,{params:params}).toPromise().then(data =>{
      console.log(data)
    })

  }

  isProductOrdered(userId: number,itemId:number):boolean{
    var orders: OrderModel[];
    orders = this.getOrdersForUser(userId);
    var bool: boolean;
    console.log(orders);
    console.log(itemId)
    orders.forEach(order => {if(order.proizvod_id===itemId){bool = true}})
    return bool;
  }
  
  insertOrder(kolicina:number,cena_porudzbine:number,status:string,korisnik_id:number,proizvod_id:number){
    const param = {"kolicina":kolicina,"cena_porudzbine":cena_porudzbine,"status":status,"korisnik_id":korisnik_id,"proizvod_id":proizvod_id}

    this.http.post("http://localhost:8080/poruceniproizvodi/addporuceniproizvod",param).toPromise().then(data =>{
      console.log(data)
    });
    this.http.post("http://localhost:8081/porudzbina/addnewporudzbina",param).toPromise().then(data =>{
      console.log(data)
    });
  }

  getStats(godina:number , proizvodId:number){
    var stats:any = [];
    var statsFinal:any = [];
    var url1 ="http://localhost:8081/porudzbina/getStatByYearAndMonth2";
    var url2 ="http://localhost:8081/porudzbina/getStatForProduct2";

      if(proizvodId == 0){ //url1 bez productId-a
        let param = new HttpParams().set("godina",godina);
        const t = this.http.get(url1,{params:param}).toPromise().then(data =>{
          for (let key in data){
            if (data.hasOwnProperty(key)){
              stats.push(data[key]);
              //console.log(data[key]);
            }
          }
        })
      }
      else{//url2 prosledjuje productId
        let param = new HttpParams().set("godina",godina).set("productId",proizvodId);
        const t = this.http.get(url2,{params:param}).toPromise().then(data =>{
          for (let key in data){
            if (data.hasOwnProperty(key)){
              stats.push(data[key]);
            }
          }
        })

      }
    console.log(stats)
    return stats;

  }

}
