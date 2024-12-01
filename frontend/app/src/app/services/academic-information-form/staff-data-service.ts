import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { BasicFormDataService } from '../basic-form-data-service';

@Injectable({
  providedIn: 'root',
})
export class StaffDataService {
apiUrl = environment.apiUrl;

private universityId: number | null = null;
private staffInfoId: number | null = null;

private staffInfoModelSource = new BehaviorSubject<any>({});
staffInfoModel$ = this.staffInfoModelSource.asObservable();

constructor(private http: HttpClient, private basicFormDataService : BasicFormDataService){}

// Method to fetch the universityId dynamically
getUniversityId(): number | null {
  return this.basicFormDataService.getUniversityId();
}
// Staff Info ID and model management
setStaffInfoId(id: number | null) {
  this.staffInfoId = id;
}

getStaffInfoId(): number | null {
  return this.staffInfoId;
}

setStaffInfoData(data: any) {
  this.staffInfoModelSource.next(data);
}

getStaffInfoData(): Observable<any> {
  return this.staffInfoModel$;
}

submitStaffInfoData() {
  const data = this.staffInfoModelSource.value;
  this.universityId = this.getUniversityId();
  console.log('University Id:', this.universityId);
  console.log('Staff Info Id:', this.staffInfoId);
  console.log('Staff Info Data:', data.staff);
  if (this.staffInfoId != null) {
    return this.http.put(`${this.apiUrl}/university/${this.universityId}/staff/${this.staffInfoId}`, data.staff)
      .pipe(
        catchError(error => throwError(() => error))
      );
  } else {
    return this.http.post(`${this.apiUrl}/university/${this.universityId}/staff`, data.staff)
      .pipe(
        tap((response: any) => {
          this.staffInfoId = response.id;
          this.setStaffInfoId(response.id);
        }),
        catchError(error => throwError(() => error))
      );
  }
}
}