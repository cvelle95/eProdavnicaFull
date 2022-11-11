import { analyzeAndValidateNgModules } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { FavouriteCategoryModel } from 'src/app/_models/favouriteCategory.model';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  errorExists = false;
  errorText = '';
  //selectedCategoriesArray: Array<FavouriteCategoryModel>;

  categories = [{value: 1, viewValue: 'Laptop'},
     {value: 2, viewValue: 'Desktop'},
     {value: 3, viewValue: 'Mobile'}
    ];

    selectedCategories: any[]
  

  constructor(private userService: UserService,private router:Router) {}

  ngOnInit(): void {
  
  }

  onSubmit(data){ 
    var selectedCategoriesArray: FavouriteCategoryModel[] = [];
    
    this.selectedCategories.forEach(categoryId => {

      var kategorije: any = this.categories
      var id: number=categoryId;
      var naziv: string = kategorije[categoryId-1].viewValue;
      var elem: FavouriteCategoryModel = {id:id,naziv:naziv};

      selectedCategoriesArray.push(elem);

    })
    console.log(selectedCategoriesArray);
    console.log(data.username);

    if(true){
      this.errorExists = false;
      var newUser = this.userService.registerUser(data.name,data.forename,data.email,data.phone,data.adress,data.username,data.password,selectedCategoriesArray);
      console.log(newUser)
      this.router.navigate(['/']);
    } else{
      this.errorText = "User with this username already exists";
      this.errorExists = true;
    }

  }


}
