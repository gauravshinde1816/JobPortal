import { TestBed } from '@angular/core/testing';

import { CentralDataServiceService } from './central-data-service.service';

describe('CentralDataServiceService', () => {
  let service: CentralDataServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CentralDataServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
