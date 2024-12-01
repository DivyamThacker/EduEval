
insert into university_details (id, name, address, city, state, pincode)
values (10001, 'Example University', '123 University St', 'Sample City', 'Sample State', '123456');

insert into university_details (id,name,address,city,state,pincode,website_url,establishment_date,prior_status,nature,type)
values (1,'DHIRUBHAI AMBANI INSTITUE OF INFORMATION AND COMMUNICATION TECHNOLOGY','GANDHINAGAR','GANDHINAGAR','GUJARAT','382007','www.daiict.ac.in','24-1-2003','No','Private','Unitary');

insert into contact_details (id,name,  designation, email, phone, fax, telephone, university_id)
values (101, 'John Doe', 'Manager', 'johndoe@gmail.com', '1234567890', '1234567890', '1234567890', '1');

insert into contact_details (id,name,  designation, email, phone, fax, telephone, university_id)
values (102, 'Mayank Gour', 'Manager', 'johndoe@gmail.com', '1234567890', '1234567890', '1234567890', '1');

insert into document_details(id,file_name,file_identifier)
values (1001,'202101072_Assignment.pdf','1733055996043_202101072_Assignment.pdf');

insert into document_details(id,file_name,file_identifier)
values (1002,'202101072_Assignment.pdf','1733055998265_202101072_Assignment.pdf');

-- insert into recognition_details(ID,RECOGNITION_DOCUMENT12B_ID  	RECOGNITION_DOCUMENT2F_ID  	UNIVERSITY_ID  	ISUPE  	RECOGNITION_DATE_UNDER_SECTION12B  	RECOGNITION_DATE_UNDER_SECTION2F  
-- )
insert into recognition_details(id,recognition_document12b_id,recognition_document2f_id,university_id,isupe,recognition_date_under_section12b,recognition_date_under_section2f)
values (10001,1001,1002,1,'True','24-01-2003','20-02-2001');

-- ID  	UNIVERSITY_ID  	ADDRESS  	BUILT_UP_AREA  	CAMPUS_AREA  	ESTABLISHMENT_DATE  	LOCATION  	RECOGNITION_DATE  	TYPE  	PROGRAMMES_OFFERED

insert into campus (id,university_id,address,built_up_area,campus_area,establishment_date,location,recognition_date,type,programmes_offered)
values (100001,1,'Gandhinagar','100','21','20-02-2921','Rural','23-02-2021','Education','UG,PG');

insert into campus (id,university_id,address,built_up_area,campus_area,establishment_date,location,recognition_date,type,programmes_offered)
values (100002,1,'Gandhinagar','100','21','20-02-2921','Rural','23-02-2021','Sports','Basketball,Badminton');

-- AFFILIATED_COLLEGES  	AUTONOMOUS_COLLEGES  	COLLEGES_UNDER2F  	COLLEGES_UNDER2F12B  	COLLEGES_WITH_EXCELLENCE  	COLLEGES_WITH_PG_DEPARTMENTS  	COLLEGES_WITH_RESEARCH_DEPARTMENTS  	CONSTITUENT_COLLEGES  	ID  	NAAC_ACCREDITED  	RESEARCH_INSTITUTES  	UNIVERSITY_ID 

insert into college_stats (affiliated_colleges,autonomous_colleges,colleges_under2f,colleges_under2f12b,colleges_with_excellence,colleges_with_pg_departments,colleges_with_research_departments,constituent_colleges,id,naac_accredited,research_institutes,university_id)
values (0,1,1,2,1,3,7,2,3,4,10,1);