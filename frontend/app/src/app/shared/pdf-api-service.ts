import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class PdfApiService{
    apiUrl = environment.apiUrl;

    constructor(private http : HttpClient) { }
    generatePdf(){ //only generates ssr on backend
        // Generate PDF
        this.http.get(`${this.apiUrl}/generate`, { responseType: 'json' }).pipe(
            tap((response: any) => {
            console.log('PDF generated successfully', response);
            }),
            catchError((error) => {
            console.error('Error generating PDF', error);
            return throwError(error);
            })
        ).subscribe();
    }

    getPdf(): Observable<Blob> {
        return this.http.get(`${this.apiUrl}/generate-ssr`, { responseType: 'blob' });
    }
}