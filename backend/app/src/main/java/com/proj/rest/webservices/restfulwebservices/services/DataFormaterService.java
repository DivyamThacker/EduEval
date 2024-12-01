package com.proj.rest.webservices.restfulwebservices.services;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.rest.webservices.restfulwebservices.models.Campus;
import com.proj.rest.webservices.restfulwebservices.models.CollegeStats;
import com.proj.rest.webservices.restfulwebservices.models.Contact;
import com.proj.rest.webservices.restfulwebservices.models.DocumentDetails;
import com.proj.rest.webservices.restfulwebservices.models.RecognitionDetails;
import com.proj.rest.webservices.restfulwebservices.models.University;
// import com.proj.rest.webservices.restfulwebservices.repositories.BasicInfoDataRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.*;

@Service
public class DataFormaterService {

    private ContactDetailsRepository contactDetailsRepository;
    private CampusRepository campusRepository;
    private CollegeStatsRepository collegeStatsRepository;
    private DocumentDetailsRepository documentDetailsRepository;
    private RecognitionDetailsRepository recognitionDetailsRepository;
    private UniversityRepository universityRepository;

    public DataFormaterService(ContactDetailsRepository contactDetailsRepository, CampusRepository campusRepository,
            CollegeStatsRepository collegeStatsRepository, DocumentDetailsRepository documentDetailsRepository,
            RecognitionDetailsRepository recognitionDetailsRepository, UniversityRepository universityRepository) {
                this.contactDetailsRepository = contactDetailsRepository;
                this.campusRepository = campusRepository;
                this.collegeStatsRepository = collegeStatsRepository;
                this.documentDetailsRepository = documentDetailsRepository;
                this.recognitionDetailsRepository = recognitionDetailsRepository;
                this.universityRepository = universityRepository;
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
            return mapper.writeValueAsString(university.getRecognitionDetails());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing JSON", e);
        }

        // get the recognition details
    //     RecognitionDetails recognitionDetails = recognitionDetailsRepository.findByUniversity(university)
    //         .orElseThrow(() -> new RuntimeException("Recognition details not found"));
    }

    public void formatData(Integer universityId) {
        // get the university
        University university = universityRepository.findById(universityId)
            .orElseThrow(() -> new RuntimeException("University not found"));

        
        
        // get the contact details
        // Contact contactDetails = contactDetailsRepository.findByUniversity(university)
        //     .orElseThrow(() -> new RuntimeException("Contact details not found"));
        // // get the campus
        // Campus campus = campusRepository.findByUniversity(university)
        //     .orElseThrow(() -> new RuntimeException("Campus not found"));
        // // get the college stats
        // CollegeStats collegeStats = collegeStatsRepository.findByUniversity(university)
        //     .orElseThrow(() -> new RuntimeException("College stats not found"));
        // // get the document details
        // RecognitionDetails recognitionDetails = recognitionDetailsRepository.findByUniversity(university)
        //     .orElseThrow(() -> new RuntimeException("Recognition details not found"));
    }


}
    // collect the university first and then call all repos to get their data according to 
    // their Id in the university, and then convert the file into text from the S storage

