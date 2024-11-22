import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { PdfApiService } from '../../services/pdf-api-service';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-services',
  standalone: true,
  imports: [RouterModule, NgIf],
  templateUrl: './services.component.html',
  styleUrl: './services.component.css'
})
export class ServicesComponent {

  pdfUrl: SafeResourceUrl | undefined; // Make it SafeResourceUrl type

  constructor(private pdfApiService: PdfApiService, private sanitizer: DomSanitizer) { }


  onGenerate() {
    console.log('Generating PDF...');
    this.openPdfInNewTab();
  }

  displayPdf() {
    this.pdfApiService.getPdf().subscribe((pdfBlob: Blob) => {
      const pdfBlobUrl = URL.createObjectURL(pdfBlob); // Create Blob URL
      this.pdfUrl = this.sanitizer.bypassSecurityTrustResourceUrl(pdfBlobUrl); // Sanitize the Blob URL
    });
  }

  openPdfInNewTab() {
    this.pdfApiService.getPdf().subscribe((pdfBlob: Blob) => {
      const pdfBlobUrl = URL.createObjectURL(pdfBlob); // Create a Blob URL for the PDF
      window.open(pdfBlobUrl, '_blank'); // Open the Blob URL in a new tab
    });
  }
}
