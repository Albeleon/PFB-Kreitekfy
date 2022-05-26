import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CancionDataComponent } from './cancion-data.component';

describe('CancionDataComponent', () => {
  let component: CancionDataComponent;
  let fixture: ComponentFixture<CancionDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CancionDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CancionDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
