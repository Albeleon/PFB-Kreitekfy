import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../models/usuario.interface';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient) { }

  public getAllUsuarios(): Observable<Usuario[]>{
    let urlEndpoint: string = "http://localhost:8080/kreitekfy/usuarios";
    return this.http.get<Usuario[]>(urlEndpoint);
  }

}
