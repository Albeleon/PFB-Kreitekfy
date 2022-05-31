import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Estilo } from 'src/app/models/estilo.interface';
import { EstiloService } from 'src/app/services/estilo.service';
import { SharedService } from 'src/app/services/shared.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-tabla-estilos',
  templateUrl: './tabla-estilos.component.html',
  styleUrls: ['./tabla-estilos.component.scss'],
  providers: [MessageService, ConfirmationService],
})
export class TablaEstilosComponent implements OnInit {
  @ViewChild('estiloForm') form?: NgForm;

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
  estilos: Estilo[] = [];
  estiloActive: Estilo = {
    nombre: '',
    id: 0,
  };
  defaultImage: string = environment.defaultImage;
  localizacion: any ;
  loader: boolean = true;

  constructor(private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private estiloService: EstiloService,
    private sharedService: SharedService) { }

  ngOnInit(): void {
    this.getEstilosFiltrados();
    this.sharedService.getEmittedValue().subscribe((data) => {
      this.busqueda = data;
      this.getEstilosFiltrados();
    });
    this.setLocation();
  }

  setLocation(){
    this.sharedService.changeBack("estilos");
  }

  showDialogCreate() {
    this.titleMode = 'Añadir';
    this.form!.control.markAsPristine();
    this.toogleCreate = true;
    this.display = true;
    this.estiloActive = {} as Estilo;
  }

  showDialogEdit(estiloData: Estilo) {
    this.titleMode = 'Editar';
    this.form!.control.markAsPristine();
    this.toogleCreate = false;
    this.display = true;
    this.estiloActive = { ...estiloData };
  }

  public nextPage(): void {
    this.clickpage = true;
    this.page = this.page + 1;
    this.getEstilosFiltrados();
  }

  public previousPage(): void {
    this.clickpage = true;
    this.page = this.page - 1;
    this.getEstilosFiltrados();
  }

  public getEstilosFiltrados(): void {
    if (this.busqueda == '' || this.busqueda.match(this.pattern)) {
      if (this.clickpage == true) {
        this.clickpage = false;
      } else {
        this.page = 0;
      }
      this.estiloService
        .getEstilosByPage(this.busqueda, this.page.toString())
        .subscribe({
          next: (data: any) => {
            this.estilos = data.content;
            this.totalPages = data.totalPages;
            this.first = data.first;
            this.last = data.last;
            this.loader = false ;
          },
          error: (err) => {
            this.handleError(err);
          },
        });
    }
  }

  showDialogDelete(idEstilo: number) {

    this.confirmationService.confirm({
      message: '¿Desea eliminar este Estilo?',
      header: 'Confirmacion de eliminacion',
      icon: 'pi pi-info-circle',
      accept: () => {
        this.estiloService.deleteEstiloById(idEstilo).subscribe({
          next: () => {
            this.messageService.add({
              severity: 'success',
              summary: 'Estilo',
              detail: 'Se ha eliminado correctamente',
            });
            this.ngOnInit();
          },
          error: (err) => {
            console.log(err);
            this.messageService.add({
              severity: 'error',
              summary: 'Error Estilo',
              detail: 'Se ha producido un error',
            });
          },
        });
      },
    });
  }

  insertarEstilo() {
    this.estiloService.insertEstilo(this.estiloActive).subscribe({
      next: (response) => {
        this.display = false;
        this.ngOnInit();
        this.messageService.add({
          severity: 'success',
          summary: 'Estilo',
          detail: 'Se ha insertado correctamente',
        });
      },
      error: (err) => {
        console.log(err);
        this.display = false;
        this.messageService.add({
          severity: 'error',
          summary: 'Error Estilo',
          detail: 'Se ha producido un error',
        });
      },
    });
  }

  editarEstilo() {
    this.estiloService.updateEstilo(this.estiloActive).subscribe({
      next: (response) => {
        this.display = false;
        this.ngOnInit();
        this.messageService.add({
          severity: 'success',
          summary: 'Estilo',
          detail: 'Se ha modificado correctamente',
        });
      },
      error: (err) => {
        console.log(err);
        this.display = false;
        this.messageService.add({
          severity: 'error',
          summary: 'Error Estilo',
          detail: 'Se ha producido un error',
        });
      },
    });
  }

  saveEstilo() {
    this.toogleCreate ? this.insertarEstilo() : this.editarEstilo();
  }

  private handleError(err: any): void {
    // implementar gestión de errores;
  }





}
