import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule, NgIf } from '@angular/common';
import { AcademicFormDataService } from '../../../services/academic-form-data-service';

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
  
  constructor(private fb: FormBuilder, private academicFormDataService: AcademicFormDataService) {
    this.form = this.fb.group({
      constituentColleges: [''],
      affiliatedColleges: [''],
      collegesUnder2f: [''],
      collegesUnder2f12b: [''],
      naacAccredited: [''],
      collegesExcellence: [''],
      autonomousColleges: [''],
      pgDepartments: [''],
      researchDepartments: [''],
      researchInstitutes: [''],
    });

    this.sraForm = this.fb.group({
      isSRAProgram: [false],
      sraPrograms: this.fb.array([])
    });

    this.sraForm.get('isSRAProgram')?.valueChanges.subscribe((isSRAProgram: boolean) => {
      if (isSRAProgram) {
        this.sraForm.setControl('sraPrograms', this.fb.array([this.createSRAFormGroup()]));
      } else {
        this.sraForm.setControl('sraPrograms', this.fb.array([]));
      }
    });
  }

  get sraPrograms(): FormArray {
    return this.sraForm.get('sraPrograms') as FormArray;
  }

  createSRAFormGroup(): FormGroup {
    return this.fb.group({
      sraProgramName: [''],
      sraDocument: ['']
    });
  }

  addSRAProgram(): void {
    this.sraPrograms.push(this.createSRAFormGroup());
  }

  removeSRAProgram(index: number): void {
    this.sraPrograms.removeAt(index);
  } 

  ngOnInit() {
    this.form.valueChanges.subscribe(() => {
      // this.academicFormDataService.setBasicInfoData(this.form.value);
    });
  }
}
