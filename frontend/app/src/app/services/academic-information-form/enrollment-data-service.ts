import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { BasicFormDataService } from '../basic-form-data-service';

@Injectable({
  providedIn: 'root',
})
export class EnrollmentDataService {
apiUrl = environment.apiUrl;

private universityId: number | null = null;
private enrollmentId: number | null = null;

private enrollmentModelSource = new BehaviorSubject<any>({
   hasIntegrated: false , enrollments: [] , totalIntegratedPrograms: '' 
});
// private enrollmentModelSource = new BehaviorSubject<any>({});
enrollmentModel$ = this.enrollmentModelSource.asObservable();

constructor(private http: HttpClient, private basicFormDataService : BasicFormDataService){}

// Method to fetch the universityId dynamically
getUniversityId(): number | null {
  return this.basicFormDataService.getUniversityId();
}

// Enrollment Info ID and model management
setEnrollmentId(id: number | null) {
  this.enrollmentId = id;
}

getEnrollmentId(): number | null {
  return this.enrollmentId;
}

setEnrollmentData(data: any) {
  this.enrollmentModelSource.next(data);
}

getEnrollmentData(): Observable<any> {
  return this.enrollmentModel$;
}

// Enrollment Info API submission
submitEnrollmentData() {
  this.universityId = this.getUniversityId();
  const formData = new FormData();
  const data = this.enrollmentModelSource.value;

  console.log('University Id:', this.universityId);
  console.log('Enrollment Id:', this.enrollmentId);
  console.log('Data:', data);

  formData.append('enrollments', JSON.stringify(data.enrollments));
  formData.append('hasIntegrated', JSON.stringify(data.hasIntegrated));
  formData.append('totalIntegratedPrograms', JSON.stringify(data.totalIntegratedPrograms));

  if (this.enrollmentId != null) {
    return this.http.put(`${this.apiUrl}/university/${this.universityId}/enrollment/${this.enrollmentId}`, formData)
      .pipe(
        catchError(error => throwError(() => error))
      );
  } else {
    return this.http.post(`${this.apiUrl}/university/${this.universityId}/enrollment`, formData)
      .pipe(
        tap((response: any) => {
          this.enrollmentId = response.id;
          this.setEnrollmentId(response.id);
        }),
        catchError(error => throwError(() => error))
      );
  }

}
}