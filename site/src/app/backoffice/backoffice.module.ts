import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './layouts/header/header.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { BackofficeComponent } from './backoffice/backoffice.component';



@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    BackofficeComponent
  ],
  imports: [
    CommonModule
  ]
})
export class BackofficeModule { }
