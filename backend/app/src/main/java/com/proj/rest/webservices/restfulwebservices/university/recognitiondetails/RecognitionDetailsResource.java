package com.proj.rest.webservices.restfulwebservices.university.recognitiondetails;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.proj.rest.webservices.restfulwebservices.jpa.DocumentDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.jpa.RecognitionDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.jpa.UniversityRepository;
import com.proj.rest.webservices.restfulwebservices.services.StorageService;
import com.proj.rest.webservices.restfulwebservices.university.University;
import com.proj.rest.webservices.restfulwebservices.university.document.DocumentDetails;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/naac/university/{universityId}/recognition-details")
public class RecognitionDetailsResource {

    private RecognitionDetailsRepository recognitionDetailsRepository;
    private StorageService storageService;
    private UniversityRepository universityRepository;
    private DocumentDetailsRepository documentDetailsRepository;

    public RecognitionDetailsResource(RecognitionDetailsRepository recognitionDetailsRepository, 
    DocumentDetailsRepository documentDetailsRepository,StorageService storageService, UniversityRepository universityRepository) {
        this.recognitionDetailsRepository = recognitionDetailsRepository;
        this.storageService = storageService;
        this.universityRepository = universityRepository;
        this.documentDetailsRepository = documentDetailsRepository;
    }

    @PostMapping("")
    public ResponseEntity<RecognitionDetails> submitRecognitionDetails(
            @PathVariable Integer universityId,
            @RequestParam String recognitionDateUnderSection2f,
            @RequestParam String recognitionDateUnderSection12b,
            @RequestParam(required = false) MultipartFile recognitionDocument2f,
            @RequestParam(required = false) MultipartFile recognitionDocument12b,
            @RequestParam String isUPE) {
            
        University university = universityRepository.findById(universityId)
                .orElseThrow(() -> new RuntimeException("University not found"));

        RecognitionDetails recognitionDetails = new RecognitionDetails();
        recognitionDetails.setRecognitionDateUnderSection2f(recognitionDateUnderSection2f);
        recognitionDetails.setRecognitionDateUnderSection12b(recognitionDateUnderSection12b);
        recognitionDetails.setIsUPE(isUPE);

        if (recognitionDocument2f != null) {
            String fileIdentifier = storageService.uploadFile(recognitionDocument2f);
            DocumentDetails doc2f = new DocumentDetails();
            doc2f.setFileName(recognitionDocument2f.getOriginalFilename());
            doc2f.setFileIdentifier(fileIdentifier);
            documentDetailsRepository.save(doc2f); // Save the DocumentDetails entity
            recognitionDetails.setRecognitionDocument2f(doc2f);
        }
        
        if (recognitionDocument12b != null) {
            String fileIdentifier = storageService.uploadFile(recognitionDocument12b);
            DocumentDetails doc12b = new DocumentDetails();
            doc12b.setFileName(recognitionDocument12b.getOriginalFilename());
            doc12b.setFileIdentifier(fileIdentifier);
            documentDetailsRepository.save(doc12b); // Save the DocumentDetails entity
            recognitionDetails.setRecognitionDocument12b(doc12b);
        }

        recognitionDetails.setUniversity(university);
        recognitionDetailsRepository.save(recognitionDetails);
        university.setRecognitionDetials(recognitionDetails);

        universityRepository.save(university);
        return ResponseEntity.ok(recognitionDetails);
    }

    @PutMapping("/{recognitionDetailsId}")
    public ResponseEntity<RecognitionDetails> updateRecognitionDetails(
        @PathVariable Integer universityId,
        @PathVariable Integer recognitionDetailsId,
        @RequestParam String recognitionDateUnderSection2f,
        @RequestParam String recognitionDateUnderSection12b,
        @RequestParam(required = false) MultipartFile recognitionDocument2f,
        @RequestParam(required = false) MultipartFile recognitionDocument12b,
        @RequestParam String isUPE) {

        University university = universityRepository.findById(universityId)
                .orElseThrow(() -> new RuntimeException("University not found"));
        
        RecognitionDetails recognitionDetails = recognitionDetailsRepository.findById(recognitionDetailsId)
                .orElseThrow(() -> new RuntimeException("RecognitionDetails not found"));
        recognitionDetails.setRecognitionDateUnderSection2f(recognitionDateUnderSection2f);
        recognitionDetails.setRecognitionDateUnderSection12b(recognitionDateUnderSection12b);
        recognitionDetails.setIsUPE(isUPE);

        if (recognitionDetails.getRecognitionDocument2f() != null) {
            storageService.deleteFile(recognitionDetails.getRecognitionDocument2f().getFileIdentifier());
            documentDetailsRepository.delete(recognitionDetails.getRecognitionDocument2f());
        }

        if (recognitionDetails.getRecognitionDocument12b() != null) {
            storageService.deleteFile(recognitionDetails.getRecognitionDocument12b().getFileIdentifier());
            documentDetailsRepository.delete(recognitionDetails.getRecognitionDocument12b());
        }

        if (recognitionDocument2f != null) {
            String fileIdentifier = storageService.uploadFile(recognitionDocument2f);
            DocumentDetails doc2f = new DocumentDetails();
            doc2f.setFileName(recognitionDocument2f.getOriginalFilename());
            doc2f.setFileIdentifier(fileIdentifier);
            // documentDetailsRepository.save(doc2f); // not needed as cascade type is All in RecognitionDetails
            recognitionDetails.setRecognitionDocument2f(doc2f);
        }

        if (recognitionDocument12b != null) {
            String fileIdentifier = storageService.uploadFile(recognitionDocument12b);
            DocumentDetails doc12b = new DocumentDetails();
            doc12b.setFileName(recognitionDocument12b.getOriginalFilename());
            doc12b.setFileIdentifier(fileIdentifier);
            recognitionDetails.setRecognitionDocument12b(doc12b);
        }

        recognitionDetails.setUniversity(university);
        recognitionDetailsRepository.save(recognitionDetails);
        university.setRecognitionDetials(recognitionDetails);

        universityRepository.save(university);
        return ResponseEntity.ok(recognitionDetails);
    }

    @DeleteMapping("/{recognitionDetailsId}")
    public ResponseEntity<Object> deleteRecognitionDetails(@PathVariable Integer recognitionDetailsId) {
        RecognitionDetails recognitionDetails = recognitionDetailsRepository.findById(recognitionDetailsId)
                .orElseThrow(() -> new RuntimeException("RecognitionDetails not found"));
        recognitionDetailsRepository.delete(recognitionDetails);
        return ResponseEntity.noContent().build();
    }
}
    
    
    
