import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Album } from '../models/album.interface';

@Injectable({
  providedIn: 'root'
})
export class AlbumService {
  urlBackend: string = environment.urlBackend;

  constructor(private http: HttpClient) { }

  public getAlbumes(filtro: string): Observable<Album[]> {
    let urlEndpoint: string = this.urlBackend + "albumes";
    if (filtro != "") {
      urlEndpoint = urlEndpoint + "?filter=nombre:MATCH:" + filtro;
    }
    return this.http.get<Album[]>(urlEndpoint);
  }

  getAlbumById(id: string): Observable<Album> {
    let urlEndpoint: string = this.urlBackend + "albumes/" + id;
    return this.http.get<Album>(urlEndpoint);
  }
}
