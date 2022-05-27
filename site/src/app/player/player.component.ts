import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Cancion_Simple } from '../models/cancion-simple.interface';
import { Estilo } from '../models/estilo.interface';
import { CancionService } from '../services/cancion.service';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.scss']
})
export class PlayerComponent implements OnInit {
  estilo?: Estilo;

  constructor() { }

  ngOnInit(): void {}

  actualizarEstilo(value: Estilo) {
    this.estilo = value;
  }
}
