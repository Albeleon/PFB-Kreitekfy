export class Cancion {
    id?: number;
    nombre?: string;
    artistaId?: number;
    artistaNombre?: string;
    albumId?: number;
    albumNombre?: string;
    albumImagen?: string;
    estiloId?: number;
    estiloNombre?: string;
    fecha?: Date;
    duracion?: number;

    Cancion() {
        this.nombre = "";
        this.duracion = 1;
        this.fecha = new Date();
    }
}