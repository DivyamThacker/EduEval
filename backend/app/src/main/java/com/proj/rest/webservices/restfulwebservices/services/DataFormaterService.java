package com.proj.rest.webservices.restfulwebservices.services;

import java.io.File;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.rest.webservices.restfulwebservices.models.*;
import com.proj.rest.webservices.restfulwebservices.repositories.*;

@Service
public class DataFormaterService {

    private UniversityRepository universityRepository;
    private StorageService storageService;
    private FileTextExtractionService fileTextExtractionService;

    public DataFormaterService(UniversityRepository universityRepository, 
    StorageService storageService, FileTextExtractionService fileTextExtractionService) {
        this.universityRepository = universityRepository;
        this.storageService = storageService;
        this.fileTextExtractionService = fileTextExtractionService;
    }

    public String getBasicInfo(Integer universityId) {
        // get the university
        University university = universityRepository.findById(universityId)
            .orElseThrow(() -> new RuntimeException("University not found"));

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(university);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing JSON", e);
        }
    }

    public String getRecognitionDetails(Integer universityId) {
        // get the university
        University university = universityRepository.findById(universityId)
            .orElseThrow(() -> new RuntimeException("University not found"));

        
        ObjectMapper mapper = new ObjectMapper();
        try {
            // System.out.println(university.getRecognitionDetails());
            return mapper.writeValueAsString(university.getRecognitionDetails());
        
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing JSON", e);
        }
    }

    public String getContactDetials(Integer universityId)
    {
        University university = universityRepository.findById(universityId)
        .orElseThrow(() -> new RuntimeException("Contact Details Not Found"));

        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writeValueAsString(university.getContactDetails());
        } catch(JsonProcessingException e){
            throw new RuntimeException("Error processing JSON", e);
        }
    }

    public String getCampusDetails(Integer universityId)
    {
        University university = universityRepository.findById(universityId)
        .orElseThrow(() -> new RuntimeException("Campus Details Not Found"));

        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writeValueAsString(university.getCampuses());
        } catch(JsonProcessingException e){
            throw new RuntimeException("Error processing JSON", e);
        }
    }

    public String getCollegeStatsDetails(Integer universityId)
    {
        University university = universityRepository.findById(universityId)
        .orElseThrow(() -> new RuntimeException("College Stats Details Not Found"));

        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writeValueAsString(university.getCollegeStats());
        } catch(JsonProcessingException e){
            throw new RuntimeException("Error processing JSON", e);
        }
    }

    public String getSRAPRogramms(Integer universityId)
    {
        University university = universityRepository.findById(universityId)
        .orElseThrow(() -> new RuntimeException("SRA Program Details Not Found"));

        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writeValueAsString(university.getSraPrograms());
        } catch(JsonProcessingException e){
            throw new RuntimeException("Error processing JSON", e);
        }
    }

    public String getFacultyDetails(Integer universityId)
    {
        University university = universityRepository.findById(universityId)
        .orElseThrow(() -> new RuntimeException("Faculty Details Not Found"));

        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writeValueAsString(university.getFaculties());
        } catch(JsonProcessingException e){
            throw new RuntimeException("Error processing JSON", e);
        }
    }

    public String getNonTeachingDetails(Integer universityId)
    {
        University university = universityRepository.findById(universityId)
        .orElseThrow(() -> new RuntimeException("Non Teaching Staff Details Not Found"));

        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writeValueAsString(university.getNonTeachingStaff());
        } catch(JsonProcessingException e){
            throw new RuntimeException("Error processing JSON", e);
        }
    }

    public String getDistinguishAcadmecian(Integer universityId)
    {
        University university = universityRepository.findById(universityId)
        .orElseThrow(() -> new RuntimeException("Distinguish Acadmecian Details Not Found"));

        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writeValueAsString(university.getDistinguishedAcademicians());
        } catch(JsonProcessingException e){
            throw new RuntimeException("Error processing JSON", e);
        }
    }

    public String getChairsDetails(Integer universityId)
    {
        University university = universityRepository.findById(universityId)
        .orElseThrow(() -> new RuntimeException("Chairs Details Not Found"));

        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writeValueAsString(university.getChairDetails());
        } catch(JsonProcessingException e){
            throw new RuntimeException("Error processing JSON", e);
        }
    }

    public String getHRDCDetails(Integer universityId)
    {
        University university = universityRepository.findById(universityId)
        .orElseThrow(() -> new RuntimeException("HRDC Details Not Found"));

        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writeValueAsString(university.getHrdcDetails());
        } catch(JsonProcessingException e){
            throw new RuntimeException("Error processing JSON", e);
        }
    }

    public String getAccreditationDetails(Integer universityId)
    {
        University university = universityRepository.findById(universityId)
        .orElseThrow(() -> new RuntimeException("Accreditation Details Not Found"));

        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writeValueAsString(university.getAccreditationDetails());
        } catch(JsonProcessingException e){
            throw new RuntimeException("Error processing JSON", e);
        }
    }    

    public String getDepartmetEvaluation(Integer universityId)
    {
        University university = universityRepository.findById(universityId)
        .orElseThrow(() -> new RuntimeException("Department Evaluation Details Not Found"));

        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writeValueAsString(university.getDepartmentEvaluations());
        } catch(JsonProcessingException e){
            throw new RuntimeException("Error processing JSON", e);
        }
    }
    
    public String getNepDetails(Integer universityId)
    {
        University university = universityRepository.findById(universityId)
        .orElseThrow(() -> new RuntimeException("Nep Details Not Found"));

        ObjectMapper mapper = new ObjectMapper();
        try{
            var nepDetails = university.getNepDetails();
            nepDetails.forEach(nep -> {
                String fileIdentifier = nep.getDocument().getFileIdentifier();
                File downloadedFile = storageService.downloadFileAsFile(fileIdentifier);
            nep.setExtractedText(fileTextExtractionService.extractTextFromTxt(downloadedFile));
            System.out.println("Extracted text: " + nep.getExtractedText());
            });
            return mapper.writeValueAsString(nepDetails);
        } catch(JsonProcessingException e){
            throw new RuntimeException("Error processing JSON", e);
        }
    }

    public String getElectoralDetails(Integer universityId)
    {
        University university = universityRepository.findById(universityId)
        .orElseThrow(() -> new RuntimeException("Electoral Details Not Found"));

        ObjectMapper mapper = new ObjectMapper();
        try{
            var electoralDetails = university.getElectoralLiteracyDetails();
            electoralDetails.forEach(detail -> {
            String fileIdentifier = detail.getDocument().getFileIdentifier();
            File downloadedFile = storageService.downloadFileAsFile(fileIdentifier);
            detail.setExtractedText(fileTextExtractionService.extractTextFromTxt(downloadedFile));
            System.out.println("Extracted text: " + detail.getExtractedText());
        });
        return mapper.writeValueAsString(electoralDetails);
        } catch(JsonProcessingException e){
            throw new RuntimeException("Error processing JSON", e);
        }
    }
}


