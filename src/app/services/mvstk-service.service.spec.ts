import { TestBed } from '@angular/core/testing';

import { MvstkService } from './mvstk.service';

describe('MvstkServiceService', () => {
  let service: MvstkService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MvstkService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
