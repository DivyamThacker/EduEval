import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule, NgIf } from '@angular/common';


@Component({
  selector: 'app-enrollment-details',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, NgIf],
  templateUrl: './enrollment-details.component.html',
  styleUrl: './enrollment-details.component.css'
})
export class EnrollmentDetailsComponent implements OnInit {
  enrollmentForm: FormGroup;

  integratedForm: FormGroup;
  
  constructor(private fb: FormBuilder) {
    this.enrollmentForm = this.fb.group({
      enrollments : this.fb.array([]),
    });
    this.addEnrollment();

    this.integratedForm = this.fb.group({
      hasIntegrated: [false],
      totalIntegratedProgrammes: [''],
      integratedEnrollments: this.fb.array([])
    });

    this.integratedForm.get('hasIntegrated')?.valueChanges.subscribe((hasIntegrated: boolean) => {
      if (hasIntegrated) {
        this.integratedForm.setControl('integratedEnrollments', this.fb.array([this.createIntegratedFormGroup()]));
      } else {
        this.integratedForm.setControl('integratedEnrollments', this.fb.array([]));
      }
    });
  }

  get integratedEnrollments(): FormArray {
    return this.integratedForm.get('integratedEnrollments') as FormArray;
  }

  createIntegratedFormGroup(): FormGroup {
    return this.fb.group({
      gender: [''],
      location: [''],
      count: ['']
    });
  }

  addIntegratedEnrollment(): void {
    this.integratedEnrollments.push(this.createIntegratedFormGroup());
  }

  removeIntegratedEnrollment(index: number): void {
    this.integratedEnrollments.removeAt(index);
  } 

  get enrollments(): FormArray {
    return this.enrollmentForm.get('enrollments') as FormArray;
  }

  addEnrollment(): void {
    const enrollmentGroup = this.fb.group({
      programme: [''],
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
      // this.collegeInfoDataService.setBasicInfoData(this.form.value);
    });
    this.integratedForm.valueChanges.subscribe(() => {
      // this.collegeInfoDataService.setBasicInfoData(this.form.value);
    });
  }
}
