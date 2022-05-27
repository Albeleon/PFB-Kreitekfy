import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TiraNovedadesComponent } from './tira-novedades.component';

describe('TiraNovedadesComponent', () => {
  let component: TiraNovedadesComponent;
  let fixture: ComponentFixture<TiraNovedadesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TiraNovedadesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TiraNovedadesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
