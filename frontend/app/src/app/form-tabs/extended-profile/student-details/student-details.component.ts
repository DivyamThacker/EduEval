import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, ReactiveFormsModule, FormsModule , FormControl} from '@angular/forms';

@Component({
  selector: 'app-student-details',
  standalone: true,
  imports: [CommonModule,ReactiveFormsModule, FormsModule],
  templateUrl: './student-details.component.html',
  styleUrl: './student-details.component.css'
})
export class StudentDetailsComponent implements OnInit{

 startYear: number = 2021;
  years: number[] = [];
  form: FormGroup;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      section1: this.fb.array([] as FormControl[]),
      section2: this.fb.array([] as FormControl[]),
      section3: this.fb.array([] as FormControl[]),
      section4: this.fb.array([] as FormControl[]),
    });
  }

  ngOnInit() {
    this.generateYears();
  }

  generateYears() {
    this.years = [];

    // Clear previous controls for each section and generate new ones
    ['section1', 'section2', 'section3', 'section4'].forEach(section => {
      const sectionArray = this.form.get(section) as FormArray;
      sectionArray.clear();

      for (let i = 0; i < 5; i++) {
        const year = this.startYear - i;
        if (!this.years.includes(year)) {
          this.years.push(year);
        }
        sectionArray.push(this.fb.control('') as FormControl);
      }
    });
  }

  onFileSelected(event: Event, sectionId: string) {
    const fileInput = event.target as HTMLInputElement;
    if (fileInput.files && fileInput.files.length > 0) {
      const file = fileInput.files[0];
      console.log(`Selected file for ${sectionId}:`, file);
      // Process the file for the respective section
      // You can now process the file as needed based on sectionId
    }
  }

  // Accessor methods for each section's FormArray
  get section1(): FormArray<FormControl> {
    return this.form.get('section1') as FormArray<FormControl>;
  }
  get section2(): FormArray<FormControl> {
    return this.form.get('section2') as FormArray<FormControl>;
  }
  get section3(): FormArray<FormControl> {
    return this.form.get('section3') as FormArray<FormControl>;
  }
  get section4(): FormArray<FormControl> {
    return this.form.get('section4') as FormArray<FormControl>;
  }
}