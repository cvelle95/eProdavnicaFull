import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { OrderService } from 'src/app/_services/order.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor(private orderService:OrderService,private userService:UserService,private _snackBar: MatSnackBar) { }
  orders: any[];
  isUserLogged: boolean;
  isEmpty: boolean = false;
  disableOrder: boolean = false;
  ngOnInit(): void {
    this.isUserLogged = UserService.isUserLogged
    this.orders = OrderService.shoppingCart;

    if(this.orders.length<1){
      this.isEmpty = true;
    }
  }

  onRemove(order){
    var index = OrderService.shoppingCart.indexOf(order);

    OrderService.shoppingCart.splice(index,1);
    if(this.orders.length<1){
      this.isEmpty = true;
    }
    this.ngOnInit();
  }

  addOrders(){
    this.disableOrder = true;
    if(UserService.isUserLogged){
      OrderService.shoppingCart.forEach(order=>{
        this.orderService.insertOrder(1,order.cena,"active",UserService.currentUser.id,order.id);
      });
      this._snackBar.open("Orders Sent!","ok");
      setTimeout(() => {
        console.log('sleep');
        OrderService.shoppingCart = [];
        this.ngOnInit();
         }
      , 500);
    }
    else{
      this._snackBar.open("Please login first!","ok");
    }
  }

}
