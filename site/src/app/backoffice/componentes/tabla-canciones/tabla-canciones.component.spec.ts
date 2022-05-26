import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TablaCancionesComponent } from './tabla-canciones.component';

describe('TablaCancionesComponent', () => {
  let component: TablaCancionesComponent;
  let fixture: ComponentFixture<TablaCancionesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TablaCancionesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TablaCancionesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
