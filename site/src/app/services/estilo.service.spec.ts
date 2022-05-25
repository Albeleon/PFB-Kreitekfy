import { TestBed } from '@angular/core/testing';

import { EstiloService } from './estilo.service';

describe('EstiloService', () => {
  let service: EstiloService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EstiloService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
