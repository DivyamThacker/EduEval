import { NgFor } from '@angular/common';
import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule , Validators} from '@angular/forms';

@Component({
  selector: 'app-contact-details',
  standalone: true,
  imports: [ReactiveFormsModule, NgFor],
  templateUrl: './contact-details.component.html',
  styleUrl: './contact-details.component.css'
})
export class ContactDetailsComponent{
  form: FormGroup;

  constructor(private fb: FormBuilder) {
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
}