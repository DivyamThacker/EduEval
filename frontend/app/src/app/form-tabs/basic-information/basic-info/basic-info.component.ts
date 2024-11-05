import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { FormDataService } from '../../../shared/form-api-service';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-basic-info',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './basic-info.component.html',
  styleUrl: './basic-info.component.css'
})
export class BasicInfoComponent implements OnInit {
  form: FormGroup;

  constructor(private fb: FormBuilder, private formDataService: FormDataService) {
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

  ngOnInit() {
    this.form.valueChanges.subscribe(() => {
      this.formDataService.setBasicInfoData(this.form.value);
    });
  }
}