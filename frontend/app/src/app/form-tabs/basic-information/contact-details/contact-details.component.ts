import { NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule , Validators} from '@angular/forms';
import { BasicFormDataService } from '../../../services/basic-form-data-service';
import { CanComponentDeactivate } from '../../../guards/can-deactivate.guard';

@Component({
  selector: 'app-contact-details',
  standalone: true,
  imports: [ReactiveFormsModule, NgFor],
  templateUrl: './contact-details.component.html',
  styleUrl: './contact-details.component.css'
})
export class ContactDetailsComponent implements OnInit , CanComponentDeactivate {
  form: FormGroup;

  constructor(private fb: FormBuilder, private basicFormDataService: BasicFormDataService) {
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
      designation: [''],
      name: [''],
      email: [''],
      telephone: [''],
      phone: [''],
      fax: [''],
    });
    this.contacts.push(contactGroup);
    // const contactGroup = this.fb.group({
    //   designation: ['', Validators.required],
    //   name: ['', Validators.required],
    //   email: ['', [Validators.required, Validators.email]],
    //   telephone: ['', Validators.pattern(/^\d{10}$/)],
    //   phone: ['', Validators.pattern(/^\d{10}$/)],
    //   fax: ['', Validators.pattern(/^\d{10}$/)],
    // });
    // this.contacts.push(contactGroup);
  }
  
  removeContact(index: number): void {
    this.contacts.removeAt(index);
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
      this.basicFormDataService.setContactData(this.form.value);
      this.basicFormDataService.setUnsavedChanges(true);
    });
  }
}