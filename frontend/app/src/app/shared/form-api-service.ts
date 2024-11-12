import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class FormDataService {
  areUnsavedChanges = false;
  apiUrl = environment.apiUrl;

  private universityId: number | null = null;
  private contactId: number | null = null;
  private campusId: number | null = null;

  private contactModelSource = new BehaviorSubject<any>({});
  contactModel$ = this.contactModelSource.asObservable();

  private campusModelSource = new BehaviorSubject<any>({});
  campusModel$ = this.campusModelSource.asObservable();

  private basicInfoModelSource = new BehaviorSubject<any>({});
  basicInfoModel$ = this.basicInfoModelSource.asObservable();

  private academicInfoModelSource = new BehaviorSubject<any>({});
  academicInfoModel$ = this.academicInfoModelSource.asObservable();
  
  constructor(private http: HttpClient) {}
  
  setUnsavedChanges(value: boolean) {
    this.areUnsavedChanges = value;
  }

  getUnsavedChanges(): boolean {
    return this.areUnsavedChanges;
  }

  // University ID management
  setUniversityId(id: number) {
    this.universityId = id;
  }

  getUniversityId(): number | null {
    return this.universityId;
  }
  
  // Contact ID and model management
  setContactId(id: number | null) {
    this.contactId = id;
  }
  getContactId(): number | null {
    return this.contactId;
  }
  setContactData(data: any) {
    this.contactModelSource.next(data);
  }
  getContactData(): Observable<any> {
    return this.contactModel$;
  }
  
  // Campus ID and model management
  setCampusId(id: number | null) {
    this.campusId = id;
  }
  getCampusId(): number | null {
    return this.campusId;
  }
  setCampusData(data: any) {
    this.campusModelSource.next(data);
  }
  getCampusData(): Observable<any> {
    return this.campusModel$;
  }
  
  setBasicInfoData(data: any) {
    this.basicInfoModelSource.next({ ...this.basicInfoModelSource.value, ...data });
  }

  setAcademicInfoData(data: any) {
    this.academicInfoModelSource.next({ ...this.academicInfoModelSource.value, ...data });
  }

  // Basic Info API submission
  submitBasicInfo() {
    const data = this.basicInfoModelSource.value;
    if (this.universityId) {
      return this.http.put(`${this.apiUrl}/university/${this.universityId}/basic-info`, data)
        .pipe(catchError(error => throwError(() => error)));
    } else {
      return this.http.post(`${this.apiUrl}/university/basic-info`, data)
        .pipe(
          tap((response: any) => this.setUniversityId(response.id)),
          catchError(error => throwError(() => error))
        );
    }
  }

  // Contact Details API submission
  submitContactDetails() {
    const data = this.contactModelSource.value;
     this.http.delete(`${this.apiUrl}/university/${this.universityId}/contact-details`)
    .subscribe({
      next: response => console.log(`Contact Details for this university id : ${this.universityId} deleted successfully`, response),
      error: error => console.error('Error deleting Contact Details', error)
    });
      return this.http.post(`${this.apiUrl}/university/${this.universityId}/contact-details`, data.contacts)
        .pipe(
          catchError(error => throwError(() => error))
        );
  }

  // Campus Details API submission
  submitCampusDetails() {
    const data = this.campusModelSource.value;
    console.log("this is university id", this.universityId);
    console.log("this is campus id", this.campusId);
    if (this.campusId) {
      return this.http.put(`${this.apiUrl}/university/${this.universityId}/campus/${this.campusId}`, data)
        .pipe(catchError(error => throwError(() => error)));
    } else {
      return this.http.post(`${this.apiUrl}/university/${this.universityId}/campus`, data)
        .pipe(
          tap((response: any) => this.setCampusId(response.id)),
          catchError(error => throwError(() => error))
        );
    }
  }
}
