import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FormDataService } from '../../../shared/form-api-service';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-staff',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './staff.component.html',
  styleUrl: './staff.component.css'
})
export class StaffComponent implements OnInit {
  nonTeachStaffForm: FormGroup;

  techStaffForm : FormGroup;
  
  constructor(private fb: FormBuilder, private formDataService: FormDataService) {
    this.nonTeachStaffForm = this.fb.group({
      nonTeachStaff : this.fb.array([]),
    });
    this.addNonTeachStaff();

    this.techStaffForm = this.fb.group({
      techStaff: this.fb.array([]),
    });
    this.addTechStaff();
  }

  get nonTeachStaff(): FormArray {
    return this.nonTeachStaffForm.get('nonTeachStaff') as FormArray;
  }

  get techStaff(): FormArray {
    return this.techStaffForm.get('techStaff') as FormArray;
  }

  addNonTeachStaff(): void {
    const nonTeachStaffGroup = this.fb.group({
      recruitmentStatus: [''],
      gender: [''],
      count: ['']
    });
    this.nonTeachStaff.push(nonTeachStaffGroup);
  }

  addTechStaff(): void {
    const techStaffGroup = this.fb.group({
      recruitmentStatus: [''],
      gender: [''],
      count: ['']
    });
    this.techStaff.push(techStaffGroup);
  }

  removeNonTeachStaff(index: number): void {
    this.nonTeachStaff.removeAt(index);
  } 

  removeTechStaff(index: number): void {
    this.techStaff.removeAt(index);
  } 

  ngOnInit() {
    this.nonTeachStaffForm.valueChanges.subscribe(() => {
      // this.formDataService.setBasicInfoData(this.form.value);
    });
    this.techStaffForm.valueChanges.subscribe(() => {
      // this.formDataService.setBasicInfoData(this.form.value);
    });
  }
}
