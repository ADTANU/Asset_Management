import { TestBed } from '@angular/core/testing';

import { AssetService } from './asset';

describe('Asset', () => {
  let service: AssetService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AssetService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
