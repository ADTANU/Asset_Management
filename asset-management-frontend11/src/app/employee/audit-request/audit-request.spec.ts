import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuditRequestComponent } from './audit-request';

describe('AuditRequest', () => {
  let component: AuditRequestComponent;
  let fixture: ComponentFixture<AuditRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AuditRequestComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AuditRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
