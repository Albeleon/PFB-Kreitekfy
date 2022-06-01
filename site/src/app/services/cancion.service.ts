import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Cancion_Simple } from '../models/cancion-simple.interface';
import { Cancion_Usuario } from '../models/cancion-usuario.interface';
import { Cancion } from '../models/cancion.interface';
import { Estilo } from '../models/estilo.interface';

@Injectable({
  providedIn: 'root',
})
export class CancionService {
  urlBackend: string = environment.urlBackend;

  constructor(private http: HttpClient) {}

  public getCancionesNovedades(
    estilo: Estilo | undefined
  ): Observable<Cancion_Simple[]> {
    let urlEndpoint: string =
      this.urlBackend + 'canciones/novedades';
    if (estilo) {
      urlEndpoint = urlEndpoint + '?filter=estilo.id:EQUAL:' + estilo.id;
    }
    return this.http.get<Cancion_Simple[]>(urlEndpoint);
  }

  getCancionesMasReproducidas(
    estilo: Estilo | undefined
  ): Observable<Cancion_Simple[]> {
    let urlEndpoint: string =
      this.urlBackend + 'canciones/masReproducidas';
    if (estilo) {
      urlEndpoint = urlEndpoint + '?filter=estilo.id:EQUAL:' + estilo.id;
    }
    return this.http.get<Cancion_Simple[]>(urlEndpoint);
  }

  getCancionesMasValoradas(
    estilo: Estilo | undefined
  ): Observable<Cancion_Simple[]> {
    let urlEndpoint: string =
      this.urlBackend + 'canciones/masValoradas';
    if (estilo) {
      urlEndpoint = urlEndpoint + '?filter=estilo.id:EQUAL:'+ estilo.id;
    }
    return this.http.get<Cancion_Simple[]>(urlEndpoint);
  }

  getCancionesRecomendadas(
    usuarioId: string | null
  ): Observable<Cancion_Simple[]> {
    let urlEndpoint: string =
      this.urlBackend + 'canciones/recomendadas/' + usuarioId;
    return this.http.get<Cancion_Simple[]>(urlEndpoint);
  }

  public getCancionesAdministrador(
    busqueda: string,
    filtro: string,
    page: number
  ): Observable<Cancion[]> {
    let url: string = this.urlBackend + 'canciones';

    if (
      (busqueda != '' && filtro != 'cancion' && filtro != '') ||
      (busqueda != '' && filtro != 'cancion' && filtro != '' && page != 1)
    ) {
      url =
        url +
        '?sort=nombre,ASC&size=' + environment.sizePage + '&page=' +
        page +
        '&filter=' +
        filtro +
        '.nombre:MATCH:' +
        busqueda;
    } else if (
      (busqueda != '' && page != 1 && filtro == '') ||
      filtro == 'cancion'
    ) {
      url =
        url +
        '?sort=nombre,ASC&size=' + environment.sizePage + '&page=' +
        page +
        '&filter=nombre:MATCH:' +
        busqueda;
    } else {
      url = url + '?sort=nombre,ASC&size=' + environment.sizePage + '&page=' + page;
    }

    return this.http.get<Cancion[]>(url);
  }

  getCancionById(id: string): Observable<Cancion> {
    const urlEndpoint: string =
      this.urlBackend + 'canciones/' + id;
    return this.http.get<Cancion>(urlEndpoint);
  }

  updateValoracion(
    cancionUsuario: Cancion_Usuario
  ): Observable<Cancion_Usuario> {
    const urlEndpoint = this.urlBackend + `canciones/${cancionUsuario.cancionId}/usuarios/${cancionUsuario.usuarioId}/valoracion`;
    return this.http.patch<Cancion_Usuario>(urlEndpoint, cancionUsuario);
  }

  getValoracionCancionById(
    idCancion: string,
    idUsuario: string
  ): Observable<Cancion_Usuario> {
    const urlEndpoint: string =
      this.urlBackend + 'canciones/' +
      idCancion +
      '/usuarios/' +
      idUsuario;
    return this.http.get<Cancion_Usuario>(urlEndpoint);
  }

  insertarCancion(cancion: Cancion): Observable<Cancion> {
    const urlEndpoint: string = this.urlBackend + 'canciones';
    return this.http.post<Cancion>(urlEndpoint, cancion);
  }

  editarCancion(cancion: Cancion): Observable<Cancion> {
    const urlEndpoint: string = this.urlBackend + 'canciones';
    return this.http.put<Cancion>(urlEndpoint, cancion);
  }

  updateReproduccion(
    cancionUsuario: Cancion_Usuario
  ): Observable<Cancion_Usuario> {
    const urlEndpoint = this.urlBackend + `canciones/${cancionUsuario.cancionId}/usuarios/${cancionUsuario.usuarioId}/reproduccion`;
    return this.http.put<Cancion_Usuario>(urlEndpoint, cancionUsuario);
  }

  deleteCancion(cancionId: number): Observable<any> {
    const urlEndpoint = this.urlBackend + `canciones/${cancionId}`;
    return this.http.delete(urlEndpoint);
  }
}
