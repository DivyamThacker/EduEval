import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Subscription } from 'rxjs';

import { BasicFormDataService } from '../../services/basic-form-data-service';
import { CollegeInfoDataService } from '../../services/academic-information-form/college-info-data-service';
import { ChairsHrdcDataService } from '../../services/academic-information-form/chairs-hrdc-data-service';
import { TeachingFacultyDataService } from '../../services/academic-information-form/teaching-faculty-data-service';
import { EvaluationReportsDataService } from '../../services/academic-information-form/evaluation-reports-data-service';
import { NepDetailsDataService } from '../../services/academic-information-form/nep-details-data-service';
import { ElectoralLiteracyDataService } from '../../services/academic-information-form/electoral-literacy-data-service';
import { EnrollmentDataService } from '../../services/academic-information-form/enrollment-data-service';
import { StaffDataService } from '../../services/academic-information-form/staff-data-service';

@Component({
  selector: 'app-forms-actions',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './forms-actions.component.html',
  styleUrl: './forms-actions.component.css'
})
export class FormsActionsComponent implements OnInit, OnDestroy{

  private routes = [
    'basic-information/basic-info',
    'basic-information/contact-details',
    'basic-information/recognition-details',
    'basic-information/area-location',
    'academic-information/college-info',
    'academic-information/teaching-faculty',
    'academic-information/staff',
    'academic-information/enrollment-details',
    'academic-information/chairs-hrdc-details',
    'academic-information/evaluation-reports',
    'academic-information/nep-details',
    'academic-information/electoral-literacy',
    'extended-profile/program-details',
    'extended-profile/student-details',
    'extended-profile/academic-details',
    'extended-profile/institution-details'
  ];

  isAtFirst: boolean = false; // Flag for 'Back' button condition
  isAtLast: boolean = false;  // Flag for 'Next' button condition
  private routeSub!: Subscription; // Subscription for route changes


  constructor(
    private basicFormDataService: BasicFormDataService,
    private collegeInfoDataService: CollegeInfoDataService,
    private chairHrdcDataService: ChairsHrdcDataService,
    private teachingFacultyDataService: TeachingFacultyDataService,
    private evaluationReportsDataService: EvaluationReportsDataService,
    private nepDetailsDataService: NepDetailsDataService,
    private electoralLiteracyDataService: ElectoralLiteracyDataService,
    private enrollmentDataService: EnrollmentDataService,
    private staffDataService: StaffDataService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.updateNavigationState(); // Set initial state
    this.routeSub = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.updateNavigationState(); // Update state on route change
      }
    });
  }

  ngOnDestroy() {
    if (this.routeSub) {
      this.routeSub.unsubscribe(); // Clean up the subscription
    }
  }

  private updateNavigationState() {
    const currentUrl = this.router.url.split('?')[0].replace(/^\//, '');
    const currentIndex = this.routes.indexOf(currentUrl);

    this.isAtFirst = currentIndex === 0;
    this.isAtLast = currentIndex === this.routes.length - 1;
  }

  navigate(direction: 'next' | 'back') {
    // Get the current URL path and find its index in the routes array
    const currentUrl = this.router.url.split('?')[0].replace(/^\//, ''); // strip leading slash
    const currentIndex = this.routes.indexOf(currentUrl);

    let newIndex = currentIndex;
    if (direction === 'next' && currentIndex < this.routes.length - 1) {
      newIndex++;
    } else if (direction === 'back' && currentIndex > 0) {
      newIndex--;
    }

    // Navigate to the new route
    this.router.navigate([this.routes[newIndex]]);
  }

  onExit(){
    console.log('Form exited');
    //confirm if the changes on the form should be saved
    this.router.navigate(['/dashboard']);
  }
  
  onSaveChanges() {
    const currentUrl = this.router.url;

    if (currentUrl.startsWith('/basic-information/basic-info')) {
      this.basicFormDataService.submitBasicInfo().subscribe({
        next: response => {
          console.log('Basic Info submitted successfully', response);
          alert('Basic Info submitted successfully');
          this.basicFormDataService.setUnsavedChanges(false);
        },
        error: error => {
          console.error('Error submitting Basic Info', error);
          alert('Error submitting Basic Info');
        }
      });
    } else if (currentUrl.startsWith('/basic-information/contact-details')) {
      this.basicFormDataService.submitContactDetails().subscribe({
        next: response => {
          console.log('Contact Details submitted successfully', response);
          alert('Contact Details submitted successfully');
          this.basicFormDataService.setUnsavedChanges(false);
        },
        error: error => {
          console.error('Error submitting Contact Details', error);
          alert('Error submitting Contact Details');
        }
      });
    } else if (currentUrl.startsWith('/basic-information/area-location')) {
      this.basicFormDataService.submitCampusDetails().subscribe({
        next: response => {
          console.log('Campus Details submitted successfully', response);
          alert('Campus Details submitted successfully');
          this.basicFormDataService.setUnsavedChanges(false);
        },
        error: error => {
          console.error('Error submitting Campus Details', error);
          alert('Error submitting Campus Details');
        }
      });
    } else if (currentUrl.startsWith('/basic-information/recognition-details')) {
      this.basicFormDataService.submitRecognitionDetails().subscribe({
        next: response => {
          console.log('Recognition Details submitted successfully', response);
          alert('Recognition Details submitted successfully');
          this.basicFormDataService.setUnsavedChanges(false);
        },
        error: error => {
          console.error('Error submitting Recognition Details', error);
          alert('Error submitting Recognition Details');
        }
      });
    } else if (currentUrl.startsWith('/academic-information/college-info')) {
      this.collegeInfoDataService.submitCollegeInfo().subscribe({
        next: response => {
          console.log('College Information submitted successfully', response);
          alert('College Information submitted successfully');
          this.basicFormDataService.setUnsavedChanges(false);
        },
        error: error => {
          console.error('Error submitting College Information', error);
          alert('Error submitting College Information');
        }
      });

      //sra program
      this.collegeInfoDataService.submitSraProgram().subscribe({
        next: response => {
          console.log('Sra Programs submitted successfully', response);
          alert('Sra Programs submitted successfully');
          this.basicFormDataService.setUnsavedChanges(false);
        },
        error: error => {
          console.error('Error submitting Sra Programs', error);
          alert('Error submitting Sra Programs');
        }
      });
    } else if (currentUrl.startsWith('/academic-information/teaching-faculty')) {
      this.teachingFacultyDataService.submitFacultyDetails().subscribe({
        next: response => {
          console.log('Faculty Information submitted successfully', response);
          alert('Faculty Information submitted successfully');
          this.basicFormDataService.setUnsavedChanges(false);
        },
        error: error => {
          console.error('Error submitting Faculty Information', error);
          alert('Error submitting Faculty Information');
        }
      });

      //Academician
      this.teachingFacultyDataService.submitAcademicianDetails().subscribe({
        next: response => {
          console.log('Academician Details submitted successfully', response);
          alert('Academician Details submitted successfully');
          this.basicFormDataService.setUnsavedChanges(false);
        },
        error: error => {
          console.error('Error submitting Academician Details', error);
          alert('Error submitting Academician Details');
        }
      });
    }  else if (currentUrl.startsWith('/academic-information/staff')) {
      this.staffDataService.submitStaffInfoData().subscribe({
        next: response => {
          console.log('Staff Information submitted successfully', response);
          alert('Staff Information submitted successfully');
          this.basicFormDataService.setUnsavedChanges(false);
        },
        error: error => {
          console.error('Error submitting Staff Information', error);
          alert('Error submitting Staff Information');
        }
      });
    }  else if (currentUrl.startsWith('/academic-information/enrollment-details')) {
      this.enrollmentDataService.submitEnrollmentData().subscribe({
        next: response => {
          console.log('Enrollment Information submitted successfully', response);
          alert('Enrollment Information submitted successfully');
          this.basicFormDataService.setUnsavedChanges(false);
        },
        error: error => {
          console.error('Error submitting Enrollment Information', error);
          alert('Error submitting Enrollment Information');
        }
      });
    // }  else if (currentUrl.startsWith('/academic-information/chairs-hrdc-details')) {
    //   this.chairHrdcDataService.submitChairs().subscribe({
    //     next: response => {
    //       console.log('Chairs Information submitted successfully', response);
    //       alert('Chairs Information submitted successfully');
    //       this.basicFormDataService.setUnsavedChanges(false);
    //     },
    //     error: error => {
    //       console.error('Error submitting CollChairsege Information', error);
    //       alert('Error submitting Chairs Information');
    //     }
    //   });

    //   // HRDC Details
    //   this.chairHrdcDataService.submitHrdc().subscribe({
    //     next: response => {
    //       console.log('Hrdc Details submitted successfully', response);
    //       alert('Hrdc Details submitted successfully');
    //       this.basicFormDataService.setUnsavedChanges(false);
    //     },
    //     error: error => {
    //       console.error('Error submitting Hrdc Details', error);
    //       alert('Error submitting Hrdc Details');
    //     }
    //   });
    // }  else if (currentUrl.startsWith('/academic-information/evaluation-reports')) {
    //   this.evaluationReportsDataService.submitAccredationDetails().subscribe({
    //     next: response => {
    //       console.log('Accredation Details submitted successfully', response);
    //       alert('Accredation Details submitted successfully');
    //       this.basicFormDataService.setUnsavedChanges(false);
    //     },
    //     error: error => {
    //       console.error('Error submitting Accredation Details', error);
    //       alert('Error submitting Accredation Details');
    //     }
    //   });

    //   //Evaluation Reports
    //   this.evaluationReportsDataService.submitEvaluationReports().subscribe({
    //     next: response => {
    //       console.log('Evaluation Reports submitted successfully', response);
    //       alert('Evaluation Reports  submitted successfully');
    //       this.basicFormDataService.setUnsavedChanges(false);
    //     },
    //     error: error => {
    //       console.error('Error submitting Evaluation Reports ', error);
    //       alert('Error submitting Evaluation Reports ');
    //     }
    //   });
    // }  else if (currentUrl.startsWith('/academic-information/nep-details')) {
    //   this.nepDetailsDataService.submitNepDetails().subscribe({
    //     next: response => {
    //       console.log('Nep Details  submitted successfully', response);
    //       alert('Nep Details submitted successfully');
    //       this.basicFormDataService.setUnsavedChanges(false);
    //     },
    //     error: error => {
    //       console.error('Error submitting Nep Details ', error);
    //       alert('Error submitting Nep Details ');
    //     }
    //   });
    // }  else if (currentUrl.startsWith('/academic-information/electoral-literacy')) {
    //   this.electoralLiteracyDataService.submitElectoralLiteracy().subscribe({
    //     next: response => {
    //       console.log('Electoral Literacy Details submitted successfully', response);
    //       alert('Electoral Literacy Details  submitted successfully');
    //       this.basicFormDataService.setUnsavedChanges(false);
    //     },
    //     error: error => {
    //       console.error('Error submitting Electoral Literacy Details ', error);
    //       alert('Error submitting Electoral Literacy Details ');
    //     }
    //   });
    }
    
  }
}
