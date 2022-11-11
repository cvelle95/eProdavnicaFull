import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {

  constructor() { }
  static answer:boolean;
  ngOnInit(): void {
  }
  onYesClick(){
    DialogComponent.answer = true;
  }
  onNoClick(){
    DialogComponent.answer = false
  }
  
}
