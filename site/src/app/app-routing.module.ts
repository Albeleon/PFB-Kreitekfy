import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaUsuariosComponent } from './componentes/lista-usuarios/lista-usuarios.component';
import { BackofficeComponent } from './backoffice/backoffice/backoffice.component';
import { PlayerComponent } from './player/player/player.component';

const routes: Routes = [
  {path:'' , component: ListaUsuariosComponent},
  {path:'player' , component: PlayerComponent},
  {path:'backoffice' , component: BackofficeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
