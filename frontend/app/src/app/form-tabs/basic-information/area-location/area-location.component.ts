import { NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { BasicFormDataService } from '../../../services/basic-form-data-service';
import { CanComponentDeactivate } from '../../../guards/can-deactivate.guard';

@Component({
  selector: 'app-area-location',
  standalone: true,
  imports: [ReactiveFormsModule, NgFor],
  templateUrl: './area-location.component.html',
  styleUrl: './area-location.component.css'
})
export class AreaLocationComponent implements OnInit, CanComponentDeactivate {
  form: FormGroup;

  constructor( private basicFormDataService : BasicFormDataService, private fb: FormBuilder) {
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

  canDeactivate(): boolean {
    if (this.basicFormDataService.getUnsavedChanges()) {
      const confirmExit = confirm('You have unsaved changes. Do you really want to exit?');
      if (confirmExit) {
        this.basicFormDataService.setUnsavedChanges(false);
      }
      return confirmExit;
    }
    return true;
  }

  ngOnInit(): void {
    this.form.valueChanges.subscribe(() => {
      this.basicFormDataService.setCampusData(this.form.value);
      this.basicFormDataService.setUnsavedChanges(true);
    });
  }

}
