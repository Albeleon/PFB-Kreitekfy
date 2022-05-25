import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './layouts/header/header.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PlayerComponent } from './player/player.component';



@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    PlayerComponent
  ],
  imports: [
    CommonModule
  ]
})
export class PlayerModule { }
