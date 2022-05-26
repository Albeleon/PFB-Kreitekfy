import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BackofficeRoutingModule } from './backoffice-routing.module';
import { BackofficeComponent } from './backoffice.component';
import { HeaderComponent } from './layouts/header/header.component';
import { FooterComponent } from './layouts/footer/footer.component';
import {TableModule} from 'primeng/table';
import { TablaCancionesComponent } from './componentes/tabla-canciones/tabla-canciones.component';
import { TablaArtistasComponent } from './componentes/tabla-artistas/tabla-artistas.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    BackofficeComponent,
    HeaderComponent,
    FooterComponent,
    TablaCancionesComponent,
    TablaArtistasComponent
  ],
  imports: [
    CommonModule,
    BackofficeRoutingModule,
    TableModule,
    FormsModule
  ]
})
export class BackofficeModule { }
