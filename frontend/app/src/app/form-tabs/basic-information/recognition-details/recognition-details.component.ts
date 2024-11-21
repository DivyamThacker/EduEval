import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { FormDataService } from '../../../shared/form-api-service';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-recognition-details',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './recognition-details.component.html',
  styleUrl: './recognition-details.component.css'
})
export class RecognitionDetailsComponent implements OnInit {
  form: FormGroup;
  selectedFile2f: File | null = null;
  selectedFile12b: File | null = null;

  constructor(private fb: FormBuilder, private formDataService: FormDataService) {
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
        [controlName]: file
      });
    }
  }
  
  ngOnInit() {
    this.form.valueChanges.subscribe(() => {
      this.formDataService.setBasicInfoData(this.form.value);
    });
  }
}