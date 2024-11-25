import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
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
  staffForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.staffForm = this.fb.group({
      staff : this.fb.array([]),
    });
    this.addStaff();
  }

  addStaff(): void {
    const techStaffGroup = this.fb.group({
      isTechnical: [''],
      recruitmentStatus: [''],
      gender: [''],
      count: ['']
    });
    this.staff.push(techStaffGroup);
  }

  get staff(): FormArray {
    return this.staffForm.get('staff') as FormArray
  }

  removeStaff(index: number): void {
    this.staff.removeAt(index);
  } 

  ngOnInit() {
    this.staffForm.valueChanges.subscribe(() => {
      // this.academicFormDataService.setBasicInfoData(this.form.value);
    });
  }
}
