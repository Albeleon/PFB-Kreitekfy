import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Artista } from '../models/artista.interface';
import { Estilo } from '../models/estilo.interface';

@Injectable({
  providedIn: 'root',
})
export class ArtistaService {
  urlBackend: string = environment.urlBackend;

  constructor(private http: HttpClient) {}

  public getArtistas(filtro: string): Observable<Artista[]> {
    let urlEndpoint: string = this.urlBackend + 'artistas';
    if (filtro != '') {
      urlEndpoint = urlEndpoint + '?filter=nombre:MATCH:' + filtro;
    }
    return this.http.get<Artista[]>(urlEndpoint);
  }

  public getArtistasByPage(
    filtro: string,
    pagina: string
  ): Observable<Artista[]> {
    let urlEndpoint: string =
      this.urlBackend +
      'artistas?sort=nombre,ASC&page=' +
      pagina +
      '&size=' +
      environment.sizePage;
    if (filtro != '') {
      urlEndpoint = urlEndpoint + '&filter=nombre:MATCH:' + filtro;
    }
    return this.http.get<Artista[]>(urlEndpoint);
  }

  getArtistaById(id: string): Observable<Artista> {
    let urlEndpoint: string = `${this.urlBackend}artistas/${id}`;
    return this.http.get<Artista>(urlEndpoint);
  }

  insertArtista(artista: Artista): Observable<Artista> {
    const urlEndpoint = `${this.urlBackend}artistas`;
    return this.http.post<Artista>(urlEndpoint, artista);
  }

  updateArtista(artista: Artista): Observable<Artista> {
    const urlEndpoint = `${this.urlBackend}artistas`;
    return this.http.put<Artista>(urlEndpoint, artista);
  }

  deleteArtistaById(idArtista: number): Observable<any> {
    const urlEndpoint = `${this.urlBackend}artistas/${idArtista}`;
    return this.http.delete(urlEndpoint);
  }
}
