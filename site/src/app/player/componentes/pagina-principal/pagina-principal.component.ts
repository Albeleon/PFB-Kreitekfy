import { Component, Input, OnInit } from '@angular/core';
import { Estilo } from 'src/app/models/estilo.interface';

@Component({
  selector: 'app-pagina-principal',
  templateUrl: './pagina-principal.component.html',
  styleUrls: ['./pagina-principal.component.scss']
})
export class PaginaPrincipalComponent implements OnInit {
  @Input() estilo?: Estilo;
  
  constructor() { }

  ngOnInit(): void {
  }

}
