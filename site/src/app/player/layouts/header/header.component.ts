import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  estilo: string = "";
  @Output() eventEmitter = new EventEmitter<string>();

  constructor() { }

  ngOnInit(): void {
  }

  enviarEstilo(): void {
    this.eventEmitter.emit(this.estilo);
  }

}
