import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TiraReproduccionesComponent } from './tira-reproducciones.component';

describe('TiraReproduccionesComponent', () => {
  let component: TiraReproduccionesComponent;
  let fixture: ComponentFixture<TiraReproduccionesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TiraReproduccionesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TiraReproduccionesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
