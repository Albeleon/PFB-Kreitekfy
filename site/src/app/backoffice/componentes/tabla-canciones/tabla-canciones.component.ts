import {
  Component,
  Input,
  OnInit,
  OnChanges,
  SimpleChanges,
} from '@angular/core';
import { ViewChild } from '@angular/core';
import { Album } from 'src/app/models/album.interface';
import { Artista } from 'src/app/models/artista.interface';
import { Cancion } from 'src/app/models/cancion.interface';
import { Estilo } from 'src/app/models/estilo.interface';
import { AlbumService } from 'src/app/services/album.service';
import { ArtistaService } from 'src/app/services/artista.service';
import { CancionService } from 'src/app/services/cancion.service';
import { EstiloService } from 'src/app/services/estilo.service';
import { ActivatedRoute } from '@angular/router';
import { Calendar } from 'primeng/calendar';
import { ConfirmationService, MessageService } from 'primeng/api';
import { SharedService } from 'src/app/services/shared.service';
import { environment } from 'src/environments/environment';
import { NgForm } from '@angular/forms';
import { debounceTime, pipe } from 'rxjs';

@Component({
  selector: 'app-tabla-canciones',
  templateUrl: './tabla-canciones.component.html',
  styleUrls: ['./tabla-canciones.component.scss'],
  providers: [MessageService, ConfirmationService],
})
export class TablaCancionesComponent implements OnInit {
  @ViewChild('cancionForm') form?: NgForm;

  pattern: string = environment.pattern;
  base64Prefix: string = environment.base64Prefix;
  defaultImage: string = environment.defaultImage;

  // Variables lista y búsqueda de canciones//
  Canciones: Cancion[] = [];
  page: number = 0;
  first: boolean = false;
  last: boolean = false;
  clickpage: boolean = false;
  totalPages: number = 0;
  totalElements: number = 0;
  display: boolean = false;

  busqueda: string = '';

  localizacion: any;
  getLocalizacion: any = localStorage.getItem('localizacion');
  // Variables modal inserción de canciones//

  exito: Boolean = false;
  operacion: string = '';

  cancion: Cancion = {
    nombre: '',
    id: 0,
  };
  albumFiltro?: Album;
  albumes: Album[] = [];
  artistaFiltro?: Artista;
  artistas: Artista[] = [];

  estiloFiltro?: Estilo;
  estilos: Estilo[] = [];
  fechaHoy: Date = new Date();
  toogleCreate: boolean = false;
  titleMode: string = '';
  flagErrorSearch: boolean = false;
  loader: boolean = true;

  constructor(
    private cancionService: CancionService,
    private albumService: AlbumService,
    private artistaService: ArtistaService,
    private estiloService: EstiloService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private sharedService: SharedService
  ) {}

  // Lógica lista y búsqueda de canciones//

  showDialogCreate() {
    this.form!.control.markAsPristine();
    this.toogleCreate = true;
    this.display = true;
    this.initInsertarCancion();
  }

  setLocation() {
    this.sharedService.changeBack('canciones');
  }

  showDialogEdit(idCancion: number) {
    this.form!.control.markAsPristine();
    this.toogleCreate = false;
    this.display = true;
    this.initEditarCancion(idCancion!.toString());
  }

  ngOnInit(): void {
    this.getCancionesFiltradas();
    this.sharedService
      .getEmittedValue()
      .pipe(debounceTime(250))
      .subscribe((data) => {
        this.busqueda = data;
        this.getCancionesFiltradas();
      });
    this.setLocation();
    localStorage.setItem('filtroAdmin', 'cancion');
  }

  public nextPage(): void {
    this.clickpage = true;
    this.page = this.page + 1;
    this.getCancionesFiltradas();
  }

  public previousPage(): void {
    this.clickpage = true;
    this.page = this.page - 1;
    this.getCancionesFiltradas();
  }

  public getCancionesFiltradas(): void {
    if (this.busqueda == '' || this.busqueda.match(this.pattern)) {
      const filtro = localStorage.getItem('filtroAdmin');
      if (this.clickpage == true) {
        this.clickpage = false;
      } else {
        this.page = 0;
      }
      this.cancionService
        .getCancionesAdministrador(this.busqueda, filtro!, this.page)
        .subscribe({
          next: (data: any) => {
            this.Canciones = data.content;
            this.totalPages = data.totalPages;
            this.first = data.first;
            this.last = data.last;
            this.loader = false;
          },
          error: (err) => {
            this.handleError(err);
          },
        });
    }
  }

  private handleError(err: any): void {
    // implementar gestión de errores;
  }

  // Lógica modal inserción de canciones//

  initInsertarCancion() {
    const timeNow = Date.now();
    const now = new Date(timeNow);
    this.cancion = {} as Cancion;
    this.albumFiltro = {} as Album;
    this.artistaFiltro = {} as Artista;
    this.estiloFiltro = {} as Estilo;
    this.cancion!.fecha = now;
    this.titleMode = 'Crear';
  }

  initEditarCancion(id: string) {
    this.cancion = { nombre: '', duracion: 1, fecha: new Date() };
    this.operacion = 'edit';
    this.cancionService.getCancionById(id).subscribe({
      next: (data: Cancion) => {
        this.cancion = data;
        this.cancion.fecha = new Date(this.cancion.fecha!);

        this.albumService
          .getAlbumById(this.cancion.albumId!.toString())
          .subscribe({
            next: (data: any) => {
              this.albumFiltro = data;
            },
          });
        this.artistaService
          .getArtistaById(this.cancion.artistaId!.toString())
          .subscribe({
            next: (data: any) => {
              this.artistaFiltro = data;
            },
          });
        this.estiloService
          .getEstiloById(this.cancion.estiloId!.toString())
          .subscribe({
            next: (data: any) => {
              this.estiloFiltro = data;
            },
          });
      },
    });
  }

  filtrarAlbumes(event?: any): void {
    let albumBusqueda: string = '';
    if (event?.query) {
      albumBusqueda = event.query;
    }

    this.albumService.getAlbumes(albumBusqueda).subscribe({
      next: (data: any) => {
        this.albumes = data.content;
      },
    });
  }

  filtrarEstilos(event?: any): void {
    let estiloBusqueda: string = '';
    if (event?.query) {
      estiloBusqueda = event.query;
    }

    this.estiloService.getEstilos(estiloBusqueda).subscribe({
      next: (data: any) => {
        this.estilos = data.content;
      },
    });
  }

  filtrarArtistas(event?: any): void {
    let artistaBusqueda: string = '';
    if (event?.query) {
      artistaBusqueda = event.query;
    }

    this.artistaService.getArtistas(artistaBusqueda).subscribe({
      next: (data: any) => {
        this.artistas = data.content;
      },
    });
  }

  showDialogDelete(cancionId: number) {
    this.confirmationService.confirm({
      message: '¿Desea eliminar esta cancion?',
      header: 'Confirmacion de eliminacion',
      icon: 'pi pi-info-circle',
      accept: () => {
        this.cancionService.deleteCancion(cancionId).subscribe({
          next: () => {
            this.messageService.add({
              severity: 'success',
              summary: 'Cancion',
              detail: 'Se ha eliminado correctamente',
            });
            this.ngOnInit();
          },
          error: (err: any) => {
            console.log(err);
            this.messageService.add({
              severity: 'error',
              summary: 'Error Cancion',
              detail: 'Se ha producido un error',
            });
          },
        });
      },
    });
  }

  saveCancion() {
    this.toogleCreate ? this.insertarCancion() : this.editarCancion();
  }

  insertarCancion(): void {
    if (
      this.albumFiltro &&
      this.estiloFiltro &&
      this.artistaFiltro &&
      this.cancion &&
      this.cancion.nombre != '' &&
      this.cancion.duracion! > 0 &&
      this.cancion.duracion! <= 1000000 &&
      this.cancion.fecha
    ) {
      this.cancion.albumId = this.albumFiltro.id;
      this.cancion.artistaId = this.artistaFiltro.id;
      this.cancion.estiloId = this.estiloFiltro.id;
      this.cancionService.insertarCancion(this.cancion).subscribe({
        next: (data: any) => {
          this.messageService.add({
            severity: 'success',
            summary: 'Inserción Correcta',
            detail: 'Canción Insertada',
          });
          this.display = false;
          this.ngOnInit();
        },
      });
    }
  }

  editarCancion(): void {
    if (
      this.albumFiltro &&
      this.estiloFiltro &&
      this.artistaFiltro &&
      this.cancion &&
      this.cancion.nombre != '' &&
      this.cancion.duracion! > 0 &&
      this.cancion.fecha
    ) {
      this.cancion.albumId = this.albumFiltro.id;
      this.cancion.artistaId = this.artistaFiltro.id;
      this.cancion.estiloId = this.estiloFiltro.id;
      this.cancionService.editarCancion(this.cancion).subscribe({
        next: (data: any) => {
          this.exito = true;
          this.messageService.add({
            severity: 'success',
            summary: 'Edicion',
            detail: 'Se ha editado con exito',
          });
          this.display = false;
          this.ngOnInit();
        },
        error: (err: any) => {
          console.log(err);
          // this.exito = true;
          this.messageService.add({
            severity: 'error',
            summary: 'Edicion',
            detail: 'Se ha producido un problema con la edicion',
          });
        },
      });
    }
  }
}
