import { EventEmitter, Injectable, Output } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class SharedService {
  @Output() fire: EventEmitter<any> = new EventEmitter();
  @Output() fireBack: EventEmitter<any> = new EventEmitter();

  constructor() {}

  change(data: any) {
    this.fire.emit(data);
  }
  changeBack(data: any) {
    this.fireBack.emit(data);
  }

  getEmittedValue() {
    return this.fire;
  }
  getEmittedValueBack() {
    return this.fireBack;
  }
}
