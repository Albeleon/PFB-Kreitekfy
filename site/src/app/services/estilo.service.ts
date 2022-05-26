import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Estilo } from '../models/estilo.interface';

@Injectable({
  providedIn: 'root'
})
export class EstiloService {

  constructor(private http: HttpClient) { }

  public getEstilos(filtro: string): Observable<Estilo[]> {
    let urlEndpoint: string = "http://localhost:8080/kreitekfy/estilos";
    if (filtro != "") {
      urlEndpoint = urlEndpoint + "?filter=nombre:MATCH:" + filtro;
    }
    return this.http.get<Estilo[]>(urlEndpoint);
  }

  getEstiloById(id: string): Observable<Estilo> {
    let urlEndpoint: string = "http://localhost:8080/kreitekfy/estilos/" + id;
    return this.http.get<Estilo>(urlEndpoint);
  }
}
