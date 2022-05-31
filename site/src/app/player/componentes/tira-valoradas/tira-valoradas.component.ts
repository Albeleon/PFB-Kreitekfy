import { Component, Input, OnInit } from '@angular/core';
import { Cancion_Simple } from 'src/app/models/cancion-simple.interface';
import { Estilo } from 'src/app/models/estilo.interface';
import { CancionService } from 'src/app/services/cancion.service';
import { SharedService } from 'src/app/services/shared.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-tira-valoradas',
  templateUrl: './tira-valoradas.component.html',
  styleUrls: ['./tira-valoradas.component.scss']
})
export class TiraValoradasComponent implements OnInit {
  canciones: Cancion_Simple[] = [];
  base64Prefix: string = environment.base64Prefix;
  defaultImagen: string = environment.defaultImage;
  estilo?: Estilo;
  loader: boolean = true;

  constructor(private cancionService: CancionService, private sharedService: SharedService) { }

  ngOnInit(): void {
    this.actualizarTiras();
    this.sharedService.getEmittedValue().subscribe(item => { this.estilo = item; this.actualizarTiras()});
  }

  actualizarTiras(): void {
    if (!this.estilo || this.estilo.id) {
      this.cancionService.getCancionesMasValoradas(this.estilo).subscribe({
        next: (data: any) => {
          this.canciones = data;
          this.loader = false;
        }
      }
      );
    }
  }

}
