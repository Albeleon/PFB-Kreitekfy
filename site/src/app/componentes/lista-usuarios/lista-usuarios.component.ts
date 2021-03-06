import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../../services/usuario.service';
import { Usuario } from 'src/app/models/usuario.interface';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';


@Component({
  selector: 'app-lista-usuarios',
  templateUrl: './lista-usuarios.component.html',
  styleUrls: ['./lista-usuarios.component.scss'],
  providers: [MessageService]
})
export class ListaUsuariosComponent implements OnInit {
  Usuarios: Usuario[] = [];
  loader: boolean = true;
  
  constructor(private usuarioService: UsuarioService , 
    private messageService: MessageService, private router: Router) {

   }

  ngOnInit(): void {
    this.getUsuarios();
  }

  private getUsuarios():void{
    this.usuarioService.getAllUsuarios().subscribe({
      next: (usuariosRequest) => {
        this.Usuarios = usuariosRequest
        this.loader = false;
      },
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
    this.loader = false;
    this.messageService.add({
      severity: 'error',
      summary: 'Error',
      detail: 'Se ha producido un error conectando a la base de datos',
    });
  }

}
