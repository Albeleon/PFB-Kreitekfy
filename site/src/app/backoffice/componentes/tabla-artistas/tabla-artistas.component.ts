import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Artista } from 'src/app/models/artista.interface';
import { ArtistaService } from 'src/app/services/artista.service';
import { SharedService } from 'src/app/services/shared.service';
import { environment } from 'src/environments/environment';
import { ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-tabla-artistas',
  templateUrl: './tabla-artistas.component.html',
  styleUrls: ['./tabla-artistas.component.scss'],
  providers: [MessageService, ConfirmationService],
})
export class TablaArtistasComponent implements OnInit {
  @ViewChild('artistaForm') form?: NgForm;

  pattern: string = environment.pattern;
  page: number = 0;
  first: boolean = false;
  last: boolean = false;
  clickpage: boolean = false;
  totalPages: number = 0;
  totalElements: number = 0;
  display: boolean = false;
  toogleCreate: boolean = false;
  titleMode: string = '';
  flagErrorSearch: boolean = false;
  busqueda: string = '';
  artistas: Artista[] = [];
  artistaActive: Artista = {
    nombre: '',
    id: 0,
  };
  defaultImage: string = environment.defaultImage;
  localizacion: any;

  constructor(
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private artistaService: ArtistaService,
    private sharedService: SharedService
  ) {}

  ngOnInit(): void {
    this.getArtistasFiltrados();
    this.sharedService.getEmittedValue().subscribe((data) => {
      this.busqueda = data;
      this.getArtistasFiltrados();
    });
    this.setLocation();
  }

  setLocation(){
    this.sharedService.changeBack("artistas");
  }

  showDialogCreate() {
    this.titleMode = 'Añadir';
    this.form!.control.markAsPristine();
    this.toogleCreate = true;
    this.display = true;
    this.artistaActive = {} as Artista;
  }

  showDialogEdit(artistaData: Artista) {
    this.titleMode = 'Editar';
    this.form!.control.markAsPristine();
    this.toogleCreate = false;
    this.display = true;
    this.artistaActive = { ...artistaData };
  }

  public nextPage(): void {
    this.clickpage = true;
    this.page = this.page + 1;
    this.getArtistasFiltrados();
  }

  public previousPage(): void {
    this.clickpage = true;
    this.page = this.page - 1;
    this.getArtistasFiltrados();
  }

  public getArtistasFiltrados(): void {
    if (this.busqueda == '' || this.busqueda.match(this.pattern)) {
      if (this.clickpage == true) {
        this.clickpage = false;
      } else {
        this.page = 0;
      }
      this.artistaService
        .getArtistasByPage(this.busqueda, this.page.toString())
        .subscribe({
          next: (data: any) => {
            this.artistas = data.content;
            this.totalPages = data.totalPages;
            this.first = data.first;
            this.last = data.last;
          },
          error: (err) => {
            this.handleError(err);
          },
        });
    }
  }

  showDialogDelete(cancionId: number) {

    this.confirmationService.confirm({
      message: '¿Desea eliminar esta cancion?',
      header: 'Confirmacion de eliminacion',
      icon: 'pi pi-info-circle',
      accept: () => {
        this.artistaService.deleteArtistaById(cancionId).subscribe({
          next: () => {
            this.messageService.add({
              severity: 'success',
              summary: 'Artista',
              detail: 'Se ha eliminado correctamente',
            });
            this.ngOnInit();
          },
          error: (err) => {
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

  insertarArtista() {
    this.artistaService.insertArtista(this.artistaActive).subscribe({
      next: (response) => {
        this.display = false;
        this.ngOnInit();
        this.messageService.add({
          severity: 'success',
          summary: 'Artista',
          detail: 'Se ha insertado correctamente',
        });
      },
      error: (err) => {
        console.log(err);
        this.display = false;
        this.messageService.add({
          severity: 'error',
          summary: 'Error Cancion',
          detail: 'Se ha producido un error',
        });
      },
    });
  }

  editarArtista() {
    this.artistaService.updateArtista(this.artistaActive).subscribe({
      next: (response) => {
        this.display = false;
        this.ngOnInit();
        this.messageService.add({
          severity: 'success',
          summary: 'Artista',
          detail: 'Se ha modificado correctamente',
        });
      },
      error: (err) => {
        console.log(err);
        this.display = false;
        this.messageService.add({
          severity: 'error',
          summary: 'Error Cancion',
          detail: 'Se ha producido un error',
        });
      },
    });
  }

  saveArtista() {
    this.toogleCreate ? this.insertarArtista() : this.editarArtista();
  }

  private handleError(err: any): void {
    // implementar gestión de errores;
  }
}
