import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cancion_Simple } from '../models/cancion-simple.interface';

@Injectable({
  providedIn: 'root'
})
export class CancionService {

  constructor(private http: HttpClient) { }

  public getCancionesNovedades(estilo: string): Observable<Cancion_Simple[]> {
    let urlEndpoint: string = "http://localhost:8080/kreitekfy/canciones?size=5&sort=fecha,DESC";
    if (estilo != "") {
      urlEndpoint = urlEndpoint + "&filter=estilo.nombre:MATCH:" + estilo;
    }
    return this.http.get<Cancion_Simple[]>(urlEndpoint);
  }
}
