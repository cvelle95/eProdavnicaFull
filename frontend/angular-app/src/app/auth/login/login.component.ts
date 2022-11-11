import { stringify } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserModel } from 'src/app/_models/user.model';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  errorExists = false;
  errorText = "";

  constructor(private userService: UserService,private router: Router) { }

  ngOnInit(): void {
    this.userService;
  }

  onSubmit(data){ // uzimanje podataka iz forme
    //proveri i posalji formu ovde
    var username = data.username;
    var password = data.password;
    //var user = this.userService.getUserByUsername(username);
    /*if(user){
        if(user.password === password){
          
          this.router.navigate(["/"])
        }
    }else{
        this.errorExists = true;
        this.errorText = "Incorrect username or password";
    }*/
  this.userService.userLogin(username,password);
  var user: UserModel = UserService.currentUser;
  console.log(UserService.currentUser)

  setTimeout(() => {
    console.log('sleep');
    if(this.userService.userLogin(username,password)){
      this.router.navigate(["/"])
    }
    else{
      this.errorExists = true;
      this.errorText = "Incorrect username or password";
     }
  }, 600);

 }
}

