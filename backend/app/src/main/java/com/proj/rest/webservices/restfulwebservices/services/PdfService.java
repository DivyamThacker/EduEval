
package com.proj.rest.webservices.restfulwebservices.services;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;

import java.io.*;

import org.springframework.stereotype.Service;
import com.itextpdf.layout.Document;

@Service
public class PdfService {
    private static final String SRC = "src/main/resources/static/input.html"; 
    private static final String DEST = "target/output.pdf"; 

    public void convertHtmlToPdf() throws IOException {
        try {
            HtmlConverter.convertToPdf(new FileInputStream(SRC), 
            new FileOutputStream(DEST));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void generatePdf() throws IOException {
        try {
            // Step 1: Create a PdfWriter instance to write to the destination file
            PdfWriter writer = new PdfWriter(DEST);

            // Step 2: Create a PdfDocument which will hold the PDF structure
            PdfDocument pdfDoc = new PdfDocument(writer);

            // Step 3: Create a Document which handles the content layout
            Document document = new Document(pdfDoc);

            // Step 4: Add content to the document
            document.add(new Paragraph("Hello! This is a PDF generated using iText 7."));
            document.add(new Paragraph("iText Kernel and iText Layout are used to manage the PDF structure and content formatting."));

            // Step 5: Close the document (and also the PdfDocument)
            document.close();
            System.out.println("PDF created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


