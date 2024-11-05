import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class FormDataService {
  private universityIdSource = new BehaviorSubject<number | null>(null);
  universityId$ = this.universityIdSource.asObservable();

  // Set the universityId after the initial POST
  setUniversityId(id: number) {
    this.universityIdSource.next(id);
  }

  getUniversityId(): number | null {
    return this.universityIdSource.value;
  }
  apiUrl = environment.apiUrl;

  universityId : number = 0;
  private basicInfoModelSource = new BehaviorSubject<any>({});
  basicInfoModel$ = this.basicInfoModelSource.asObservable();

  private academicInfoModelSource = new BehaviorSubject<any>({});
  academicInfoModel$ = this.academicInfoModelSource.asObservable();

  constructor(private http: HttpClient) {}

  setBasicInfoData(data: any) {
    this.basicInfoModelSource.next({ ...this.basicInfoModelSource.value, ...data });
  }

  setAcademicInfoData(data: any) {
    this.academicInfoModelSource.next({ ...this.academicInfoModelSource.value, ...data });
  }


submitBasicInfo() {
  if (this.universityId != 0) {
    return this.http.put(`${this.apiUrl}/naac/basic-info/${this.universityId}`, this.basicInfoModelSource.value)
      .pipe(
        catchError((error) => {
          console.error('Error in PUT request:', error);
          return throwError(() => error);  // Updated usage of throwError
        })
      );
  } else {
    return this.http.post(this.apiUrl + '/naac/basic-info', this.basicInfoModelSource.value).pipe(
      tap((response: any) => {
        this.universityId = response.id;
      }),
      catchError((error) => {
        console.error('Error in POST request:', error);
        return throwError(() => error);  // Updated usage of throwError
      })
    );
  }
}

  // POST or PUT for Contact section based on universityId
  submitContactDetails(data: any, contactId?: number): Observable<any> {
    const universityId = this.getUniversityId();
    if (universityId) {
      if (contactId) {
        // Update existing contact
        return this.http.put(`${this.apiUrl}/university/${universityId}/contacts/${contactId}`, data)
          .pipe(catchError(error => throwError(() => error)));
      } else {
        // Add new contact
        return this.http.post(`${this.apiUrl}/university/${universityId}/contacts`, data)
          .pipe(catchError(error => throwError(() => error)));
      }
    } else {
      return throwError(() => 'University ID not set.');
    }
  }

  // POST or PUT for Campus section based on universityId
  submitCampusDetails(data: any, campusId?: number): Observable<any> {
    const universityId = this.getUniversityId();
    if (universityId) {
      if (campusId) {
        // Update existing campus
        return this.http.put(`${this.apiUrl}/university/${universityId}/campuses/${campusId}`, data)
          .pipe(catchError(error => throwError(() => error)));
      } else {
        // Add new campus
        return this.http.post(`${this.apiUrl}/university/${universityId}/campuses`, data)
          .pipe(catchError(error => throwError(() => error)));
      }
    } else {
      return throwError(() => 'University ID not set.');
    }
  }

  // POST or PUT for Basic Info + Recognition Details
  submitBasicInformation(data: any): Observable<any> {
    const universityId = this.getUniversityId();
    if (universityId) {
      return this.http.put(`${this.apiUrl}/university/${universityId}`, data)
        .pipe(catchError(error => throwError(() => error)));
    } else {
      return this.http.post(`${this.apiUrl}/university`, data).pipe(
        tap((response: any) => this.setUniversityId(response.id)),
        catchError(error => throwError(() => error))
      );
    }
  }
  

  submitAcademicInfo() {
    return this.http.post(this.apiUrl + '/naac/academic-information', this.academicInfoModelSource.value);
  }
}
