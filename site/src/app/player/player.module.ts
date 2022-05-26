import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { PlayerRoutingModule } from './player-routing.module';
import { PlayerComponent } from './player.component';
import { HeaderComponent } from './layouts/header/header.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { CancionDataComponent } from './componentes/cancion-data/cancion-data.component';
import { TimeDurationPipe } from '../pipes/time-duration.pipe';

@NgModule({
  declarations: [
    PlayerComponent,
    HeaderComponent,
    FooterComponent,
    CancionDataComponent,
    TimeDurationPipe
  ],
  imports: [
    CommonModule,
    PlayerRoutingModule,
    FormsModule
  ],
  providers: [
  ]
})
export class PlayerModule { }
