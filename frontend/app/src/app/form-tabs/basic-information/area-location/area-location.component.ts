import { NgFor } from '@angular/common';
import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-area-location',
  standalone: true,
  imports: [ReactiveFormsModule, NgFor],
  templateUrl: './area-location.component.html',
  styleUrl: './area-location.component.css'
})
export class AreaLocationComponent {
  form: FormGroup;

  constructor(private fb: FormBuilder) {
    // Initialize the form with a FormArray
    this.form = this.fb.group({
      campuses: this.fb.array([]),//campus locations
    });
    this.addCampus(); // Add the first contact form on component initialization
  }

  get campuses(): FormArray {
    return this.form.get('campuses') as FormArray;
  }

  addCampus(): void {
    const campusGroup = this.fb.group({
      campusType: ['', Validators.required],
      location: ['', Validators.required],
      campusArea: ['', Validators.pattern(/^\d{5}$/)],
      address: ['', Validators.required],
      builtInArea: ['', Validators.pattern(/^\d{5}$/)],
      ProgrammesOffered: ['', Validators.required],
      dateOfEstablishment: ['', [Validators.required, Validators.pattern(/^\d{2} \d{2} \d{4}$/)]],
      dateOfRecognition: ['', [Validators.required, Validators.pattern(/^\d{2} \d{2} \d{4}$/)]]
    });
    this.campuses.push(campusGroup);
  }

  removeCampus(index: number): void {
    this.campuses.removeAt(index);
  }

  onSubmit(): void {
    console.log(this.form.value);
  }
}
