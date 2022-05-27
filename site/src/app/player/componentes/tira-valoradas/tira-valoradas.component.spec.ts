import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TiraValoradasComponent } from './tira-valoradas.component';

describe('TiraValoradasComponent', () => {
  let component: TiraValoradasComponent;
  let fixture: ComponentFixture<TiraValoradasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TiraValoradasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TiraValoradasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
