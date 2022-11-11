import {ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {UserService} from '../_services/user.service';
import {StatModel} from '../_models/stat.model';
import {OrderService} from '../_services/order.service';
import {MatTable, MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css']
})

export class StatsComponent implements OnInit {

  loadPage:boolean=false;
  stats:Array<StatModel>=[{brojPorudzbina:0,ukupanPrihod:0,ukupnaKolicina:0},{brojPorudzbina:0,ukupanPrihod:0,ukupnaKolicina:0},{brojPorudzbina:0,ukupanPrihod:0,ukupnaKolicina:0},{brojPorudzbina:0,ukupanPrihod:0,ukupnaKolicina:0},{brojPorudzbina:0,ukupanPrihod:0,ukupnaKolicina:0},{brojPorudzbina:0,ukupanPrihod:0,ukupnaKolicina:0},{brojPorudzbina:0,ukupanPrihod:0,ukupnaKolicina:0},{brojPorudzbina:0,ukupanPrihod:0,ukupnaKolicina:0},{brojPorudzbina:0,ukupanPrihod:0,ukupnaKolicina:0},{brojPorudzbina:0,ukupanPrihod:0,ukupnaKolicina:0},{brojPorudzbina:0,ukupanPrihod:0,ukupnaKolicina:0},{brojPorudzbina:0,ukupanPrihod:0,ukupnaKolicina:0}];
  selectedItem:any;

  dataSource = new MatTableDataSource<StatModel>([]);

  constructor(private orderService:OrderService,private cdr: ChangeDetectorRef) { }

  meseci = [{mesec: 'Januar'},{mesec: 'Februar'},{mesec: 'Mart'},{mesec: 'April'},
    {mesec: 'Maj'},{mesec: 'Jun'},{mesec: 'Jul'},{mesec: 'Avgust'},{mesec: 'Septembar'},
    {mesec: 'Oktobar'},{mesec: 'Novembar'},{mesec: 'Decembar'}];

  displayedColumns: string[] = ['mesec'];
  displayedColumns2: string[] = ['brojPorudzbina','ukupanPrihod','ukupnaKolicina'];


  items = [{value: 0, viewValue: 'Svi'},
    {value: 3, viewValue: 'Laptop Sony'},
    {value: 4, viewValue: 'Lenovo Laptop i5'},
    {value: 5, viewValue: 'Samsung s8'},
    {value: 6, viewValue: 'Samsung J4'},
    {value: 7, viewValue: 'Dell Desktop Core 2'},
    {value: 8, viewValue: 'Iphone 12'},
    {value: 9, viewValue: 'Huawei P40 pro'},
    {value: 10, viewValue: 'Apple MacBook pro'},
    {value: 11, viewValue: 'Gaming Computer'},
    {value: 12, viewValue: 'Apple Imac Desktop'}
  ]

  ngOnInit(){
    this.loadPage = UserService.isAdmin;
    this.selectedItem = this.items[0];
    //try{
       //const stats = this.orderService.getStats(2021,0);
       //console.log(stats)

    //}catch (error){
      //console.error(error)
    //}
  }

  onSubmit(data){
    var selItemId = this.selectedItem
    this.stats = this.orderService.getStats(data.godina,selItemId);
    this.cdr.detectChanges();
  }

}
