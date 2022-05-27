import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { PlayerRoutingModule } from './player-routing.module';
import { PlayerComponent } from './player.component';
import { HeaderComponent } from './layouts/header/header.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { CancionDataComponent } from './componentes/cancion-data/cancion-data.component';
import { TimeDurationPipe } from '../pipes/time-duration.pipe';
import { PaginaPrincipalComponent } from './componentes/pagina-principal/pagina-principal.component';
import { TiraNovedadesComponent } from './componentes/tira-novedades/tira-novedades.component';
import { AutoCompleteModule} from 'primeng/autocomplete';
import {ToastModule} from 'primeng/toast';
import { TiraReproduccionesComponent } from './componentes/tira-reproducciones/tira-reproducciones.component';
import { TiraValoradasComponent } from './componentes/tira-valoradas/tira-valoradas.component';
import { TiraRecomendadosComponent } from './componentes/tira-recomendados/tira-recomendados.component';

@NgModule({
  declarations: [
    PlayerComponent,
    HeaderComponent,
    FooterComponent,
    CancionDataComponent,
    TimeDurationPipe,
    PaginaPrincipalComponent,
    TiraNovedadesComponent,
    TiraReproduccionesComponent,
    TiraValoradasComponent,
    TiraRecomendadosComponent
  ],
  imports: [
    CommonModule,
    PlayerRoutingModule,
    FormsModule,
    AutoCompleteModule,
    ToastModule
  ],
  providers: [
  ]
})
export class PlayerModule { }
