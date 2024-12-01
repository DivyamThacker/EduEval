import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { BasicFormDataService } from '../basic-form-data-service';

@Injectable({
  providedIn: 'root',
})
export class NepDetailsDataService {
apiUrl = environment.apiUrl;

private universityId: number | null = null;
private nepDetailsId: number | null = null;

private nepDetailsModelSource = new BehaviorSubject<any>({
  files: [] // Initialize as an array
});
nepDetailsModel$ = this.nepDetailsModelSource.asObservable();

constructor(private http: HttpClient, private basicFormDataService : BasicFormDataService){}

// Method to fetch the universityId dynamically
getUniversityId(): number | null {
  return this.basicFormDataService.getUniversityId();
}

// NEP Details ID and model management
setNepDetailsId(id: number | null) {
  this.nepDetailsId = id;
}

getNepDetailsId(): number | null {
  return this.nepDetailsId;
}

setNepDetailsData(data: any) {
  this.nepDetailsModelSource.next(data);
}

getNepDetailsData(): Observable<any> {
  return this.nepDetailsModel$;
}

submitNepDetails(): Observable<any> {
  this.universityId = this.getUniversityId();
  const formData = new FormData();
  const data = this.nepDetailsModelSource.value;

  data.files.forEach((file: File) => {
    formData.append('files', file);
  });

  if (this.nepDetailsId != null) {
    // Update (PUT) existing NEP Details
    return this.http.put(`${this.apiUrl}/university/${this.universityId}/nep-details/${this.nepDetailsId}`, formData)
      .pipe(
        tap(response => console.log('NEP Details updated:', response)),
        catchError(error => {
          console.error('Error updating NEP Details:', error);
          return throwError(error);
        })
      );
  } else {
    // Create (POST) new NEP Details
    return this.http.post(`${this.apiUrl}/university/${this.universityId}/nep-details`, formData)
      .pipe(
        tap(response => console.log('NEP Details created:', response)),
        catchError(error => {
          console.error('Error creating NEP Details:', error);
          return throwError(error);
        })
      );
  }
}
}