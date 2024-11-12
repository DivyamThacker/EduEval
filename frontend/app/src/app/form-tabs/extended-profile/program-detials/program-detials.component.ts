import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, ReactiveFormsModule, FormsModule , FormControl} from '@angular/forms';

@Component({
  selector: 'app-program-detials',
  standalone: true,
  imports: [CommonModule,ReactiveFormsModule, FormsModule],
  templateUrl: './program-detials.component.html',
  styleUrl: './program-detials.component.css'
})

export class ProgramDetialsComponent implements OnInit {
  startYear: number = 2021;
  years: number[] = [];
  form: FormGroup;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      programs: this.fb.array([] as FormControl[]),
      departmentsOfferingPrograms: this.fb.control(''),
    });
  }

  ngOnInit() {
    this.generateYears();
  }

  generateYears() {
    this.years = [];
    const programsArray = this.form.get('programs') as FormArray;
    programsArray.clear(); // Clear previous form controls

    for (let i = 0; i < 5; i++) {
      const year = this.startYear - i;
      this.years.push(year);

      programsArray.push(this.fb.control('') as FormControl);
    }
  }

  get programs(): FormArray<FormControl> {
    return this.form.get('programs') as FormArray<FormControl>;
  }

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (!input.files?.length) {
      return;
    }
  
    const file = input.files[0];
    const reader = new FileReader();
  
    reader.onload = (e) => {
      const content = e.target?.result as string;
      this.parseFileContent(content);
    };
  
    reader.readAsText(file);
  }
  
  parseFileContent(content: string) {
    const lines = content.split('\n');
    const programsArray = this.form.get('programs') as FormArray;
    programsArray.clear();
  
    lines.forEach(line => {
      programsArray.push(this.fb.control(line.trim()) as FormControl);
    });
  }
}
