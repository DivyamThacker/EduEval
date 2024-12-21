package com.proj.rest.webservices.restfulwebservices.services;


import java.util.List;

import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class StaticDataService {
    private static final List<String> nepSections = Arrays.asList(
        "Multidisciplinary / interdisciplinary",
        "Academic bank of credits (ABC)",
        "Skill development",
        "Appropriate integration of Indian Knowledge system (teaching in Indian Language, culture, using online course)",
        "Focus on Outcome based education (OBE)",
        "Distance education/online education"
    );

    private static final List<String> electoralLiteracyQuestions = Arrays.asList(
        "Whether Electoral Literacy Club (ELC) has been set up in the University?\r",
        "Whether students’ co-ordinator and co-ordinating faculty members are appointed by the\r\n" +
        "University and whether the ELCs are functional? Whether the ELCs are representative in\r\n" +
        "character?",
        "What innovative programmes and initiatives undertaken by the ELCs? These may\r\n" +
        "include voluntary contribution by the students in electoral processes-participation in\r\n" +
        "voter registration of students and communities where they come from, assisting district\r\n" +
        "election administration in conduct of poll, voter awareness campaigns, promotion of\r\n" +
        "ethical voting, enhancing participation of the under privileged sections of society\r\n" +
        "especially transgender, commercial sex workers, disabled persons, senior citizens, etc.",
        "Any socially relevant projects/initiatives taken by University in electoral related issues\r\n" +
        "especially research projects, surveys, awareness drives, creating content, publications\r\n" +
        "highlighting their contribution to advancing democratic values and participation in\r\n" +
        "electoral processes, etc.",
        "Extent of students above 18 years who are yet to be enrolled as voters in the electoral roll\r\n" +
        "and efforts by ELCs as well as efforts by the University to institutionalize mechanisms to\r\n" +
        "register eligible students as voters."
    );

    public List<String>   getNepSections(){
        return nepSections;
    }

    public List<String>  getElectoralLiteracyQuestions() {
        return electoralLiteracyQuestions;
    }

    public String getJsonData() {
        return jsonData;
    }

    private static final String jsonData = """
{
"universityInfo": {
"name": "DHIRUBHAI AMBANI INSTITUTE OF INFORMATION AND COMMUNICATION TECHNOLOGY (DA-IICT)",
"address": "Near Indroda Circle, Gandhinagar",
"city": "Gandhinagar",
"state": "Gujarat",
"pin": "382007",
"website": "https://www.daiict.ac.in"
},
"contacts": [
{
"designation": "Director",
"name": "K S Dasgupta",
"telephone": "079-68261572",
"mobile": "9327043614",
"fax": "079-68261710",
"email": "director@daiict.ac.in"
},
{
"designation": "IQAC / CIQA Coordinator",
"name": "Anil Roy",
"telephone": "079-68261567",
"mobile": "9376163094",
"fax": "079-68261710",
"email": "iqac_dir@daiict.ac.in"
}
],
"recognitionDetails": [
{
"section": "2f of UGC",
"date": "30-11-2004",
"document": "View Document"
},
{
"section": "12B of UGC",
"date": "Not Applicable",
"document": "Not Available"
}
],
"campusDetails": [
{
"type": "Main Campus",
"address": "Near Indroda Circle, Gandhinagar",
"location": "Urban",
"area": "50",
"builtUpArea": "202350",
"programs": "Undergraduate, Postgraduate, PhD",
"establishmentDate": "06-08-2001",
"dateOfRecognitionByUGC": ""
}
],
"natureOfUniversity": "State Private University",
"typeOfUniversity": "Unitary",
"establishmentDate": "06-08-2001",
"priorDate": "NA",
"universityPotential": "No",
"academicDetails": {
"Constituent Colleges": 0,
"Affiliated Colleges": 0,
"Colleges Under 2(f)": 0,
"Colleges Under 2(f) and 12B": 0,
"NAAC Accredited Colleges": 0,
"Colleges with Potential for Excellence (UGC)": 0,
"Autonomous Colleges": 0,
"Colleges with Postgraduate Departments": 0,
"Colleges with Research Departments": 0,
"University Recognized Research Institutes/Centers": 0
},
"isSRAProgram": "Yes",
"sraPrograms": [
{ "program": "PCI", "document": "url1.pdf" },
{ "program": "BCI", "document": "url2.pdf" },
{ "program": "COA", "document": "url3.pdf" }
],
"affiliatedInstitution": [
{ "Type of Colleges": "Education/Teachers Training", "Permanent": 4, "Temporary": 0, "Total": 4 },
{ "Type of Colleges": "Arts", "Permanent": 1, "Temporary": 0, "Total": 1 },
{ "Type of Colleges": "Rehabilitation Sciences", "Permanent": 2, "Temporary": 0, "Total": 2 },
{ "Type of Colleges": "Engineering/Technology/Architecture/Design", "Permanent": 1, "Temporary": 0, "Total": 1 },
{ "Type of Colleges": "General", "Permanent": 64, "Temporary": 0, "Total": 64 },
{
"Type of Colleges": "Medicine & Surgery/Ayurveda/Unani/Homeopathy/Health & Allied Sciences/Paramedical/Sciences",
"Permanent": 18,
"Temporary": 0,
"Total": 18
}
],
"TeachingFaculty": {
"Sanctioned": {
"Professor": { "Male": 10, "Female": 5, "Others": 0, "Total": 15 },
"Associate Professor": { "Male": 8, "Female": 7, "Others": 1, "Total": 16 },
"Assistant Professor": { "Male": 12, "Female": 10, "Others": 0, "Total": 22 }
},
"Recruited": {
"Professor": { "Male": 8, "Female": 4, "Others": 0, "Total": 12 },
"Associate Professor": { "Male": 7, "Female": 5, "Others": 0, "Total": 12 },
"Assistant Professor": { "Male": 10, "Female": 8, "Others": 1, "Total": 19 }
},
"Yet to Recruit": {
"Professor": { "Male": 2, "Female": 1, "Others": 0, "Total": 3 },
"Associate Professor": { "Male": 1, "Female": 2, "Others": 1, "Total": 4 },
"Assistant Professor": { "Male": 2, "Female": 2, "Others": 0, "Total": 4 }
},
"Contractual": {
"Professor": { "Male": 1, "Female": 1, "Others": 0, "Total": 2 },
"Associate Professor": { "Male": 2, "Female": 0, "Others": 0, "Total": 2 },
"Assistant Professor": { "Male": 5, "Female": 3, "Others": 0, "Total": 8 }
}
},
"NonTeachingStaff": {
"Sanctioned": { "Male": 10, "Female": 8, "Others": 2, "Total": 20 },
"Recruited": { "Male": 8, "Female": 6, "Others": 1, "Total": 15 },
"Yet to Recruit": { "Male": 2, "Female": 2, "Others": 1, "Total": 5 },
"Contractual": { "Male": 5, "Female": 3, "Others": 0, "Total": 8 }
},
"TechnicalStaff": {
"Sanctioned": { "Male": 15, "Female": 10, "Others": 0, "Total": 25 },
"Recruited": { "Male": 12, "Female": 8, "Others": 0, "Total": 20 },
"Yet to Recruit": { "Male": 3, "Female": 2, "Others": 0, "Total": 5 },
"Contractual": { "Male": 6, "Female": 4, "Others": 0, "Total": 10 }
},
"QualificationDetails": {
"permanentTeacher": {
"D.sc/D.Litt": {
"Professor": { "Male": 3, "Female": 2, "Others": 0 },
"Associate Professor": { "Male": 4, "Female": 1, "Others": 0 },
"Assistant Professor": { "Male": 5, "Female": 3, "Others": 0 }
},
"Ph.D.": {
"Professor": { "Male": 6, "Female": 4, "Others": 1 },
"Associate Professor": { "Male": 8, "Female": 6, "Others": 0 },
"Assistant Professor": { "Male": 12, "Female": 8, "Others": 1 }
},
"M.Phil.": {
"Professor": { "Male": 1, "Female": 0, "Others": 0 },
"Associate Professor": { "Male": 2, "Female": 2, "Others": 0 },
"Assistant Professor": { "Male": 3, "Female": 2, "Others": 0 }
},
"PG": {
"Professor": { "Male": 2, "Female": 1, "Others": 0 },
"Associate Professor": { "Male": 3, "Female": 2, "Others": 0 },
"Assistant Professor": { "Male": 5, "Female": 3, "Others": 1 }
}
},
"temporaryTeacher": {
"D.sc/D.Litt": {
"Professor": { "Male": 1, "Female": 0, "Others": 0 },
"Associate Professor": { "Male": 0, "Female": 1, "Others": 0 },
"Assistant Professor": { "Male": 1, "Female": 1, "Others": 0 }
},
"Ph.D.": {
"Professor": { "Male": 3, "Female": 2, "Others": 0 },
"Associate Professor": { "Male": 1, "Female": 2, "Others": 0 },
"Assistant Professor": { "Male": 4, "Female": 3, "Others": 0 }
},
"M.Phil.": {
"Professor": { "Male": 0, "Female": 0, "Others": 0 },
"Associate Professor": { "Male": 0, "Female": 1, "Others": 0 },
"Assistant Professor": { "Male": 1, "Female": 1, "Others": 0 }
},
"PG": {
"Professor": { "Male": 1, "Female": 1, "Others": 0 },
"Associate Professor": { "Male": 2, "Female": 2, "Others": 0 },
"Assistant Professor": { "Male": 3, "Female": 2, "Others": 0 }
}
},
"partTimeTeacher": {
"D.sc/D.Litt": { "Professor": { "Male": 1, "Female": 1, "Others": 0 }, "Associate Professor": { "Male": 1, "Female": 0, "Others": 0 }, "Assistant Professor": { "Male": 0, "Female": 1, "Others": 0 } },
"Ph.D.": { "Professor": { "Male": 2, "Female": 2, "Others": 0 }, "Associate Professor": { "Male": 3, "Female": 1, "Others": 0 }, "Assistant Professor": { "Male": 4, "Female": 3, "Others": 0 } },
"M.Phil.": { "Professor": { "Male": 1, "Female": 1, "Others": 0 }, "Associate Professor": { "Male": 1, "Female": 1, "Others": 0 }, "Assistant Professor": { "Male": 1, "Female": 2, "Others": 0 } },
"PG": { "Professor": { "Male": 3, "Female": 2, "Others": 0 }, "Associate Professor": { "Male": 4, "Female": 2, "Others": 0 }, "Assistant Professor": { "Male": 5, "Female": 3, "Others": 0 } }
}
},
"DistinguishedAcademicians": {
    "Emeritus Professor": { "Male": 2, "Female": 1, "Others": 0, "Total": 3 },
    "Adjunct Professor": { "Male": 3, "Female": 2, "Others": 1, "Total": 6 },
    "Visiting Professor": { "Male": 4, "Female": 3, "Others": 0, "Total": 7 }
  },
"chairs": [
                {
                    "serialNo": 1,
                    "department": "Department of Computer Science",
                    "chair": "AI Research Chair",
                    "sponsor": "Google AI"
                },
                {
                    "serialNo": 2,
                    "department": "Department of Physics",
                    "chair": "Quantum Mechanics Chair",
                    "sponsor": "NASA"
                }
    ],

    "programmes": [
        {
          "name": "PG",
          "categories": [
            {
              "gender": "Male",
              "fromState": 50,
              "fromOtherStates": 30,
              "nri": 5,
              "foreign": 10,
              "total": 95
            },
            {
              "gender": "Female",
              "fromState": 60,
              "fromOtherStates": 40,
              "nri": 7,
              "foreign": 8,
              "total": 115
            },
            {
              "gender": "Others",
              "fromState": 5,
              "fromOtherStates": 3,
              "nri": 1,
              "foreign": 2,
              "total": 11
            }
          ]
        },
        {
          "name": "UG",
          "categories": [
            {
              "gender": "Male",
              "fromState": 80,
              "fromOtherStates": 50,
              "nri": 10,
              "foreign": 15,
              "total": 155
            },
            {
              "gender": "Female",
              "fromState": 90,
              "fromOtherStates": 60,
              "nri": 12,
              "foreign": 20,
              "total": 182
            },
            {
              "gender": "Others",
              "fromState": 10,
              "fromOtherStates": 5,
              "nri": 2,
              "foreign": 3,
              "total": 20
            }
          ]
        },
        {
          "name": "PG Diploma recognized by statutory authority",
          "categories": [
            {
              "gender": "Male",
              "fromState": 25,
              "fromOtherStates": 15,
              "nri": 3,
              "foreign": 4,
              "total": 47
            },
            {
              "gender": "Female",
              "fromState": 30,
              "fromOtherStates": 20,
              "nri": 5,
              "foreign": 6,
              "total": 61
            },
            {
              "gender": "Others",
              "fromState": 3,
              "fromOtherStates": 2,
              "nri": 0,
              "foreign": 1,
              "total": 6
            }
          ]
        }
      ]

}""";
}

