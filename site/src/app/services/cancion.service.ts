import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cancion_Simple } from '../models/cancion-simple.interface';
import { Cancion_Usuario } from '../models/cancion-usuario.interface';
import { Cancion } from '../models/cancion.model';

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


  public getCancionesAdministrador(busqueda:string, filtro:string , page:number): Observable<Cancion[]> {
    let url: string = "http://localhost:8080/kreitekfy/canciones";

    if(busqueda != "" && filtro != "cancion" && filtro != "" || busqueda != "" && filtro != "cancion" && filtro != "" && page != 1 ) {

      url =  url + "?sort=nombre,ASC&size=1&page="+ page +"&filter="+filtro+".nombre:MATCH:" + busqueda;

    }else if(busqueda != "" && page != 1 && filtro == "" || filtro == "cancion"){

      url =  url + "?sort=nombre,ASC&size=1&page="+ page +"&filter=nombre:MATCH:" + busqueda;

    }else{
      url =  url + "?sort=nombre,ASC&size=1&page="+ page;
    }

    return this.http.get<Cancion[]>(url);
  }



  
  getCancionById(id: string): Observable<Cancion> {
    let urlEndpoint: string = "http://localhost:8080/kreitekfy/canciones/" + id;
    return this.http.get<Cancion>(urlEndpoint);
  }

  getValoracionCancionById(idCancion: string, idUsuario: string): Observable<Cancion_Usuario> {
    let urlEndpoint: string = "http://localhost:8080/kreitekfy/canciones/" + idCancion + "/usuarios/" + idUsuario;
    return this.http.get<Cancion_Usuario>(urlEndpoint);
  }

  insertarCancion(cancion: Cancion) {
    let urlEndpoint: string = "http://localhost:8080/kreitekfy/canciones";
    return this.http.post<Cancion[]>(urlEndpoint, cancion);
  }

  editarCancion(cancion: Cancion) {
    let urlEndpoint: string = "http://localhost:8080/kreitekfy/canciones";
    return this.http.put<Cancion>(urlEndpoint, cancion);
  }

}
