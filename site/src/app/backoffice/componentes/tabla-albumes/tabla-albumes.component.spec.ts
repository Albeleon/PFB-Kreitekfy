import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TablaAlbumesComponent } from './tabla-albumes.component';

describe('TablaAlbumesComponent', () => {
  let component: TablaAlbumesComponent;
  let fixture: ComponentFixture<TablaAlbumesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TablaAlbumesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TablaAlbumesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
