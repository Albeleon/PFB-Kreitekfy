import { Component, ElementRef, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { SharedService } from 'src/app/services/shared.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  pattern: string = environment.pattern;
  textPattern: string = environment.textPattern;
  nombre: any = localStorage.getItem('userName');
  filtrosModel: string = "";
  textoBuscado: string = "";
  getLocalizacion: any;
  @ViewChild('selectValue') selectedvalue!: ElementRef<HTMLSelectElement>;
  constructor(private sharedService: SharedService) { }

  getLocation(){
    this.sharedService.getEmittedValueBack().subscribe((data: any) => { this.getLocalizacion = data; });
  }

  ngOnInit(): void {
    this.getLocation();
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
    localStorage.removeItem('localizacion');
  }

  enviarTextoBuscado(): void {
    this.sharedService.change(this.textoBuscado);
  }



  





 }
  



