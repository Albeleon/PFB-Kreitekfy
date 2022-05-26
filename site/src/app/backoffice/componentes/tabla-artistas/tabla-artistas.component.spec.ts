import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TablaArtistasComponent } from './tabla-artistas.component';

describe('TablaArtistasComponent', () => {
  let component: TablaArtistasComponent;
  let fixture: ComponentFixture<TablaArtistasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TablaArtistasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TablaArtistasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
