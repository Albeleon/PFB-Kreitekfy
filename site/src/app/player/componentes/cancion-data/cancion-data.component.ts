import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Cancion_Usuario } from 'src/app/models/cancion-usuario.interface';
import { Cancion } from 'src/app/models/cancion.interface';
import { CancionService } from 'src/app/services/cancion.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-cancion-data',
  templateUrl: './cancion-data.component.html',
  styleUrls: ['./cancion-data.component.scss'],
  providers: [MessageService],
})
export class CancionDataComponent implements OnInit {
  @ViewChild('estrella1') estrella1?: ElementRef;
  @ViewChild('estrella2') estrella2?: ElementRef;
  @ViewChild('estrella3') estrella3?: ElementRef;
  @ViewChild('estrella4') estrella4?: ElementRef;

  cancion?: Cancion;
  base64Prefix: string = environment.base64Prefix;
  cancionUsuario: Cancion_Usuario = {
    cancionId: 0,
    usuarioId: 0,
  };
  usuarioId: string | null = localStorage.getItem('usuarioId');
  valoracion?: number;

  constructor(
    private cancionService: CancionService,
    private route: ActivatedRoute,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.getCancion();
  }

  getCancion(): void {
    const entryParam: string =
      this.route.snapshot.paramMap.get('cancionId') ?? '';
    if (entryParam !== '') {
      this.cancionService.getCancionById(entryParam).subscribe({
        next: (data: Cancion) => {
          this.cancion = data;
          this.getValoracion();
        },
      });
    }
  }

  getValoracion(): void {
    this.cancionService
      .getValoracionCancionById(
        this.cancion!.id!.toString(),
        this.usuarioId!.toString()
      )
      .subscribe({
        next: (data: Cancion_Usuario) => {
          if (data.valoracion) {
            this.valoracion = data.valoracion;
          } else {
            this.valoracion = 0;
          }
          this.actualizarEstrellas(this.valoracion!);
        },
        error: (err: any) => {
          this.valoracion = 0;
          this.actualizarEstrellas(this.valoracion!);
        },
      });
  }

  actualizarEstrellas(numero: number): void {
    let classAdd, classRemove;

    classAdd = numero >= 1 ? 'pi-star-fill' : 'pi-star';
    classRemove = numero >= 1 ? 'pi-star' : 'pi-star-fill';
    this.estrella1!.nativeElement.classList.add(classAdd);
    this.estrella1!.nativeElement.classList.remove(classRemove);

    classAdd = numero >= 2 ? 'pi-star-fill' : 'pi-star';
    classRemove = numero >= 2 ? 'pi-star' : 'pi-star-fill';
    this.estrella2!.nativeElement.classList.add(classAdd);
    this.estrella2!.nativeElement.classList.remove(classRemove);

    classAdd = numero >= 3 ? 'pi-star-fill' : 'pi-star';
    classRemove = numero >= 3 ? 'pi-star' : 'pi-star-fill';
    this.estrella3!.nativeElement.classList.add(classAdd);
    this.estrella3!.nativeElement.classList.remove(classRemove);

    classAdd = numero >= 4 ? 'pi-star-fill' : 'pi-star';
    classRemove = numero >= 4 ? 'pi-star' : 'pi-star-fill';
    this.estrella4!.nativeElement.classList.add(classAdd);
    this.estrella4!.nativeElement.classList.remove(classRemove);
  }

  enviarValoracion(numero: number): void {
    this.valoracion = numero;

    this.cancionUsuario!.cancionId = this.cancion!.id!;
    this.cancionUsuario!.usuarioId = +this.usuarioId!;
    this.cancionUsuario!.valoracion = this.valoracion;

    this.cancionService.updateValoracion(this.cancionUsuario!).subscribe({
      next: (data: Cancion_Usuario) => {
        this.messageService.add({
          severity: 'success',
          summary: 'Valoración',
          detail: 'Valorado con éxito',
        });

        this.actualizarEstrellas(this.valoracion!);
      },
      error: (err) => {
        console.log(err);
        this.messageService.add({
          severity: 'error',
          summary: 'Valoración',
          detail: 'Se ha producido un error',
        });
      },
    })
  }

  updateReproduccion() {
    this.cancionUsuario!.cancionId = this.cancion!.id!;
    this.cancionUsuario!.usuarioId = +this.usuarioId!;

    this.cancionService.updateReproduccion(this.cancionUsuario!).subscribe({
      next: () => {
        this.messageService.add({
          severity: 'success',
          summary: 'Reproduccion',
          detail: 'Reproducido con exito',
        });
      },
      error: (err) => {
        console.log(err);
        this.messageService.add({
          severity: 'error',
          summary: 'Reproduccion',
          detail: 'Se ha producido un error',
        });
      },
    });
  }
}
