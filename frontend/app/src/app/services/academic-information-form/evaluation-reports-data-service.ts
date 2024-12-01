import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { BasicFormDataService } from '../basic-form-data-service';

@Injectable({
  providedIn: 'root',
})
export class EvaluationReportsDataService {
apiUrl = environment.apiUrl;

private universityId: number | null = null;
private departmentId: number | null = null;
private accreditationId: number | null = null;

private departmentModelSource = new BehaviorSubject<any>({
  formValue: { departments: [] },
  files: [] // Initialize as an array
});
departmentModel$ = this.departmentModelSource.asObservable();

private accreditationModelSource = new BehaviorSubject<any>({
  formValue: { accreditations: [] },
  files: [] // Initialize as an array
});
accreditationModel$ = this.accreditationModelSource.asObservable();

constructor(private http: HttpClient, private basicFormDataService : BasicFormDataService){}

// Method to fetch the universityId dynamically
getUniversityId(): number | null {
  return this.basicFormDataService.getUniversityId();
}


// Accreditation ID and model management
setAccreditationId(id: number | null) {
  this.accreditationId = id;
}

getAccreditationId(): number | null {
  return this.accreditationId;
}

setAccreditationData(data: any) {
  this.accreditationModelSource.next(data);
}

getAccreditationData(): Observable<any> {
  return this.accreditationModel$;
}

// Department ID and model management
setDepartmentId(id: number | null) {
  this.departmentId = id;
}

getDepartmentId(): number | null {
  return this.departmentId;
}

setDepartmentData(data: any) {
  this.departmentModelSource.next(data);
}

getDepartmentData(): Observable<any> {
  return this.departmentModel$;
}

submitAccredationDetails(): Observable<any> {
  this.universityId = this.getUniversityId();
  const formData = new FormData();
  const data = this.accreditationModelSource.value;

  // Convert accreditations to JSON
  formData.append('accreditations', JSON.stringify(data.formValue.accreditations));

  data.files.forEach((file: File) => {
    formData.append('files', file);
  });

  if (this.accreditationId != null) {
    // Update (PUT) existing Accreditation
    return this.http.put(`${this.apiUrl}/university/${this.universityId}/accreditation/${this.accreditationId}`, formData)
      .pipe(
        tap(response => console.log('Accreditation updated:', response)),
        catchError(error => {
          console.error('Error updating Accreditation:', error);
          return throwError(error);
        })
      );
  } else {
    // Create (POST) new Accreditation
    return this.http.post(`${this.apiUrl}/university/${this.universityId}/accreditation`, formData)
      .pipe(
        tap(response => console.log('Accreditation created:', response)),
        catchError(error => {
          console.error('Error creating Accreditation:', error);
          return throwError(error);
        })
      );
  }
}

submitEvaluationReports(): Observable<any> {
  this.universityId = this.getUniversityId();
  const formData = new FormData();
  const data = this.departmentModelSource.value;

  // Convert departments to JSON
  const departmentNames = data.formValue.departments.map((department: any) => department.departmentName);

  formData.append('departmentNames', JSON.stringify(departmentNames));

  data.files.forEach((file: File) => {
    formData.append('files', file);
  });

  if (this.departmentId != null) {
    // Update (PUT) existing Department
    return this.http.put(`${this.apiUrl}/university/${this.universityId}/department-evaluation/${this.departmentId}`, formData)
      .pipe(
        tap(response => console.log('Department updated:', response)),
        catchError(error => {
          console.error('Error updating Department:', error);
          return throwError(error);
        })
      );
  } else {
    // Create (POST) new Department
    return this.http.post(`${this.apiUrl}/university/${this.universityId}/department-evaluation`, formData)
      .pipe(
        tap(response => console.log('Department created:', response)),
        catchError(error => {
          console.error('Error creating Department:', error);
          return throwError(error);
        })
      );
  }
}

}