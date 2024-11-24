package com.proj.rest.webservices.restfulwebservices.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.proj.rest.webservices.restfulwebservices.services.PdfService;

@RestController
@RequestMapping("/api/naac")
public class HelloWorldController {

    private PdfService pdfService;
    HelloWorldController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping(path = "/hello")
    public String helloWorld() {
		// Implemented using String Templates
        return """
                { "message": "Hello World Java v2" }
               """;
    }

    @GetMapping("/convert")//html to pdf converter
    public String convertToPdf() {
        try {
            pdfService.convertHtmlToPdf();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return """
            { "message": "Hello, converted to Pdf" }
           """;
    }

    @GetMapping("/generate")
    public String createPdf() {
        try {
            pdfService.generatePdf();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return """
            { "message": "Hello, Pdf generated" }
           """;
    }

    @GetMapping("/generate-ssr")//generate ssr and return
     public ResponseEntity<InputStreamResource> downloadPdf() throws IOException {

        try {
            pdfService.generatePdf();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Path pdfPath = new ClassPathResource("UniversityProfile.pdf").getFile().toPath();
        byte[] pdfBytes = Files.readAllBytes(pdfPath);

        ByteArrayInputStream pdfStream = new ByteArrayInputStream(pdfBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=SSR.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfStream));
    }

}
