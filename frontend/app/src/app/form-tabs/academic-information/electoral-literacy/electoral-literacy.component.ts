import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AcademicFormDataService } from '../../../services/academic-form-data-service';
import { electoralLiteracyQuestions } from '../../../shared/constants/app.constants';

@Component({
  selector: 'app-electoral-literacy',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './electoral-literacy.component.html',
  styleUrl: './electoral-literacy.component.css'
})
export class ElectoralLiteracyComponent implements OnInit {
  form!: FormGroup;
  electoralLiteracyQuestions = electoralLiteracyQuestions;

  constructor(private fb: FormBuilder) {}

  // Initialize form
  initForm(): void {
    this.form = this.fb.group({
      sections: this.fb.array(this.electoralLiteracyQuestions.map(() => this.createsectionGroup())),
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
