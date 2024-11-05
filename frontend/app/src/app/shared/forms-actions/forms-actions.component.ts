import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormDataService } from '../form-api-service';

@Component({
  selector: 'app-forms-actions',
  standalone: true,
  imports: [],
  templateUrl: './forms-actions.component.html',
  styleUrl: './forms-actions.component.css'
})
export class FormsActionsComponent{
  constructor(private formDataService: FormDataService, private router: Router) {}

  onBack(){
    console.log('Back button clicked');
  }

  onNext(){
    console.log('Next button clicked');
  }

  onSubmit(){
    console.log('Form submitted');
  }

  onSaveChanges() {
    const currentUrl = this.router.url;
  
    if (currentUrl.startsWith('/basic-information')) {
      this.formDataService.submitBasicInfo().subscribe({
        next: response => console.log('Basic Info submitted successfully', response),
        error: error => console.error('Error submitting Basic Info', error)
      });
    } else if (currentUrl.startsWith('/academic-information')) {
      this.formDataService.submitAcademicInfo().subscribe({
        next: response => console.log('Academic Info submitted successfully', response),
        error: error => console.error('Error submitting Academic Info', error)
      });
    } else {
      console.warn('Unrecognized section for submission');
    }
  }
  

}
