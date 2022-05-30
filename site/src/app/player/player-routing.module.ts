import { Input, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Estilo } from '../models/estilo.interface';
import { CancionDataComponent } from './componentes/cancion-data/cancion-data.component';
import { PaginaPrincipalComponent } from './componentes/pagina-principal/pagina-principal.component';
import { PlayerComponent } from './player.component';

const routes: Routes = [
  { path: '', component: PlayerComponent, children: [
    { path: '', component: PaginaPrincipalComponent },
    { path: 'fichaCancion/:cancionId', component: CancionDataComponent }
  ] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PlayerRoutingModule {
  @Input() estilo?: Estilo;
 }
