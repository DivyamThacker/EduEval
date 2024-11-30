import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule, NgIf } from '@angular/common';
import { CollegeInfoDataService } from '../../../services/academic-information-form/college-info-data-service';

@Component({
  selector: 'app-college-info',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf, CommonModule],
  templateUrl: './college-info.component.html',
  styleUrl: './college-info.component.css'
})
export class CollegeInfoComponent implements OnInit {
  form: FormGroup;
  sraForm: FormGroup;
  files: { [key: number]: File } = {}; // Store files corresponding to SRA programs

  constructor(private fb: FormBuilder, private collegeInfoDataService: CollegeInfoDataService) {
    // College Info Form
    this.form = this.fb.group({
      constituentColleges: [''],
      affiliatedColleges: [''],
      collegesUnder2f: [''],
      collegesUnder2f12b: [''],
      naacAccredited: [''],
      collegesWithExcellence: [''],
      autonomousColleges: [''],
      collegesWithPgDepartments: [''],
      collegesWithResearchDepartments: [''],
      researchInstitutes: [''],
    });

    // SRA Form
    this.sraForm = this.fb.group({
      areSraProgram: [false],
      sraPrograms: this.fb.array([]),
    });

    // Update SRA Programs array dynamically based on checkbox
    this.sraForm.get('areSraProgram')?.valueChanges.subscribe((areSraProgram: boolean) => {
      if (areSraProgram) {
        this.sraForm.setControl('sraPrograms', this.fb.array([this.createSraFormGroup()]));
      } else {
        this.sraForm.setControl('sraPrograms', this.fb.array([]));
      }
    });
  }

  // Getter for SRA Programs FormArray
  get sraPrograms(): FormArray {
    return this.sraForm.get('sraPrograms') as FormArray;
  }

  // Create a new FormGroup for an individual SRA Program
  createSraFormGroup(): FormGroup {
    return this.fb.group({
      sraProgramName: [''], // Name of the SRA program
      sraDocument: [''],    // Placeholder for file input
    });
  }

  // Handle file input change
  onFileChange(event: any, index: number): void {
    const file = event.target.files[0];
    this.files[index] = file; // Store file by program index

    // Update SRA Program data in service
    this.updateSraProgramModelSource();
  }

  // Add a new SRA Program
  addSraProgram(): void {
    this.sraPrograms.push(this.createSraFormGroup());
  }

  // Remove an SRA Program
  removeSraProgram(index: number): void {
    this.sraPrograms.removeAt(index);
    delete this.files[index]; // Remove file at the corresponding index

    // Update SRA Program data in service
    this.updateSraProgramModelSource();
  }

  // Update the SRA Program Model Source in the service
  private updateSraProgramModelSource(): void {
    this.collegeInfoDataService.setSraProgramData({
      formValue: this.sraForm.value,
      files: Object.values(this.files), // Convert file object to array
    });
  }

  ngOnInit(): void {
    // Sync College Info Form with the service
    this.form.valueChanges.subscribe(() => {
      this.collegeInfoDataService.setCollegeInfoData(this.form.value);
    });

    // Sync SRA Form with the service
    this.sraForm.valueChanges.subscribe(() => {
      this.updateSraProgramModelSource();
    });
  }
}