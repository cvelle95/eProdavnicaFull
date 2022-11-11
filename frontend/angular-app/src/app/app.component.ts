import {Component, OnInit, SimpleChanges} from '@angular/core';
import { UserService } from './_services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'angular-app';
  prikaziStat:boolean = false;

  constructor(private userService:UserService){}

  ngOnInit(){
    //Is Admin Logged?
    if(UserService.currentUser.id === 1) {
      UserService.isAdmin = true;
    }
}

  get staticIsAdmin() {
    return UserService.isAdmin;
  }

  listOfLinks = [
    {
      name:"Home",
      url:"/",
      icon:"home"
    },
    {
      name:"Login",
      url:"/login",
      icon:"login"
    },
    {
      name:"Signup",
      url:"/signup",
      icon:"face"
    },
    {
      name:"Profile",
      url:"/profile",
      icon:"perm_identity"
    },
    {
      name:"Cart",
      url:"/cart",
      icon:"shopping_cart"
    }
  ]
  
  
  //logout ikonica = 'exit_to_app'
}
