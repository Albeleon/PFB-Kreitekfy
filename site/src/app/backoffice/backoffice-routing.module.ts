import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BackofficeComponent } from './backoffice.component';
import { TablaCancionesComponent } from './componentes/tabla-canciones/tabla-canciones.component';
import { TablaArtistasComponent } from './componentes/tabla-artistas/tabla-artistas.component';


const routes: Routes = [
  { path: '', component: BackofficeComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BackofficeRoutingModule { }
