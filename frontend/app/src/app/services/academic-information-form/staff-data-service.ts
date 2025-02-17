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

private staffInfoModelSource = new BehaviorSubject<any>({});
staffInfoModel$ = this.staffInfoModelSource.asObservable();

constructor(private http: HttpClient, private basicFormDataService : BasicFormDataService){}

// Method to fetch the universityId dynamically
getUniversityId(): number | null {
  return this.basicFormDataService.getUniversityId();
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
  this.http.delete(`${this.apiUrl}/university/${this.universityId}/staff`)
  .subscribe({
    next: response => console.log(`Staff Details for this university id : ${this.universityId} deleted successfully`, response),
    error: error => console.error('Error deleting Staff Details', error)
  });

  console.log('University Id:', this.universityId);
  console.log('Staff Info Data:', data.staff);

  return this.http.post(`${this.apiUrl}/university/${this.universityId}/staff`, data.staff)
    .pipe(
      catchError(error => throwError(() => error))
    );
}
}
