import { Component, OnInit, ViewChild } from '@angular/core';
import { Album } from 'src/app/models/album.interface';
import { Artista } from 'src/app/models/artista.interface';
import { Cancion } from 'src/app/models/cancion.model';
import { Estilo } from 'src/app/models/estilo.interface';
import { AlbumService } from 'src/app/services/album.service';
import { ArtistaService } from 'src/app/services/artista.service';
import { CancionService } from 'src/app/services/cancion.service';
import { EstiloService } from 'src/app/services/estilo.service';
import { ActivatedRoute } from '@angular/router';
import { Calendar } from 'primeng/calendar';

@Component({
  selector: 'app-cancion-form',
  templateUrl: './cancion-form.component.html',
  styleUrls: ['./cancion-form.component.scss']
})
export class CancionFormComponent implements OnInit {
  exito: Boolean = false;
  operacion: string = "";

  cancion?: Cancion;

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
    private estiloService: EstiloService,
    private route: ActivatedRoute ) {}

  ngOnInit(): void {
    const entryParam: string = this.route.snapshot.paramMap.get("cancionId") ?? "new";
    if (entryParam !== "new") {
      this.initEditarCancion(entryParam);
    }
    else {
      this.initInsertarCancion();
    }
  }

  initInsertarCancion() {
    this.operacion = "new";
    this.cancion = new Cancion();
  }

  initEditarCancion(id: string) {
    this.cancion = new Cancion();
    this.operacion = "edit";
    this.cancionService.getCancionById(id).subscribe({
      next: (data: Cancion) => {
        this.cancion = data;
        this.cancion.fecha = new Date(this.cancion.fecha!);

        this.albumService.getAlbumById(this.cancion.albumId!.toString()).subscribe({
          next: (data: any) => { this.albumFiltro = data; } } );
        this.artistaService.getArtistaById(this.cancion.artistaId!.toString()).subscribe({
          next: (data: any) => { this.artistaFiltro = data; } } );
        this.estiloService.getEstiloById(this.cancion.estiloId!.toString()).subscribe({
          next: (data: any) => { this.estiloFiltro = data; } } );

         }
    });
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
       && this.cancion && this.cancion.nombre != "" && this.cancion.duracion! > 0
       && this.cancion.fecha) {
      this.cancion.albumId = this.albumFiltro.id;
      this.cancion.artistaId = this.artistaFiltro.id;
      this.cancion.estiloId = this.estiloFiltro.id;
      this.cancionService.insertarCancion(this.cancion).subscribe({
        next: (data: any) => { this.exito = true; }
      }
      )
    }
  }

  editarCancion(): void {
    if (this.albumFiltro && this.estiloFiltro && this.artistaFiltro
       && this.cancion && this.cancion.nombre != "" && this.cancion.duracion! > 0
       && this.cancion.fecha) {
      this.cancion.albumId = this.albumFiltro.id;
      this.cancion.artistaId = this.artistaFiltro.id;
      this.cancion.estiloId = this.estiloFiltro.id;
      this.cancionService.editarCancion(this.cancion).subscribe({
        next: (data: any) => { this.exito = true; }
      }
      )
    }
  }

}
