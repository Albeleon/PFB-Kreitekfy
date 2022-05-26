import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { BackofficeRoutingModule } from './backoffice-routing.module';
import { BackofficeComponent } from './backoffice.component';
import { HeaderComponent } from './layouts/header/header.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { CancionFormComponent } from './componentes/cancion-form/cancion-form.component';
import { CalendarModule } from 'primeng/calendar';
import { AutoCompleteModule} from 'primeng/autocomplete';

@NgModule({
  declarations: [
    BackofficeComponent,
    HeaderComponent,
    FooterComponent,
    CancionFormComponent
  ],
  imports: [
    CommonModule,
    BackofficeRoutingModule,
    CalendarModule,
    AutoCompleteModule,
    FormsModule
  ]
})
export class BackofficeModule { }
