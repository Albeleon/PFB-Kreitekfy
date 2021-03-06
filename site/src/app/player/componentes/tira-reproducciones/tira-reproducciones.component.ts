import { Component, Input, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Cancion_Simple } from 'src/app/models/cancion-simple.interface';
import { Cancion_Usuario } from 'src/app/models/cancion-usuario.interface';
import { Estilo } from 'src/app/models/estilo.interface';
import { CancionService } from 'src/app/services/cancion.service';
import { SharedService } from 'src/app/services/shared.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-tira-reproducciones',
  templateUrl: './tira-reproducciones.component.html',
  styleUrls: ['./tira-reproducciones.component.scss'],
  providers: [MessageService]
})
export class TiraReproduccionesComponent implements OnInit {
  canciones: Cancion_Simple[] = [];
  base64Prefix: string = environment.base64Prefix;
  defaultImagen: string = environment.defaultImage;
  estilo?: Estilo;
  loader: boolean = true;

  constructor(private cancionService: CancionService, private messageService: MessageService, private sharedService: SharedService) { }

    ngOnInit(): void {
      this.actualizarTiras();
      this.sharedService.getEmittedValue().subscribe(item => { this.estilo = item; this.actualizarTiras()});
    }

  ngOnChanges(changes: any): void {
    this.actualizarTiras();
  }

  actualizarTiras(): void {
    if (!this.estilo || this.estilo.id) {
      this.cancionService.getCancionesMasReproducidas(this.estilo).subscribe({
        next: (data: any) => {
          this.canciones = data;
          this.loader = false;
        },
        error: (err: any) => {
          this.loader = false;
          this.messageService.add({
            severity: 'error',
            summary: 'Error',
            detail: 'Se ha producido un error conectando a la base de datos',
          });
        }
      }
      )
    }
  }
  
}
