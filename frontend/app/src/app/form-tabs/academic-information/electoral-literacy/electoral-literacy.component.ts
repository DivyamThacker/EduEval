import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { electoralLiteracyQuestions } from '../../../shared/constants/app.constants';
import { ElectoralLiteracyDataService } from '../../../services/academic-information-form/electoral-literacy-data-service';

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
  files: { [key: number]: File } = {}; 

  constructor(private fb: FormBuilder, private electoralLiteracyDataService : ElectoralLiteracyDataService) {}

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

  // Update the Electoral Literacy Details in the service
  private updateElectoralLiteracyDetails(): void {
    this.electoralLiteracyDataService.setElectoralLiteracyDetailsData({
      files: Object.values(this.files), // Convert file object to array
    });
  }

  // Handle file change
  onFileChange(event: any, index: number): void {
    const file = event.target.files[0];
    this.files[index] = file; // Store file by program index

    this.updateElectoralLiteracyDetails();
  }

  ngOnInit() {
    this.initForm();
    this.form.valueChanges.subscribe(() => {
      this.updateElectoralLiteracyDetails();
    });
  }
}
