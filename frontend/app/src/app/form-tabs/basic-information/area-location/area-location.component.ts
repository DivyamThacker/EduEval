import { NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormDataService } from '../../../shared/form-api-service';

@Component({
  selector: 'app-area-location',
  standalone: true,
  imports: [ReactiveFormsModule, NgFor],
  templateUrl: './area-location.component.html',
  styleUrl: './area-location.component.css'
})
export class AreaLocationComponent implements OnInit{
  form: FormGroup;

  constructor( private formDataService : FormDataService, private fb: FormBuilder) {
    // Initialize the form with a FormArray
    this.form = this.fb.group({
      campuses: this.fb.array([]),//campus locations
    });
    this.addCampus(); // Add the first contact form on component initialization
  }

  get campuses(): FormArray {
    return this.form.get('campuses') as FormArray;
  }

  addCampus(): void {
    const campusGroup = this.fb.group({
      type: [''],
      location: [''],
      campusArea: [''],
      address: [''],
      builtInArea: [''],
      programmesOffered: [''],
      establishmentDate: [''],
      recognitionDate: ['']
    });
    // const campusGroup = this.fb.group({
    //   type: ['', Validators.required],
    //   location: ['', Validators.required],
    //   campusArea: ['', Validators.pattern(/^\d{5}$/)],
    //   address: ['', Validators.required],
    //   builtInArea: ['', Validators.pattern(/^\d{5}$/)],
    //   programmesOffered: ['', Validators.required],
    //   dateOfEstablishment: ['', [Validators.required, Validators.pattern(/^\d{2} \d{2} \d{4}$/)]],
    //   dateOfRecognition: ['', [Validators.required, Validators.pattern(/^\d{2} \d{2} \d{4}$/)]]
    // });
    this.campuses.push(campusGroup);
  }

  removeCampus(index: number): void {
    this.campuses.removeAt(index);
  }

  ngOnInit(): void {
    // Set initial data and watch for changes
    this.form.valueChanges.subscribe(() => {
      this.formDataService.setCampusData(this.form.value);
    });
  }

}
