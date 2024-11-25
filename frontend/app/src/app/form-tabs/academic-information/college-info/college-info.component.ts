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
  
  constructor(private fb: FormBuilder, private collegeInfoDataService: CollegeInfoDataService) {
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

    this.sraForm = this.fb.group({
      areSraProgram: [false],
      sraPrograms: this.fb.array([])
    });

    this.sraForm.get('areSraProgram')?.valueChanges.subscribe((areSraProgram: boolean) => {
      if (areSraProgram) {
        this.sraForm.setControl('sraPrograms', this.fb.array([this.createSraFormGroup()]));
      } else {
        this.sraForm.setControl('sraPrograms', this.fb.array([]));
      }
    });
  }

  get sraPrograms(): FormArray {
    return this.sraForm.get('sraPrograms') as FormArray;
  }

  createSraFormGroup(): FormGroup {
    return this.fb.group({
      sraProgramName: [''],
      sraDocument: ['']
    });
  }

  addSraProgram(): void {
    this.sraPrograms.push(this.createSraFormGroup());
  }

  removeSraProgram(index: number): void {
    this.sraPrograms.removeAt(index);
  } 

  ngOnInit() {
    this.form.valueChanges.subscribe(() => {
      this.collegeInfoDataService.setCollegeInfoData(this.form.value);
    });
    this.sraForm.valueChanges.subscribe(() => {
      this.collegeInfoDataService.setSraProgramData(this.sraForm.value);
    });
  }
}
