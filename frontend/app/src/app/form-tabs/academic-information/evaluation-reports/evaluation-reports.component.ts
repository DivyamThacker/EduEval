import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { EvaluationReportsDataService } from '../../../services/academic-information-form/evaluation-reports-data-service';

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

  constructor(private fb: FormBuilder, private evaluationReportsDataService: EvaluationReportsDataService) {
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
      type: [''],
      grade: [''],
      cgpa: [''],
      // peerTeamReport: [''],
    });
    this.accreditationArray.push(accreditationGroup);
  
  }
  

  removeAccreditation(index: number): void {
    this.accreditationArray.removeAt(index);
    delete this.accreditationReports[index];
  }

  // Department Form Handling
  get departmentArray(): FormArray {
    return this.departmentForm.get('departments') as FormArray;
  }

  addDepartment(): void {
    const departmentGroup = this.fb.group({
      departmentName: [''],
      // departmentReport: [''],
    });
    this.departmentArray.push(departmentGroup);
  }

  removeDepartment(index: number): void {
    this.departmentArray.removeAt(index);
    delete this.departmentReports[index];
  }

  // Handle file input change for accreditation
  onAccreditationFileChange(event: any, index: number): void {
    const file = event.target.files[0];
    this.accreditationReports[index] = file; // Store file by accreditation index

    // Update Accreditation data in service
    this.updateAccreditationModelSource();
  }

  // Handle file input change for department
  onDepartmentFileChange(event: any, index: number): void {
    const file = event.target.files[0];
    this.departmentReports[index] = file; // Store file by department index

    // Update Department data in service
    this.updateDepartmentModelSource();
  }

  // Update the Accreditation Model Source in the service
  private updateAccreditationModelSource(): void {
    this.evaluationReportsDataService.setAccreditationData({
      formValue: this.accreditationForm.value,
      files: Object.values(this.accreditationReports), // Convert file object to array
    });
  }

  // Update the Department Model Source in the service
  private updateDepartmentModelSource(): void {
    this.evaluationReportsDataService.setDepartmentData({
      formValue: this.departmentForm.value,
      files: Object.values(this.departmentReports), // Convert file object to array
    });
  }

  ngOnInit() {
    this.accreditationForm.valueChanges.subscribe(() => {
      this.updateAccreditationModelSource();
    });
    this.departmentForm.valueChanges.subscribe(() => {
      this.updateDepartmentModelSource();
    });
  }
}
