package com.proj.rest.webservices.restfulwebservices.services;


import org.springframework.stereotype.Service;

@Service
public class StaticDataService {
    private static final String[] nepSections = {" Multidisciplinary / interdisciplinary",
     "Academic bank of credits (ABC)", "Skill development", "Appropriate integration of Indian Knowledge system (teaching in Indian Language,\r\n" + //
                  "culture, using online course)", "Focus on Outcome based education (OBE)", 
                  "Distance education/online education"};

    private static final String[]  electoralLiteracyQuestions = {"Whether Electoral Literacy Club (ELC) has been set up in the University?\r",
    "Whether students’ co-ordinator and co-ordinating faculty members are appointed by the\r\n" + //
                "University and whether the ELCs are functional? Whether the ELCs are representative in\r\n" + //
                "character? ", "What innovative programmes and initiatives undertaken by the ELCs? These may\r\n" + //
                                        "include voluntary contribution by the students in electoral processes-participation in\r\n" + //
                                        "voter registration of students and communities where they come from, assisting district\r\n" + //
                                        "election administration in conduct of poll, voter awareness campaigns, promotion of\r\n" + //
                                        "ethical voting, enhancing participation of the under privileged sections of society\r\n" + //
                                        "especially transgender, commercial sex workers, disabled persons, senior citizens, etc.",
    "Any socially relevant projects/initiatives taken by University in electoral related issues\r\n" + //
                "especially research projects, surveys, awareness drives, creating content, publications\r\n" + //
                "highlighting their contribution to advancing democratic values and participation in\r\n" + //
                "electoral processes, etc.",
                "Extent of students above 18 years who are yet to be enrolled as voters in the electoral roll\r\n" + //
                                        "and efforts by ELCs as well as efforts by the University to institutionalize mechanisms to\r\n" + //
                                        "register eligible students as voters."};

    public String[]  getNepSections(){
        return nepSections;
    }

    public String[]  getElectoralLiteracyQuestions() {
        return electoralLiteracyQuestions;
    }
}

