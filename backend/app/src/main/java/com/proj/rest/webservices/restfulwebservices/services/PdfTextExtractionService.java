package com.proj.rest.webservices.restfulwebservices.services;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
// step 3
@Service
public class PdfTextExtractionService {
    public String extractTextFromPdf(File pdfFile) {
        try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document);
        } catch (IOException e) {
            throw new RuntimeException("Error extracting text from PDF", e);
        }
    }
}
