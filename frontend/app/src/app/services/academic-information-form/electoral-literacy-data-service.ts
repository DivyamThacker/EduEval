import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { BasicFormDataService } from '../basic-form-data-service';

@Injectable({
  providedIn: 'root',
})
export class ElectoralLiteracyDataService {
apiUrl = environment.apiUrl;

private universityId: number | null = null;

private electoralLiteracyDetailsModelSource = new BehaviorSubject<any>({
  files: [] // Initialize as an array
});
electoralLiteracyDetailsModel$ = this.electoralLiteracyDetailsModelSource.asObservable();


constructor(private http: HttpClient, private basicFormDataService : BasicFormDataService){}

// Method to fetch the universityId dynamically
getUniversityId(): number | null {
  return this.basicFormDataService.getUniversityId();
}

setElectoralLiteracyDetailsData(data: any) {
  this.electoralLiteracyDetailsModelSource.next(data);
}

getElectoralLiteracyDetailsData(): Observable<any> {
  return this.electoralLiteracyDetailsModel$;
}

submitElectoralLiteracyDetails(): Observable<any> {
  this.universityId = this.getUniversityId();
  const formData = new FormData();
  const data = this.electoralLiteracyDetailsModelSource.value;

  data.files.forEach((file: File) => {
    formData.append('files', file);
  });

  return this.http.post(`${this.apiUrl}/university/${this.universityId}/electoral-literacy-details`, formData)
    .pipe(
      tap(response => console.log('Electoral Literacy Details created:', response)),
      catchError(error => {
        console.error('Error creating Electoral Literacy Details:', error);
        return throwError(error);
      })
    );
}
}