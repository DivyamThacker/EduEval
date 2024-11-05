import { NgFor } from '@angular/common';
import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule , Validators} from '@angular/forms';
import { FormDataService } from '../../../shared/form-api-service';

@Component({
  selector: 'app-contact-details',
  standalone: true,
  imports: [ReactiveFormsModule, NgFor],
  templateUrl: './contact-details.component.html',
  styleUrl: './contact-details.component.css'
})
export class ContactDetailsComponent{
  form: FormGroup;
  contactId: number | null = null; // Set this ID when editing an existing contact

  constructor(private fb: FormBuilder, private formDataService: FormDataService) {
    // Initialize the form with a FormArray
    this.form = this.fb.group({
      contacts: this.fb.array([]),
    });
    this.addContact(); // Add the first contact form on component initialization
  }

  get contacts(): FormArray {
    return this.form.get('contacts') as FormArray;
  }

  addContact(): void {
    const contactGroup = this.fb.group({
      designation: ['', Validators.required],
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telephone: ['', Validators.pattern(/^\d{10}$/)],
      mobile: ['', Validators.pattern(/^\d{10}$/)],
      fax: ['', Validators.pattern(/^\d{10}$/)],
    });
    this.contacts.push(contactGroup);
  }
  
  removeContact(index: number): void {
    this.contacts.removeAt(index);
  }

  onSubmit(): void {
    console.log(this.form.value);
  }

  onSaveContact(contactData: any) {
    this.formDataService.submitContactDetails(contactData, this.contactId ?? undefined).subscribe({
      next: response => console.log('Contact saved successfully', response),
      error: error => console.error('Error saving contact', error)
    });
  }
}