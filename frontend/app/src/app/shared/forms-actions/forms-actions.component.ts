import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { FormDataService } from '../form-api-service';

@Component({
  selector: 'app-forms-actions',
  standalone: true,
  imports: [RouterModule],
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

  onExit(){
    console.log('Form exited');
    //confirm if the changes on the form should be saved
    this.router.navigate(['/dashboard']);
  }

  onSaveChanges() {
    const currentUrl = this.router.url;

    if (currentUrl.startsWith('/basic-information/basic-info')) {
      this.formDataService.submitBasicInfo().subscribe({
        next: response => console.log('Basic Info submitted successfully', response),
        error: error => console.error('Error submitting Basic Info', error)
      });
    } else if (currentUrl.startsWith('/basic-information/contact-details')) {
      this.formDataService.submitContactDetails().subscribe({
        next: response => console.log('Contact Details submitted successfully', response),
        error: error => console.error('Error submitting Contact Details', error)
      });
    } else if (currentUrl.startsWith('/basic-information/area-location')) {
      this.formDataService.submitCampusDetails().subscribe({
        next: response => console.log('Campus Details submitted successfully', response),
        error: error => console.error('Error submitting Campus Details', error)
      });
    }
  }
  

}
