import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class BasicFormDataService {
  areUnsavedChanges = false;
  apiUrl = environment.apiUrl;

  private universityId: number | null = null;
  private contactId: number | null = null;
  private campusId: number | null = null;
  private recognitionDetailsId: number | null = null;

  private contactModelSource = new BehaviorSubject<any>({});
  contactModel$ = this.contactModelSource.asObservable();

  private campusModelSource = new BehaviorSubject<any>({});
  campusModel$ = this.campusModelSource.asObservable();

  private recognitionDetailsModelSource = new BehaviorSubject<any>({});
  recognitionDetailsModel$ = this.recognitionDetailsModelSource.asObservable();

  private basicInfoModelSource = new BehaviorSubject<any>({});
  basicInfoModel$ = this.basicInfoModelSource.asObservable();

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

  // Recognition Details ID and model management
  setRecognitionDetailsId(id: number | null) {
    this.recognitionDetailsId = id;
  }
  getRecognitionDetailsId(): number | null {
    return this.recognitionDetailsId;
  }
  setRecognitionDetailsData(data: any) {
    this.recognitionDetailsModelSource.next(data);
  }
  getRecognitionDetailsData(): Observable<any> {
    return this.recognitionDetailsModel$;
  }
  
  setBasicInfoData(data: any) {
    this.basicInfoModelSource.next({ ...this.basicInfoModelSource.value, ...data });
  }

  // Basic Info API submission
  submitBasicInfo() {
    const data = this.basicInfoModelSource.value;
    console.log("this is university id", this.universityId);
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
    // Split the comma-separated list of programmes offered into an array
    data.campuses.forEach((campus: any) => {
      campus.programmesOffered = campus.programmesOffered.split(',')
      .map((s: string) => s.trim())
      .filter((s: string) => s !== '');
    });
    this.http.delete(`${this.apiUrl}/university/${this.universityId}/campus`)
    .subscribe({
      next: response => console.log(`Campus Details for this university id : ${this.universityId} deleted successfully`, response),
      error: error => console.error('Error deleting Campus Details', error)
    });
      return this.http.post(`${this.apiUrl}/university/${this.universityId}/campus`, data.campuses)
        .pipe(
          catchError(error => throwError(() => error))
        );
  }

  // Recognition Details API submission
  submitRecognitionDetails(): Observable<any> {
    const data = this.recognitionDetailsModelSource.value;

    // Prepare FormData for file uploads
    const formData = new FormData();
    formData.append('recognitionDateUnderSection2f', data.recognitionDateUnderSection2f);
    formData.append('recognitionDateUnderSection12b', data.recognitionDateUnderSection12b);
    formData.append('isUPE', data.isUPE);

    if (data.recognitionDocument2f) {
      formData.append('recognitionDocument2f', data.recognitionDocument2f);
    }

    if (data.recognitionDocument12b) {
      formData.append('recognitionDocument12b', data.recognitionDocument12b);
    }

    // Submit the data
    if (this.recognitionDetailsId) {
      return this.http.put(
        `${this.apiUrl}/university/${this.universityId}/recognition-details/${this.recognitionDetailsId}`,
        formData
      ).pipe(
        catchError((error) => throwError(() => error))
      );
    } else {
      return this.http.post(
        `${this.apiUrl}/university/${this.universityId}/recognition-details`,
        formData
      ).pipe(
        tap((response: any) => this.setRecognitionDetailsId(response.id)),
        catchError((error) => throwError(() => error))
      );
    }
  }
}
