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
import com.proj.rest.webservices.restfulwebservices.models.NepDetails;
import com.proj.rest.webservices.restfulwebservices.models.DocumentDetails;
import com.proj.rest.webservices.restfulwebservices.models.University;
import com.proj.rest.webservices.restfulwebservices.repositories.DocumentDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.NepDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;
import com.proj.rest.webservices.restfulwebservices.services.StorageService;
@RestController
@RequestMapping("/api/naac/university/{universityId}/nep-details")
public class NepDetailsResource {

    private UniversityRepository universityRepository;
    private NepDetailsRepository nepDetailsRepository;
    private StorageService storageService;
    private DocumentDetailsRepository documentDetailsRepository;

    public NepDetailsResource(UniversityRepository universityRepository, 
                              NepDetailsRepository nepDetailsRepository,StorageService storageService,
                              DocumentDetailsRepository documentDetailsRepository) {
        this.universityRepository = universityRepository;
        this.storageService = storageService;
        this.documentDetailsRepository = documentDetailsRepository;
        this.nepDetailsRepository = nepDetailsRepository;
    }

     @PostMapping(value="")
    public ResponseEntity<?> createNepDetails(
    @PathVariable Integer universityId,
    @RequestParam(required = false) List<MultipartFile> files) {

    University university = universityRepository.findById(universityId)
            .orElseThrow(() -> new RuntimeException("University not found"));

    List<NepDetails> savedNeps = new ArrayList<>();

    for (int i = 0; i < files.size(); i++) {
        NepDetails nepDetails = new NepDetails();
        nepDetails.setSection(i);
        nepDetails.setUniversity(university);

        // Upload file to S3 and get the identifier
        String fileIdentifier = storageService.uploadFile(files.get(i));

        // Save DocumentDetails
        DocumentDetails doc = new DocumentDetails();
        doc.setFileName(files.get(i).getOriginalFilename());
        doc.setFileIdentifier(fileIdentifier);
        documentDetailsRepository.save(doc);
        nepDetails.setDocument(doc);

        savedNeps.add(nepDetailsRepository.save(nepDetails));
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(savedNeps);
}

@DeleteMapping("")
	public ResponseEntity<Object> deleteAllNepDetails(@PathVariable Integer universityId) {
		University university = universityRepository.findById(universityId).orElse(null);
		if (university == null) {
			return ResponseEntity.notFound().build();
		}
		List<NepDetails> nepDetails = university.getNepDetails();
		for (NepDetails c : nepDetails) {
			System.out.println(c);
            storageService.deleteFile(c.getDocument().getFileIdentifier());
		}
		if (!nepDetails.isEmpty()) {
			nepDetailsRepository.deleteAll(nepDetails);
			university.getNepDetails().clear();       // Remove from university
			universityRepository.save(university);        // Persist update to university
		}
		return ResponseEntity.noContent().build();
	}
}