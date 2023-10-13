import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobApplicationCardComponent } from './job-application-card.component';

describe('JobApplicationCardComponent', () => {
  let component: JobApplicationCardComponent;
  let fixture: ComponentFixture<JobApplicationCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [JobApplicationCardComponent]
    });
    fixture = TestBed.createComponent(JobApplicationCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
