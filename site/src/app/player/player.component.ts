import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Cancion_Simple } from '../models/cancion-simple.interface';
import { CancionService } from '../services/cancion.service';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.scss']
})
export class PlayerComponent implements OnInit {
  novedades: Cancion_Simple[] = [];
  base64Prefix: string = environment.base64Prefix;
  estilo: string = "";

  constructor(private cancionService: CancionService) { }

  ngOnInit(): void {
    this.actualizarTiras();
  }

  actualizarTiras(): void {
    this.cancionService.getCancionesNovedades(this.estilo).subscribe({
      next: (data: any) => {
        this.novedades = data.content;
      }
    }
    )
  }

  actualizarEstilo(value: string) {
    this.estilo = value;
    this.actualizarTiras();
  }
}
