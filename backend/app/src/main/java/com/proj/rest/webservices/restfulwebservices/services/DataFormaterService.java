package com.proj.rest.webservices.restfulwebservices.services;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.rest.webservices.restfulwebservices.models.*;
import com.proj.rest.webservices.restfulwebservices.repositories.*;

@Service
public class DataFormaterService {

    private UniversityRepository universityRepository;

    public DataFormaterService(UniversityRepository universityRepository) {
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

    
}


