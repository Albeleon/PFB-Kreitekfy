import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BackofficeComponent } from './backoffice.component';
import { TablaCancionesComponent } from './componentes/tabla-canciones/tabla-canciones.component';
import { TablaArtistasComponent } from './componentes/tabla-artistas/tabla-artistas.component';
import { CancionFormComponent } from './componentes/cancion-form/cancion-form.component';

const routes: Routes = [
  { path: '', redirectTo: 'canciones', pathMatch: 'full' },
  {
    path: '',
    component: BackofficeComponent,
    children: [
      { path: 'canciones', component: TablaCancionesComponent },
      { path: 'artistas', component: TablaArtistasComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class BackofficeRoutingModule {}
