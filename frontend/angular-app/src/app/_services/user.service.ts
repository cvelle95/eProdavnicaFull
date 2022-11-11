import { Injectable } from '@angular/core';
import { UserModel } from '../_models/user.model';
import { FavouriteCategoryModel } from '../_models/favouriteCategory.model';
import { HttpClient } from '@angular/common/http';

@Injectable({providedIn: 'root'})
export class UserService {
  static dummyUserList: Array<UserModel> = [{id:1,ime: "Ivan",prezime:"Cvetkovic",email:"cvetkovici888@gmail.com",telefon:"12345678",adresa:"VV70",username:"vani",password:"ivan1234",omiljeneKategorije:[{id:1,naziv:"Laptop"},{id:2,naziv:"desktop"}]}]

  static currentUser: UserModel;
  static isUserLogged: boolean;
  constructor(private http:HttpClient) { 
      
   }
   
   getUserById(id:number): UserModel{
     var userFound: UserModel;

     UserService.dummyUserList.forEach(user => {if(user.id==id)userFound=user});

     return userFound;
   }

   getUserByUsername(username: string): UserModel{
    var userFound: UserModel;

    UserService.dummyUserList.forEach(user => {if(user.username === username )userFound=user});
    UserService.currentUser = userFound;
    UserService.isUserLogged = true;
    return userFound;
   } 
   static isLoginOk:boolean = false;
  public static isAdmin:boolean = false
   userLogin(username: string,password: string):boolean{
     var user: any;
     const param = {"username":username,"password":password};
     this.http.post("http://localhost:8080/korisnik/loginuser",param).toPromise().then((data)=>{
       console.log(data);
       user = data;
       UserService.currentUser = user;
       console.log(UserService.currentUser)
       UserService.isUserLogged = true;
       console.log(UserService.currentUser)
       if(user){
         UserService.isLoginOk = true;
         if(UserService.currentUser.id === 1){
             UserService.isAdmin = true;
         }
       }
     })
     console.log(UserService.isLoginOk);
     return UserService.isLoginOk;
   }


   public userId:number;

   registerUser(ime: string,prezime: string,email: string,telefon: string,adresa: string,username: string,password: string,omiljeneKategorije: Array<FavouriteCategoryModel>) : UserModel{
      var user:any;

      const param = {"ime":ime,"prezime":prezime,"email":email,"telefon":telefon,"adresa":adresa,"username":username,"password":password}
    this.http.post("http://localhost:8080/korisnik/insertnewkorisnik",param).toPromise().then(data => {
      console.log(data);
      user = data;
      UserService.currentUser = user;
      this.userId = user.id
      //console.log(this.userId)
      omiljeneKategorije.forEach(category => {
        console.log(category)
        console.log(this.userId)
        var param2={"kategorijaProizvodaId":category.id,"korisnikId":this.userId};
         
        this.http.post("http://localhost:8080/korisnik/addomiljenukategorijuproizvoda",param2).toPromise().then(data => {
      console.log(data);
    });
      })
      UserService.currentUser = user;
      UserService.isUserLogged = true;
      console.log(UserService.currentUser)
      UserService.dummyUserList.push(user);
    });
    return UserService.currentUser;
   }

  updateUser(user: UserModel){
    const param = {"ime":user.ime,"prezime":user.prezime,"email":user.email,"telefon":user.telefon,"adresa":user.adresa,"username":user.username,"password":user.password}
    this.http.post("http://localhost:8080/korisnik/updatekorisnik",param).toPromise().then(data => {
      console.log(data);
    });
  }

}
