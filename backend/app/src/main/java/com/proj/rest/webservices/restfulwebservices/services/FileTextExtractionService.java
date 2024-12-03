package com.proj.rest.webservices.restfulwebservices.services;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class FileTextExtractionService {

    public String extractTextFromFile(File file) {
        String fileName = file.getName();
        if (fileName.endsWith(".pdf")) {
            return extractTextFromPdf(file);
        } else if (fileName.endsWith(".txt")) {
            return extractTextFromTxt(file);
        } else {
            throw new RuntimeException("Unsupported file format: " + fileName);
        }
    }

    private String extractTextFromPdf(File pdfFile) {
        try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document);
        } catch (IOException e) {
            throw new RuntimeException("Error extracting text from PDF", e);
        }
    }

    private String extractTextFromTxt(File txtFile) {
        try {
            return new String(Files.readAllBytes(txtFile.toPath()));
        } catch (IOException e) {
            throw new RuntimeException("Error reading text file", e);
        }
    }
}
