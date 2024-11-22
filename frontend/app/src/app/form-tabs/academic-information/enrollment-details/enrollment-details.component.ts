import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule, NgIf } from '@angular/common';
import { AcademicFormDataService } from '../../../services/academic-form-data-service';


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
  
  constructor(private fb: FormBuilder, private academicFormDataService: AcademicFormDataService) {
    this.enrollmentForm = this.fb.group({
      enrollments : this.fb.array([]),
    });
    this.addEnrollment();

    this.integratedForm = this.fb.group({
      isIntegrated: [false],
      integratedEnrollments: this.fb.array([])
    });

    this.integratedForm.get('isIntegrated')?.valueChanges.subscribe((isIntegrated: boolean) => {
      if (isIntegrated) {
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
      totalProgrammes: [''],
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
      // this.academicFormDataService.setBasicInfoData(this.form.value);
    });
    this.integratedForm.valueChanges.subscribe(() => {
      // this.academicFormDataService.setBasicInfoData(this.form.value);
    });
  }
}
