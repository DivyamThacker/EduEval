package com.proj.rest.webservices.restfulwebservices.university;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proj.rest.webservices.restfulwebservices.university.campus.Campus;
import com.proj.rest.webservices.restfulwebservices.university.contact.Contact;

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

    private String recognitionSection;
    private String recognitionDate;
    private String recognitionDocument;

    private String isUPE; //university with potential for excellence

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("university") // Prevent circular serialization
	private List<Campus> campuses;

    
    public University(Integer id, String name, String address, String city, String state, String pincode,
     String websiteUrl, String nature, String type, String establishmentDate, String priorStatus, String recognitionSection,
      String recognitionDate, String recognitionDocument, String isUPE, List<Campus> campuses, List<Contact> contactDetails) {
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
        this.priorStatus = priorStatus;
        this.recognitionSection = recognitionSection;
        this.recognitionDate = recognitionDate;
        this.recognitionDocument = recognitionDocument;
        this.isUPE = isUPE;
        this.campuses = campuses;
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
    
    public String getPriorStatus() {
        return priorStatus;
    }
    
    public void setPriorStatus(String priorStatus) {
        this.priorStatus = priorStatus;
    }
    
    public String getRecognitionSection() {
        return recognitionSection;
    }

    public String setRecognitionSection(String recognitionSection) {
        return this.recognitionSection = recognitionSection;
    }

    public String getRecognitionDate() {
        return recognitionDate;
    }

    public void setRecognitionDate(String recognitionDate) {
        this.recognitionDate = recognitionDate;
    }

    public String getRecognitionDocument() {
        return recognitionDocument;
    }

    public void setRecognitionDocument(String recognitionDocument) {
        this.recognitionDocument = recognitionDocument;
    }

    public String getIsUPE() {
        return isUPE;
    }

    public void setIsUPE(String isUPE) {
        this.isUPE = isUPE;
    }

    public List<Campus> getCampuses() {
        return campuses;
    }

    public void setCampuses(List<Campus> campuses) {
        this.campuses = campuses;
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
                ", recognitionSection='" + recognitionSection + '\'' +
                ", recognitionDate='" + recognitionDate + '\'' +
                ", recognitionDocument='" + recognitionDocument + '\'' +
                ", isUPE='" + isUPE + '\'' +
                ", campuses=" + campuses +
                '}';
    }

}


