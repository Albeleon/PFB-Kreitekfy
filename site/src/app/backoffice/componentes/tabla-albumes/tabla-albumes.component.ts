import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Album } from 'src/app/models/album.interface';
import { AlbumService } from 'src/app/services/album.service';
import { SharedService } from 'src/app/services/shared.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-tabla-albumes',
  templateUrl: './tabla-albumes.component.html',
  styleUrls: ['./tabla-albumes.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class TablaAlbumesComponent implements OnInit {
  @ViewChild('albumForm') form?: NgForm;

  pattern: string = environment.pattern;
  base64Prefix: string = environment.base64Prefix;
  defaultImage: string = environment.defaultImage;
  

  // Variables lista y búsqueda de canciones//
  albumes: Album[] = [];
  page: number = 0;
  first: boolean = false;
  last: boolean = false;
  clickpage: boolean = false;
  totalPages: number = 0;
  totalElements: number = 0;
  display: boolean = false;
  
  localizacion: any ;
  busqueda: string = '';

  // Variables modal inserción de canciones//

  exito: Boolean = false;
  operacion: string = '';

  album: Album = {
    nombre: '',
    id: 0,
    imagen: undefined
  };
  toogleCreate: boolean = false;
  titleMode: string = '';
  flagErrorSearch: boolean = false;

  constructor(
    private albumService: AlbumService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private sharedService: SharedService
  ) {}

  // Lógica lista y búsqueda de canciones//

  showDialogCreate() {
    this.form!.control.markAsPristine();
    this.toogleCreate = true;
    this.display = true;
    this.initInsertarAlbum();
  }


  setLocation(){
    this.localizacion = localStorage.setItem('localizacion' , 'albumes');
  }

  showDialogEdit(idAlbum: number) {
    this.titleMode = 'Editar';
    this.toogleCreate = false;
    this.display = true;
    this.initEditarAlbum(idAlbum!.toString());
  }

  ngOnInit(): void {
    this.getAlbumesFiltrados();
    this.sharedService.getEmittedValue().subscribe((data: any) => { this.busqueda = data; this.getAlbumesFiltrados(); });
    this.setLocation();

  }

  public nextPage(): void {
    this.clickpage = true;
    this.page = this.page + 1;
    this.getAlbumesFiltrados();
  }

  public previousPage(): void {
    this.clickpage = true;
    this.page = this.page - 1;
    this.getAlbumesFiltrados();
  }

  public getAlbumesFiltrados():void{
    if (this.busqueda == "" || this.busqueda.match(this.pattern)) {
      const filtro = localStorage.getItem('filtroAdmin');
      if(this.clickpage == true){
        this.clickpage = false;
      }else{
        this.page = 0;
      }
      this.albumService.getAlbumesPagina(this.busqueda, this.page.toString()).subscribe({
            next: (data: any) => {this.albumes = data.content
              this.totalPages = data.totalPages;
              this.first = data.first;
              this.last = data.last;
            },
            error: (err) => {this.handleError(err);}
          })
    }
  }
  
  private handleError(err: any): void {
    // implementar gestión de errores;
  }

  // Lógica modal inserción de canciones//

  initInsertarAlbum() {
    this.album = {} as Album;
    this.titleMode = 'Crear';
  }

  initEditarAlbum(id: string) {
    this.album = { nombre: "", imagen: "" };
    this.operacion = 'edit';
    this.albumService.getAlbumById(id).subscribe({
      next: (data: Album) => {
        this.album = data;
      },
    });
  }

  showDialogDelete(albumId: number) {
    this.confirmationService.confirm({
      message: '¿Desea eliminar este album?',
      header: 'Confirmacion de eliminacion',
      icon: 'pi pi-info-circle',
      accept: () => {
        this.albumService.deleteAlbum(albumId).subscribe({
          next: () => {
            this.messageService.add({
              severity: 'success',
              summary: 'Album',
              detail: 'Se ha eliminado correctamente',
            });
            this.ngOnInit();
          },
          error: (err: any) => {
            console.log(err);
            this.messageService.add({
              severity: 'error',
              summary: 'Error Album',
              detail: 'Se ha producido un error',
            });
          },
        });
      },
    });
  }

  saveAlbum() {
    this.toogleCreate ? this.insertarAlbum() : this.editarAlbum();
  }

  insertarAlbum(): void {
    if ( this.album && this.album.nombre != '') {
      this.albumService.insertarAlbum(this.album).subscribe({
        next: (data: any) => {
          this.messageService.add({
            severity: 'success',
            summary: 'Inserción Correcta',
            detail: 'Album Insertado',
          });
          this.display = false;
          this.ngOnInit();
        },
      });
    }
  }

  editarAlbum(): void {
    if ( this.album && this.album.nombre != '') {
      this.albumService.editarAlbum(this.album).subscribe({
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

  public deleteImagen(): void {
    this.album!.imagen = undefined;
  }

  public includeImageInItem(event: any): void {
    const inputFile = event.target as HTMLInputElement;
    const file: File | null = inputFile.files?.item(0) ?? null;


    this.readFileAsString(file!).then(
      (result) => {
        const imageType: string = this.getImageType(result);
        const imageBase64: string = this.getImageBase64(result);

        this.album!.imagen = imageBase64;

      },
      (error) => {
        console.log("No se pudo cargar la imagen")
      })
        
  }

  private getImageType(imageString: string): string {
    const imageStringParts: string[] = imageString.split(",");
    if (imageStringParts.length == 2) {
      return imageStringParts[0];
    } else {
      return "";
    }
  }

  private getImageBase64(imageString: string): string {
    const imageStringParts: string[] = imageString.split(",");
    if (imageStringParts.length == 2) {
      return imageStringParts[1];
    } else {
      return "";
    }
  }

  private readFileAsString(file: File) {
    return new Promise<string>(function(resolve, reject) {
      let reader: FileReader = new FileReader();
      reader.readAsDataURL(file)
      reader.onload = function() {
        resolve(this.result as string)
      }
    })
  }
  
}
