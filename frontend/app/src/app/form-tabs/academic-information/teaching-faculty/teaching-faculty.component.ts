import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { TeachingFacultyDataService } from '../../../services/academic-information-form/teaching-faculty-data-service';

@Component({
  selector: 'app-teaching-faculty',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './teaching-faculty.component.html',
  styleUrl: './teaching-faculty.component.css'
})

export class TeachingFacultyComponent implements OnInit {
  facultyForm: FormGroup;

  academiciansForm : FormGroup;
  
  constructor(private fb: FormBuilder, private teachingFacultyDataService: TeachingFacultyDataService) {
    this.facultyForm = this.fb.group({
      faculties : this.fb.array([]),
    });
    this.addFaculty();

    this.academiciansForm = this.fb.group({
      academicians : this.fb.array([]),
    });
    this.addAcademician();
  }

  get faculties(): FormArray {
    return this.facultyForm.get('faculties') as FormArray;
  }

  get academicians(): FormArray {
    return this.academiciansForm.get('academicians') as FormArray;
  }

  addFaculty(): void {
    const facultyGroup = this.fb.group({
      academicRank: [''],
      recruitmentStatus: [''],
      gender: [''],
      highestQualification: [''],
      tenure: [''],
      count: ['']
    });
      // const facultyGroup = this.fb.group({
      //     academicRank: ['', Validators.required],
      //     recruitmentStatus: ['', Validators.required],
      //     gender: ['', Validators.required],
      //     highestQualification: ['', Validators.required],
      //     tenure: ['', Validators.required]
      // });

    this.faculties.push(facultyGroup);
  }

  addAcademician(): void {
    const academicianGroup = this.fb.group({
      type: [''],
      gender: [''],
      count: ['']
    });
    this.academicians.push(academicianGroup);
  }

  removeFaculty(index: number): void {
    this.faculties.removeAt(index);
  } 

  removeAcademician(index: number): void {
    this.academicians.removeAt(index);
  } 

  ngOnInit() {
    this.facultyForm.valueChanges.subscribe(() => {
      this.teachingFacultyDataService.setFacultyData(this.facultyForm.value);
    });
    this.academiciansForm.valueChanges.subscribe(() => {
      this.teachingFacultyDataService.setAcademicianData(this.academiciansForm.value);
    });
  }
}
