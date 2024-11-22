import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router, RouterModule } from '@angular/router';
import { BasicFormDataService } from '../../services/basic-form-data-service';
import { CommonModule } from '@angular/common';
import { Subscription } from 'rxjs';

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
    'academic-information/extra-details',
    'extended-profile/program-details',
    'extended-profile/student-details',
    'extended-profile/academic-details',
    'extended-profile/institution-details'
  ];

  isAtFirst: boolean = false; // Flag for 'Back' button condition
  isAtLast: boolean = false;  // Flag for 'Next' button condition
  private routeSub!: Subscription; // Subscription for route changes


  constructor(private basicFormDataService: BasicFormDataService, private router: Router, private route: ActivatedRoute) {}
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
    this.basicFormDataService.setUnsavedChanges(false); 
    const currentUrl = this.router.url;

    if (currentUrl.startsWith('/basic-information/basic-info')) {
      this.basicFormDataService.submitBasicInfo().subscribe({
        next: response => {
          console.log('Basic Info submitted successfully', response);
          alert('Basic Info submitted successfully');
        },
        error: error => console.error('Error submitting Basic Info', error)
      });
    } else if (currentUrl.startsWith('/basic-information/contact-details')) {
      this.basicFormDataService.submitContactDetails().subscribe({
        next: response => {
          console.log('Contact Details submitted successfully', response);
          alert('Contact Details submitted successfully');
        },
        error: error => console.error('Error submitting Contact Details', error)
      });
    } else if (currentUrl.startsWith('/basic-information/area-location')) {
      this.basicFormDataService.submitCampusDetails().subscribe({
        next: response => {
          console.log('Campus Details submitted successfully', response);
          alert('Campus Details submitted successfully');
        },
        error: error => console.error('Error submitting Campus Details', error)
      });
    } else if (currentUrl.startsWith('/basic-information/recognition-details')) {
      this.basicFormDataService.submitRecognitionDetails().subscribe({
        next: response => {
          console.log('Recognition Details submitted successfully', response);
          alert('Recognition Details submitted successfully');
        },
        error: error => console.error('Error submitting Recognition Details', error)
      });
    }
  }
}
