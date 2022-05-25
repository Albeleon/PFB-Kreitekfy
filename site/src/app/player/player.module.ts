import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { PlayerRoutingModule } from './player-routing.module';
import { PlayerComponent } from './player.component';
import { HeaderComponent } from './layouts/header/header.component';
import { FooterComponent } from './layouts/footer/footer.component';


@NgModule({
  declarations: [
    PlayerComponent,
    HeaderComponent,
    FooterComponent
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
