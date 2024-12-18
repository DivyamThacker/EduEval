import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { BasicFormDataService } from '../basic-form-data-service';

@Injectable({
  providedIn: 'root',
})
export class ChairsHrdcDataService {
apiUrl = environment.apiUrl;

private universityId: number | null = null;
private hrdcInfoId: number | null = null;

private chairsInfoModelSource = new BehaviorSubject<any>({});
chairsInfoModel$ = this.chairsInfoModelSource.asObservable();

private hrdcInfoModelSource = new BehaviorSubject<any>({});
hrdcInfoModel$ = this.hrdcInfoModelSource.asObservable();


constructor(private http: HttpClient, private basicFormDataService : BasicFormDataService){}

// Method to fetch the universityId dynamically
getUniversityId(): number | null {
  return this.basicFormDataService.getUniversityId();
}

setChairsInfoData(data: any) {
  this.chairsInfoModelSource.next(data);
}

getChairsInfoData(): Observable<any> {
  return this.chairsInfoModel$;
}

// HRDC Info ID and model management
setHrdcInfoId(id: number | null) {
  this.hrdcInfoId = id;
}

getHrdcInfoId(): number | null {
  return this.hrdcInfoId;
}

setHrdcInfoData(data: any) {
  this.hrdcInfoModelSource.next(data);
}

getHrdcInfoData(): Observable<any> {
  return this.hrdcInfoModel$;
}

// Chairs Info API submission
submitChairs() {
  const data = this.chairsInfoModelSource.value;
  this.universityId = this.getUniversityId();
  this.http.delete(`${this.apiUrl}/university/${this.universityId}/chair`)
  .subscribe({
    next: response => console.log(`Chair Details for this university id : ${this.universityId} deleted successfully`, response),
    error: error => console.error('Error deleting Chair Details', error)
  });
  console.log('University Id:', this.universityId);
  return this.http.post(`${this.apiUrl}/university/${this.universityId}/chair`, data.chairs)
    .pipe(
      catchError(error => throwError(() => error))
    );
}

// HRDC Info API submission
submitHrdc() {
  const data = this.hrdcInfoModelSource.value;
  this.universityId = this.getUniversityId();
  console.log('University Id:', this.universityId);
  console.log('HRDC Info Id:', this.hrdcInfoId);
  if (this.hrdcInfoId != null) {
    return this.http.put(`${this.apiUrl}/university/${this.universityId}/hrdc/${this.hrdcInfoId}`, data)
    .pipe(
      catchError(error => throwError(() => error))
    );
  } else {
    return this.http.post(`${this.apiUrl}/university/${this.universityId}/hrdc`, data)
    .pipe(
      tap((response: any) => {
        this.hrdcInfoId = response.id;
        this.setHrdcInfoId(response.id);
      }),
      catchError(error => throwError(() => error))
    );
  }
}

}