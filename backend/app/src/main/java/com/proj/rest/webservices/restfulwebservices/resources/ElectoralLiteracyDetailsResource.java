package com.proj.rest.webservices.restfulwebservices.resources;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.proj.rest.webservices.restfulwebservices.models.DocumentDetails;
import com.proj.rest.webservices.restfulwebservices.models.ElectoralLiteracyDetails;
import com.proj.rest.webservices.restfulwebservices.models.University;
import com.proj.rest.webservices.restfulwebservices.repositories.DocumentDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.ElectoralLiteracyDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;
import com.proj.rest.webservices.restfulwebservices.services.StorageService;
import com.proj.rest.webservices.restfulwebservices.services.FileTextExtractionService;

@RestController
@RequestMapping("/api/naac/university/{universityId}/electoral-literacy-details")
public class ElectoralLiteracyDetailsResource {

    private UniversityRepository universityRepository;
    private ElectoralLiteracyDetailsRepository electoralLiteracyDetailsRepository;
    private StorageService storageService;
    private DocumentDetailsRepository documentDetailsRepository;
    private FileTextExtractionService fileTextExtractionService;

    public ElectoralLiteracyDetailsResource(UniversityRepository universityRepository, 
                                            ElectoralLiteracyDetailsRepository electoralLiteracyDetailsRepository,StorageService storageService,
                                            DocumentDetailsRepository documentDetailsRepository,
                                            FileTextExtractionService fileTextExtractionService) {
                      this.universityRepository = universityRepository;
                      this.storageService = storageService;
                      this.documentDetailsRepository = documentDetailsRepository;
                        this.electoralLiteracyDetailsRepository = electoralLiteracyDetailsRepository;
                        this.fileTextExtractionService = fileTextExtractionService;
                     }    
                     
        @PostMapping(value="")
    public ResponseEntity<?> createElectoralLiteracyDetails(
        @PathVariable Integer universityId,
    @RequestParam(required = false) List<MultipartFile> files) throws JsonProcessingException {

    University university = universityRepository.findById(universityId)
        .orElseThrow(() -> new RuntimeException("University not found"));
    
    List<ElectoralLiteracyDetails> savedElectoralLiteracyDetails = new ArrayList<>();

    for (int i = 0; i < files.size(); i++) {
        ElectoralLiteracyDetails electoralLiteracyDetails = new ElectoralLiteracyDetails();
        electoralLiteracyDetails.setSection(i);
        electoralLiteracyDetails.setUniversity(university); 
        String fileIdentifier = storageService.uploadFile(files.get(i));
        DocumentDetails doc = new DocumentDetails();
        doc.setFileName(files.get(i).getOriginalFilename());
        doc.setFileIdentifier(fileIdentifier);
        documentDetailsRepository.save(doc); // Save the DocumentDetails entity
        electoralLiteracyDetails.setDocument(doc);

         // Download file from S3
        File downloadedFile = storageService.downloadFileAsFile(fileIdentifier);

        // Extract text based on file type
        String extractedText = fileTextExtractionService.extractTextFromFile(downloadedFile);

        electoralLiteracyDetails.setExtractedText(extractedText);
        System.out.println("Extracted text: " + extractedText);
        savedElectoralLiteracyDetails.add(electoralLiteracyDetailsRepository.save(electoralLiteracyDetails));
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(savedElectoralLiteracyDetails);
}
}