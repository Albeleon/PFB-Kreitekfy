import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlayerComponent } from './player/player.component';
import { ListaUsuariosComponent } from './componentes/lista-usuarios/lista-usuarios.component';
import { UserGuard } from './guards/user.guard';
import { AdminGuard } from './guards/admin.guard';

const routes: Routes = [
  { path: '', component: ListaUsuariosComponent, canActivate: [UserGuard] },
  {
    path: 'player',
    component: PlayerComponent,
    pathMatch: 'full',
    canActivate: [UserGuard],
  },
  {
    path: 'player',
    loadChildren: () =>
      import('./player/player.module').then((m) => m.PlayerModule),
  },
  {
    path: 'backoffice',
    loadChildren: () =>
      import('./backoffice/backoffice.module').then((m) => m.BackofficeModule),
    canActivate: [AdminGuard],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
