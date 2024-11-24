
package com.proj.rest.webservices.restfulwebservices.services;

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
                            "isSRAProgram": "NO",
                            "TeachingFaculty": {
                                "Professor": {
                                    "Sanctioned": 20,
                                    "Recruited": {
                                        "Male": 9,
                                        "Female": 2,
                                        "Others": 0,
                                        "Total": 11
                                    },
                                    "YetToRecruit": 9,
                                    "OnContract": {
                                        "Male": 7,
                                        "Female": 1,
                                        "Others": 0,
                                        "Total": 8
                                    }
                                },
                                "AssociateProfessor": {
                                    "Sanctioned": 25,
                                    "Recruited": {
                                        "Male": 15,
                                        "Female": 3,
                                        "Others": 0,
                                        "Total": 18
                                    },
                                    "YetToRecruit": 7,
                                    "OnContract": {
                                        "Male": 8,
                                        "Female": 1,
                                        "Others": 0,
                                        "Total": 9
                                    }
                                },
                                "AssistantProfessor": {
                                    "Sanctioned": 35,
                                    "Recruited": {
                                        "Male": 24,
                                        "Female": 4,
                                        "Others": 0,
                                        "Total": 28
                                    },
                                    "YetToRecruit": 7,
                                    "OnContract": {
                                        "Male": 6,
                                        "Female": 3,
                                        "Others": 0,
                                        "Total": 9
                                    }
                                }
                            }
                        }
                    """;
            // Parse JSON data
            JSONObject jsonObject = new JSONObject(jsonData);

            // Call the function to generate PDF
            generateUniversityProfilePDF(jsonObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateUniversityProfilePDF(JSONObject jsonObject) throws Exception {
        // Extract university info
        // path to font library

        JSONObject universityInfo = jsonObject.getJSONObject("universityInfo");
        String name = universityInfo.getString("name");
        String address = universityInfo.getString("address");
        String city = universityInfo.getString("city");
        String state = universityInfo.getString("state");
        String pin = universityInfo.getString("pin");
        String website = universityInfo.getString("website");

        // Extract contacts
        JSONArray contactsArray = jsonObject.getJSONArray("contacts");

        // Extract recognition details
        JSONArray recognitionDetails = jsonObject.getJSONArray("recognitionDetails");

        // Extract campus details
        JSONArray campusDetails = jsonObject.getJSONArray("campusDetails");

        // Extract Academic Info
        JSONObject academicDetails = jsonObject.getJSONObject("academicDetails");

        // Extract other details
        String natureOfUniversity = jsonObject.getString("natureOfUniversity");
        String typeOfUniversity = jsonObject.getString("typeOfUniversity");
        String establishmentDate = jsonObject.getString("establishmentDate");
        String priorDate = jsonObject.getString("priorDate");
        String universityPotential = jsonObject.getString("universityPotential");
        String isSRA = jsonObject.getString("isSRAProgram");

        PdfWriter writer = new PdfWriter(DEST);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);
        document.setMargins(20, 20, 20, 20);

        // Add Header
        addHeader(document, "Self Study Report of " + name);

        // Title
        Paragraph profileTitle = new Paragraph("2. PROFILE").setBold().setFontSize(15).setMarginBottom(5);
        document.add(profileTitle);

        // Add a line beneath "Profile"
        document.add(new LineSeparator(new SolidLine(1f)).setStrokeWidth(1f).setMarginBottom(5));

        // Add Basic Information Section
        document.add(new Paragraph("2.1 BASIC INFORMATION").setBold().setFontSize(15).setMarginBottom(5));
        addUniversityInfoTable(document, name, address, city, state, pin, website);

        // Add Contacts Table
        addDynamicContactsTable(document, contactsArray);

        // Add Nature and Type of University
        addSimpleRowTable(document, "Nature of University", natureOfUniversity);
        addSimpleRowTable(document, "Type of University", typeOfUniversity);

        // Add Establishment Date
        addEstablishmentTable(document, "Establishment Date of the University", establishmentDate,
                priorDate.equals("NA") ? "" : priorDate);

        document.add(new AreaBreak());
        // Add Recognition Details

        addRecognitionDetailsTable(document, recognitionDetails);

        // Add University Potential for Excellence
        addSimpleRowTable(document, "University with Potential for Excellence", universityPotential);

        // Add Campus Details
        addCampusDetailsTable(document, campusDetails);

        document.add(new Paragraph("2.2 ACADEMIC INFROMATION").setBold().setFontSize(15).setMarginBottom(5));

        document.add(new Paragraph("Furnish the Details of Colleges of University").setBold().setFontSize(10)
                .setMarginBottom(5));
        // Add Academic Table
        addAcademicTable(document, academicDetails);
        // Add SRA
        addSRATable(document, isSRA);
        // Add Teaching and Non-Teaching Details
        // addSRATable(document, isSRA);
        // addSRATable(document, isSRA);
        addFooter(document, pdf);
        
        document.add(new AreaBreak());
        document.add(new Paragraph("Details Of Teaching & Non-Teaching Staff Of University")
        .setBold().setFontSize(10));
        addFooter(document, pdf);

        JSONObject facultyData = jsonObject.getJSONObject("TeachingFaculty");
        // System.out.println("THis is rollldlflkdsjf +++++++++++++ " + facultyData);

        addTeachingTable(document, facultyData);

        document.close();
        System.out.println("Extended PDF Created!");
    }

    private static void addTeachingTable(Document document,JSONObject facultyData)
    {
        float[] columnWidths = {3, 2, 2, 2, 2}; // Adjust as needed
            Table table = new Table(columnWidths);

            // Add Header Row
            table.addHeaderCell(new Cell().add(new Paragraph("Teaching Faculty").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Male").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Female").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Others").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Total").setBold()));

            // Add rows dynamically
            for (String role : facultyData.keySet()) {
                JSONObject roleData = facultyData.getJSONObject(role);
                System.out.println("THis is rollldlflkdsjf +++++++++++++ " + roleData);
                // Add rows for Sanctioned
                System.out.println(String.valueOf(roleData.getInt("Sanctioned")));
                table.addCell(new Cell().add(new Paragraph(role + " (Sanctioned)")));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(roleData.getInt("Sanctioned")))));
                table.addCell(new Cell().add(new Paragraph("")));
                table.addCell(new Cell().add(new Paragraph("")));
                table.addCell(new Cell().add(new Paragraph("")));

                // Add rows for Recruited
                JSONObject recruited = roleData.getJSONObject("Recruited");
                table.addCell(new Cell().add(new Paragraph(role + " (Recruited)")));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(recruited.getInt("Male")))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(recruited.getInt("Female")))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(recruited.getInt("Others")))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(recruited.getInt("Total")))));

                // Add rows for Yet to Recruit
                table.addCell(new Cell().add(new Paragraph(role + " (Yet to Recruit)")));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(roleData.getInt("YetToRecruit")))));
                table.addCell(new Cell().add(new Paragraph("")));
                table.addCell(new Cell().add(new Paragraph("")));
                table.addCell(new Cell().add(new Paragraph("")));

                // Add rows for On Contract
                JSONObject onContract = roleData.getJSONObject("OnContract");
                table.addCell(new Cell().add(new Paragraph(role + " (On Contract)")));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(onContract.getInt("Male")))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(onContract.getInt("Female")))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(onContract.getInt("Others")))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(onContract.getInt("Total")))));
            }

            // Add table to document
            document.add(table);
    }

    private static void addSRATable(Document document, String isSRA) {
        // Create a table with two columns: one wide, one narrow
        Table table = new Table(UnitValue.createPercentArray(new float[] { 5, 2 }));
        table.setWidth(UnitValue.createPercentValue(100)); // Set table width to 100% of the page width

        // Add the question cell
        Cell questionCell = new Cell()
                .add(new Paragraph(
                        "Is the University Offering any Programmes Recognised by any Statutory Regulatory Authority (SRA)")
                        .setFontSize(10)) // Use Arial font
                .setPadding(5); // Add padding
        table.addCell(questionCell);

        // Add the response cell
        Cell responseCell = new Cell()
                .add(new Paragraph(": " + isSRA)
                        .setFontSize(10)) // Use Arial font
                .setPadding(5) // Add padding
                .setTextAlignment(TextAlignment.LEFT); // Align text to the left
        table.addCell(responseCell);

        // Add the table to the document
        try {
            document.add(table);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
