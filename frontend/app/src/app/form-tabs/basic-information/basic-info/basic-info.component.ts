import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { BasicFormDataService } from '../../../services/basic-form-data-service';
import { ReactiveFormsModule } from '@angular/forms';
import { CanComponentDeactivate } from '../../../guards/can-deactivate.guard';

@Component({
  selector: 'app-basic-info',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './basic-info.component.html',
  styleUrl: './basic-info.component.css'
})
export class BasicInfoComponent implements OnInit, CanComponentDeactivate {
  form: FormGroup;

  constructor(private fb: FormBuilder, private basicFormDataService: BasicFormDataService) {
    this.form = this.fb.group({
      name: [''],
      address: [''],
      city: [''],
      pincode: [''],
      state: [''],
      websiteUrl: [''],
      nature: [''],
      type: [''],
      establishmentDate: [''],
      priorStatus: ['']
    });
  }

  canDeactivate(): boolean {
    if (this.basicFormDataService.getUnsavedChanges()) {
      // Confirm the user wants to leave the page without saving changes
      const confirmExit = confirm('You have unsaved changes. Do you really want to exit?');
      if (confirmExit) {
        this.basicFormDataService.setUnsavedChanges(false);
      }
      return confirmExit;
    }
    return true; // Allow navigation if no changes were made
  }

  ngOnInit() {
    this.form.valueChanges.subscribe(() => {
      this.basicFormDataService.setBasicInfoData(this.form.value);
      this.basicFormDataService.setUnsavedChanges(true);
    });
  }
}