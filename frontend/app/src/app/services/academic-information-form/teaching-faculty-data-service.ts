import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { BasicFormDataService } from '../basic-form-data-service';

@Injectable({
  providedIn: 'root',
})
export class TeachingFacultyDataService {
apiUrl = environment.apiUrl;

private universityId: number | null = null;
private facultyId: number | null = null;
private academicianId: number | null = null;

private facultyModelSource = new BehaviorSubject<any>({});
facultyModel$ = this.facultyModelSource.asObservable();

private academicianModelSource = new BehaviorSubject<any>({});
academicianModel$ = this.academicianModelSource.asObservable();

constructor(private http: HttpClient, private basicFormDataService : BasicFormDataService){}

// Method to fetch the universityId dynamically
getUniversityId(): number | null {
  return this.basicFormDataService.getUniversityId();
}

// Faculty ID and model management
setFacultyId(id: number | null) {
  this.facultyId = id;
}

getFacultyId(): number | null {
  return this.facultyId;
}

setFacultyData(data: any) {
  this.facultyModelSource.next(data);
}

getFacultyData(): Observable<any> {
  return this.facultyModel$;
}

// Academician ID and model management
setAcademicianId(id: number | null) {
  this.academicianId = id;
}

getAcademicianId(): number | null {
  return this.academicianId;
}

setAcademicianData(data: any) {
  this.academicianModelSource.next(data);
}

getAcademicianData(): Observable<any> {
  return this.academicianModel$;
}

//change the logic here

submitFacultyDetails() {
  const data = this.facultyModelSource.value;
  this.universityId = this.getUniversityId();
  console.log('University Id:', this.universityId);
  console.log('faculty Id:', this.facultyId);
  console.log('This is the data:', data.faculties);
  if (this.facultyId != null) {
    return this.http.put(`${this.apiUrl}/university/${this.universityId}/faculty/${this.facultyId}`, data.faculties)
    .pipe(
        catchError(error => throwError(() => error))
      );
  } else {
    return this.http.post(`${this.apiUrl}/university/${this.universityId}/faculty`, data.faculties)
      .pipe(
        tap((response: any) => {
          this.facultyId = response.id;
          this.setFacultyId(response.id);
        }),
        catchError(error => throwError(() => error))
      );
  }
}

submitAcademicianDetails(){
  const data = this.academicianModelSource.value;
  this.universityId = this.getUniversityId();
  console.log('University Id:', this.universityId);
  console.log('academician Id:', this.academicianId);
  if (this.facultyId != null) {
    return this.http.put(`${this.apiUrl}/university/${this.universityId}/academician/${this.academicianId}`, data.academicians)
    .pipe(
        catchError(error => throwError(() => error))
      );
  } else {
    return this.http.post(`${this.apiUrl}/university/${this.universityId}/academician`, data.academicians)
      .pipe(
        tap((response: any) => {
          this.academicianId = response.id;
          this.setAcademicianId(response.id);
        }),
        catchError(error => throwError(() => error))
      );
  }
}
}