import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EducationCardComponent } from './education-card.component';

describe('EducationCardComponent', () => {
  let component: EducationCardComponent;
  let fixture: ComponentFixture<EducationCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EducationCardComponent]
    });
    fixture = TestBed.createComponent(EducationCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
