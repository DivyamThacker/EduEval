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

  constructor(private fb: FormBuilder, private formDataService: FormDataService) {
    this.form = this.fb.group({
      recognition: [''],
      address: [''],
      city: [''],
      pincode: [''],
      isUPE: [''],
    });
  }

  ngOnInit() {
    this.form.valueChanges.subscribe(() => {
      this.formDataService.setBasicInfoData(this.form.value);
    });
  }
}