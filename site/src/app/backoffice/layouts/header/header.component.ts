import { Component, ElementRef, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
nombre: any = localStorage.getItem('userName');
filtrosModel: string = "";
textoBuscado: string = "";
@Output() eventEmitter = new EventEmitter<string>();
@ViewChild('selectValue') selectedvalue!: ElementRef<HTMLSelectElement>;
  constructor() { }

  ngOnInit(): void {
  }

  filterParametro():void{
    this.filtrosModel = this.selectedvalue.nativeElement.value;
    localStorage.setItem('filtroAdmin', this.filtrosModel);
   }

  logOut():void{
    localStorage.removeItem('usuarioId');
    localStorage.removeItem('rol');
    localStorage.removeItem('filtroAdmin');
    localStorage.removeItem('userName');
  }

  enviarTextoBuscado(): void {
    this.eventEmitter.emit(this.textoBuscado);
  }



  





 }
  



