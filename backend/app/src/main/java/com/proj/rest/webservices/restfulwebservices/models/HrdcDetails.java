package com.proj.rest.webservices.restfulwebservices.models;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "hrdc_details")
@Data
@NoArgsConstructor
public class HrdcDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date hrdcEstablishmentDate=null;
    private Integer hrdcOrientationProgrammesCount=0;
    private Integer hrdcRefresherCourseCount=0;
    private Integer hrdcOwnProgrammesCount=0;
    private Integer hrdctotalProgrammes=0; //last 5 years

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private University university;
}
