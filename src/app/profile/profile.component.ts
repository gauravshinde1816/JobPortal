import { Component, ElementRef, EventEmitter, HostListener, Input, Output } from '@angular/core';
import { CentralDataServiceService } from '../central-data-service.service';
import { JobService } from '../job-list/job.service';
import { JobApplication, UserDetails } from '../Types';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  @Input() userName: string = '';
  @Input() role: string = '';
  @Input() noOfJobsPosted: Number = 0
  @Output() stopScroll = new EventEmitter<void>();


  userDetails : UserDetails = {jwtToken  : '' , user : {password : '' , username : "" , role : "" , bio : '' , current_company  : ''}}

  public jobListCountForHiringManager: number = 0

  public jobApplicationList: JobApplication[] = [];

  constructor(private elementRef: ElementRef, private centralDataService: CentralDataServiceService, private jobService: JobService) { }

  ngOnInit() {

    this.centralDataService.userDetailsStateSubject.subscribe((val)=>{
      this.userDetails = val
    })



    if (this.userDetails.user.role === "JS") {
      const token = localStorage.getItem("authToken")
      if (!token) return;
      this.jobService.getJobApplicationsForLoggedInUser(token).subscribe((data: any) => {
        this.jobApplicationList = data
      })
    }

    this.centralDataService.jobListCountForHiringManagerSubject.subscribe((val) => {
      this.jobListCountForHiringManager = val
    })
  }

  @HostListener('window:scroll', [])
  onScroll() {
    const scrollPosition = window.scrollY || document.documentElement.scrollTop;
    if (scrollPosition >= 5000) {
      // Emit an event or use a flag to notify the parent component
      this.stopScroll.emit();
    }
  }
}
