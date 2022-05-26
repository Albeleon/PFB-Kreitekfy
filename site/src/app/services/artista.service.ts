import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Artista } from '../models/artista.interface';
import { Estilo } from '../models/estilo.interface';

@Injectable({
  providedIn: 'root'
})
export class ArtistaService {

  constructor(private http: HttpClient) { }

  public getArtistas(filtro: string): Observable<Artista[]> {
    let urlEndpoint: string = "http://localhost:8080/kreitekfy/artistas";
    if (filtro != "") {
      urlEndpoint = urlEndpoint + "?filter=nombre:MATCH:" + filtro;
    }
    return this.http.get<Artista[]>(urlEndpoint);
  }

  getArtistaById(id: string): Observable<Artista> {
    let urlEndpoint: string = "http://localhost:8080/kreitekfy/artistas/" + id;
    return this.http.get<Artista>(urlEndpoint);
  }
}
