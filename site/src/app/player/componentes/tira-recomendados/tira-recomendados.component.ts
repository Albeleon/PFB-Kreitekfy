import { Component, Input, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Cancion_Simple } from 'src/app/models/cancion-simple.interface';
import { Estilo } from 'src/app/models/estilo.interface';
import { CancionService } from 'src/app/services/cancion.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-tira-recomendados',
  templateUrl: './tira-recomendados.component.html',
  styleUrls: ['./tira-recomendados.component.scss'],
  providers: [MessageService]
})
export class TiraRecomendadosComponent implements OnInit {
  usuarioId: string | null = localStorage.getItem('usuarioId');

  canciones: Cancion_Simple[] = [];
  base64Prefix: string = environment.base64Prefix;
  defaultImagen: string = environment.defaultImage;
  loader: boolean = true;

  constructor(private cancionService: CancionService, private messageService: MessageService) { }

  ngOnInit(): void {
    this.actualizarTiras();
  }

  ngOnChanges(changes: any): void {
    this.actualizarTiras();
  }

  actualizarTiras(): void {
    this.cancionService.getCancionesRecomendadas(this.usuarioId).subscribe({
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
