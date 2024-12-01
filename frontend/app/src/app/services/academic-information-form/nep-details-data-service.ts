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
private collegeInfoId: number | null = null;

private collegeInfoModelSource = new BehaviorSubject<any>({});
collegeInfoModel$ = this.collegeInfoModelSource.asObservable();

constructor(private http: HttpClient, private basicFormDataService : BasicFormDataService){}

// Method to fetch the universityId dynamically
getUniversityId(): number | null {
  return this.basicFormDataService.getUniversityId();
}

// College Info ID and model management
setCollegeInfoId(id: number | null) {
  this.collegeInfoId = id;
}

getCollegeInfoId(): number | null {
  return this.collegeInfoId;
}

setCollegeInfoData(data: any) {
  this.collegeInfoModelSource.next(data);
}

getCollegeInfoData(): Observable<any> {
  return this.collegeInfoModel$;
}

// College Info API submission
submitCollegeInfo() {
  const data = this.collegeInfoModelSource.value;
  this.universityId = this.getUniversityId();
  console.log('University Id:', this.universityId);
  console.log('College Info Id:', this.collegeInfoId);
  if (this.collegeInfoId != null) {
    return this.http.put(`${this.apiUrl}/university/${this.universityId}/college-info/${this.collegeInfoId}`, data)
      .pipe(
        catchError(error => throwError(() => error))
      );
  } else {
    return this.http.post(`${this.apiUrl}/university/${this.universityId}/college-info`, data)
      .pipe(
        tap((response: any) => {
          this.collegeInfoId = response.id;
          this.setCollegeInfoId(response.id);
        }),
        catchError(error => throwError(() => error))
      );
  }
}

submitNepDetails() {
}

}