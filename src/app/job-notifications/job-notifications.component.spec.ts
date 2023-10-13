import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobNotificationsComponent } from './job-notifications.component';

describe('JobNotificationsComponent', () => {
  let component: JobNotificationsComponent;
  let fixture: ComponentFixture<JobNotificationsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [JobNotificationsComponent]
    });
    fixture = TestBed.createComponent(JobNotificationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
