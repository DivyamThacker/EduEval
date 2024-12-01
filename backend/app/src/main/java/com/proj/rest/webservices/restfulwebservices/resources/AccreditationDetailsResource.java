package com.proj.rest.webservices.restfulwebservices.resources;

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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.rest.webservices.restfulwebservices.models.AccreditationDetails;
import com.proj.rest.webservices.restfulwebservices.models.DocumentDetails;
import com.proj.rest.webservices.restfulwebservices.models.University;
import com.proj.rest.webservices.restfulwebservices.repositories.AccreditationDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.DocumentDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;
import com.proj.rest.webservices.restfulwebservices.services.StorageService;

@RestController
@RequestMapping("/api/naac/university/{universityId}/accreditation")
public class AccreditationDetailsResource {

    private UniversityRepository universityRepository;
    private AccreditationDetailsRepository accreditationsRepository;
    private StorageService storageService;
    private DocumentDetailsRepository documentDetailsRepository;


	public AccreditationDetailsResource(UniversityRepository universityRepository, 
                        AccreditationDetailsRepository accreditationsRepository,
                        StorageService storageService,
                        DocumentDetailsRepository documentDetailsRepository) {
		this.universityRepository = universityRepository;
        this.accreditationsRepository = accreditationsRepository;
        this.storageService = storageService;
        this.documentDetailsRepository = documentDetailsRepository;
	}

    @PostMapping(value="")
    public ResponseEntity<?> createAccreditationDetails(
        @PathVariable Integer universityId,
        @RequestParam("accreditations") String accreditationsJson,
    @RequestParam(required = false) List<MultipartFile> files) throws JsonProcessingException {

    // Deserialize JSON into List<AccreditationDetails>
    ObjectMapper objectMapper = new ObjectMapper();
    List<AccreditationDetails> accreditations = objectMapper.readValue(
        accreditationsJson, 
        new TypeReference<List<AccreditationDetails>>() {}
    );

    University university = universityRepository.findById(universityId)
        .orElseThrow(() -> new RuntimeException("University not found"));

    if (accreditations.size() != files.size()) {
        throw new RuntimeException("Mismatch between number of Accreditation Details and files");
    }

    List<AccreditationDetails> savedAccreditations = new ArrayList<>();

    for (int i = 0; i < accreditations.size(); i++) {
        accreditations.get(i).setUniversity(university); 
        String fileIdentifier = storageService.uploadFile(files.get(i));
        DocumentDetails doc = new DocumentDetails();
        doc.setFileName(files.get(i).getOriginalFilename());
        doc.setFileIdentifier(fileIdentifier);
        documentDetailsRepository.save(doc); // Save the DocumentDetails entity
        accreditations.get(i).setPeerTeamReport(doc);

        savedAccreditations.add(accreditationsRepository.save(accreditations.get(i)));
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(savedAccreditations);
}

}