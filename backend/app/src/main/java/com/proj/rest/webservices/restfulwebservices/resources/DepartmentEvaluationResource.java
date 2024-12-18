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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.rest.webservices.restfulwebservices.models.DepartmentEvaluation;
import com.proj.rest.webservices.restfulwebservices.models.DocumentDetails;
import com.proj.rest.webservices.restfulwebservices.models.University;
import com.proj.rest.webservices.restfulwebservices.repositories.DepartmentEvaluationRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.DocumentDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;
import com.proj.rest.webservices.restfulwebservices.services.StorageService;

@RestController
@RequestMapping("/api/naac/university/{universityId}/department-evaluation")
public class DepartmentEvaluationResource {

    private UniversityRepository universityRepository;
    private DepartmentEvaluationRepository departmentEvaluationRepository;
    private StorageService storageService;
    private DocumentDetailsRepository documentDetailsRepository;

    public DepartmentEvaluationResource(UniversityRepository universityRepository, 
                DepartmentEvaluationRepository departmentEvaluationRepository, StorageService storageService,
                DocumentDetailsRepository documentDetailsRepository) {
    this.universityRepository = universityRepository;
    this.storageService = storageService;
    this.documentDetailsRepository = documentDetailsRepository;
    this.departmentEvaluationRepository = departmentEvaluationRepository;
}

     @PostMapping(value="")
    public ResponseEntity<?> createDeparmentEvaluations(
        @PathVariable Integer universityId,
        @RequestParam("departmentNames") String departmentNamesJson,
    @RequestParam(required = false) List<MultipartFile> files) throws JsonProcessingException {

    // Deserialize JSON into List<DeparmentEvaluation>
    ObjectMapper objectMapper = new ObjectMapper();
    List<String> departmentNames = objectMapper.readValue(
        departmentNamesJson, 
        new TypeReference<List<String>>() {}
    );

    University university = universityRepository.findById(universityId)
        .orElseThrow(() -> new RuntimeException("University not found"));

    if (departmentNames.size() != files.size()) {
        throw new RuntimeException("Mismatch between number of department Evaluation Details and files");
    }

    List<DepartmentEvaluation> savedDepartmentEvaluations = new ArrayList<>();

    for (int i = 0; i < departmentNames.size(); i++) {
        DepartmentEvaluation departmentEvaluation = new DepartmentEvaluation();
        departmentEvaluation.setUniversity(university);
        String fileIdentifier = storageService.uploadFile(files.get(i));
        DocumentDetails doc = new DocumentDetails();
        doc.setFileName(files.get(i).getOriginalFilename());
        doc.setFileIdentifier(fileIdentifier);
        documentDetailsRepository.save(doc); // Save the DocumentDetails entity
        departmentEvaluation.setReport(doc);
        departmentEvaluation.setDepartmentName(departmentNames.get(i));
        savedDepartmentEvaluations.add(departmentEvaluationRepository.save(departmentEvaluation));
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(savedDepartmentEvaluations);
    }

    @DeleteMapping("")
    public ResponseEntity<Object> deleteAllDepartmentEvaluations(@PathVariable Integer universityId) {
        University university = universityRepository.findById(universityId).orElse(null);
        if (university == null) {
            return ResponseEntity.notFound().build();
        }
        List<DepartmentEvaluation> departmentEvaluations = university.getDepartmentEvaluations();
        for (DepartmentEvaluation c : departmentEvaluations) {
            System.out.println(c);
            storageService.deleteFile(c.getReport().getFileIdentifier());
        }
        if (!departmentEvaluations.isEmpty()) {
            departmentEvaluationRepository.deleteAll(departmentEvaluations);
            university.getDepartmentEvaluations().clear();       // Remove from university
            universityRepository.save(university);        // Persist update to university
        }
        return ResponseEntity.noContent().build();
    }

}