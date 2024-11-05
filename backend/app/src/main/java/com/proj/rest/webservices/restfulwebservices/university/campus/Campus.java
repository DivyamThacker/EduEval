package com.proj.rest.webservices.restfulwebservices.university.campus;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proj.rest.webservices.restfulwebservices.university.University;

@Entity
public class Campus {

    protected Campus() {}

    @Id
	@GeneratedValue
	private Integer id;

    private String address;
    private String location;
    private String campusArea;
    private String builtUpArea;
    private List<String> programmesOffered;
    private String establishmentDate;
    private String recognitionDate;

    @ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private University university;

    public Campus(Integer id, String address, String location, String campusArea, String builtUpArea, List<String> programmesOffered, String establishmentDate, String recognitionDate, University university) {
        this.id = id;
        this.address = address;
        this.location = location;
        this.campusArea = campusArea;
        this.builtUpArea = builtUpArea;
        this.programmesOffered = programmesOffered;
        this.establishmentDate = establishmentDate;
        this.recognitionDate = recognitionDate;
        this.university = university;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCampusArea() {
        return campusArea;
    }

    public void setCampusArea(String campusArea) {
        this.campusArea = campusArea;
    }

    public String getBuiltUpArea() {
        return builtUpArea;
    }

    public void setBuiltUpArea(String builtUpArea) {
        this.builtUpArea = builtUpArea;
    }

    public List<String> getProgrammesOffered() {
        return programmesOffered;
    }

    public void setProgrammesOffered(List<String> programmesOffered) {
        this.programmesOffered = programmesOffered;
    }

    public String getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(String establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public String getRecognitionDate() {
        return recognitionDate;
    }

    public void setRecognitionDate(String recognitionDate) {
        this.recognitionDate = recognitionDate;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "Campus{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", location='" + location + '\'' +
                ", campusArea='" + campusArea + '\'' +
                ", builtUpArea='" + builtUpArea + '\'' +
                ", programmesOffered=" + programmesOffered +
                ", establishmentDate='" + establishmentDate + '\'' +
                ", recognitionDate='" + recognitionDate + '\'' +
                ", university=" + university +
                '}';
    }



}
