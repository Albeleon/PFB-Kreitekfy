import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Estilo } from '../models/estilo.interface';

@Injectable({
  providedIn: 'root'
})
export class EstiloService {
  urlBackend: string = environment.urlBackend;

  constructor(private http: HttpClient) { }

  public getEstilos(filtro: string): Observable<Estilo[]> {
    let urlEndpoint: string = this.urlBackend + "estilos";
    if (filtro != "") {
      urlEndpoint = urlEndpoint + "?filter=nombre:MATCH:" + filtro;
    }
    return this.http.get<Estilo[]>(urlEndpoint);
  }

  getEstiloById(id: string): Observable<Estilo> {
    let urlEndpoint: string = this.urlBackend + "estilos/" + id;
    return this.http.get<Estilo>(urlEndpoint);
  }
}
