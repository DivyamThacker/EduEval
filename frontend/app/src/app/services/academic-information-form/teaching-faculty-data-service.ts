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

private facultyModelSource = new BehaviorSubject<any>({});
facultyModel$ = this.facultyModelSource.asObservable();

private academicianModelSource = new BehaviorSubject<any>({});
academicianModel$ = this.academicianModelSource.asObservable();

constructor(private http: HttpClient, private basicFormDataService : BasicFormDataService){}

// Method to fetch the universityId dynamically
getUniversityId(): number | null {
  return this.basicFormDataService.getUniversityId();
}

setFacultyData(data: any) {
  this.facultyModelSource.next(data);
}

getFacultyData(): Observable<any> {
  return this.facultyModel$;
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
  this.http.delete(`${this.apiUrl}/university/${this.universityId}/faculty`)
  .subscribe({
    next: response => console.log(`Faculty Details for this university id : ${this.universityId} deleted successfully`, response),
    error: error => console.error('Error deleting Faculty Details', error)
  });
  console.log('University Id:', this.universityId);
  console.log('This is the data:', data.faculties);
  return this.http.post(`${this.apiUrl}/university/${this.universityId}/faculty`, data.faculties)
    .pipe(
      catchError(error => throwError(() => error))
    );
}

submitAcademicianDetails(){
  const data = this.academicianModelSource.value;
  this.universityId = this.getUniversityId();
  this.http.delete(`${this.apiUrl}/university/${this.universityId}/academician`)
  .subscribe({
    next: response => console.log(`Academician Details for this university id : ${this.universityId} deleted successfully`, response),
    error: error => console.error('Error deleting Academician Details', error)
  });
  console.log('University Id:', this.universityId);
  return this.http.post(`${this.apiUrl}/university/${this.universityId}/academician`, data.academicians)
    .pipe(
      catchError(error => throwError(() => error))
    );
}
}