import { Component, Input, OnInit , OnChanges, SimpleChanges } from '@angular/core';
import { Cancion } from 'src/app/models/cancion.interface';
import { CancionService } from 'src/app/services/cancion.service';

@Component({
  selector: 'app-tabla-canciones',
  templateUrl: './tabla-canciones.component.html',
  styleUrls: ['./tabla-canciones.component.scss']
})
export class TablaCancionesComponent implements OnInit , OnChanges{

  Canciones: Cancion[] = [];


  page: number = 0;


  first: boolean = false;
  last: boolean = false;
  clickpage: boolean = false;
  totalPages: number = 0;
  totalElements: number = 0;
  
  
  @Input()busqueda: string = "";

  constructor(private cancionService: CancionService) { }

  ngOnChanges(busqueda: SimpleChanges): void {
    this.getCancionesFiltradas()
  }

  ngOnInit(): void {
    this.getCancionesFiltradas()
  }

  public nextPage():void {
    this.clickpage = true;
    this.page = this.page + 1;
    this.getCancionesFiltradas();
  }

  public previousPage():void {
    this.clickpage = true;
    this.page = this.page - 1;
    this.getCancionesFiltradas();
  }

 

  public getCancionesFiltradas():void{
    const filtro = localStorage.getItem('filtroAdmin');
    if(this.clickpage == true){
      this.clickpage = false;
    }else{
      this.page = 0;
    }
    this.cancionService.getCancionesAdministrador(this.busqueda, filtro! , this.page).subscribe({
          next: (data: any) => {this.Canciones = data.content
            this.totalPages = data.totalPages;
            this.first = data.first;
            this.last = data.last;
            console.log(data);
          },
          error: (err) => {this.handleError(err);}
        })
    }
  


  private handleError(err:any):void{
    // implementar gestión de errores;
  }


}
