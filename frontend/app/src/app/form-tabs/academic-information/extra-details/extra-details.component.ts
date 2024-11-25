import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-extra-details',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './extra-details.component.html',
  styleUrl: './extra-details.component.css'
})
export class ExtraDetailsComponent implements OnInit {

  chairForm : FormGroup;

  devlopmentCentreForm: FormGroup;
  
  constructor(private fb: FormBuilder) {
    this.chairForm = this.fb.group({
      chairs : this.fb.array([]),
    });
    this.addChair();

    this.devlopmentCentreForm = this.fb.group({
      establishmentDate : [''],
      numOrientationProgrammes : [''],
      numRefresherCourses : [''],
      numOwnProgrammes : [''],
      totalProgrammes : ['']
    });
  }

  get chairs(): FormArray {
    return this.chairForm.get('chairs') as FormArray;
  }

  addChair(): void {
    const chairGroup = this.fb.group({
      departmentName: [''],
      name: [''],
      sponsorName: ['']
    });
    this.chairs.push(chairGroup);
  }

  removeChair(index: number): void {
    this.chairs.removeAt(index);
  } 

  ngOnInit() {
    this.devlopmentCentreForm.valueChanges.subscribe(() => {
      // this.academicFormDataService.setBasicInfoData(this.form.value);
    });
    this.chairForm.valueChanges.subscribe(() => {
      // this.academicFormDataService.setBasicInfoData(this.form.value);
    });
  }
}
