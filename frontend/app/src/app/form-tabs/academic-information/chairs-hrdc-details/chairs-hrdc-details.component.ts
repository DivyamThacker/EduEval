import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { ChairsHrdcDataService } from '../../../services/academic-information-form/chairs-hrdc-data-service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-chairs-hrdc-details',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './chairs-hrdc-details.component.html',
  styleUrl: './chairs-hrdc-details.component.css'
})
export class ChairsHrdcDetailsComponent implements OnInit {

  chairForm : FormGroup;

  devlopmentCentreForm: FormGroup;
  
  constructor(private fb: FormBuilder, private chairsHrdcDataService: ChairsHrdcDataService) {
    this.chairForm = this.fb.group({
      chairs : this.fb.array([]),
    });
    this.addChair();

    this.devlopmentCentreForm = this.fb.group({
      hrdcEstablishmentDate : [''],
      hrdcOrientationProgramCount : [''],
      hrdcRefresherCourseCount : [''],
      hrdcOwnProgramCount : [''],
      hrdctotalProgramCount : ['']
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
    this.chairForm.valueChanges.subscribe(() => {
      this.chairsHrdcDataService.setChairsInfoData(this.chairForm.value);
    });
    this.devlopmentCentreForm.valueChanges.subscribe(() => {
      this.chairsHrdcDataService.setHrdcInfoData(this.devlopmentCentreForm.value);
    });
  }
}

