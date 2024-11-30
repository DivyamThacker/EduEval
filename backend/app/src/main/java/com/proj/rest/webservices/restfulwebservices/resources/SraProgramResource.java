package com.proj.rest.webservices.restfulwebservices.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.proj.rest.webservices.restfulwebservices.models.DocumentDetails;
import com.proj.rest.webservices.restfulwebservices.models.SraProgram;
import com.proj.rest.webservices.restfulwebservices.models.University;
import com.proj.rest.webservices.restfulwebservices.repositories.DocumentDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.SraProgramRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;
import com.proj.rest.webservices.restfulwebservices.services.StorageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/naac/university/{universityId}/sra-program")
public class SraProgramResource {

    private SraProgramRepository sraProgramRepository;
    private StorageService storageService;
    private UniversityRepository universityRepository;
    private DocumentDetailsRepository documentDetailsRepository;

    public SraProgramResource(SraProgramRepository sraProgramRepository, 
    DocumentDetailsRepository documentDetailsRepository,StorageService storageService, UniversityRepository universityRepository) {
        this.sraProgramRepository = sraProgramRepository;
        this.storageService = storageService;
        this.universityRepository = universityRepository;
        this.documentDetailsRepository = documentDetailsRepository;
        }

    @PostMapping(value="")
    public ResponseEntity<?> createSraPrograms(
        @PathVariable Integer universityId,
        @RequestParam String areSraProgram,
        @RequestParam(required = false) List<String> sraProgramNames,
        @RequestParam(required = false) List<MultipartFile> files)  {
    
        University university = universityRepository.findById(universityId)
            .orElseThrow(() -> new RuntimeException("University not found"));
            System.out.println(areSraProgram);

            if (areSraProgram == null || areSraProgram.equals("false")) {
                return ResponseEntity.status(HttpStatus.OK).body("Not an SRA Program");
            }
            university.setAreSraProgram(true);
            universityRepository.save(university);
            
            
        if (sraProgramNames.size() != files.size()) {
            throw new RuntimeException("Mismatch between number of SRA programs and files");
        }


        List<SraProgram> savedPrograms = new ArrayList<>();
    
        for (int i = 0; i < sraProgramNames.size(); i++) {
            SraProgram sraProgram = new SraProgram();
            sraProgram.setUniversity(university); 
            String fileIdentifier = storageService.uploadFile(files.get(i));
            DocumentDetails doc = new DocumentDetails();
            doc.setFileName(files.get(i).getOriginalFilename());
            doc.setFileIdentifier(fileIdentifier);
            documentDetailsRepository.save(doc); // Save the DocumentDetails entity
            sraProgram.setName(sraProgramNames.get(i));
            sraProgram.setSraDocument(doc);

            savedPrograms.add(sraProgramRepository.save(sraProgram));
        }
    
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPrograms);
    }

    @PostMapping(value="/single")
    public ResponseEntity<?> createSingleSraProgram(
            @PathVariable Integer universityId,
            @RequestParam String sraProgramName,
            @RequestParam(required = false) MultipartFile file) {
        
        SraProgram sraProgram = new SraProgram();
            University university = universityRepository.findById(universityId)
        .orElseThrow(() -> new RuntimeException("University not found"));
        
        sraProgram.setUniversity(university); // Assuming you have a method to set the University by its ID

        if (file != null) {
            String fileIdentifier = storageService.uploadFile(file);
            DocumentDetails doc = new DocumentDetails();
            doc.setFileName(file.getOriginalFilename());
            doc.setFileIdentifier(fileIdentifier);
            documentDetailsRepository.save(doc); // Save the DocumentDetails entity
            sraProgram.setSraDocument(doc);
        }
        sraProgram.setName(sraProgramName);
        SraProgram savedSraProgram = sraProgramRepository.save(sraProgram);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedSraProgram);
    }

    @PutMapping("/{sraProgramId}")
    public ResponseEntity<?> updateSraProgram(
            @PathVariable Integer universityId,
            @PathVariable Integer sraProgramId,
            @RequestPart("sraProgram") SraProgram sraProgram,
            @RequestPart(value = "file", required = false) MultipartFile file) {

            University university = universityRepository.findById(universityId)
            .orElseThrow(() -> new RuntimeException("University not found"));

        if (file != null) {
            String fileName = storageService.uploadFile(file);
            DocumentDetails documentDetails = new DocumentDetails();
            documentDetails.setFileName(file.getOriginalFilename());
            documentDetails.setFileIdentifier(fileName);
            sraProgram.setSraDocument(documentDetails);
        }

        sraProgram.setUniversity(university); // Assuming you have a method to set the University by its ID
        sraProgram.setId(sraProgramId);

        Optional<SraProgram> existingSraProgramOpt = sraProgramRepository.findById(sraProgram.getId());
        if (existingSraProgramOpt.isPresent()) {
            SraProgram existingSraProgram = existingSraProgramOpt.get();
            existingSraProgram.setName(sraProgram.getName());
            existingSraProgram.setSraDocument(sraProgram.getSraDocument());
            existingSraProgram.setUniversity(sraProgram.getUniversity());
            sraProgramRepository.save(existingSraProgram);
        }


        return ResponseEntity.status(HttpStatus.OK).body(existingSraProgramOpt.get());
    }

    @DeleteMapping("/{sraProgramId}")
    public ResponseEntity<?> deleteSraProgram(@PathVariable Integer sraProgramId) {
        sraProgramRepository.deleteById(sraProgramId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

        
}