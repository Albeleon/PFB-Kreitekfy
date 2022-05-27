import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TiraRecomendadosComponent } from './tira-recomendados.component';

describe('TiraRecomendadosComponent', () => {
  let component: TiraRecomendadosComponent;
  let fixture: ComponentFixture<TiraRecomendadosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TiraRecomendadosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TiraRecomendadosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
