import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderService } from 'src/app/_services/order.service';
import { UserService } from 'src/app/_services/user.service';
import { DialogComponent } from '../dialog/dialog.component';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  orders;

  constructor(private userService: UserService,private orderService:OrderService,private router:Router,private activatedRoute: ActivatedRoute,public dialog:MatDialog) { 
    this.router.routeReuseStrategy.shouldReuseRoute = () => {
      return false;
    };
  }

  ngOnInit(): void { // get porudzbine za ulogovanog usera
    if(UserService.isUserLogged){
      this.orders = this.orderService.getOrdersForUser(UserService.currentUser.id);
    }
  }
  

  onCancel(orderId: number){
    let dialogRef = this.dialog.open(DialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      if(DialogComponent.answer){
        this.orderService.cancelOrder(orderId);
        console.log(orderId);
        this.router.navigate([this.router.url])
      }
    });
  }

}
