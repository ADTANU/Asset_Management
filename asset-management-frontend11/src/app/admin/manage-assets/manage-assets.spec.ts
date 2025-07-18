import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageAssetsComponent } from './manage-assets';

describe('ManageAssets', () => {
  let component: ManageAssetsComponent;
  let fixture: ComponentFixture<ManageAssetsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManageAssetsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManageAssetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
