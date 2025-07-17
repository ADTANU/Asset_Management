import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuditAssets } from './audit-assets';

describe('AuditAssets', () => {
  let component: AuditAssets;
  let fixture: ComponentFixture<AuditAssets>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AuditAssets]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AuditAssets);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
