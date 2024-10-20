import { Component } from '@angular/core';
import {HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-file-upload',
  standalone: true,
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css'],
})
export class FileUploadComponent {
  constructor(private http: HttpClient) {}
  
  selectedFile: File | null = null;
  message: string = "Hello World";
  
  sayHello() {
    this.http.get<any>('http://localhost:8080/hello').subscribe(
      response => {
        console.log(response);
        this.message = response.message;
      },
      error => {
        console.error('Error occurred:', error);
      }
    );
  }

  generatePdf() {
    this.http.get<any>('http://localhost:8080/convert').subscribe(
      response => {
        console.log(response);
        this.message = response.message;
      },
      error => {
        console.error('Error occurred:', error);
      }
    );
  }

  onFileSelected(event: any): void {
    const file: File = event.target.files[0];

    if (file) {
      const formData = new FormData();
      formData.append('file', file);

      // Send the file to the backend
      this.uploadFile(formData).subscribe((response) => {
        // Handle the response, for example, download the generated PDF
        const blob = new Blob([response], { type: 'application/pdf' });
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'output.pdf';
        a.click();
        window.URL.revokeObjectURL(url);
      });
    }
  }

  uploadFile(formData: FormData) {
    // Adjust this URL to your Spring Boot API endpoint
    return this.http.post('http://localhost:8080/convert-to-pdf', formData, { responseType: 'blob' });
  }
}