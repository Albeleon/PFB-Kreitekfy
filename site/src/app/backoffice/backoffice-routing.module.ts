import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BackofficeComponent } from './backoffice.component';
import { CancionFormComponent } from './componentes/cancion-form/cancion-form.component';

const routes: Routes = [
  { path: '', component: BackofficeComponent },
  { path: 'ingresarCancion', component: CancionFormComponent },
  { path: 'editarCancion/:cancionId', component: CancionFormComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BackofficeRoutingModule { }
