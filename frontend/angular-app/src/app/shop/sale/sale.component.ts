import { Component, OnInit} from '@angular/core';
import { NavigationExtras, Router } from '@angular/router';
import { ItemModel } from 'src/app/_models/item.model';
import { ItemService } from 'src/app/_services/item.service';

@Component({
  selector: 'app-sale',
  templateUrl: './sale.component.html',
  styleUrls: ['./sale.component.css']
})
export class SaleComponent implements OnInit {

  constructor(private router: Router,private itemService: ItemService) { }
  items: any;
  ngOnInit(): void {
    this. items = [];
    this.items = this.itemService.findAll();
    console.log(this.items);
    //this.items = [{id: 1,title: "Kuce",category: "kucni ljubimci",description: "Polovno kuce Doga",cena: 500},
    //{id: 2,title: "Pas",category: "kucni ljubimci",description: "Polovno pasce malo",cena: 300},{id: 3,title: "Veliki Pas",category: "kucni ljubimci",description: "Polovno Veliko kuce Doga 2",cena: 250}]
  }
  
  categories = [{value: 1, viewValue: 'Laptop'},
     {value: 2, viewValue: 'Desktop'},
     {value: 3, viewValue: 'Mobile'}
    ]

    selectedCategories: any;

  ngOnDestroy(): void {
    this.items = []; //
  }
  
  openItem(id:number): void {
    var itemFound: any;
    console.log("clicked item "+id);
    this.items.forEach(item => {if(item.id == id )itemFound=item});

    let navigationExtras: NavigationExtras = { // pravljanje stejta za prosledjivanje id-a preko routera(mogu se prosledjivati celi modeli ovako)
      state: { // definisanje sta se salje
        item: itemFound
      }
    }

    this.router.navigate(['item'],navigationExtras); //redirekcija i slanje stejta
  }

  onSubmit(data){
    console.log(data);
    console.log(this.selectedCategories)
    const MAXIMUM_INT = 999999;

    var categoryId = this.selectedCategories;
    var priceMin = data.minPrice;
    var priceMax = data.maxPrice;

    if(priceMin < 1 || isNaN(priceMin) || priceMin == ""){
      priceMin = 0;
    }

    if(isNaN(priceMax) || priceMax > MAXIMUM_INT || priceMax == "" || priceMax == null){
      priceMax = MAXIMUM_INT;
    }

    console.log(priceMin,priceMax);

    this.items = this.itemService.filterSearch(categoryId,priceMin,priceMax);

    console.log(this.items);
    
  }
  
}
