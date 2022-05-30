import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Output, EventEmitter } from '@angular/core';
import { Estilo } from 'src/app/models/estilo.interface';
import { EstiloService } from 'src/app/services/estilo.service';
import { SharedService } from 'src/app/services/shared.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  usuarioNombre: any = localStorage.getItem('userName');

  estiloFiltro?: Estilo;
  estilos: Estilo[] = [];

  constructor(private estiloService: EstiloService, private sharedService: SharedService) { }

  ngOnInit(): void {
  }

  logOut():void{
    localStorage.removeItem('usuarioId');
    localStorage.removeItem('rol');
    localStorage.removeItem('filtroAdmin');
    localStorage.removeItem('userName');
  }

  enviarEstilo(): void {
    this.sharedService.change(this.estiloFiltro);
  }

  filtrarEstilos(event?: any): void {
    let estiloBusqueda: string = "";
    if (event?.query) {
      estiloBusqueda = event.query;
    }

    this.estiloService.getEstilos(estiloBusqueda).subscribe({
      next: (data: any) => {
        this.estilos = data.content;
      }
    }
    )
  }
}
