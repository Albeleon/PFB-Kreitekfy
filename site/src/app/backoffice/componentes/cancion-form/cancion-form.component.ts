import { Component, OnInit } from '@angular/core';
import { Album } from 'src/app/models/album.interface';
import { Artista } from 'src/app/models/artista.interface';
import { Cancion } from 'src/app/models/cancion.model';
import { Estilo } from 'src/app/models/estilo.interface';
import { AlbumService } from 'src/app/services/album.service';
import { ArtistaService } from 'src/app/services/artista.service';
import { CancionService } from 'src/app/services/cancion.service';
import { EstiloService } from 'src/app/services/estilo.service';

@Component({
  selector: 'app-cancion-form',
  templateUrl: './cancion-form.component.html',
  styleUrls: ['./cancion-form.component.scss']
})
export class CancionFormComponent implements OnInit {

  exito: Boolean = false;

  cancion?: Cancion;
  nombre: string = "";
  duracion: string = "";
  fecha: string = "";

  albumFiltro?: Album;
  albumes: Album[] = [];

  artistaFiltro?: Artista;
  artistas: Artista[] = [];
  
  estiloFiltro?: Estilo;
  estilos: Estilo[] = [];

  constructor(
    private cancionService: CancionService,
    private albumService: AlbumService,
    private artistaService: ArtistaService,
    private estiloService: EstiloService) { }

  ngOnInit(): void {
    this.cancion = new Cancion();
  }

  filtrarAlbumes(event?: any): void {
    let albumBusqueda: string = "";
    if (event?.query) {
      albumBusqueda = event.query;
    }

    this.albumService.getAlbumes(albumBusqueda).subscribe({
      next: (data: any) => {
        this.albumes = data.content;
      }
    }
    )
  }

  filtrarArtistas(event?: any): void {
    let artistaBusqueda: string = "";
    if (event?.query) {
      artistaBusqueda = event.query;
    }

    this.artistaService.getArtistas(artistaBusqueda).subscribe({
      next: (data: any) => {
        this.artistas = data.content;
      }
    }
    )
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

  insertarCancion(): void {
    if (this.albumFiltro && this.estiloFiltro && this.artistaFiltro
       && this.cancion && this.cancion.nombre != "" && this.cancion.duracion! > 0) {
      this.cancion.albumId = this.albumFiltro.id;
      this.cancion.artistaId = this.artistaFiltro.id;
      this.cancion.estiloId = this.estiloFiltro.id;
      this.cancionService.insertarCancion(this.cancion).subscribe({
        next: (data: any) => { this.exito = true; }
      }
      )
    }
  }

}
