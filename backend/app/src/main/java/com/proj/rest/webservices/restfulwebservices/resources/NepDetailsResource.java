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
import com.proj.rest.webservices.restfulwebservices.models.NepDetails;
import com.proj.rest.webservices.restfulwebservices.models.DocumentDetails;
import com.proj.rest.webservices.restfulwebservices.models.University;
import com.proj.rest.webservices.restfulwebservices.repositories.DocumentDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.NepDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;
import com.proj.rest.webservices.restfulwebservices.services.StorageService;
import com.proj.rest.webservices.restfulwebservices.services.FileTextExtractionService;
import java.io.File;
@RestController
@RequestMapping("/api/naac/university/{universityId}/nep-details")
public class NepDetailsResource {

    private UniversityRepository universityRepository;
    private NepDetailsRepository nepDetailsRepository;
    private StorageService storageService;
    private DocumentDetailsRepository documentDetailsRepository;
    private FileTextExtractionService fileTextExtractionService;

    public NepDetailsResource(UniversityRepository universityRepository, 
                              NepDetailsRepository nepDetailsRepository,StorageService storageService,
                              DocumentDetailsRepository documentDetailsRepository,FileTextExtractionService fileTextExtractionService) {
        this.universityRepository = universityRepository;
        this.storageService = storageService;
        this.documentDetailsRepository = documentDetailsRepository;
        this.nepDetailsRepository = nepDetailsRepository;
        this.fileTextExtractionService = fileTextExtractionService;
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

        // Download file from S3
        File downloadedFile = storageService.downloadFileAsFile(fileIdentifier);

        // Extract text based on file type
        String extractedText = fileTextExtractionService.extractTextFromFile(downloadedFile);

        // Save extracted text
        nepDetails.setExtractedText(extractedText);
        System.out.println("Extracted text: " + extractedText);

        // Save NepDetails
        savedNeps.add(nepDetailsRepository.save(nepDetails));

        // Clean up the downloaded file
        if (downloadedFile != null) {
            downloadedFile.delete();
        }
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(savedNeps);
}

}