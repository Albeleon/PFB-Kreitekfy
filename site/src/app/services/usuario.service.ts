import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Usuario } from '../models/usuario.interface';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  urlBackend: string = environment.urlBackend;

  constructor(private http: HttpClient) { }

  public getAllUsuarios(): Observable<Usuario[]>{
    let urlEndpoint: string = this.urlBackend + "usuarios";
    return this.http.get<Usuario[]>(urlEndpoint);
  }

}
