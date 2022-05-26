import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-backoffice',
  templateUrl: './backoffice.component.html',
  styleUrls: ['./backoffice.component.scss']
})
export class BackofficeComponent implements OnInit {
  busqueda: string = "";
  constructor() { }

  ngOnInit(): void {
  }

  recibirTextoBuscado(value:string){
    this.busqueda = value;
  }

}
