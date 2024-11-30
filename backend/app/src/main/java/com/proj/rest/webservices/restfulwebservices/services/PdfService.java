package com.proj.rest.webservices.restfulwebservices.services;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.google.gson.*;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.kernel.pdf.action.PdfAction;

@Service
public class PdfService {
    private static final String SRC = "src/main/resources/static/input.html";
    private static final String DEST = "target/classes/UniversityProfile.pdf";
    // String arialFontPath =
    // "EduEval/backend/app/src/main/resources/static/Arial.ttf";
    // PdfFont arialFont = PdfFontFactory.createFont(arialFontPath,
    // PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED);

    public void convertHtmlToPdf() throws IOException {
        try {
            HtmlConverter.convertToPdf(new FileInputStream(SRC),
                    new FileOutputStream(DEST));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void generatePdf() throws IOException {
        try {
            // JSON String
            String jsonData = """
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
  }
}""";
            // Parse JSON data
            JSONObject jsonObject = new JSONObject(jsonData);
            JsonObject tempJsonObject = JsonParser.parseString(jsonData).getAsJsonObject();
            // Call the function to generate PDF
            generateUniversityProfilePDF(jsonObject, tempJsonObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateUniversityProfilePDF(JSONObject jsonObject, JsonObject tempJsonObject) throws Exception {
        // Extract university info
        JSONObject universityInfo = jsonObject.getJSONObject("universityInfo");
        String name = universityInfo.getString("name");
        String address = universityInfo.getString("address");
        String city = universityInfo.getString("city");
        String state = universityInfo.getString("state");
        String pin = universityInfo.getString("pin");
        String website = universityInfo.getString("website");

        // Extract other data as usual
        JSONArray contactsArray = jsonObject.getJSONArray("contacts");
        JSONArray recognitionDetails = jsonObject.getJSONArray("recognitionDetails");
        JSONArray campusDetails = jsonObject.getJSONArray("campusDetails");
        JSONObject academicDetails = jsonObject.getJSONObject("academicDetails");
        String natureOfUniversity = jsonObject.getString("natureOfUniversity");
        String typeOfUniversity = jsonObject.getString("typeOfUniversity");
        String establishmentDate = jsonObject.getString("establishmentDate");
        String priorDate = jsonObject.getString("priorDate");
        String universityPotential = jsonObject.getString("universityPotential");
        String isSRA = jsonObject.getString("isSRAProgram");
        JSONObject facultyData = jsonObject.getJSONObject("TeachingFaculty");
        JsonArray affiliatedInstitutionArray = tempJsonObject.getAsJsonArray("affiliatedInstitution");
        JSONArray sraProgramsArray = jsonObject.getJSONArray("sraPrograms");

        // Create PDF document
        PdfWriter writer = new PdfWriter(DEST);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);
        document.setMargins(20, 20, 20, 20);

        // Add Header
        addHeader(document, "Self Study Report of " + name);

        // Add Sections
        document.add(new Paragraph("2. PROFILE").setBold().setFontSize(15).setMarginBottom(5));
        document.add(new LineSeparator(new SolidLine(1f)).setStrokeWidth(1f).setMarginBottom(5));

        document.add(new Paragraph("2.1 BASIC INFORMATION").setBold().setFontSize(15).setMarginBottom(5));
        addUniversityInfoTable(document, name, address, city, state, pin, website);

        // Add Dynamic Tables
        addDynamicContactsTable(document, contactsArray);
        addSimpleRowTable(document, "Nature of University", natureOfUniversity);
        addSimpleRowTable(document, "Type of University", typeOfUniversity);
        addEstablishmentTable(document, "Establishment Date of the University", establishmentDate,
                priorDate.equals("NA") ? "" : priorDate);

        document.add(new AreaBreak());

        // Add other sections
        addRecognitionDetailsTable(document, recognitionDetails);
        addSimpleRowTable(document, "University with Potential for Excellence", universityPotential);
        addCampusDetailsTable(document, campusDetails);

        // Academic Info
        document.add(new Paragraph("2.2 ACADEMIC INFORMATION").setBold().setFontSize(15).setMarginBottom(5));

        // add affilated institution if any
        document.add(new Paragraph("Affiliated Institutions to the University").setBold().setFontSize(10)
                .setMarginBottom(5));

        if (!natureOfUniversity.equalsIgnoreCase("private") &&
                !natureOfUniversity.equalsIgnoreCase("deemed to be")) {
            addAffiliatedInstitution(document, affiliatedInstitutionArray);
        } else {
            document.add(new Paragraph("(Not applicable for private and deemed to be Universities)")
                    .setFontSize(10).setItalic());
        }
        document.add(new Paragraph("Furnish the Details of Colleges of University\r").setBold().setFontSize(10));
        addAcademicTable(document, academicDetails);
        // addSRATable(document, isSRA);
        addDynamicSRATable(document, isSRA, sraProgramsArray);
        // Faculty Data
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("Details Of Teaching & Non-Teaching Staff Of University").setBold().setFontSize(10));
        addTeachingFacultyTable(document, facultyData);
        document.add(new Paragraph("\n")); // Add some space between tables

        addStaffTable(document, "Non-Teaching Staff", jsonObject.getJSONObject("NonTeachingStaff"));
        document.add(new Paragraph("\n")); // Add some space between tables
        addStaffTable(document, "Technical Staff", jsonObject.getJSONObject("TechnicalStaff"));
        document.add(new Paragraph("\n")); // Add some space between tables

        // add Qualification Details
        document.add(new Paragraph("Qualifications of the Teaching Staff ").setBold().setFontSize(10));
        addQualificationTable(document, "Permanent Teachers", jsonObject.getJSONObject("QualificationDetails").getJSONObject("permanentTeacher"));
        document.add(new Paragraph("\n")); // Add space
        addQualificationTable(document, "Temporary Teachers", jsonObject.getJSONObject("QualificationDetails").getJSONObject("temporaryTeacher"));
        document.add(new Paragraph("\n")); // Add space
        addQualificationTable(document, "Part-time Teachers", jsonObject.getJSONObject("QualificationDetails").getJSONObject("partTimeTeacher"));

        // Close Document
        document.close();
        System.out.println("Extended PDF Created!");
    }

// Method to create the qualification table
    private static void addQualificationTable(Document document, String title, JSONObject jsonObject) {
        
        // Create table with 11 columns (Qualification + 3 Columns per Teacher Type + Total)
        // Title
        Table table = new Table(UnitValue.createPercentArray(new float[]{3, 1, 1, 1, 1, 1, 1, 1, 1, 1,2}))
                .useAllAvailableWidth();
        table.addHeaderCell(new Cell(1, 11).add(new Paragraph(title).setBold().setFontSize(10))
                .setFontSize(10).setPadding(5).setTextAlignment(TextAlignment.CENTER));
        // Header Row
        table.addCell(createHeaderCell("Highest Qualification",2,1));
        table.addCell(createHeaderCell("Professor",2,3));
        table.addCell(createHeaderCell("Associate Professor",2,3));
        table.addCell(createHeaderCell("Assistant Professor",2,3));
        table.addCell(createHeaderCell("Total",2,1));

        // Sub-header Row
        table.addCell("");
        for (int i = 0; i < 3; i++) {
            table.addCell(createSubHeaderCell("Male"));
            table.addCell(createSubHeaderCell("Female"));
            table.addCell(createSubHeaderCell("Others"));
        }
        table.addCell(""); // Total column

        // Data Rows
        String[] qualifications = {"D.sc/D.Litt", "Ph.D.", "M.Phil.", "PG"};
        for (String qualification : qualifications) {
            table.addCell(createBodyCell(qualification));
            JSONObject profData = jsonObject.getJSONObject(qualification).getJSONObject("Professor");
            table.addCell(createBodyCell(String.valueOf(profData.getInt("Male"))));
            table.addCell(createBodyCell(String.valueOf(profData.getInt("Female"))));
            table.addCell(createBodyCell(String.valueOf(profData.getInt("Others"))));

            JSONObject assocProfData = jsonObject.getJSONObject(qualification).getJSONObject("Associate Professor");
            table.addCell(createBodyCell(String.valueOf(assocProfData.getInt("Male"))));
            table.addCell(createBodyCell(String.valueOf(assocProfData.getInt("Female"))));
            table.addCell(createBodyCell(String.valueOf(assocProfData.getInt("Others"))));

            JSONObject assistProfData = jsonObject.getJSONObject(qualification).getJSONObject("Assistant Professor");
            table.addCell(createBodyCell(String.valueOf(assistProfData.getInt("Male"))));
            table.addCell(createBodyCell(String.valueOf(assistProfData.getInt("Female"))));
            table.addCell(createBodyCell(String.valueOf(assistProfData.getInt("Others"))));

            int total = profData.getInt("Male") + profData.getInt("Female") + profData.getInt("Others")
                    + assocProfData.getInt("Male") + assocProfData.getInt("Female") + assocProfData.getInt("Others")
                    + assistProfData.getInt("Male") + assistProfData.getInt("Female") + assistProfData.getInt("Others");
            table.addCell(createBodyCell(String.valueOf(total)));
        }

        // Add table to document
        document.add(table);
    }

    private static Cell createHeaderCell(String content,int rowspan,int colspan) {
        return new Cell(rowspan, colspan)
                .add(new Paragraph(content).setBold())
                .setTextAlignment(TextAlignment.CENTER);
    }

    private static Cell createSubHeaderCell(String content) {
        return new Cell().add(new Paragraph(content).setBold()).setTextAlignment(TextAlignment.CENTER);
    }



    private static void addStaffTable(Document document, String title, JSONObject jsonObject) {
        // Title for the table
        document.add(new Paragraph(title).setBold().setTextAlignment(TextAlignment.LEFT));

        // Create table with 5 columns
        Table table = new Table(UnitValue.createPercentArray(new float[]{2, 2, 2, 2, 2}))
                .useAllAvailableWidth();

        // Add header row
        table.addCell(createHeaderCell(""));
        table.addCell(createHeaderCell("Male"));
        table.addCell(createHeaderCell("Female"));
        table.addCell(createHeaderCell("Others"));
        table.addCell(createHeaderCell("Total"));

        // Populate table rows dynamically from JSON
        String[] categories = {"Sanctioned", "Recruited", "Yet to Recruit", "Contractual"};
        for (String category : categories) {
            table.addCell(createBodyCell(category));
            JSONObject data = jsonObject.getJSONObject(category);
            table.addCell(createBodyCell(String.valueOf(data.getInt("Male"))));
            table.addCell(createBodyCell(String.valueOf(data.getInt("Female"))));
            table.addCell(createBodyCell(String.valueOf(data.getInt("Others"))));
            table.addCell(createBodyCell(String.valueOf(data.getInt("Total"))));
        }

        // Add table to document
        document.add(table);
    }

    // Helper to create header cell
    private static Cell createHeaderCell(String content) {
        return new Cell()
                .add(new Paragraph(content).setBold())
                .setTextAlignment(TextAlignment.CENTER);
    }

    // Helper to create body cell
    private static Cell createBodyCell(String content) {
        return new Cell()
                .add(new Paragraph(content))
                .setTextAlignment(TextAlignment.CENTER);
    }


    private static void addTeachingFacultyTable(Document document, JSONObject facultyData) {
        // Create table with 13 columns
        Table table = new Table(UnitValue.createPercentArray(new float[]{3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}))
                .useAllAvailableWidth();

        
        table.addCell(createBodyCell("", 1,3));

        table.addCell(createHeaderCell("Designation", 12));

        table.addCell(createHeaderCell("Professor", 4));
        table.addCell(createHeaderCell("Associate Professor", 4));
        table.addCell(createHeaderCell("Assistant Professor", 4));


        // Header Row 3
        for (int i = 0; i < 3; i++) {
            table.addCell(createHeaderCell("Male", 1));
            table.addCell(createHeaderCell("Female", 1));
            table.addCell(createHeaderCell("Others", 1));
            table.addCell(createHeaderCell("Total", 1));
        }

        // Populate table rows dynamically from JSON
        String[] categories = {"Sanctioned", "Recruited", "Yet to Recruit", "Contractual"};
        String[] designations = {"Professor", "Associate Professor", "Assistant Professor"};

        for (String category : categories) {
            table.addCell(createBodyCell(category, 1));
            for (String designation : designations) {
                JSONObject data = facultyData.getJSONObject(category).getJSONObject(designation);
                table.addCell(createBodyCell(String.valueOf(data.getInt("Male")), 1));
                table.addCell(createBodyCell(String.valueOf(data.getInt("Female")), 1));
                table.addCell(createBodyCell(String.valueOf(data.getInt("Others")), 1));
                table.addCell(createBodyCell(String.valueOf(data.getInt("Total")), 1));
            }
        }

        // Add table to document
        document.add(table);
    }

    // Helper to create header cell
    private static Cell createHeaderCell(String content, int colspan) {
        return new Cell(1, colspan)
                .add(new Paragraph(content).setBold())
                .setTextAlignment(TextAlignment.CENTER);
    }

    // Helper to create body cell
    private static Cell createBodyCell(String content, int colspan) {
        return new Cell(1, colspan)
                .add(new Paragraph(content))
                .setTextAlignment(TextAlignment.CENTER);
    }
    private static Cell createBodyCell(String content, int colspan,int rowspan) {
        return new Cell(rowspan, colspan)
                .add(new Paragraph(content))
                .setTextAlignment(TextAlignment.CENTER);
    }

    private static void addDynamicSRATable(Document document, String isSRA, JSONArray sraProgramsArray) {
        // Outer table with two equal-width columns
        Table outerTable = new Table(new float[] { 1, 1 }); // 1:1 ratio
        outerTable.setWidth(UnitValue.createPercentValue(100)); // Set width to 100%

        // Add the question cell (Column 1)
        Cell questionCell = new Cell()
                .add(new Paragraph(
                        "Is the University Offering any Programmes Recognised by any Statutory Regulatory Authority (SRA)")
                        .setFontSize(10)) // Set font size
                .setPadding(5) // Add padding
                .setBorder(new SolidBorder(1)); // Ensure visible border
        outerTable.addCell(questionCell);

        // Add the response cell (Column 2)
        Cell responseCell = new Cell()
                .add(new Paragraph(": " + isSRA)
                        .setFontSize(10)) // Set font size
                .setPadding(5) // Add padding
                .setTextAlignment(TextAlignment.LEFT) // Align text to the left
                .setBorder(new SolidBorder(1)); // Ensure visible border
        outerTable.addCell(responseCell);

        // If "Yes", create and add the inner table in column 1
        if ("Yes".equalsIgnoreCase(isSRA)) {
            // Create an inner table for SRA programs and documents
            Table innerTable = new Table(new float[] { 3, 2 }); // Adjust column widths for SRA Program and Document
            innerTable.setWidth(UnitValue.createPercentValue(100)); // Inner table takes full width of column 1

            // Add headers to the inner table
            innerTable.addCell(new Cell().add(new Paragraph("SRA Program").setBold().setFontSize(10)).setPadding(5));
            innerTable.addCell(new Cell().add(new Paragraph("Document").setBold().setFontSize(10)).setPadding(5));

            // Loop through the JSONArray to add rows for each program
            for (int i = 0; i < sraProgramsArray.length(); i++) {
                JSONObject program = sraProgramsArray.getJSONObject(i);

                // Add SRA Program
                innerTable.addCell(
                        new Cell().add(new Paragraph(program.getString("program")).setFontSize(10)).setPadding(5));

                // Add Document (as a hyperlink)
                innerTable.addCell(new Cell().add(new Paragraph(new Link(program.getString("document"),
                        PdfAction.createURI(program.getString("document"))).setFontSize(10))).setPadding(5));
            }

            // Add the inner table to column 1
            Cell innerTableCell = new Cell(1, 2); // Span across both columns of the outer table
            innerTableCell.add(innerTable);
            innerTableCell.setPadding(5); // Add padding for separation
            innerTableCell.setBorder(new SolidBorder(1)); // Ensure proper border for outer table
            outerTable.addCell(innerTableCell);
        } else {
            // If no SRA programs, add an empty cell to column 2 to maintain the structure
            outerTable.addCell(new Cell(1, 2)
                    .add(new Paragraph("No programs recognized").setFontSize(10))
                    .setPadding(5)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBorder(new SolidBorder(1))); // Ensure border for the cell
        }

        // Add the outer table to the document
        try {
            document.add(outerTable.setMarginBottom(10));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addAffiliatedInstitution(Document document, JsonArray affiliatedInstitutionArray) {
        Table table = new Table(new float[] { 3, 1, 1, 1 }); // Column widths
        table.setWidth(UnitValue.createPercentValue(100));

        table.addCell(new Cell().add(new Paragraph("Type of Colleges").setBold())
                .setFontSize(10).setPadding(5).setTextAlignment(TextAlignment.CENTER));
        table.addCell(new Cell().add(new Paragraph("Permanent").setBold())
                .setFontSize(10).setPadding(5).setTextAlignment(TextAlignment.CENTER));
        table.addCell(new Cell().add(new Paragraph("Temporary").setBold())
                .setFontSize(10).setPadding(5).setTextAlignment(TextAlignment.CENTER));
        table.addCell(new Cell().add(new Paragraph("Total").setBold())
                .setFontSize(10).setPadding(5).setTextAlignment(TextAlignment.CENTER));

        // Loop through the affiliated institution array to fill the data
        for (JsonElement element : affiliatedInstitutionArray) {
            JsonObject row = element.getAsJsonObject();

            table.addCell(new Cell().add(new Paragraph(row.get("Type of Colleges").getAsString()))
                    .setFontSize(10).setPadding(5));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(row.get("Permanent").getAsInt())))
                    .setFontSize(10).setPadding(5).setTextAlignment(TextAlignment.RIGHT));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(row.get("Temporary").getAsInt())))
                    .setFontSize(10).setPadding(5).setTextAlignment(TextAlignment.RIGHT));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(row.get("Total").getAsInt())))
                    .setFontSize(10).setPadding(5).setTextAlignment(TextAlignment.RIGHT));
        }

        // Add the table to the document
        document.add(table.setMarginBottom(10));
    }

    // Spacing Cell
    private static Cell createCellWithSpacing(String text, float fontSize) {
        return new Cell()
                .add(new Paragraph(text).setFontSize(fontSize))
                .setPadding(5) // Inner spacing within the cell
                .setMarginBottom(5); // Space between rows
    }

    // Bold Spacing Cell
    private static Cell createCellWithBoldandSpacing(String text, float fontSize) {
        return new Cell()
                .add(new Paragraph(text).setFontSize(fontSize))
                .setPadding(5) // Inner spacing within the cell
                .setMarginBottom(5).setBold(); // Space between rows
    }

    // Function to add AcademincInfo
    private static void addAcademicTable(Document document, JSONObject academicDetails) {
        Table table = new Table(UnitValue.createPercentArray(new float[] { 1, 1 }));
        table.setWidth(UnitValue.createPercentValue(100));

        table.addCell(createCellWithBoldandSpacing("Type of Colleges", 10));
        table.addCell(createCellWithBoldandSpacing("Numbers", 10));

        for (String key : academicDetails.keySet()) {
            table.addCell(createCellWithSpacing(key, 10));
            table.addCell(createCellWithSpacing(String.valueOf(academicDetails.getInt(key)), 10));

        }

        document.add(table.setMarginBottom(10));
    }

    // Function to add Recognition Details Table
    private static void addRecognitionDetailsTable(Document document, JSONArray recognitionDetails) {
        Table table = new Table(UnitValue.createPercentArray(new float[] { 4, 4, 4 }));
        table.setWidth(UnitValue.createPercentValue(100));

        table.addHeaderCell(new Cell(1, 6).add(new Paragraph(" Recognition Details").setBold().setFontSize(10))
                .setFontSize(10).setPadding(5));
        table.addHeaderCell(new Cell(1, 6)
                .add(new Paragraph(" Date of Recognition as a University by UGC or Any Other National Agency : ")
                        .setBold().setFontSize(10))
                .setFontSize(10).setPadding(5));
        table.addHeaderCell(
                new Cell().add(new Paragraph("Under Section").setBold().setFontSize(10)));
        table.addHeaderCell(new Cell().add(new Paragraph("Date").setBold().setFontSize(10)));
        table.addHeaderCell(
                new Cell().add(new Paragraph("View Document").setBold().setFontSize(10)));

        for (int i = 0; i < recognitionDetails.length(); i++) {
            JSONObject detail = recognitionDetails.getJSONObject(i);
            table.addCell(new Cell().add(new Paragraph(detail.getString("section")).setFontSize(10)));
            table.addCell(new Cell().add(new Paragraph(detail.getString("date")).setFontSize(10)));
            // table.addCell(new Cell().add(new
            // Paragraph(detail.getString("document")).setFontSize(10)));
            String doc_url = detail.getString("document");
            if (doc_url != "Not Available") {
                Link link = new Link(doc_url, PdfAction.createURI(doc_url));
                table.addCell(
                        new Cell().add(new Paragraph(link).setFontColor(com.itextpdf.kernel.colors.ColorConstants.BLUE))
                                .setFontSize(10).setPadding(5));
            } else {
                table.addCell(new Cell().add(new Paragraph(detail.getString("document")).setFontSize(10)));
            }

        }

        document.add(table.setMarginBottom(10));
    }

    // Function to add Campus Details Table
    private static void addCampusDetailsTable(Document document, JSONArray campusDetails) {
        Table table = new Table(UnitValue.createPercentArray(new float[] { 2, 4, 2, 2, 2, 3, 2, 2 }));
        table.setWidth(UnitValue.createPercentValue(100));
        table.addHeaderCell(
                new Cell(1, 8).add(new Paragraph("  Location, Area and Activity of Campus").setBold().setFontSize(10))
                        .setFontSize(10).setPadding(5));
        table.addHeaderCell(new Cell().add(new Paragraph("Campus Type").setBold()).setFontSize(10));
        table.addHeaderCell(new Cell().add(new Paragraph("Address").setBold()).setFontSize(10));
        table.addHeaderCell(new Cell().add(new Paragraph("Location*").setBold()).setFontSize(10));
        table.addHeaderCell(new Cell().add(new Paragraph("Campus Area in Acres").setBold()).setFontSize(10));
        table.addHeaderCell(new Cell().add(new Paragraph("Built-up Area in sq.mts").setBold()).setFontSize(10));
        table.addHeaderCell(new Cell().add(new Paragraph("Programs Offered").setBold()).setFontSize(10));
        table.addHeaderCell(new Cell().add(new Paragraph("Establishment Date").setBold()).setFontSize(10));
        table.addHeaderCell(new Cell().add(new Paragraph("Date of Recognition by UGC/MHRD").setBold()).setFontSize(10));

        for (int i = 0; i < campusDetails.length(); i++) {
            JSONObject detail = campusDetails.getJSONObject(i);
            table.addCell(new Cell().add(new Paragraph(detail.getString("type"))).setFontSize(10));
            table.addCell(new Cell().add(new Paragraph(detail.getString("address"))).setFontSize(10));
            table.addCell(new Cell().add(new Paragraph(detail.getString("location"))).setFontSize(10));
            table.addCell(new Cell().add(new Paragraph(detail.getString("area"))).setFontSize(10));
            table.addCell(new Cell().add(new Paragraph(detail.getString("builtUpArea"))).setFontSize(10));
            table.addCell(new Cell().add(new Paragraph(detail.getString("programs"))).setFontSize(10));
            table.addCell(new Cell().add(new Paragraph(detail.getString("establishmentDate"))).setFontSize(10));
            table.addCell(new Cell().add(new Paragraph(detail.getString("dateOfRecognitionByUGC"))).setFontSize(10));

        }

        document.add(table.setMarginBottom(10));
    }

    // Function to add Header
    private static void addHeader(Document document, String headerText) {
        Paragraph header = new Paragraph(headerText)
                .setFontSize(8)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(10)
                .setBold();
        document.add(header);
    }

    // Function to add Footer
    private static void addFooter(Document document, PdfDocument pdf) {
        int n = pdf.getNumberOfPages();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String timestamp = dtf.format(LocalDateTime.now());

        for (int i = 1; i <= n; i++) {
            // PdfPage page = pdf.getPage(i);

            // Page number in the center
            document.showTextAligned(new Paragraph(String.format("Page %d/%d", i, n))
                    .setFontSize(8),
                    297, 20, i, TextAlignment.CENTER, VerticalAlignment.BOTTOM, 0);

            // Timestamp on the right
            document.showTextAligned(new Paragraph(timestamp)
                    .setFontSize(8),
                    550, 20, i, TextAlignment.RIGHT, VerticalAlignment.BOTTOM, 0);
        }
    }

    // Function to add University Info Table
    private static void addUniversityInfoTable(Document document, String name, String address, String city,
            String state, String pin, String website) {
        Table table = new Table(UnitValue.createPercentArray(new float[] { 50, 50 }));
        table.setWidth(UnitValue.createPercentValue(100));

        table.addHeaderCell(new Cell(1, 2).add(new Paragraph("Name and Address of the University")
                .setBold().setFontSize(10)).setFontSize(10).setPadding(5)); // .setMinHeight(12)
        table.addCell(new Cell().add(new Paragraph("Name")).setFontSize(10).setPadding(5));
        table.addCell(new Cell().add(new Paragraph(name)).setFontSize(10).setPadding(5));

        table.addCell(new Cell().add(new Paragraph("Address")).setFontSize(10).setPadding(5));
        table.addCell(new Cell().add(new Paragraph(address)).setFontSize(10).setPadding(5));

        table.addCell(new Cell().add(new Paragraph("City")).setFontSize(10).setPadding(5));
        table.addCell(new Cell().add(new Paragraph(city)).setFontSize(10).setPadding(5));

        table.addCell(new Cell().add(new Paragraph("State")).setFontSize(10).setPadding(5));
        table.addCell(new Cell().add(new Paragraph(state)).setFontSize(10).setPadding(5));

        table.addCell(new Cell().add(new Paragraph("Pin")).setFontSize(10).setPadding(5));
        table.addCell(new Cell().add(new Paragraph(pin)).setFontSize(10).setPadding(5));

        table.addCell(new Cell().add(new Paragraph("Website")).setFontSize(10).setPadding(5));

        Link link = new Link(website, PdfAction.createURI(website));

        table.addCell(new Cell().add(new Paragraph(link).setFontColor(com.itextpdf.kernel.colors.ColorConstants.BLUE))
                .setFontSize(10).setPadding(5));

        document.add(table.setMarginBottom(10));
    }

    // Function to add Dynamic Contacts Table
    private static void addDynamicContactsTable(Document document, JSONArray contactsArray) {
        Table table = new Table(new float[] { 2, 2, 2, 2, 2, 3 });
        table.setWidth(UnitValue.createPercentValue(100));

        // Add Header row for the table
        table.addHeaderCell(new Cell(1, 6).add(new Paragraph(" Contacts for Communication").setBold().setFontSize(10))
                .setFontSize(10).setPadding(5));
        table.addCell(new Cell().add(new Paragraph("Designation").setBold()).setFontSize(10).setPadding(5));
        table.addCell(new Cell().add(new Paragraph("Name").setBold()).setFontSize(10).setPadding(5));
        table.addCell(new Cell().add(new Paragraph("Telephone with STD Code").setBold()).setFontSize(10).setPadding(5));
        table.addCell(new Cell().add(new Paragraph("Mobile").setBold()).setFontSize(10).setPadding(5));
        table.addCell(new Cell().add(new Paragraph("Fax").setBold()).setFontSize(10).setPadding(5));
        table.addCell(new Cell().add(new Paragraph("Email").setBold()).setFontSize(10).setPadding(5));

        // Loop through the contacts array to fill the data
        for (int i = 0; i < contactsArray.length(); i++) {
            JSONObject contact = contactsArray.getJSONObject(i);

            table.addCell(new Cell().add(new Paragraph(contact.getString("designation")).setFontSize(10)));
            table.addCell(new Cell().add(new Paragraph(contact.getString("name")).setFontSize(10)));
            table.addCell(new Cell().add(new Paragraph(contact.getString("telephone")).setFontSize(10)));
            table.addCell(new Cell().add(new Paragraph(contact.getString("mobile")).setFontSize(10)));
            table.addCell(new Cell().add(new Paragraph(contact.getString("fax")).setFontSize(10)));
            table.addCell(new Cell().add(new Paragraph(contact.getString("email")).setFontSize(10)));
        }

        document.add(table.setMarginBottom(10));
    }

    // Function to add a Simple Row Table
    private static void addSimpleRowTable(Document document, String label, String value) {
        Table table = new Table(UnitValue.createPercentArray(new float[] { 50, 50 }));
        table.setWidth(UnitValue.createPercentValue(100));

        table.addHeaderCell(
                new Cell(1, 2).add(new Paragraph(label).setBold().setFontSize(10)).setFontSize(10).setPadding(5));
        table.addCell(new Cell().add(new Paragraph(label)).setFontSize(10).setPadding(5));
        table.addCell(new Cell().add(new Paragraph(value)).setFontSize(10).setPadding(5));

        document.add(table.setMarginBottom(10));
    }

    private static void addEstablishmentTable(Document document, String label, String establishmentDate,
            String priorDate) {
        Table table = new Table(UnitValue.createPercentArray(new float[] { 50, 50 }));
        table.setWidth(UnitValue.createPercentValue(100));

        table.addHeaderCell(
                new Cell(1, 2).add(new Paragraph(label).setBold().setFontSize(10)).setFontSize(10).setPadding(5));
        table.addCell(new Cell().add(new Paragraph(label)).setFontSize(10).setPadding(5));
        table.addCell(new Cell().add(new Paragraph(establishmentDate)).setFontSize(10).setPadding(5));
        table.addCell(new Cell().add(new Paragraph("Status Prior to Establishment,If applicable")).setFontSize(10)
                .setPadding(5));
        table.addCell(new Cell().add(new Paragraph("Prior Date")).setFontSize(10).setPadding(5));
        table.addCell(
                new Cell().add(new Paragraph(priorDate.isEmpty() ? "N/A" : priorDate)).setFontSize(10).setPadding(5));
        document.add(table.setMarginBottom(10));
    }
}
