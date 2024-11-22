import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AcademicFormDataService {
  areUnsavedChanges = false;
  apiUrl = environment.apiUrl;

  private universityId: number | null = null;

  private academicInfoModelSource = new BehaviorSubject<any>({});
  academicInfoModel$ = this.academicInfoModelSource.asObservable();

  constructor(private http: HttpClient) {}
  
  setUnsavedChanges(value: boolean) {
    this.areUnsavedChanges = value;
  }

  getUnsavedChanges(): boolean {
    return this.areUnsavedChanges;
  }

  // University ID management
  setUniversityId(id: number) {
    this.universityId = id;
  }

  getUniversityId(): number | null {
    return this.universityId;
  }
  
  setAcademicInfoData(data: any) {
    this.academicInfoModelSource.next({ ...this.academicInfoModelSource.value, ...data });
  }

  // Academic Info API submission
  submitAcademicInfo() {}
}