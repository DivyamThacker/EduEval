
package com.proj.rest.webservices.restfulwebservices.services;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
// import com.itextpdf.kernel.pdf.PdfPage;
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
              "natureOfUniversity": "State Private University",
              "typeOfUniversity": "Unitary",
              "establishmentDate": "06-08-2001",
              "priorDate": "NA"
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

    // Function to generate the University Profile PDF
    public static void generateUniversityProfilePDF(JSONObject jsonObject) throws Exception {
        // Extract university info
        JSONObject universityInfo = jsonObject.getJSONObject("universityInfo");
        String name = universityInfo.getString("name");
        String address = universityInfo.getString("address");
        String city = universityInfo.getString("city");
        String state = universityInfo.getString("state");
        String pin = universityInfo.getString("pin");
        String website = universityInfo.getString("website");

        // Extract contacts
        JSONArray contactsArray = jsonObject.getJSONArray("contacts");

        // Extract other details
        String natureOfUniversity = jsonObject.getString("natureOfUniversity");
        String typeOfUniversity = jsonObject.getString("typeOfUniversity");
        String establishmentDate = jsonObject.getString("establishmentDate");
        String priorDate = jsonObject.getString("priorDate");

        PdfWriter writer = new PdfWriter(DEST);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);
        document.setMargins(20, 20, 20, 20);

        // Add Header
        addHeader(document, "Self Study Report of "+name);

        // Title
        Paragraph profileTitle = new Paragraph("2. PROFILE").setBold().setFontSize(15).setMarginBottom(5);
        document.add(profileTitle);

        // Add a line beneath "Profile"
        document.add(new LineSeparator(new SolidLine(1f)).setStrokeWidth(1f).setMarginBottom(5));

        document.add(new Paragraph("2.1 BASIC INFORMATION").setBold().setFontSize(15).setMarginBottom(5));

        // Add the university information table with heading
        addUniversityInfoTable(document, name, address, city, state, pin, website);

        // Add the contacts table with heading
        addDynamicContactsTable(document, contactsArray);

        // Add remaining sections with titles in their own rows
        addSimpleRowTable(document, "Nature of University", natureOfUniversity);
        addSimpleRowTable(document, "Type of University", typeOfUniversity);
        addEstablishmentTable(document, "Establishment Date of the University",establishmentDate, priorDate.equals("NA") ? "" : priorDate);
        
        // Add Footer
        addFooter(document, pdf);

        document.add(new AreaBreak());
        addSimpleRowTable(document, "Establishment Date of the University", establishmentDate);
        addSimpleRowTable(document, "Establishment Date of the University", establishmentDate);




        document.close();
        System.out.println("PDF Created!");
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
    private static void addUniversityInfoTable(Document document, String name, String address, String city, String state, String pin, String website) {
        Table table = new Table(UnitValue.createPercentArray(new float[] { 50, 50 }));
        table.setWidth(UnitValue.createPercentValue(100));

        table.addHeaderCell(new Cell(1, 2).add(new Paragraph("Name and Address of the University")
        .setBold().setFontSize(10)).setFontSize(10).setPadding(5)); //.setMinHeight(12)
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

        table.addCell(new Cell().add(new Paragraph(link).setFontColor(com.itextpdf.kernel.colors.ColorConstants.BLUE)).setFontSize(10).setPadding(5));

        document.add(table.setMarginBottom(10));
    }

    // Function to add Dynamic Contacts Table
    private static void addDynamicContactsTable(Document document, JSONArray contactsArray) {
        Table table = new Table(new float[]{2, 2, 2, 2, 2, 3});
        table.setWidth(UnitValue.createPercentValue(100));

        // Add Header row for the table
        table.addHeaderCell(new Cell(1,6).add(new Paragraph(" Contacts for Communication").setBold().setFontSize(10)).setFontSize(10).setPadding(5));
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

        table.addHeaderCell(new Cell(1, 2).add(new Paragraph(label).setBold().setFontSize(10)).setFontSize(10).setPadding(5));
        table.addCell(new Cell().add(new Paragraph(label)).setFontSize(10).setPadding(5));
        table.addCell(new Cell().add(new Paragraph(value)).setFontSize(10).setPadding(5));

        document.add(table.setMarginBottom(10));
    }

    private static void addEstablishmentTable (Document document, String label, String establishmentDate, String priorDate) {
        Table table = new Table(UnitValue.createPercentArray(new float[] { 50, 50 }));
        table.setWidth(UnitValue.createPercentValue(100));

        table.addHeaderCell(new Cell(1, 2).add(new Paragraph(label).setBold().setFontSize(10)).setFontSize(10).setPadding(5));
        table.addCell(new Cell().add(new Paragraph(label)).setFontSize(10).setPadding(5));
        table.addCell(new Cell().add(new Paragraph(establishmentDate)).setFontSize(10).setPadding(5));
        table.addCell(new Cell().add(new Paragraph("Status Prior to Establishment,If applicable")).setFontSize(10).setPadding(5));
        table.addCell(new Cell().add(new Paragraph(priorDate)).setFontSize(10).setPadding(5));

        document.add(table.setMarginBottom(10));
    }
}

