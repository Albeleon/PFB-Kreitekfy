import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CancionDataComponent } from './componentes/cancion-data/cancion-data.component';
import { PlayerComponent } from './player.component';

const routes: Routes = [
  { path: '', component: PlayerComponent },
  { path: 'fichaCancion/:cancionId', component: CancionDataComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PlayerRoutingModule { }
