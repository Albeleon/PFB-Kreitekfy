import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Album } from '../models/album.interface';

@Injectable({
  providedIn: 'root'
})
export class AlbumService {

  constructor(private http: HttpClient) { }

  public getAlbumes(filtro: string): Observable<Album[]> {
    let urlEndpoint: string = "http://localhost:8080/kreitekfy/albumes";
    if (filtro != "") {
      urlEndpoint = urlEndpoint + "?filter=nombre:MATCH:" + filtro;
    }
    return this.http.get<Album[]>(urlEndpoint);
  }

  getAlbumById(id: string): Observable<Album> {
    let urlEndpoint: string = "http://localhost:8080/kreitekfy/albumes/" + id;
    return this.http.get<Album>(urlEndpoint);
  }
}
