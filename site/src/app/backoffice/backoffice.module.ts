import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BackofficeRoutingModule } from './backoffice-routing.module';
import { BackofficeComponent } from './backoffice.component';
import { HeaderComponent } from './layouts/header/header.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { TableModule } from 'primeng/table';
import { TablaCancionesComponent } from './componentes/tabla-canciones/tabla-canciones.component';
import { TablaArtistasComponent } from './componentes/tabla-artistas/tabla-artistas.component';

import { FormsModule } from '@angular/forms';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { CalendarModule } from 'primeng/calendar';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { TablaAlbumesComponent } from './componentes/tabla-albumes/tabla-albumes.component';
import { TablaEstilosComponent } from './componentes/tabla-estilos/tabla-estilos.component';

@NgModule({
  declarations: [
    BackofficeComponent,
    HeaderComponent,
    FooterComponent,
    TablaCancionesComponent,
    TablaArtistasComponent,
    TablaAlbumesComponent,
    TablaEstilosComponent
  ],
  imports: [
    CommonModule,
    BackofficeRoutingModule,
    TableModule,
    CalendarModule,
    AutoCompleteModule,
    FormsModule,
    DialogModule,
    ButtonModule,
    ToastModule,
    ConfirmDialogModule,
  ],
  exports: [HeaderComponent, FooterComponent],
})
export class BackofficeModule {}
