import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../../services/usuario.service';
import { Usuario } from 'src/app/models/usuario.interface';
import { Router } from '@angular/router';


@Component({
  selector: 'app-lista-usuarios',
  templateUrl: './lista-usuarios.component.html',
  styleUrls: ['./lista-usuarios.component.scss']
})
export class ListaUsuariosComponent implements OnInit {
  Usuarios: Usuario[] = [];
  
  constructor(private usuarioService: UsuarioService , private router: Router) {

   }

  ngOnInit(): void {
    this.getUsuarios();
  }

  private getUsuarios():void{
    this.usuarioService.getAllUsuarios().subscribe({
      next: (usuariosRequest) => {this.Usuarios = usuariosRequest},
      error: (err) => {this.handleError(err);}
    })
    
  }

   setUser(id:string , rol:string , username:string){
    localStorage.setItem('usuarioId' , id);
    localStorage.setItem('rol' , rol);
    localStorage.setItem('userName',username);
    localStorage.setItem('filtroAdmin','');
  }

  navigateUser(){
    if (localStorage.getItem('rol') == 'USUARIO'){
      this.router.navigate(['/player']);
    }
    if(localStorage.getItem('rol') == 'ADMINISTRADOR'){
      localStorage.setItem('localizacion' , 'canciones');
      this.router.navigate(['/backoffice']);
    }
  }

  private handleError(err:any):void{
    // implementar gestión de errores;
  }

}
