import { TestBed } from '@angular/core/testing';

import { AuditAsset } from './audit';

describe('Audit', () => {
  let service: AuditAsset;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuditAsset);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
