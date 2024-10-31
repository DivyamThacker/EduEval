package com.proj.rest.webservices.restfulwebservices.university;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "university_details")
public class University {
    protected University() {
		
	}
	
	  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("university") // Prevent circular serialization
	private List<Contact> contactDetails;

    private String name;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private String websiteUrl;
    private String nature;
    private String type;
    private String establishmentDate;
    private String priorStatus;
    private String priorEstablishmentDate;
    private String recognitionDate;

    public University(Integer id, String name, String address, String city, String state, String pincode, String websiteUrl, String nature, String type, String establishmentDate,String recognitionDate, String priorStatus, String priorEstablishmentDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.websiteUrl = websiteUrl;
        this.nature = nature;
        this.type = type;
        this.establishmentDate = establishmentDate;
        this.recognitionDate = recognitionDate;
        this.priorStatus = priorStatus;
        this.priorEstablishmentDate = priorEstablishmentDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Contact> getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(List<Contact> contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getPriorStatus() {
        return priorStatus;
    }

    public void setPriorStatus(String priorStatus) {
        this.priorStatus = priorStatus;
    }

    public String getPriorEstablishmentDate() {
        return priorEstablishmentDate;
    }

    public void setPriorEstablishmentDate(String priorEstablishmentDate) {
        this.priorEstablishmentDate = priorEstablishmentDate;
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", contactDetails=" + contactDetails +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode='" + pincode + '\'' +
                ", websiteUrl='" + websiteUrl + '\'' +
                ", nature='" + nature + '\'' +
                ", type='" + type + '\'' +
                ", establishmentDate='" + establishmentDate + '\'' +
                ", priorStatus='" + priorStatus + '\'' +
                ", priorEstablishmentDate='" + priorEstablishmentDate + '\'' +
                '}';
    }

}


