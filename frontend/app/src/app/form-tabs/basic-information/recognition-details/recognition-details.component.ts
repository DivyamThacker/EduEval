import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { BasicFormDataService } from '../../../services/basic-form-data-service';
import { ReactiveFormsModule } from '@angular/forms';
import { CanComponentDeactivate } from '../../../guards/can-deactivate.guard';

@Component({
  selector: 'app-recognition-details',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './recognition-details.component.html',
  styleUrl: './recognition-details.component.css'
})
export class RecognitionDetailsComponent implements OnInit , CanComponentDeactivate {
  form: FormGroup;
  files: { [key: string]: File | null } = {
    recognitionDocument2f: null,
    recognitionDocument12b: null,
  };

  constructor(private fb: FormBuilder, private basicFormDataService: BasicFormDataService) {
    this.form = this.fb.group({
      recognitionDateUnderSection2f: [''],
      recognitionDateUnderSection12b: [''],
      recognitionDocument2f: [''],
      recognitionDocument12b: [''],
      isUPE: [''],
    });
  }

  onFileChange(event: Event, controlName: string) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      this.form.patchValue({
        [controlName]: file,
      });
    }
  }

  canDeactivate(): boolean {
    if (this.basicFormDataService.getUnsavedChanges()) {
      const confirmExit = confirm('You have unsaved changes. Do you really want to exit?');
      if (confirmExit) {
        this.basicFormDataService.setUnsavedChanges(false);
      }
      return confirmExit;
    }
    return true; 
  }
  
  ngOnInit() {
    this.form.valueChanges.subscribe(() => {
      this.basicFormDataService.setRecognitionDetailsData(this.form.value);
      this.basicFormDataService.setUnsavedChanges(true);
    });
  }
}