import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlayerComponent } from './player/player.component';
import { ListaUsuariosComponent } from './componentes/lista-usuarios/lista-usuarios.component';

const routes: Routes = [
  { path: '' , component: ListaUsuariosComponent},
  { path: 'player', component: PlayerComponent, pathMatch: 'full' },
  { path: 'player', loadChildren: () => import('./player/player.module').then(m => m.PlayerModule) },
  { path: 'backoffice', loadChildren: () => import('./backoffice/backoffice.module').then(m => m.BackofficeModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
