import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TablaEstilosComponent } from './tabla-estilos.component';

describe('TablaEstilosComponent', () => {
  let component: TablaEstilosComponent;
  let fixture: ComponentFixture<TablaEstilosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TablaEstilosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TablaEstilosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
