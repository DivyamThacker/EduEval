package com.proj.rest.webservices.restfulwebservices.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.proj.rest.webservices.restfulwebservices.services.PdfService;

@RestController
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

    @GetMapping("/convert")
    public String convertToPdf() {
        try {
            pdfService.convertHtmlToPdf();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return """
            { "message": "Hello, Pdf generated" }
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

}
