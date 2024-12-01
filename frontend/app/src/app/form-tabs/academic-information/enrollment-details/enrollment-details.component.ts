import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule, NgIf } from '@angular/common';
import { EnrollmentDataService } from '../../../services/academic-information-form/enrollment-data-service';


@Component({
  selector: 'app-enrollment-details',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, NgIf],
  templateUrl: './enrollment-details.component.html',
  styleUrl: './enrollment-details.component.css'
})
export class EnrollmentDetailsComponent implements OnInit {
  enrollmentForm: FormGroup;

  constructor(private fb: FormBuilder, private enrollmentDataService: EnrollmentDataService) {
    this.enrollmentForm = this.fb.group({
      hasIntegrated: [false],
      totalIntegratedPrograms: [''],
      enrollments : this.fb.array([]),
    });
    this.addEnrollment();
  }

  get enrollments(): FormArray {
    return this.enrollmentForm.get('enrollments') as FormArray;
  }

  addEnrollment(): void {
    const enrollmentGroup = this.fb.group({
      program: [''],
      gender: [''],
      location: [''],
      count: ['']
    });

    this.enrollments.push(enrollmentGroup);
  }

  removeEnrollment(index: number): void {
    this.enrollments.removeAt(index);
  } 

  ngOnInit() {
    this.enrollmentForm.valueChanges.subscribe(() => {
      this.enrollmentDataService.setEnrollmentData(this.enrollmentForm.value);
    });
  }
}
