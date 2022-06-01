import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BackofficeComponent } from './backoffice.component';
import { TablaCancionesComponent } from './componentes/tabla-canciones/tabla-canciones.component';
import { TablaAlbumesComponent } from './componentes/tabla-albumes/tabla-albumes.component';
import { TablaArtistasComponent } from './componentes/tabla-artistas/tabla-artistas.component';
import { TablaEstilosComponent } from './componentes/tabla-estilos/tabla-estilos.component';
import { AdminGuard } from '../guards/admin.guard';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'canciones',
    pathMatch: 'full',
    canActivate: [AdminGuard],
  },
  {
    path: '',
    component: BackofficeComponent,
    canActivate: [AdminGuard],
    children: [
      { path: 'canciones', component: TablaCancionesComponent },
      { path: 'albumes', component: TablaAlbumesComponent },
      { path: 'artistas', component: TablaArtistasComponent },
      { path: 'estilos', component: TablaEstilosComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class BackofficeRoutingModule {}
