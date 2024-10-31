package com.proj.rest.webservices.restfulwebservices.staff;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Staff {
    protected Staff() {
		
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String designation;
    private String department;
    private String email;
    private String phone;
    private String qualification;
    private String experienceYears;
    private Boolean isTeaching;

    public Staff(Integer id, String name, String designation, String department, String email, String phone, String qualification, String experienceYears, Boolean isTeaching) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.department = department;
        this.email = email;
        this.phone = phone;
        this.qualification = qualification;
        this.experienceYears = experienceYears;
        this.isTeaching = isTeaching;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(String experienceYears) {
        this.experienceYears = experienceYears;
    }

    public Boolean getIsTeaching() {
        return isTeaching;
    }

    public void setIsTeaching(Boolean isTeaching) {
        this.isTeaching = isTeaching;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", department='" + department + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", qualification='" + qualification + '\'' +
                ", experienceYears='" + experienceYears + '\'' +
                ", isTeaching=" + isTeaching +
                '}';
    }

}
