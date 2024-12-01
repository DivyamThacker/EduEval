import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { nepSections } from '../../../shared/constants/app.constants';
import { NepDetailsDataService } from '../../../services/academic-information-form/nep-details-data-service';

@Component({
  selector: 'app-nep-details',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './nep-details.component.html',
  styleUrl: './nep-details.component.css'
})
export class NepDetailsComponent implements OnInit {
  form!: FormGroup;
  files: { [key: number]: File } = {}; 
  nepSections: string[] = nepSections;

  constructor(private fb: FormBuilder, private nepDetailsDataService : NepDetailsDataService) {}

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

  // Update the NEP Details in the service
  private updateNepDetails(): void {
    this.nepDetailsDataService.setNepDetailsData({
      files: Object.values(this.files), // Convert file object to array
    });
  }

  // Handle file change
  onFileChange(event: any, index: number): void {
    const file = event.target.files[0];
    this.files[index] = file; // Store file by program index

    this.updateNepDetails();
  }


  ngOnInit() {
    this.initForm();
    this.form.valueChanges.subscribe(() => {
      this.updateNepDetails();
    });
  }
}
