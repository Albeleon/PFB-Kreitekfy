import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Output, EventEmitter } from '@angular/core';
import { Estilo } from 'src/app/models/estilo.interface';
import { EstiloService } from 'src/app/services/estilo.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  usuarioNombre: any = localStorage.getItem('userName');

  estiloFiltro?: Estilo;
  estilos: Estilo[] = [];

  @Output() eventEmitter = new EventEmitter<Estilo>();

  constructor(private estiloService: EstiloService) { }

  ngOnInit(): void {
  }

  enviarEstilo(): void {
    this.eventEmitter.emit(this.estiloFiltro);
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
