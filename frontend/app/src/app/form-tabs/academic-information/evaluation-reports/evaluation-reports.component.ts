import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AcademicFormDataService } from '../../../services/academic-form-data-service';

@Component({
  selector: 'app-evaluation-reports',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './evaluation-reports.component.html',
  styleUrl: './evaluation-reports.component.css'
})
export class EvaluationReportsComponent implements OnInit {
  accreditationForm: FormGroup;
  departmentForm: FormGroup;

  accreditationReports: { [key: number]: File } = {};
  departmentReports: { [key: number]: File } = {};

  constructor(private fb: FormBuilder, private academicFormDataService: AcademicFormDataService) {
    this.accreditationForm = this.fb.group({
      accreditations: this.fb.array([]),
    });
    this.addAccreditation();

    this.departmentForm = this.fb.group({
      departments: this.fb.array([]),
    });
    this.addDepartment();
  }

  get accreditationArray(): FormArray {
    return this.accreditationForm.get('accreditations') as FormArray;
  }

  addAccreditation(): void {
    const index = this.accreditationArray.length;
    const accreditationGroup = this.fb.group({
      cycleNumber: [index+1],
      accreditationType: [''],
      grade: [''],
      cgpa: [''],
      peerTeamReport: [''],
    });
    this.accreditationArray.push(accreditationGroup);
  
  }
  

  removeAccreditation(index: number): void {
    this.accreditationArray.removeAt(index);
    delete this.accreditationReports[index];
  }

  onAccreditationFileChange(event: Event, index: number): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.accreditationReports[index] = input.files[0];
    }
  }

  // Department Form Handling
  get departmentArray(): FormArray {
    return this.departmentForm.get('departments') as FormArray;
  }

  addDepartment(): void {
    const departmentGroup = this.fb.group({
      departmentName: [''],
      departmentReport: [''],
    });
    this.departmentArray.push(departmentGroup);
  }

  removeDepartment(index: number): void {
    this.departmentArray.removeAt(index);
    delete this.departmentReports[index];
  }

  onDepartmentFileChange(event: Event, index: number): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.departmentReports[index] = input.files[0];
    }
  }


  onSubmit(): void {
    const formData = new FormData();
    formData.append('accreditations', JSON.stringify(this.accreditationForm.value.accreditations));

    Object.entries(this.accreditationReports).forEach(([index, file]) => {
      formData.append(`file${index}`, file);
    });

    // Make HTTP request here
    console.log(formData);
  }

  ngOnInit() {
    this.accreditationForm.valueChanges.subscribe(() => {
      // this.academicFormDataService.setBasicInfoData(this.form.value);
    });
    this.departmentForm.valueChanges.subscribe(() => {
      // this.academicFormDataService.setBasicInfoData(this.form.value);
    });
  }
}
