import { Component, Input, OnInit } from '@angular/core';
import { Cancion_Simple } from 'src/app/models/cancion-simple.interface';
import { Estilo } from 'src/app/models/estilo.interface';
import { CancionService } from 'src/app/services/cancion.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-tira-reproducciones',
  templateUrl: './tira-reproducciones.component.html',
  styleUrls: ['./tira-reproducciones.component.scss']
})
export class TiraReproduccionesComponent implements OnInit {
  canciones: Cancion_Simple[] = [];
  base64Prefix: string = environment.base64Prefix;
  @Input() estilo?: Estilo;

  constructor(private cancionService: CancionService) { }

  ngOnInit(): void {
    this.actualizarTiras();
  }

  ngOnChanges(changes: any): void {
    this.actualizarTiras();
  }

  actualizarTiras(): void {
    this.cancionService.getCancionesMasReproducidas(this.estilo).subscribe({
      next: (data: any) => {
        this.canciones = data;
      }
    }
    )
  }
}
