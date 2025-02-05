package com.proj.rest.webservices.restfulwebservices.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.proj.rest.webservices.restfulwebservices.models.ElectoralLiteracyDetails;
import com.proj.rest.webservices.restfulwebservices.models.DocumentDetails;
import com.proj.rest.webservices.restfulwebservices.models.University;
import com.proj.rest.webservices.restfulwebservices.repositories.DocumentDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.ElectoralLiteracyDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;
import com.proj.rest.webservices.restfulwebservices.services.StorageService;

@RestController
@RequestMapping("/api/naac/university/{universityId}/electoral-literacy-details")
public class ElectoralLiteracyDetailsResource {

    private UniversityRepository universityRepository;
    private ElectoralLiteracyDetailsRepository electoralLiteracyDetailsRepository;
    private StorageService storageService;
    private DocumentDetailsRepository documentDetailsRepository;

    public ElectoralLiteracyDetailsResource(UniversityRepository universityRepository, 
                                            ElectoralLiteracyDetailsRepository electoralLiteracyDetailsRepository,StorageService storageService,
                                            DocumentDetailsRepository documentDetailsRepository) {
                      this.universityRepository = universityRepository;
                      this.storageService = storageService;
                      this.documentDetailsRepository = documentDetailsRepository;
                        this.electoralLiteracyDetailsRepository = electoralLiteracyDetailsRepository;
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

        savedElectoralLiteracyDetails.add(electoralLiteracyDetailsRepository.save(electoralLiteracyDetails));
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(savedElectoralLiteracyDetails);
}

@DeleteMapping("")
	public ResponseEntity<Object> deleteAllElectoralDetails(@PathVariable Integer universityId) {
		University university = universityRepository.findById(universityId).orElse(null);
		if (university == null) {
			return ResponseEntity.notFound().build();
		}
		List<ElectoralLiteracyDetails> electoralLiteracyDetails = university.getElectoralLiteracyDetails();
		for (ElectoralLiteracyDetails c : electoralLiteracyDetails) {
			System.out.println(c);
            storageService.deleteFile(c.getDocument().getFileIdentifier());
		}
		if (!electoralLiteracyDetails.isEmpty()) {
			electoralLiteracyDetailsRepository.deleteAll(electoralLiteracyDetails);
			university.getElectoralLiteracyDetails().clear();       // Remove from university
			universityRepository.save(university);        // Persist update to university
		}
		return ResponseEntity.noContent().build();
	}
}