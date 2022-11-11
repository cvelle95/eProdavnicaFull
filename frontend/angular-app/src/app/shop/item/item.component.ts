import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';
import { ItemModel } from 'src/app/_models/item.model';
import { OrderModel } from 'src/app/_models/order.model';
import { RatingItemModel } from 'src/app/_models/ratingItem.model';
import { UserModel } from 'src/app/_models/user.model';
import { ItemService } from 'src/app/_services/item.service';
import { OrderService } from 'src/app/_services/order.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit {

  //// Podaci za proizvod
  item:any;
  selectedItem:ItemModel; // nadji proizvod pod ovim idom iz baze i ucitaj ga u template
  userComents: RatingItemModel[];
  userComentsReal:any;

  disabledRating: boolean = true;
  orders:OrderModel[];

  ////// Podaci za star rating
   rating = 0;
   starCount = 5;
   ratingArr: boolean[] = []; // true = solid,false = blank
  /////
  currentUser:UserModel;

  constructor(private router:Router,private itemService:ItemService,private userService:UserService,private activatedRoute: ActivatedRoute,private orderService:OrderService,private _snackBar: MatSnackBar) { 
    if (this.router.getCurrentNavigation().extras.state) {
      this.item = this.router.getCurrentNavigation().extras.state.item;
      this.selectedItem = this.item;

      console.log(this.selectedItem);
      
    }
    // punjenje araya za prikazivanje ratinga;
    //ako je korisnik ulogovan proveri da li je vec ocenio proizvod sa tim id-om,ako jeste napuni sa true sa vrednoscu ratinga a ostalo sa false do 5

    this.ratingArr = Array(this.starCount).fill(false);
    this.router.routeReuseStrategy.shouldReuseRoute = () => {
      return false;
    }
  }

  ngOnInit(): void {
    this.userComents = this.itemService.getRatingForItem(this.selectedItem.id);
    this.currentUser = UserService.currentUser
    console.log(this.userComents)
    
    if(UserService.isUserLogged){
      this.disabledRating = false;
      
  }//komentar polje disabled ili ne
    //array svih komentara za item sa tim idom koje treba dohvatiti iz baze
   // this.userComents = [{rating:4,name:"Ivan",text:"Super proizvod,malo je skup"},{rating:3,name:"Jovana",text:"Nista Posebno"}]
  }

  //Vrati punu zvezdu
  returnStar(i: number){
    if (this.rating >= i+1){
      return 'star';
    }
    else{
      return 'star_border';
    }
  }

  onStarClick(i: number){
    this.rating = i+1;
  }

  onSubmit(data){
    //proveri da li je korisnik ulogovan,ako jeste posalji rating u bazu ovde
    console.log(data.comment+" "+this.rating);
    
    if(UserService.isUserLogged){
    this.itemService.insertUserRating(this.rating,data.comment,this.currentUser.id,this.selectedItem.id,this.currentUser.ime);
    console.log(this.rating,data.comment,this.currentUser.id,this.selectedItem.id)

    let navigationExtras: NavigationExtras = { // pravljanje stejta za prosledjivanje
      state: { // definisanje sta se salje
        item: this.item
      }
    }
    this.router.navigate([this.router.url],navigationExtras)

    }else{
      this.router.navigate(["/login"])
    }

  }

  addToCart(){
    OrderService.shoppingCart.push(this.selectedItem);
    this._snackBar.open("Item is added to shopping cart!","ok");
    console.log(OrderService.shoppingCart)

  }


}
