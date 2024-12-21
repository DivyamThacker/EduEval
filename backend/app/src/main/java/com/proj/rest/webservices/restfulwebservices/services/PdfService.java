package com.proj.rest.webservices.restfulwebservices.services;
import java.util.LinkedHashMap;
import java.util.Map;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.BiConsumer;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.kernel.pdf.action.PdfAction;
import java.util.List; 

@Service
public class PdfService {
    // @Value("${application.bucket.prefix}")
    private static final String recognitionDetailsPrefixURL = "https://edu-eval-bucket.s3.us-east-1.amazonaws.com/";

    private static final String SRC = "src/main/resources/static/input.html";
    private static final String DEST = "target/classes/UniversityProfile.pdf";
    private final StaticDataService staticDataService;
    private final DataFormaterService dataFormaterService;

    public PdfService( StaticDataService staticDataService, DataFormaterService dataFormaterService
    ,StorageService storageService) {
        this.staticDataService = staticDataService;
        this.dataFormaterService = dataFormaterService;
    }

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
            String jsonData = staticDataService.getJsonData();
            // Parse JSON data
            JSONObject jsonObject = new JSONObject(jsonData);
            JsonObject tempJsonObject = JsonParser.parseString(jsonData).getAsJsonObject();
            // Call the function to generate PDF
            generateUniversityProfilePDF(jsonObject, tempJsonObject, dataFormaterService);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateUniversityProfilePDF(JSONObject jsonObject, JsonObject tempJsonObject, DataFormaterService dataFormaterService) throws Exception {
        // Extract university info
        // path to font library
// ---------------------------------------------------------------------------- //
        // University Models Data
      String basicInfo = dataFormaterService.getBasicInfo(1);
      System.out.println("Basic Info Data: " + basicInfo);
if (basicInfo == null || basicInfo.isEmpty()) {
    throw new IllegalArgumentException("Basic Info data is null or empty for ID 1");
}

JsonObject universityJsonObject;
try {
    universityJsonObject = JsonParser.parseString(basicInfo).getAsJsonObject();
} catch (IllegalStateException e) {
    throw new IllegalStateException("Invalid JSON format for Basic Info: " + basicInfo, e);
}
System.out.println("11111 --> " + universityJsonObject);

        // JsonObject universityJsonObject = JsonParser.parseString(dataFormaterService.getBasicInfo(1)).getAsJsonObject();
        String name = getJsonValue(universityJsonObject, "name");
        String address = getJsonValue(universityJsonObject, "address");
        String city = getJsonValue(universityJsonObject, "city");
        String state = getJsonValue(universityJsonObject, "state");
        String pin = getJsonValue(universityJsonObject, "pincode");
        String website = getJsonValue(universityJsonObject, "websiteUrl");
        String natureOfUniversity = getJsonValue(universityJsonObject, "nature");
        String typeOfUniversity = getJsonValue(universityJsonObject, "type");
        String establishmentDate = getJsonValue(universityJsonObject, "establishmentDate");
        String areSRAProgram = getJsonValue(universityJsonObject, "areSraProgram");

// ------------------------------------------------------------------------------- //

      // Contact Details
      Boolean isContactsNull = true;
      JsonArray contactsJsonArray = new JsonArray();
      String contactDetails = dataFormaterService.getContactDetials(1);
      if (contactDetails != null) {
          contactsJsonArray = JsonParser.parseString(contactDetails).getAsJsonArray();
          isContactsNull = false;
      }
      System.out.println("22222 -> " + contactsJsonArray);
        // JSONArray campusDetails = jsonObject.getJSONArray("campusDetails");
        String priorDate = getJsonValue(universityJsonObject, "priorStatus");
        // String isSRA = jsonObject.getString("isSRAProgram");
        JsonArray affiliatedInstitutionArray = tempJsonObject.getAsJsonArray("affiliatedInstitution");
        // JSONArray sraProgramsArray = jsonObject.getJSONArray("sraPrograms");
      System.out.println("33333 -> " + affiliatedInstitutionArray);
// -------------------------------------------------------------------------------- //
        
// Recognition Detail
         Boolean isRecognitionDetailsNull = true;
        JsonObject recognitionDetailsJsonObject = new JsonObject();

        try {
            String recognitionDetails = dataFormaterService.getRecognitionDetails(1);
            System.out.println("Recog wale baba --> " + recognitionDetails);

            if (recognitionDetails != null && !recognitionDetails.trim().isEmpty()) {
                // Attempt to parse the string as a JSON object
                recognitionDetailsJsonObject = JsonParser.parseString(recognitionDetails).getAsJsonObject();
                isRecognitionDetailsNull = false;
            } else {
                System.out.println("Recognition details are null or empty.");
            }
        } catch (JsonSyntaxException | IllegalStateException e) {
            System.err.println("Error parsing recognition details as JSON: " + e.getMessage());
        }

        System.out.println("4444 -> " + recognitionDetailsJsonObject);

        
// --------------------------------------------- ---------------------------------//
// Campus Details
        Boolean isCampusDetailsNull = true;
        JsonArray campusDetailsJsonArray = new JsonArray();
        String campusDetails = dataFormaterService.getCampusDetails(1);
        if (campusDetails != null) {
          campusDetailsJsonArray = JsonParser.parseString(campusDetails).getAsJsonArray();
          isCampusDetailsNull = false;
        }
        System.out.println("5555 -> " + campusDetailsJsonArray);
// -------------------------------------------------------------------------------- //
Boolean isCollegeStatsNull = true;
JsonObject collegeStatsJsonObject = new JsonObject();

try {
    String collegeStatsDetails = dataFormaterService.getCollegeStatsDetails(1);
    
    if (collegeStatsDetails != null && !collegeStatsDetails.trim().isEmpty()) {
        // Attempt to parse the string as a JSON object
        collegeStatsJsonObject = JsonParser.parseString(collegeStatsDetails).getAsJsonObject();
        isCollegeStatsNull = false;
    } else {
        System.out.println("College stats details are null or empty.");
    }
} catch (JsonSyntaxException | IllegalStateException e) {
    System.err.println("Error parsing college stats details as JSON: " + e.getMessage());
}

System.out.println("6666 -> " + collegeStatsJsonObject);
// -----------------------------------------------------------------------------------------------------------------
// Faculty Details       
        Boolean isFacultyDetailsNull = true;
        JsonArray FacultyDetails = new JsonArray();
        String facultyDetails = dataFormaterService.getFacultyDetails(1);
        if (facultyDetails != null) {
            FacultyDetails = JsonParser.parseString(facultyDetails).getAsJsonArray();
            isFacultyDetailsNull = false;
        }
        System.out.println("7777 -> "+ FacultyDetails);
// Non Teaching Staff
        Boolean isNonTeachingStaffNull = true;
        JsonArray NonTeachingStaff = new JsonArray();
        String nonTeachingStaffDetails = dataFormaterService.getNonTeachingDetails(1);
        if (nonTeachingStaffDetails != null) {
            NonTeachingStaff = JsonParser.parseString(nonTeachingStaffDetails).getAsJsonArray();
            isNonTeachingStaffNull = false;
        }
        System.out.println("8888 -> " + NonTeachingStaff);
// Distinguish Academician
        Boolean isDistinguishedAcademicianNull = true;
        JsonArray DistinguishedAcademicianDetail = new JsonArray();
        String distinguishedAcademicianDetails = dataFormaterService.getDistinguishAcadmecian(1);
        if (distinguishedAcademicianDetails != null) {
            DistinguishedAcademicianDetail = JsonParser.parseString(distinguishedAcademicianDetails).getAsJsonArray();
            isDistinguishedAcademicianNull = false;
        }
        System.out.println("9999 --> " + DistinguishedAcademicianDetail);
// Chairs Details
        Boolean isChairsDetailsNull = true;
        JsonArray ChairsDetails = new JsonArray();
        String chairsDetails = dataFormaterService.getChairsDetails(1);
        if (chairsDetails != null) {
            ChairsDetails = JsonParser.parseString(chairsDetails).getAsJsonArray();
            isChairsDetailsNull = false;
        }
        System.out.println("1010 --> " + ChairsDetails);
// HRDC Details
        Boolean isHRDCDetailsNull = true;
JsonObject HRDCDetails = new JsonObject();

try {
    String hrdcDetails = dataFormaterService.getHRDCDetails(1);

    if (hrdcDetails != null && !hrdcDetails.trim().isEmpty()) {
        // Attempt to parse the string as a JSON object
        HRDCDetails = JsonParser.parseString(hrdcDetails).getAsJsonObject();
        isHRDCDetailsNull = false;
    } else {
        System.out.println("HRDC details are null or empty.");
    }
} catch (JsonSyntaxException | IllegalStateException e) {
    System.err.println("Error parsing HRDC details as JSON: " + e.getMessage());
}

System.out.println("1111 --> " + HRDCDetails);

// Accrediation Details
        // Boolean isAccreditationDetailsNull = true;
        // JsonArray AccreditationDetails = new JsonArray();
        // String accreditationDetails = dataFormaterService.getAccreditationDetails(1);
        // if (accreditationDetails != null) {
        //     AccreditationDetails = JsonParser.parseString(accreditationDetails).getAsJsonArray();
        //     isAccreditationDetailsNull = false;
        // }
// -------------------------------------------------------------------------------------------------------------------
        // JsonArray AccreditationDetails = JsonParser.parseString(dataFormaterService.getAccreditationDetails(1)).getAsJsonArray();
// Create PDF document
// Departement Evaluation
Boolean isDepartmentEvaluationNull = true;
JsonArray DepartmentEvaluationJson = new JsonArray();
String departmentEvaluationDetails = dataFormaterService.getDepartmetEvaluation(1);
if (departmentEvaluationDetails != null) {
    DepartmentEvaluationJson = JsonParser.parseString(departmentEvaluationDetails).getAsJsonArray();
    isDepartmentEvaluationNull = false;
}
System.out.println("1212 -- > " + DepartmentEvaluationJson);
// NEP Details
Boolean isNEPDetailsNull = true;
JsonArray NEPDetailsJson = new JsonArray();
String nepDetails = dataFormaterService.getNepDetails(1);
if (nepDetails != null) {
    NEPDetailsJson = JsonParser.parseString(nepDetails).getAsJsonArray();
    isNEPDetailsNull = false;
}
System.out.println("1313 --> " + NEPDetailsJson);
// Electoral Details
Boolean isElectoralDetailsNull = true;
JsonArray electrolDetails = new JsonArray();
String electoralDetails = dataFormaterService.getElectoralDetails(1);
if (electoralDetails != null) {
    electrolDetails = JsonParser.parseString(electoralDetails).getAsJsonArray();
    isElectoralDetailsNull = false;}

    System.out.println("1414 --> "+ electrolDetails);

        PdfWriter writer = new PdfWriter(DEST);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);
        document.setMargins(20, 20, 20, 20);

        // Add Header
        addHeader(document, "Self Study Report of " + name);

        // Add Sections
        document.add(new Paragraph("2. PROFILE").setBold().setFontSize(15).setMarginBottom(5));
        document.add(new LineSeparator(new SolidLine(1f)).setStrokeWidth(1f).setMarginBottom(5));

        // Add Basic Information Section
        document.add(new Paragraph("2.1 BASIC INFORMATION").setBold().setFontSize(15).setMarginBottom(5));
        // 1)
        addUniversityInfoTable(document, name, address, city, state, pin, website);

        //2)  Add Dynamic Tables
        if (!isContactsNull)
        addDynamicContactsTable(document, contactsJsonArray);
        // 3) Nature of Uni
        addSimpleRowTable(document, "Nature of University", natureOfUniversity);
        // 4) Type of Uni
        addSimpleRowTable(document, "Type of University", typeOfUniversity);
        // 5) Date of Establishment
        addEstablishmentTable(document, "Establishment Date of the University", establishmentDate,
                priorDate.equals("NA") ? "" : priorDate);

        // 6) Recognition Details
        if(!isRecognitionDetailsNull)
        addRecognitionDetailsTable(document, recognitionDetailsJsonObject);
        // 7) Campus Details
        if(!isCampusDetailsNull)
        addCampusDetailsTable(document,campusDetailsJsonArray);

        // ---- Academic Info ----- //
        document.add(new Paragraph("2.2 ACADEMIC INFORMATION").setBold().setFontSize(15).setMarginBottom(5));

        // add affilated institution if any
        // document.add(new Paragraph("Affiliated Institutions to the University").setBold().setFontSize(10)
        //         .setMarginBottom(5));

        // if (!natureOfUniversity.equalsIgnoreCase("private") &&
        //         !natureOfUniversity.equalsIgnoreCase("deemed to be")) {
        //     addAffiliatedInstitution(document, affiliatedInstitutionArray);
        // } else {
        //     document.add(new Paragraph("(Not applicable for private and deemed to be Universities)")
        //             .setFontSize(10).setItalic());
        // }
        document.add(new Paragraph("Furnish the Details of Colleges of University\r").setBold().setFontSize(10));
        // // 8) College Stats
        if(!isCollegeStatsNull)
        addCollegeStatsTable(document, collegeStatsJsonObject);
        // 9) addSRATable;
        addDynamicSRATable(document, dataFormaterService, areSRAProgram);
        // Faculty Data
        document.add(new Paragraph("Details Of Teaching & Non-Teaching Staff Of University").setBold().setFontSize(10));
        // 10) Teaching Faculty Table
        if(!isFacultyDetailsNull)
        addTeachingFacultyTable(document, FacultyDetails);
        // 11) Staff Details Table
        if(!isNonTeachingStaffNull)
        addStaffTable(document, NonTeachingStaff);
        // 12) Qualification Detail
        document.add(new Paragraph("Qualifications of the Teaching Staff ").setBold().setFontSize(10));
        if(!isFacultyDetailsNull)
        addQualificationTable(document, FacultyDetails);
        // 13) Distinguish Academicians
        if(!isDistinguishedAcademicianNull)
        addDistinguishedAcademicians(document,DistinguishedAcademicianDetail);
        // 14) Chairs
        if(!isChairsDetailsNull)
        addChairs(document,ChairsDetails);
        /*
         * Student Enrolled in Current Academic Year Info
         */
        // // 15) HRDC Details
        if(!isHRDCDetailsNull)
        addHRDCDetails(document,HRDCDetails);
        // 16) Accrediation Details
        
        // 17) Department Evaluation Details
        if(!isDepartmentEvaluationNull)
        addEvaluativeReportTable(document, DepartmentEvaluationJson);


        // 18) NEP details
        if(!isNEPDetailsNull)
        createNEPTable(document,NEPDetailsJson);

        // 19) Electoral Details
        if(!isElectoralDetailsNull)
        createElectoralTable(document,electrolDetails);

        document.close();
        System.out.println("Extended PDF Created!");
    }

public void createElectoralTable(Document document, JsonArray jsonArray) throws Exception {

    Paragraph title = new Paragraph("Institutional Initiatives for Electoral Literacy")
            .setBold()
            .setTextAlignment(TextAlignment.LEFT)
            .setFontSize(10);
    document.add(title);

    // Define the categories in order
    List<String> categoryList = staticDataService.getElectoralLiteracyQuestions(); // Use java.util.List
    String[] categories = categoryList.toArray(new String[0]); // Convert List<String> to String[]

    // Add a table with 2 columns
    Table table = new Table(UnitValue.createPercentArray(new float[]{30, 70}));
    table.setWidth(UnitValue.createPercentValue(100));

    // Iterate over the JSON array and populate the table
    for (int i = 0; i < jsonArray.size(); i++) {
        JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
        int section = jsonObject.get("section").getAsInt();
        String extractedText = jsonObject.get("extractedText").getAsString();

        // Clean up the extracted text (e.g., remove "\r\n")
        String formattedText = extractedText.replace("\\r\\n", " ").replace("\\n", " ").trim();

        // Add category and formatted text to the table
        table.addCell(new Cell().add(new Paragraph(categories[section])));
        table.addCell(new Cell().add(new Paragraph(formattedText)));
    }

    // Add table to document
    document.add(table);
}

public void createNEPTable(Document document, JsonArray jsonArray) throws Exception {
    Paragraph title = new Paragraph("Institutional preparedness for NEP")
            .setBold()
            .setTextAlignment(TextAlignment.LEFT)
            .setFontSize(10);
    document.add(title);

    // Define the categories in order
    List<String> categoryList = staticDataService.getNepSections();
    String[] categories = categoryList.toArray(new String[0]); // Convert List<String> to String[]

    // Add a table with 2 columns
    Table table = new Table(UnitValue.createPercentArray(new float[]{30, 70}));
    table.setWidth(UnitValue.createPercentValue(100));

    // Iterate over the JSON array and populate the table
    for (int i = 0; i < jsonArray.size(); i++) {
        JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
        int section = jsonObject.get("section").getAsInt();
        String extractedText = jsonObject.get("extractedText").getAsString();

        // Clean up the extracted text (e.g., remove "\r\n")
        String formattedText = extractedText.replace("\\r\\n", " ").replace("\\n", " ").trim();

        // Add category and formatted text to the table
        table.addCell(new Cell().add(new Paragraph(categories[section])));
        table.addCell(new Cell().add(new Paragraph(formattedText)));
    }

    // Add table to document
    document.add(table);
}

  private static void addEvaluativeReportTable(Document document, JsonArray departmentEvaluationJson) {
  // Add title
  Paragraph title = new Paragraph("EVALUATIVE REPORT OF THE DEPARTMENTS")
          .setBold()
          .setTextAlignment(TextAlignment.CENTER)
          .setFontSize(10);
  document.add(title);

  // Define table with 2 columns
  float[] columnWidths = {5, 5};
  Table table = new Table(columnWidths);
  table.setWidth(UnitValue.createPercentValue(100));

  // Add table headers
  table.addHeaderCell(new Cell().add(new Paragraph("Name of the Department").setBold()));
  table.addHeaderCell(new Cell().add(new Paragraph("Evaluative Report").setBold()));

  // Add data rows
  if (departmentEvaluationJson.size() > 0) {
      for (JsonElement element : departmentEvaluationJson) {
          JsonObject department = element.getAsJsonObject();
          String departmentName = department.get("departmentName").getAsString();

          // Extract evaluative report details
          JsonObject report = department.getAsJsonObject("report");
          String fileIdentifierUrl = recognitionDetailsPrefixURL + report.get("fileIdentifier").getAsString();
          // String fileName = report.get("fileName").getAsString();

          // Create a clickable link for the file
          Link fileLink = new Link("View Document", PdfAction.createURI(fileIdentifierUrl));
          Paragraph fileLinkParagraph = new Paragraph().add(fileLink).setFontColor(com.itextpdf.kernel.colors.ColorConstants.BLUE);

          table.addCell(new Cell().add(new Paragraph(departmentName)));
          table.addCell(new Cell().add(fileLinkParagraph).setUnderline());
      }
  } else {
      // If no data, add a row with "NILL"
      table.addCell(new Cell(1, 2).add(new Paragraph("NILL").setTextAlignment(TextAlignment.CENTER)));
  }

  // Add table to document
  document.add(table);
}


  private static void addHRDCDetails(Document document, JsonObject hrdcDetails) {
  // Add title
  Paragraph title = new Paragraph("Details of programmes under the UGC Human Resource Development Centre, If applicable")
          .setBold()
          .setFontSize(10);
  document.add(title);

  // Define the table structure
  float[] columnWidths = {6, 4};
  Table table = new Table(columnWidths);
  table.setWidth(UnitValue.createPercentValue(100));

  // Add rows to the table
  table.addCell(new Cell().add(new Paragraph("Year of Establishment")));
  // Convert the establishment date from epoch to year
  long establishmentDate = hrdcDetails.get("hrdcEstablishmentDate").getAsLong();
  String establishmentYear = String.valueOf(java.time.Instant.ofEpochMilli(establishmentDate)
          .atZone(java.time.ZoneId.systemDefault()).getYear());
  table.addCell(new Cell().add(new Paragraph(establishmentYear)));

  table.addCell(new Cell().add(new Paragraph("Number of UGC Orientation Programmes")));
  table.addCell(new Cell().add(new Paragraph(String.valueOf(hrdcDetails.get("hrdcOrientationProgramCount").getAsInt()))));

  table.addCell(new Cell().add(new Paragraph("Number of UGC Refresher Course")));
  table.addCell(new Cell().add(new Paragraph(String.valueOf(hrdcDetails.get("hrdcRefresherCourseCount").getAsInt()))));

  table.addCell(new Cell().add(new Paragraph("Number of University's own Programmes")));
  table.addCell(new Cell().add(new Paragraph(String.valueOf(hrdcDetails.get("hrdcOwnProgramCount").getAsInt()))));

  table.addCell(new Cell().add(new Paragraph("Total Number of Programmes Conducted\n(during the last five years)")));
  table.addCell(new Cell().add(new Paragraph(String.valueOf(hrdcDetails.get("hrdctotalProgramCount").getAsInt()))));

  // Add the table to the document
  document.add(table);
}

    private static String getJsonValue(JsonObject jsonObject, String key) {
    return jsonObject.has(key) && !jsonObject.get(key).isJsonNull()
            ? jsonObject.get(key).getAsString()
            : ""; // Return empty string if key is missing or value is null
}

    private static void addChairs(Document document, JsonArray chairs) {
    // Add title
    Paragraph title = new Paragraph("Chairs Instituted by the University")
            .setBold()
            .setFontSize(10);
    document.add(title);

    // Define table with 4 columns
    float[] columnWidths = {1, 4, 4, 5};
    Table table = new Table(columnWidths);
    table.setWidth(UnitValue.createPercentValue(100));

    // Add table headers
    table.addHeaderCell(new Cell().add(new Paragraph("Sl.No").setBold().setTextAlignment(TextAlignment.CENTER)));
    table.addHeaderCell(new Cell().add(new Paragraph("Name of the Department").setBold().setTextAlignment(TextAlignment.CENTER)));
    table.addHeaderCell(new Cell().add(new Paragraph("Name of the Chair").setBold().setTextAlignment(TextAlignment.CENTER)));
    table.addHeaderCell(new Cell().add(new Paragraph("Name of the Sponsor Organisation/Agency").setBold().setTextAlignment(TextAlignment.CENTER)));

    // Add data rows
    if (chairs.size() > 0) {
        for (int i = 0; i < chairs.size(); i++) {
            JsonObject chair = chairs.get(i).getAsJsonObject();
            table.addCell(new Cell().add(new Paragraph(String.valueOf(i + 1)).setTextAlignment(TextAlignment.CENTER))); // Serial Number
            table.addCell(new Cell().add(new Paragraph(chair.get("departmentName").getAsString()).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(chair.get("chairName").getAsString()).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(chair.get("sponsorName").getAsString()).setTextAlignment(TextAlignment.LEFT)));
        }
    } else {
        // Add a single row with "NILL" if there are no chairs
        table.addCell(new Cell(1, 4).add(new Paragraph("NILL").setTextAlignment(TextAlignment.CENTER)));
    }

    // Add table to the document
    document.add(table);
}

    private static void addDistinguishedAcademicians(Document document, JsonArray distinguishedAcademicians) {
    // Add title
    Paragraph title = new Paragraph("Distinguished Academicians Appointed")
            .setBold()
            .setFontSize(10);
    document.add(title);

    // Create the table
    Table table = new Table(UnitValue.createPercentArray(new float[]{3, 2, 2, 2, 2}))
            .setWidth(UnitValue.createPercentValue(100));

    // Add header row
    table.addHeaderCell(createHeaderCell(""));
    table.addHeaderCell(createHeaderCell("Male"));
    table.addHeaderCell(createHeaderCell("Female"));
    table.addHeaderCell(createHeaderCell("Others"));
    table.addHeaderCell(createHeaderCell("Total"));

    // Data structure to aggregate data by position
    Map<String, int[]> aggregatedData = new HashMap<>();

    // Process the JsonArray
    for (JsonElement element : distinguishedAcademicians) {
        JsonObject academician = element.getAsJsonObject();

        String position = academician.get("type").getAsString() + " Professor";
        String gender = academician.get("gender").getAsString();
        int count = academician.get("count").getAsInt();

        // Initialize data for the position if not already present
        aggregatedData.putIfAbsent(position, new int[4]); // [Male, Female, Others, Total]

        // Update counts based on gender
        switch (gender.toLowerCase()) {
            case "male" -> aggregatedData.get(position)[0] += count;
            case "female" -> aggregatedData.get(position)[1] += count;
            default -> aggregatedData.get(position)[2] += count; // "Other"
        }
        // Update the total count
        aggregatedData.get(position)[3] += count;
    }

    // Populate the table with aggregated data
    for (Map.Entry<String, int[]> entry : aggregatedData.entrySet()) {
        String position = entry.getKey();
        int[] counts = entry.getValue();

        table.addCell(createBodyCell(position));
        table.addCell(createBodyCell(String.valueOf(counts[0]))); // Male
        table.addCell(createBodyCell(String.valueOf(counts[1]))); // Female
        table.addCell(createBodyCell(String.valueOf(counts[2]))); // Others
        table.addCell(createBodyCell(String.valueOf(counts[3]))); // Total
    }

    // Add the table to the document
    document.add(table);
}

// Method to create the qualification table
    private static void addQualificationTable(Document document, JsonArray facultyDetails) {
    // Define qualifications and recruitment statuses
    String[] qualifications = {"D.Sc/D.Litt", "Ph.D", "M.Phil", "PG"};
    String[] recruitmentStatuses = {"Permanent", "Temporary", "Part Time"};
    String[] academicRanks = {"Professor", "Associate Professor", "Assistant Professor"};

    // Group data by recruitment status and qualification
    Map<String, Map<String, Map<String, int[]>>> groupedData = new HashMap<>();

    // Initialize the data structure
    for (String status : recruitmentStatuses) {
        groupedData.put(status, new HashMap<>());
        for (String qualification : qualifications) {
            groupedData.get(status).put(qualification, new HashMap<>());
            for (String rank : academicRanks) {
                groupedData.get(status).get(qualification).put(rank, new int[3]); // [Male, Female, Others]
            }
        }
    }

    // Process the faculty details array
    for (JsonElement element : facultyDetails) {
        JsonObject faculty = element.getAsJsonObject();

        String recruitmentStatus = faculty.get("recruitmentStatus").getAsString();
        String highestQualification = faculty.get("highestQualification").getAsString();
        String academicRank = faculty.get("academicRank").getAsString();
        String gender = faculty.get("gender").getAsString();
        int count = faculty.get("count").getAsInt();

        // Validate recruitment status, qualification, and academic rank
        if (!groupedData.containsKey(recruitmentStatus) ||
            !groupedData.get(recruitmentStatus).containsKey(highestQualification) ||
            !groupedData.get(recruitmentStatus).get(highestQualification).containsKey(academicRank)) {
            continue;
        }

        // Update gender-specific counts
        int genderIndex = switch (gender.toLowerCase()) {
            case "male" -> 0;
            case "female" -> 1;
            default -> 2; // "Others"
        };
        groupedData.get(recruitmentStatus).get(highestQualification).get(academicRank)[genderIndex] += count;
    }

    // Generate a table for each recruitment status
    for (String recruitmentStatus : recruitmentStatuses) {
        // Create table for the current recruitment status
        Table table = new Table(UnitValue.createPercentArray(new float[]{3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2}));
        table.setWidth(UnitValue.createPercentValue(100));
        table.addHeaderCell(new Cell(1, 11).add(new Paragraph(recruitmentStatus + " Teachers").setBold().setFontSize(10))
                .setTextAlignment(TextAlignment.CENTER));

        // Header Row
        table.addCell(createHeaderCell("Highest Qualification", 2, 1));
        table.addCell(createHeaderCell("Professor", 2, 3));
        table.addCell(createHeaderCell("Associate Professor", 2, 3));
        table.addCell(createHeaderCell("Assistant Professor", 2, 3));
        table.addCell(createHeaderCell("Total", 2, 1));

        // Sub-header Row
        table.addCell("");
        for (int i = 0; i < 3; i++) {
            table.addCell(createSubHeaderCell("Male"));
            table.addCell(createSubHeaderCell("Female"));
            table.addCell(createSubHeaderCell("Others"));
        }
        table.addCell(""); // Total column

        // Data Rows
        for (String qualification : qualifications) {
            table.addCell(createBodyCell(qualification));

            int total = 0;
            for (String academicRank : academicRanks) {
                int[] counts = groupedData.get(recruitmentStatus).get(qualification).get(academicRank);

                table.addCell(createBodyCell(String.valueOf(counts[0]))); // Male
                table.addCell(createBodyCell(String.valueOf(counts[1]))); // Female
                table.addCell(createBodyCell(String.valueOf(counts[2]))); // Others

                total += counts[0] + counts[1] + counts[2];
            }
            table.addCell(createBodyCell(String.valueOf(total))); // Total
        }

        // Add the table to the document
        document.add(table);
        document.add(new Paragraph("\n")); // Add space between tables
    }
}

    private static Cell createHeaderCell(String content,int rowspan,int colspan) {
        return new Cell(rowspan, colspan)
                .add(new Paragraph(content).setBold())
                .setTextAlignment(TextAlignment.CENTER);
    }

    private static Cell createSubHeaderCell(String content) {
        return new Cell().add(new Paragraph(content).setBold()).setTextAlignment(TextAlignment.CENTER);
    }

      private static void addStaffTable(Document document, JsonArray staffData) {
    // Define categories
    String[] categories = {"Sanctioned", "Recruited", "Yet to Recruit","Contractual"};

    // Initialize data structures to hold aggregated data
    Map<String, int[]> technicalData = new HashMap<>();
    Map<String, int[]> nonTechnicalData = new HashMap<>();

    // Initialize default values
    for (String category : categories) {
        technicalData.put(category, new int[4]); // [Male, Female, Others, Total]
        nonTechnicalData.put(category, new int[4]); // [Male, Female, Others, Total]
    }

    // Process staff data
    for (JsonElement element : staffData) {
        JsonObject staff = element.getAsJsonObject();

        boolean isTechnical = staff.get("isTechnical").getAsBoolean();
        String recruitmentStatus = staff.get("recruitmentStatus").getAsString();
        String gender = staff.get("gender").getAsString();
        int count = staff.get("count").getAsInt();

        // Skip invalid recruitment status
        if (!Arrays.asList(categories).contains(recruitmentStatus)) {
            continue;
        }

        // Determine the correct map to update
        Map<String, int[]> targetData = isTechnical ? technicalData : nonTechnicalData;

        // Update gender-specific and total counts
        switch (gender.toLowerCase()) {
            case "male":
                targetData.get(recruitmentStatus)[0] += count;
                break;
            case "female":
                targetData.get(recruitmentStatus)[1] += count;
                break;
            default: // "Others"
                targetData.get(recruitmentStatus)[2] += count;
                break;
        }
        // Update total count
        targetData.get(recruitmentStatus)[3] += count;
    }

    // Helper function to create and populate a table
    BiConsumer<Map<String, int[]>, String> createAndAddTable = (dataMap, tableTitle) -> {
        // Add title for the table
        document.add(new Paragraph(tableTitle).setBold().setTextAlignment(TextAlignment.LEFT));

        // Create a new table for the current data
        Table table = new Table(UnitValue.createPercentArray(new float[]{2, 2, 2, 2, 2}));
        table.setWidth(UnitValue.createPercentValue(100));
        // Add header row
        table.addCell(createHeaderCell(""));
        table.addCell(createHeaderCell("Male"));
        table.addCell(createHeaderCell("Female"));
        table.addCell(createHeaderCell("Others"));
        table.addCell(createHeaderCell("Total"));

        // Populate rows with aggregated data
        for (String category : categories) {
            int[] data = dataMap.get(category);
            table.addCell(createBodyCell(category));
            table.addCell(createBodyCell(String.valueOf(data[0])));
            table.addCell(createBodyCell(String.valueOf(data[1])));
            table.addCell(createBodyCell(String.valueOf(data[2])));
            table.addCell(createBodyCell(String.valueOf(data[3])));
        }

        // Add the table to the document
        document.add(table);
        document.add(new Paragraph("\n")); // Add space after the table
    };

    // Create and add the table for Technical Staff
    createAndAddTable.accept(technicalData, "Technical Staff");

    // Create and add the table for Non-Technical Staff
    createAndAddTable.accept(nonTechnicalData, "Non-Technical Staff");
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

    private static void addTeachingFacultyTable(Document document, JsonArray FacultyDetails) {
    // Create table with 13 columns
    Table table = new Table(UnitValue.createPercentArray(new float[]{3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}));
    table.setWidth(UnitValue.createPercentValue(100));
    // Header Row 1
    table.addCell(createBodyCell("", 1, 3));
    table.addCell(createHeaderCell("Designation", 12));

    // Header Row 2
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


    // Define categories and designations
    String[] categories = {"Sanctioned", "Recruited", "Yet to Recruit","Contractual"};
    String[] designations = {"Professor", "Associate Professor", "Assistant Professor"};

    // Initialize map to store aggregated data
    Map<String, Map<String, int[]>> aggregatedData = new HashMap<>();

    // Process FacultyDetails JSON array
    for (JsonElement element : FacultyDetails) {
        JsonObject faculty = element.getAsJsonObject();
        String tenure = faculty.get("tenure").getAsString();
        String designation = faculty.get("academicRank").getAsString();
        String gender = faculty.get("gender").getAsString();
        int count = faculty.get("count").getAsInt();

        // Skip rows that don't match known categories or designations
        if (!Arrays.asList(categories).contains(tenure) || !Arrays.asList(designations).contains(designation)) {
            continue;
        }

        // Initialize data structure for the current category and designation
        aggregatedData.putIfAbsent(tenure, new HashMap<>());
        aggregatedData.get(tenure).putIfAbsent(designation, new int[4]); // [Male, Female, Others, Total]

        // Update gender-specific and total counts
        switch (gender.toLowerCase()) {
            case "male":
                aggregatedData.get(tenure).get(designation)[0] += count;
                break;
            case "female":
                aggregatedData.get(tenure).get(designation)[1] += count;
                break;
            default: // "Others"
                aggregatedData.get(tenure).get(designation)[2] += count;
                break;
        }
        // Update total count
        aggregatedData.get(tenure).get(designation)[3] += count;
    }

    // Populate table rows dynamically from aggregated data
    for (String category : categories) {
        table.addCell(createBodyCell(category, 1, 1)); // Category spanning all designations
        for (String designation : designations) {
            int[] data = aggregatedData.getOrDefault(category, new HashMap<>())
                    .getOrDefault(designation, new int[4]); // Default to zeros if no data exists

            // Add cells for Male, Female, Others, and Total counts
            table.addCell(createBodyCell(String.valueOf(data[0]), 1));
            table.addCell(createBodyCell(String.valueOf(data[1]), 1));
            table.addCell(createBodyCell(String.valueOf(data[2]), 1));
            table.addCell(createBodyCell(String.valueOf(data[3]), 1));
        }
    }

    // Add table to the document
    try {
        document.add(table);
    } catch (Exception e) {
        e.printStackTrace();
    }
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

    private static void addDynamicSRATable(Document document, DataFormaterService dataFormaterService, String areSRAProgram) {
    // Outer table with two equal-width columns
    Table outerTable = new Table(new float[]{1, 1}); // 1:1 ratio
    outerTable.setWidth(UnitValue.createPercentValue(100)); // Set width to 100%

    // Add the question cell (Column 1)
    Cell questionCell = new Cell()
            .add(new Paragraph(
                    "Is the University Offering any Programmes Recognised by any Statutory Regulatory Authority (SRA)?")
                    .setFontSize(10)) // Set font size
            .setPadding(5) // Add padding
            .setBorder(new SolidBorder(1)); // Ensure visible border
    outerTable.addCell(questionCell);

    // Add the response cell (Column 2)
    Cell responseCell = new Cell()
            .add(new Paragraph(": " + ("true".equalsIgnoreCase(areSRAProgram) ? "Yes" : "No"))
                    .setFontSize(10)) // Set font size
            .setPadding(5) // Add padding
            .setTextAlignment(TextAlignment.LEFT) // Align text to the left
            .setBorder(new SolidBorder(1)); // Ensure visible border
    outerTable.addCell(responseCell);

    // If "Yes", create and add the inner table in column 1
    if ("true".equalsIgnoreCase(areSRAProgram)) {
        // Check and extract SRA Programs
        JsonArray sraProgramsArray = JsonParser.parseString(dataFormaterService.getSRAPRogramms(1)).getAsJsonArray();

        // Create an inner table for SRA programs and documents
        Table innerTable = new Table(new float[]{3, 2}); // Adjust column widths for SRA Program and Document
        innerTable.setWidth(UnitValue.createPercentValue(100)); // Inner table takes full width of column 1

        // Add headers to the inner table
        innerTable.addCell(new Cell().add(new Paragraph("SRA Program").setBold().setFontSize(10)).setPadding(5));
        innerTable.addCell(new Cell().add(new Paragraph("Document").setBold().setFontSize(10)).setPadding(5));

        // Loop through the JsonArray to add rows for each program
        for (JsonElement element : sraProgramsArray) {
            JsonObject program = element.getAsJsonObject();

            // Safely retrieve program and document details
            String programName = program.has("name") && !program.get("name").isJsonNull()
                    ? program.get("name").getAsString()
                    : "N/A"; // Default to "N/A" if the key is missing or null

            JsonObject sraDocument = program.has("sraDocument") && !program.get("sraDocument").isJsonNull()
                    ? program.getAsJsonObject("sraDocument")
                    : null;

            String fileName = sraDocument != null && sraDocument.has("fileName") && !sraDocument.get("fileName").isJsonNull()
                    ? sraDocument.get("fileName").getAsString()
                    : "N/A";

            String fileIdentifier = sraDocument != null && sraDocument.has("fileIdentifier") && !sraDocument.get("fileIdentifier").isJsonNull()
                    ? sraDocument.get("fileIdentifier").getAsString()
                    : null;

            // Add SRA Program
            innerTable.addCell(new Cell().add(new Paragraph(programName).setFontSize(10)).setPadding(5));

            // Add Document (as a hyperlink with file name, or plain text if missing)
            if (fileIdentifier != null) {
                String documentUrl = recognitionDetailsPrefixURL + fileIdentifier; // Construct the URL
                innerTable.addCell(new Cell().add(new Paragraph(new Link(fileName, 
                        PdfAction.createURI(documentUrl)).setFontColor(com.itextpdf.kernel.colors.ColorConstants.BLUE).setFontSize(10))).setPadding(5));
            } else {
                innerTable.addCell(new Cell().add(new Paragraph(fileName).setFontSize(10)).setPadding(5));
            }
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

   
    // Function to add College Stats
    private static void addCollegeStatsTable(Document document, JsonObject academicDetails) {
    Table table = new Table(UnitValue.createPercentArray(new float[]{1, 1})); // Two equal-width columns
    table.setWidth(UnitValue.createPercentValue(100));

    // Add table headers
    table.addCell(createCellWithBoldAndSpacing("Type of Colleges", 10));
    table.addCell(createCellWithBoldAndSpacing("Numbers", 10));

    // Define mapping for JSON keys to user-friendly labels
    Map<String, String> typeMapping = new LinkedHashMap<>();
    typeMapping.put("constituentColleges", "Constituent Colleges");
    typeMapping.put("affiliatedColleges", "Affiliated Colleges");
    typeMapping.put("collegesUnder2f", "Colleges under 2(f)");
    typeMapping.put("collegesUnder2f12b", "Colleges under 2(f) and 12(b)");
    typeMapping.put("naacAccredited", "NAAC Accredited Colleges");
    typeMapping.put("collegesWithExcellence", "Colleges with Excellence");
    typeMapping.put("autonomousColleges", "Autonomous Colleges");
    typeMapping.put("collegesWithPgDepartments", "Colleges with PG Departments");
    typeMapping.put("collegesWithResearchDepartments", "Colleges with Research Departments");
    typeMapping.put("researchInstitutes", "Research Institutes");

    // Loop through the mapping and populate the table
    for (Map.Entry<String, String> entry : typeMapping.entrySet()) {
        String key = entry.getKey();
        String label = entry.getValue();

        int count = academicDetails.has(key) ? academicDetails.get(key).getAsInt() : 0;

        table.addCell(createCellWithSpacing(label, 10));
        table.addCell(createCellWithSpacing(String.valueOf(count), 10));
    }

    // Add the table to the document
    document.add(table.setMarginBottom(10));
}

private static Cell createCellWithBoldAndSpacing(String content, int fontSize) {
    return new Cell().add(new Paragraph(content).setBold().setFontSize(fontSize))
            .setPadding(5).setTextAlignment(TextAlignment.LEFT);
}

private static Cell createCellWithSpacing(String content, int fontSize) {
    return new Cell().add(new Paragraph(content).setFontSize(fontSize))
            .setPadding(5).setTextAlignment(TextAlignment.LEFT);
}

    private static void addRecognitionDetailsTable(Document document, JsonObject recognitionDetails) {
    Table table = new Table(UnitValue.createPercentArray(new float[] { 4, 4, 4 }));
    table.setWidth(UnitValue.createPercentValue(100));

    // Add the header row
    table.addHeaderCell(new Cell(1, 3)
            .add(new Paragraph("Recognition Details").setBold().setFontSize(10))
            .setFontSize(10).setPadding(5));
    table.addHeaderCell(new Cell(1, 3)
            .add(new Paragraph("Date of Recognition as a University by UGC or Any Other National Agency:")
                    .setBold().setFontSize(10))
            .setFontSize(10).setPadding(5));
    table.addHeaderCell(new Cell().add(new Paragraph("Under Section").setBold().setFontSize(10)));
    table.addHeaderCell(new Cell().add(new Paragraph("Date").setBold().setFontSize(10)));
    table.addHeaderCell(new Cell().add(new Paragraph("View Document").setBold().setFontSize(10)));

    // Add details for "recognitionDateUnderSection2f" and its document
    addRecognitionDetailRow(table, "2(f)", 
        getJsonValue(recognitionDetails, "recognitionDateUnderSection2f"), 
        recognitionDetails.has("recognitionDocument2f") 
            ? recognitionDetails.getAsJsonObject("recognitionDocument2f").get("fileIdentifier").getAsString() 
            : "Not Available");

    // Add details for "recognitionDateUnderSection12b" and its document
    addRecognitionDetailRow(table, "12(b)", 
        getJsonValue(recognitionDetails, "recognitionDateUnderSection12b"), 
        recognitionDetails.has("recognitionDocument12b") 
            ? recognitionDetails.getAsJsonObject("recognitionDocument12b").get("fileIdentifier").getAsString() 
            : "Not Available");

    // Add table to the document
    document.add(table.setMarginBottom(10));

    addSimpleRowTable(document, "University with Potential for Excellence", getJsonValue(recognitionDetails, "isUPE"));
}

private static void addRecognitionDetailRow(Table table, String section, String date, String documentUrl) {
    table.addCell(new Cell().add(new Paragraph(section).setFontSize(10)));
    table.addCell(new Cell().add(new Paragraph(date).setFontSize(10)));

    if (!"Not Available".equals(documentUrl)) {
      String final_url = recognitionDetailsPrefixURL + documentUrl;
        Link link = new Link("View Document", PdfAction.createURI(final_url));
        table.addCell(new Cell().add(new Paragraph(link).setFontColor(com.itextpdf.kernel.colors.ColorConstants.BLUE).setUnderline())
                .setFontSize(10).setPadding(5).setUnderline());
    } else {
        table.addCell(new Cell().add(new Paragraph("Not Available").setFontSize(10).setUnderline()));
    }
}

    // Function to add Campus Details Table
    private static void addCampusDetailsTable(Document document, JsonArray campusDetailsJsonArray) {
    Table table = new Table(UnitValue.createPercentArray(new float[]{2, 4, 2, 2, 2, 3, 2, 2}));
    table.setWidth(UnitValue.createPercentValue(100));

    // Add header cells
    table.addHeaderCell(
            new Cell(1, 8).add(new Paragraph("  Location, Area, and Activity of Campus").setBold().setFontSize(10))
                    .setFontSize(10).setPadding(5));
    table.addHeaderCell(new Cell().add(new Paragraph("Campus Type").setBold()).setFontSize(10));
    table.addHeaderCell(new Cell().add(new Paragraph("Address").setBold()).setFontSize(10));
    table.addHeaderCell(new Cell().add(new Paragraph("Location*").setBold()).setFontSize(10));
    table.addHeaderCell(new Cell().add(new Paragraph("Campus Area in Acres").setBold()).setFontSize(10));
    table.addHeaderCell(new Cell().add(new Paragraph("Built-up Area in sq.mts").setBold()).setFontSize(10));
    table.addHeaderCell(new Cell().add(new Paragraph("Programs Offered").setBold()).setFontSize(10));
    table.addHeaderCell(new Cell().add(new Paragraph("Establishment Date").setBold()).setFontSize(10));
    table.addHeaderCell(new Cell().add(new Paragraph("Recognition Date by UGC/MHRD").setBold()).setFontSize(10));

    // Populate table rows
    for (int i = 0; i < campusDetailsJsonArray.size(); i++) {
        JsonObject detail = campusDetailsJsonArray.get(i).getAsJsonObject();

        // Extract data with default fallbacks for safety
        String type = detail.has("type") ? detail.get("type").getAsString() : "Not Available";
        String address = detail.has("address") ? detail.get("address").getAsString() : "Not Available";
        String location = detail.has("location") ? detail.get("location").getAsString() : "Not Available";
        String campusArea = detail.has("campusArea") ? detail.get("campusArea").getAsString() : "Not Available";
        String builtUpArea = detail.has("builtUpArea") ? detail.get("builtUpArea").getAsString() : "Not Available";
        String establishmentDate = detail.has("establishmentDate") ? detail.get("establishmentDate").getAsString() : "Not Available";
        String recognitionDate = detail.has("recognitionDate") ? detail.get("recognitionDate").getAsString() : "Not Available";

        // Process programmesOffered as a comma-separated string
        String programmesOffered = "Not Available";
        if (detail.has("programmesOffered")) {
            JsonArray programmesArray = detail.getAsJsonArray("programmesOffered");
            ArrayList<String> programmesList = new ArrayList<>();
            for (JsonElement element : programmesArray) {
                programmesList.add(element.getAsString());
            }
            programmesOffered = String.join(", ", programmesList);
        }

        // Add cells to the table
        table.addCell(new Cell().add(new Paragraph(type).setFontSize(10)));
        table.addCell(new Cell().add(new Paragraph(address).setFontSize(10)));
        table.addCell(new Cell().add(new Paragraph(location).setFontSize(10)));
        table.addCell(new Cell().add(new Paragraph(campusArea).setFontSize(10)));
        table.addCell(new Cell().add(new Paragraph(builtUpArea).setFontSize(10)));
        table.addCell(new Cell().add(new Paragraph(programmesOffered).setFontSize(10)));
        table.addCell(new Cell().add(new Paragraph(establishmentDate).setFontSize(10)));
        table.addCell(new Cell().add(new Paragraph(recognitionDate).setFontSize(10)));
    }

    // Add table to the document
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
              String university_website = "http://" + website;
        Link link = new Link(website, PdfAction.createURI(university_website));

        table.addCell(new Cell().add(new Paragraph(link).setFontColor(com.itextpdf.kernel.colors.ColorConstants.BLUE))
                .setFontSize(10).setPadding(5));

        document.add(table.setMarginBottom(10));
    }

    // Function to add Dynamic Contacts Table
private static void addDynamicContactsTable(Document document, JsonArray contactsArray) {
    Table table = new Table(new float[] { 2, 2, 2, 2, 2, 3 });
    table.setWidth(UnitValue.createPercentValue(100));

    // Add Header row for the table
    table.addHeaderCell(new Cell(1, 6)
            .add(new Paragraph("Contacts for Communication").setBold().setFontSize(10))
            .setFontSize(10).setPadding(5));
    table.addCell(new Cell().add(new Paragraph("Designation").setBold()).setFontSize(10).setPadding(5));
    table.addCell(new Cell().add(new Paragraph("Name").setBold()).setFontSize(10).setPadding(5));
    table.addCell(new Cell().add(new Paragraph("Telephone with STD Code").setBold()).setFontSize(10).setPadding(5));
    table.addCell(new Cell().add(new Paragraph("Mobile").setBold()).setFontSize(10).setPadding(5));
    table.addCell(new Cell().add(new Paragraph("Fax").setBold()).setFontSize(10).setPadding(5));
    table.addCell(new Cell().add(new Paragraph("Email").setBold()).setFontSize(10).setPadding(5));

    // Loop through the contacts array to fill the data
    for (JsonElement element : contactsArray) {
        if (!element.isJsonObject()) {
            continue; // Skip invalid elements
        }
        JsonObject contact = element.getAsJsonObject();
        
        table.addCell(new Cell().add(new Paragraph(getJsonValue(contact, "designation")).setFontSize(10)));
        table.addCell(new Cell().add(new Paragraph(getJsonValue(contact, "name")).setFontSize(10)));
        table.addCell(new Cell().add(new Paragraph(getJsonValue(contact, "telephone")).setFontSize(10)));
        table.addCell(new Cell().add(new Paragraph(getJsonValue(contact, "phone")).setFontSize(10)));
        table.addCell(new Cell().add(new Paragraph(getJsonValue(contact, "fax")).setFontSize(10)));
        table.addCell(new Cell().add(new Paragraph(getJsonValue(contact, "email")).setFontSize(10)));
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
        // table.addCell(
        //         new Cell().add(new Paragraph(priorDate.isEmpty() ? "N/A" : priorDate)).setFontSize(10).setPadding(5));
        document.add(table.setMarginBottom(10));
    }
}
