import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { nepSections } from '../../../shared/constants/app.constants';

@Component({
  selector: 'app-nep-details',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './nep-details.component.html',
  styleUrl: './nep-details.component.css'
})
export class NepDetailsComponent implements OnInit {
  form!: FormGroup;
  nepSections: string[] = nepSections;

  constructor(private fb: FormBuilder) {}

  // Initialize form
  initForm(): void {
    this.form = this.fb.group({
      sections: this.fb.array(this.nepSections.map(() => this.createsectionGroup())),
    });
  }

  createsectionGroup(): FormGroup {
    return this.fb.group({
      file: [null],
    });
  }

  // Get form array
  get sections(): FormArray {
    return this.form.get('sections') as FormArray;
  }

  // Handle file change
  onFileChange(event: any, index: number): void {
    const file = event.target.files[0];
    if (file) {
      const sectionGroup = this.sections.at(index);
      sectionGroup.patchValue({ file });
    }
  }

  ngOnInit() {
    this.initForm();
    this.form.valueChanges.subscribe(() => {
      // this.academicFormDataService.setBasicInfoData(this.form.value);
    });
  }
}
