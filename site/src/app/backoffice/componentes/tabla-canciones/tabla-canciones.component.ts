import { Component, Input, OnInit , OnChanges, SimpleChanges } from '@angular/core';
import { ViewChild } from '@angular/core';
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
import {MessageService} from 'primeng/api';


@Component({
  selector: 'app-tabla-canciones',
  templateUrl: './tabla-canciones.component.html',
  styleUrls: ['./tabla-canciones.component.scss'],
  providers: [MessageService]
})
export class TablaCancionesComponent implements OnInit , OnChanges{
// Variables lista y búsqueda de canciones//
  Canciones: Cancion[] = [];
  page: number = 0;
  first: boolean = false;
  last: boolean = false;
  clickpage: boolean = false;
  totalPages: number = 0;
  totalElements: number = 0;
  display: boolean = false;
  @Input()busqueda: string = "";


  // Variables modal inserción de canciones//

  exito: Boolean = false;
  operacion: string = "";

  cancion?: Cancion;

  albumFiltro?: Album;
  albumes: Album[] = [];

  artistaFiltro?: Artista;
  artistas: Artista[] = [];
  
  estiloFiltro?: Estilo;
  estilos: Estilo[] = [];
  fechaHoy: Date = new Date();




  constructor(
    private cancionService: CancionService,
    private albumService: AlbumService,
    private artistaService: ArtistaService,
    private estiloService: EstiloService,
    private route: ActivatedRoute ,
    private messageService: MessageService) {}


    // Lógica lista y búsqueda de canciones//


  showDialog(){
    const tiempoTranscurrido = Date.now();
    const hoy = new Date(tiempoTranscurrido);
    this.display = true;
    this.cancion!.fecha = hoy;
  }

  ngOnChanges(busqueda: SimpleChanges): void {
    this.getCancionesFiltradas()
  }

  ngOnInit(): void {
    this.getCancionesFiltradas();
    const entryParam: string = this.route.snapshot.paramMap.get("cancionId") ?? "new";
    if (entryParam !== "new") {
      this.initEditarCancion(entryParam);
    }
    else {
      this.initInsertarCancion();
    }
  }

  public nextPage():void {
    this.clickpage = true;
    this.page = this.page + 1;
    this.getCancionesFiltradas();
  }

  public previousPage():void {
    this.clickpage = true;
    this.page = this.page - 1;
    this.getCancionesFiltradas();
  }

 

  public getCancionesFiltradas():void{
    const filtro = localStorage.getItem('filtroAdmin');
    if(this.clickpage == true){
      this.clickpage = false;
    }else{
      this.page = 0;
    }
    this.cancionService.getCancionesAdministrador(this.busqueda, filtro! , this.page).subscribe({
          next: (data: any) => {this.Canciones = data.content
            this.totalPages = data.totalPages;
            this.first = data.first;
            this.last = data.last;
            console.log(data);
          },
          error: (err) => {this.handleError(err);}
        })
    }
  


  private handleError(err:any):void{
    // implementar gestión de errores;
  }


    // Lógica modal inserción de canciones//


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

    addSingle() {
      this.messageService.add({severity:'success', summary:'Inserción Correcta', detail:'Canción Insertada'});
  }
  
    insertarCancion(): void {
      if (this.albumFiltro && this.estiloFiltro && this.artistaFiltro
         && this.cancion && this.cancion.nombre != "" && this.cancion.duracion! > 0
         && this.cancion.fecha) {
        this.cancion.albumId = this.albumFiltro.id;
        this.cancion.artistaId = this.artistaFiltro.id;
        this.cancion.estiloId = this.estiloFiltro.id;
        this.cancionService.insertarCancion(this.cancion).subscribe({
          next: (data: any) => { this.exito = true; 
            if(this.exito){
              this.display = false;
              this.ngOnInit();
              this.addSingle();
            }
          }
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
