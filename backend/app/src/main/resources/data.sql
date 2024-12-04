insert into university_details (are_sra_program,id,name,address,city,state,pincode,website_url,establishment_date,prior_status,nature,type)
values ('YES',1,'DHIRUBHAI AMBANI INSTITUE OF INFORMATION AND COMMUNICATION TECHNOLOGY (DAIICT)','Near Indroda Circle, Gandhinagar','Gandhinagar','Gujarat','382007','www.daiict.ac.in','06-08-2001','No','State Private University','Unitary');


insert into contact_details (id,name,  designation, email, phone, fax, telephone, university_id)
values (101, 'K S Dasgupta', 'Director', 'director@daiict.ac.in', '079-68261572', '079-68261710', '9327043614 ', '1');

insert into contact_details (id,name,  designation, email, phone, fax, telephone, university_id)
values (102, 'Anil Roy ', 'IQAC / CIQA coordinator', 'iqac_dir@daiict.ac.in', '9376163094 ', '079-68261567', '079-68261567', '1');

insert into document_details(id,file_name,file_identifier)
values (1001,'108378_9387_1_1668404817.pdf','1733289763797_108378_9387_1_1668404817.pdf');

insert into document_details(id,file_name,file_identifier)
values (1002,'doc.pdf','1733203606502_202101072_Assignment.pdf');

insert into document_details(id,file_name,file_identifier)
values (1003,'nac_cycle.pdf','1733290967666_nac_cycle.pdf');

-- 1733290967666_nac_cycle.pdf

-- insert into document_details(id,file_name,file_identifier)
-- values (1003,'2nd_wali.pdf','1733203606502_202101072_Assignment.pdf	');


-- insert into recognition_details(ID,RECOGNITION_DOCUMENT12B_ID  	RECOGNITION_DOCUMENT2F_ID  	UNIVERSITY_ID  	ISUPE  	RECOGNITION_DATE_UNDER_SECTION12B  	RECOGNITION_DATE_UNDER_SECTION2F  
-- )
insert into recognition_details(id,recognition_document12b_id,recognition_document2f_id,university_id,isupe,recognition_date_under_section12b,recognition_date_under_section2f)
values (10001,1001,1002,1,'True','24-01-2003','20-02-2001');

-- ID  	UNIVERSITY_ID  	ADDRESS  	BUILT_UP_AREA  	CAMPUS_AREA  	ESTABLISHMENT_DATE  	LOCATION  	RECOGNITION_DATE  	TYPE  	PROGRAMMES_OFFERED

insert into campus (id,university_id,address,built_up_area,campus_area,establishment_date,location,recognition_date,type,programmes_offered)
values (100001,1,'Near Indroda Circle,Gandhinagar','202350 ','50','20-02-2921','Urban','23-02-2021','Main campus','Under Graduate,Post Graduate,PhD');

insert into campus (id,university_id,address,built_up_area,campus_area,establishment_date,location,recognition_date,type,programmes_offered)
values (100002,1,'Gandhinagar','100','21','20-02-2921','Rural','23-02-2021','Sports','Basketball,Badminton');

-- AFFILIATED_COLLEGES  	AUTONOMOUS_COLLEGES  	COLLEGES_UNDER2F  	COLLEGES_UNDER2F12B  	COLLEGES_WITH_EXCELLENCE  	COLLEGES_WITH_PG_DEPARTMENTS  	COLLEGES_WITH_RESEARCH_DEPARTMENTS  	CONSTITUENT_COLLEGES  	ID  	NAAC_ACCREDITED  	RESEARCH_INSTITUTES  	UNIVERSITY_ID 

insert into college_stats (affiliated_colleges,autonomous_colleges,colleges_under2f,colleges_under2f12b,colleges_with_excellence,colleges_with_pg_departments,colleges_with_research_departments,constituent_colleges,id,naac_accredited,research_institutes,university_id)
values (0,0,0,0,0,0,0,0,0,0,0,1);

-- // ID  	SRA_DOCUMENT_ID  	UNIVERSITY_ID  	NAME 

insert into sra_program (id,sra_document_id,university_id,name)
values (1,1002,1,'PCI');

-- insert into sra_program (id,sra_document_id,university_id,name)
-- values (2,1002,1,'BCI');

-- insert into sra_program (id,sra_document_id,university_id,name)
-- values (3,1002,1,'COA');

-- COUNT  	ID  	UNIVERSITY_ID  	ACADEMIC_RANK  	GENDER  	HIGHEST_QUALIFICATION  	RECRUITMENT_STATUS  	TENURE
insert into faculty (count,id,university_id,academic_rank,gender,highest_qualification,recruitment_status,tenure)
values (10,1,1,'Professor','Male','Ph.D','Permanent','Sanctioned');

insert into faculty (count,id,university_id,academic_rank,gender,highest_qualification,recruitment_status,tenure)
values (20,2,1,'Professor','Female','PG','Permanent','Sanctioned');


insert into faculty (count,id,university_id,academic_rank,gender,highest_qualification,recruitment_status,tenure)
values (20,3,1,'Professor','Female','PG','Part Time','Recruited');


insert into faculty (count,id,university_id,academic_rank,gender,highest_qualification,recruitment_status,tenure)
values (20,4,1,'Associate Professor','Male','D.Sc/D.Litt','Part Time','Recruited');

insert into faculty (count,id,university_id,academic_rank,gender,highest_qualification,recruitment_status,tenure)
values (20,5,1,'Assistant Professor','Male','PG','Part Time','Yet to Recruit');

insert into faculty (count,id,university_id,academic_rank,gender,highest_qualification,recruitment_status,tenure)
values (20,6,1,'Professor','Male','M.Phil','Permanent','Yet to Recruit');

insert into faculty (count,id,university_id,academic_rank,gender,highest_qualification,recruitment_status,tenure)
values (50,7,1,'Associate Professor','Female','PH.D','Part Time','Yet to Recruit');

insert into faculty (count,id,university_id,academic_rank,gender,highest_qualification,recruitment_status,tenure)
values (20,8,1,'Associate Professor','Female','PG','Temporary','Contractual');

-- COUNT  	ID  	IS_TECHNICAL  	UNIVERSITY_ID  	GENDER  	RECRUITMENT_STATUS  
insert into non_teaching_staff(count,id,is_technical,university_id,gender,recruitment_status)
values (20,1,'YES',1,'Male','Sanctioned');

insert into non_teaching_staff(count,id,is_technical,university_id,gender,recruitment_status)
values (10,2,'YES',1,'Female','Sanctioned');

insert into non_teaching_staff(count,id,is_technical,university_id,gender,recruitment_status)
values (10,3,'YES',1,'Male','Recruited');
insert into non_teaching_staff(count,id,is_technical,university_id,gender,recruitment_status)
values (10,4,'YES',1,'Female','Recruited');
insert into non_teaching_staff(count,id,is_technical,university_id,gender,recruitment_status)
values (10,5,'YES',1,'Other','Recruited');

insert into non_teaching_staff(count,id,is_technical,university_id,gender,recruitment_status)
values (10,6,'YES',1,'Female','Yet to Recruit');
insert into non_teaching_staff(count,id,is_technical,university_id,gender,recruitment_status)
values (10,7,'YES',1,'Male','Yet to Recruit');
insert into non_teaching_staff(count,id,is_technical,university_id,gender,recruitment_status)
values (10,8,'YES',1,'Other','Yet to Recruit');

insert into non_teaching_staff(count,id,is_technical,university_id,gender,recruitment_status)
values (20,11,'NO',1,'Male','Sanctioned');

insert into non_teaching_staff(count,id,is_technical,university_id,gender,recruitment_status)
values (10,22,'NO',1,'Female','Sanctioned');

insert into non_teaching_staff(count,id,is_technical,university_id,gender,recruitment_status)
values (10,33,'NO',1,'Male','Recruited');
insert into non_teaching_staff(count,id,is_technical,university_id,gender,recruitment_status)
values (10,44,'NO',1,'Female','Recruited');
insert into non_teaching_staff(count,id,is_technical,university_id,gender,recruitment_status)
values (10,55,'NO',1,'Other','Recruited');

insert into non_teaching_staff(count,id,is_technical,university_id,gender,recruitment_status)
values (10,66,'NO',1,'Female','Yet to Recruit');
insert into non_teaching_staff(count,id,is_technical,university_id,gender,recruitment_status)
values (10,77,'NO',1,'Male','Yet to Recruit');
insert into non_teaching_staff(count,id,is_technical,university_id,gender,recruitment_status)
values (10,88,'NO',1,'Other','Yet to Recruit');
insert into non_teaching_staff(count,id,is_technical,university_id,gender,recruitment_status)
values (10,99,'NO',1,'Male','Contractual');
insert into non_teaching_staff(count,id,is_technical,university_id,gender,recruitment_status)
values (10,101,'NO',1,'Other','Contractual');


-- COUNT  	ID  	UNIVERSITY_ID  	GENDER  	TYPE 
insert into distinguished_academician (count,id,university_id,gender,type)
values (25,1,1,'Male','Emeritus');
insert into distinguished_academician (count,id,university_id,gender,type)
values (25,2,1,'Male','Adjunct');
insert into distinguished_academician (count,id,university_id,gender,type)
values (25,7,1,'Male','Visiting');
insert into distinguished_academician (count,id,university_id,gender,type)
values (25,4,1,'Other','Emeritus');
insert into distinguished_academician (count,id,university_id,gender,type)
values (25,3,1,'Female','Emeritus');

-- ID  	UNIVERSITY_ID  	CHAIR_NAME  	DEPARTMENT_NAME  	SPONSOR_NAME
insert into chair_details(id,university_id,chair_name,department_name,sponsor_name)
values (1,1,'Mayank Gour','Sports','Deependra');

insert into chair_details(id,university_id,chair_name,department_name,sponsor_name)
values (2,1,'Nakul Gour','Medical','Deependra');

insert into chair_details(id,university_id,chair_name,department_name,sponsor_name)
values (3,1,'Naman Patel','Finance','Dheeraj');


-- HRDC_ESTABLISHMENT_DATE  	HRDC_ORIENTATION_PROGRAM_COUNT  	HRDC_OWN_PROGRAM_COUNT  	HRDC_REFRESHER_COURSE_COUNT  	HRDCTOTAL_PROGRAM_COUNT  	ID  	UNIVERSITY_ID
insert into hrdc_details (hrdc_establishment_date,hrdc_orientation_program_count,hrdc_own_program_count,hrdc_refresher_course_count,hrdctotal_program_count,id,university_id)
values ('2024-12-15',23,32,33,23,1,1);

-- CGPA  	CYCLE_NUMBER  	ID  	PEER_TEAM_REPORT_ID  	UNIVERSITY_ID  	GRADE  	TYPE
-- ACCREDITATION_DETAILS
-- insert into accreditation_details (id,cgpa,cycle_number,peer_team_report_id,university_id,grade,type)
-- values (1,3.4,1,1002,1,'B','Accreditation');

-- ID  	REPORT_ID  	UNIVERSITY_ID  	DEPARTMENT_NAME
-- insert into department_evaluation (id,report_id,university_id,department_name)
-- values (1,1001,1,'Information and Communication Technology');

-- insert into department_evaluation (id,report_id,university_id,department_name)
-- values (2,1002,1,'Computer Science Engineering');

-- DOCUMENT_ID  	ID  	SECTION  	UNIVERSITY_ID  
-- insert into nep_details (document_id,id,section,university_id)
-- values (1001,1,0,1);