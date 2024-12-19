package com.example.demo;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.AreaBreakType;
import com.itextpdf.layout.properties.TextAlignment;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;

import java.io.IOException;

@SpringBootApplication
public class DownloadPdf {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(DemoApplication.class, args);
        // Create a PdfWriter to write to a file
        String dest = "output.pdf";
        PdfWriter writer = new PdfWriter(dest);

        // Create a PdfDocument object
        PdfDocument pdf = new PdfDocument(writer);
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new HeaderEventHandler(pdf));

        // Create a Document object
        Document document = new Document(pdf);
        PdfFont font = PdfFontFactory.createFont("Helvetica-Bold");

        // Add a paragraph to the document of 1st Table
        Paragraph p = addHeadingToPdf(font);
        document.add(p);
        Table tableFirst = addFirstTable(font, pdf);
        document.add(tableFirst);

        //Add Paragraph and Table of 2nd Table
        Paragraph secondTableHeading = addSecondHeadingToPdf(font);
        document.add(secondTableHeading);
        Table tableSecond = createSecondTable(font, pdf);
        document.add(tableSecond);

        //Add Paragraph and Table to 3rd Table
        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE)); // Forces the table to start on a new page
        addThirdHeadingToPdf(font, document);
        Table tableThird = createThirdTable(font, pdf);
        document.add(tableThird);

        //Add Paragraph and Table to 4th Table
        addFourthHeadingToPdf(font, document);
        Table tableFourth = createFourthTable(font, pdf);
        document.add(tableFourth);


        addFifthHeadingToPdf(font, document);
        createFifthTable(font, pdf,document);


        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE)); // Forces the table to start on a new page
        addSixthHeadingToPdf(font, document);
        Table tableSixth = createSixthTable(font, pdf,document);


        document.close();
        System.out.println("PDF created successfully at " + dest);
    }

    private static Table createSixthTable(PdfFont font, PdfDocument pdf, Document document) {
        float pageWidth = pdf.getDefaultPageSize().getWidth();
        float margin = 36;
        float[] columnWidths = {2, 2, 2, 2, 2, 2};
        Table table = new Table(columnWidths);
        table.setTextAlignment(TextAlignment.LEFT);
        table.setWidth(pageWidth - 2 * margin);


        table.addCell("Criteria");
        table.addCell("Formula");
        table.addCell("Bank Setup Value");
        table.addCell("Application Value");
        table.addCell("Decision Value");
        table.addCell("Status");

        // Second Row (Data Row) - add text to cells using Strings
        table.addCell("Age");
        table.addCell("Less than or equal to");
        table.addCell("60");
        table.addCell("61");
        table.addCell("1x");
        table.addCell("False");

        table.addCell(createSpanCellGray("Decision Boundary(decision Boundary under Decision Calculator", font, 6).setBold());
        float[] columnWidth2 = {2, 2, 2, 2, 2};
        Table table2 = new Table(columnWidth2);
        table2.addCell("Min Boundary");
        table2.addCell("Max Boundary");
        table2.addCell("Deviation");
        table2.addCell("Application Approved Limit");
        table2.addCell("Status");


        // Second Row (Data Row) - add text to cells using Strings
        table2.addCell("5,00");
        table2.addCell("15,000");
        table2.addCell("Fixed: Min:1,000 Max: 15,000");
        table2.addCell("15,000");
        table2.addCell("Deviated");

        table2.addCell(createSpanCellGray("Approval Limit Result", font, 6).setBold());

        float[] columnWidth3 = {2, 2};
        Table table3= new Table(columnWidth3);
        table3.setTextAlignment(TextAlignment.LEFT);
        table3.setWidth(pageWidth - 2 * margin);
          String[] res1={"Decision Matrix","Finalized Income (AED)"
          ,"Multiple Value", "Multiple Status", "(A) Limit Based on Income Multiplier"
          ,"Maximum DSR","Existing DSR","DSR Room (Maximum DSR-Existing DSR)"
                  ,"(B) Max Eligibility based on Exisiting Liabilities(AED)"
          ,"(C)Limit Amount(AED)","Decision Boundary (if any)",
          "Decision Boundary Deviation (if any)",
          "(D) Limit Amount",
          "Final Decision From","Final Decision Status"}   ;

        String[] res2= {"Expat Sit Income Multiplier","14,000","1.5x","ACTIVE","21,000","7000","3,535"
                ,"3,465","69,300","21,000","Min: 1,000 Max: 50,000",
                "Min: 990 Max:50,015","21,000","EXPAT_SIT_INCOME_MULTIPIER","Approved"};

        for(int i=0;i<res1.length;i++){
            table3.addCell(createCell(res1[i], font).setBold());
            table3.addCell(createCell(res2[i], font).setBold());
        }

        document.add(table);
        document.add(table2);
        document.add(table3);
        return table;
    }

    private static void addSixthHeadingToPdf(PdfFont font, Document document) {
        Paragraph sectionD = new Paragraph("Section E: Limit Assignment Results")
                .setFont(font)
                .setFontSize(9)
                .setBold()
                .setMarginTop(20)
                .setTextAlignment(TextAlignment.LEFT);

        Paragraph seg1 = new Paragraph("Decision Calculator 1")
                .setFont(font)
                .setFontSize(9)
                .setBold()
                .setMarginTop(20)
                .setTextAlignment(TextAlignment.LEFT);
        document.add(sectionD);
        document.add(seg1);
    }

    private static void createFifthTable(PdfFont font, PdfDocument pdf, Document document) {
        float pageWidth = pdf.getDefaultPageSize().getWidth();
        float margin = 36;
        float[] columnWidths = {2, 2, 2, 2, 2};
        Table table = new Table(columnWidths);
        table.setTextAlignment(TextAlignment.LEFT);
        table.setWidth(pageWidth - 2 * margin);

        String[] column1 = {
                "Criteria", "Age", "Nationality"
        };

        String[] column2 = {
                "Formula", "Greater Than or equal to", "Is in"
        };

        String[] column3 = {
                "Bank Set up Value", "18", "UAE"
        };

        String[] column4 = {
                "Application Value", "20", "UAE"
        };

        String[] column5 = {
                "Status", "True", "True"
        };
        for (int i = 0; i < column1.length; i++) {
            if (i == 0) {
                table.addCell(createCell(column1[i], font).setBold());  // First column
                table.addCell(createCell(column2[i], font).setBold());  // First column
                table.addCell(createCell(column3[i], font).setBold());  // First column
                table.addCell(createCell(column4[i], font).setBold());  // First column
                table.addCell(createCell(column5[i], font).setBold());  // First column
            } else {
                table.addCell(createCell(column1[i], font));  // First column
                table.addCell(createCell(column2[i], font));  // First column
                table.addCell(createCell(column3[i], font));  // First column
                table.addCell(createCell(column4[i], font));  // First column
                table.addCell(createCell(column5[i], font));  // First column
            }
        }

        //order of filtration
        table.addCell(createSpanCellGray("[Order of Filtration under 1 segmentation] (Filteration could be multiple in 1 segmentation", font, 6).setBold());
        document.add(table);
        float[] columnWidths2 = {2, 2, 2, 2, 2,2};
        Table table2 = new Table(columnWidths2);
        table2.setTextAlignment(TextAlignment.LEFT);
        table2.setWidth(pageWidth - 2 * margin);
        String[] ord1 = {
                "Condition", "Active"
        };

        String[] ord2 = {
                "Criteria", "UAE residency Years"
        };

        String[] ord3 = {
                "Formula", "Greater than or equal to"
        };

        String[] ord4 = {
                "Bank Set up Value", "55"
        };

        String[] ord5 = {
                "Application Value", "50"
        };

        String[] ord6 = {
                "Status", "False",""
        };

        for (int i = 0; i < ord1.length; i++) {
            if (i == 0) {
                table2.addCell(createCell(ord1[i], font).setBold());  // First column
                table2.addCell(createCell(ord2[i], font).setBold());  // First column
                table2.addCell(createCell(ord3[i], font).setBold());  // First column
                table2.addCell(createCell(ord4[i], font).setBold());  // First column
                table2.addCell(createCell(ord5[i], font).setBold());  // First column
                table2.addCell(createCell(ord6[i], font).setBold());  // First column

            } else {
                table2.addCell(createCell(ord1[i], font));  // First column
                table2.addCell(createCell(ord2[i], font));  // First column
                table2.addCell(createCell(ord3[i], font));  // First column
                table2.addCell(createCell(ord4[i], font));  // First column
                table2.addCell(createCell(ord5[i], font));  // First column
                table2.addCell(createCell(ord6[i], font));  // First column
            }
        }
        table2.addCell(createSpanCellGray("Deviation", font, 6).setBold());
        document.add(table2);

        float[] columnWidths3 = {2, 2, 2, 2};
        Table table3 = new Table(columnWidths3);
        table3.setTextAlignment(TextAlignment.LEFT);
        table3.setWidth(pageWidth - 2 * margin);


        String[] dev = {"Condition", ""};
        String[] dev2 = {"Formula", "Less by 2 years AND more by 20%"};
        String[] dev3 = {"Application Value", "50"};
        String[] dev4 = {"Status", "False"};


        for (int i = 0; i < ord1.length; i++) {
            if (i == 0) {
                table3.addCell(createCell(dev[i], font).setBold());  // First column
                table3.addCell(createCell(dev2[i], font).setBold());  // First column
                table3.addCell(createCell(dev3[i], font).setBold());  // First column
                table3.addCell(createCell(dev4[i], font).setBold());

            } else {
                table3.addCell(createCell(dev[i], font));  // First column
                table3.addCell(createCell(dev2[i], font));  // First column
                table3.addCell(createCell(dev3[i], font));  // First column
                table3.addCell(createCell(dev4[i], font));  // First column
            }
        }
        document.add(table3);
    }

    private static void addFifthHeadingToPdf(PdfFont font, Document document) {
        Paragraph sectionD = new Paragraph("Section D: Credit Rule Engine Results")
                .setFont(font)
                .setFontSize(9)
                .setBold()
                .setMarginTop(20)
                .setTextAlignment(TextAlignment.LEFT);

        Paragraph seg1 = new Paragraph("Segmentation 1 Failed")
                .setFont(font)
                .setFontSize(9)
                .setBold()
                .setMarginTop(20)
                .setTextAlignment(TextAlignment.LEFT);
        document.add(sectionD);
        document.add(seg1);
    }

    private static Table createFourthTable(PdfFont font, PdfDocument pdf) {
        float pageWidth = pdf.getDefaultPageSize().getWidth();
        float margin = 36;
        float[] columnWidths = {3, 3, 3, 3}; // Adjust width based on preference
        Table table = new Table(columnWidths);
        table.setTextAlignment(TextAlignment.LEFT);
        table.setWidth(pageWidth - 2 * margin);
        String[] column1 = {
                "Secured Lending Exposure", "Unsecured Lending Exposure", "Installments", "Open Date",
                "Currency Type", "Phase", "Role", "No of Installments", "No of Remaining Installments",
                "Method of Payment", "Outstanding Amount", "Payment Amount", "Number of Days Payment Delay"
        };

        // Define the second column data (values for first column)
        String[] column2 = {
                "$1,000,000", "-", "$200,000", "2022-05-01", "USD", "Initial Phase", "Lender", "12", "8",
                "Monthly", "$100,000", "$50,000", "30"
        };

        // Define the third column data (13 rows)
        String[] column3 = {
                "Secured Outstanding balance", "Unsecured Outstanding balance", "Phase", "Closed Date",
                "CB Contract ID", "Type of Contract", "Total Amount", "Provider No", "Payment Frequency",
                "Secured Contract Flag", "Worst Status", "Worst Status Date", "Overdue Amount"
        };

        // Define the fourth column data (values for third column)
        String[] column4 = {
                "-", "-", "Final Phase", "2023-04-01", "12345", "Fixed", "$1,200,000", "6789",
                "Monthly", "Yes", "Active", "2023-05-01", "$20,000"
        };

        for (int i = 0; i < column1.length; i++) {
            // Add cell data for the first and second columns

            if (i == 0) {
                table.addCell(createCell(column1[i], font).setBold());  // First column
                table.addCell(createCell(column2[i], font).setBold());  // Second column
                table.addCell(createCell(column3[i], font).setBold());  // Third column
                table.addCell(createCell(column4[i], font).setBold());  // Fourth column
            } else {
                table.addCell(createCell(column1[i], font));  // First column
                table.addCell(createCell(column2[i], font));  // Second column
                table.addCell(createCell(column3[i], font));  // Third column
                table.addCell(createCell(column4[i], font));  // Fourth column
            }

        }

        table.addCell(createSpanCellGray("", font, 4));
        table.addCell(createSpanCellGray("", font, 4));


        //After 2 empty rows values for Liability Information
        // First Column Data
        String[] li1 = {
                "Non-Installments",
                "Open Date",
                "Currency Type",
                "Phase",
                "Role",
                "Credit Limit",
                "Outstanding Balance",
                "Provider No"
        };

        // Second Column Data
        String[] li2 = {
                "Active",      // Bold
                "2023-01-01",
                "USD",
                "In Progress",
                "Manager",
                "$5000",
                "$2000",
                "Provider 1"
        };

        // Third Column Data
        String[] li3 = {
                "Phase",          // Bold
                "2023-12-31",
                "Full-Time",
                "CB12345",
                "$1000",
                "2023-01-01",
                "$500",
                ""  // Empty value for the last row
        };

        // Fourth Column Data (Mapped Values)
        String[] li4 = {
                "A",        // First value is "A"
                "B",
                "C",
                "D",
                "E",
                "F",
                "G",
                ""
        };

        // Add rows to the table
        for (int i = 0; i < 8; i++) {
            // Add cell data for the first and second columns
            table.addCell(createCell(li1[i], font));  // First column
            table.addCell(createCell(li2[i], font));  // Second column

            // Add cell data for the third and fourth columns
            table.addCell(createCell(li3[i], font));  // Third column
            table.addCell(createCell(li4[i], font));  // Fourth column
        }

        //Card
        table.addCell(createSpanCellGray("", font, 4));
        // First Column Data
        String[] card1 = {
                "Card",           // First row empty
                "Open Date",
                "Currency Type",
                "Phase",
                "Role",
                "Balance",
                "Credit Limit",
                "Amount Spent in this month",
                "Overdue Amount",
                ""  // Empty row
        };

        // Second Column Data (Mapped Values)
        String[] card2 = {
                "Active",          // Empty row for first row
                "2023-01-01",
                "USD",
                "Active",
                "Manager",
                "$5000",
                "", // Empty value for Credit Limit
                "$1000",
                "$200",
                ""  // Empty row
        };

        // Third Column Data
        String[] card3 = {
                "Phase",           // Empty row for first row
                "Closed Date",
                "CB Contract Id",
                "Type of Contract",
                "Provider No",
                "Number of Days Payment Delay",
                "Card Used Flag",
                "Secured Contract Flag",
                "Worst Status",
                "Worst Status Date"
        };

        // Fourth Column Data (Mapped Values)
        String[] card4 = {
                "A",          // Empty row for first row
                "2023-12-31",
                "CB12345",
                "Full-Time",
                "Provider 1",
                "30",
                "Yes",
                "Yes",
                "Critical",
                "2023-01-01"
        };

        // Add rows to the table
        for (int i = 0; i < 10; i++) {
            // Make the first row empty
            if (i == 0) {
                table.addCell(createCell(card1[i], font).setBold());  // First column
                table.addCell(createCell(card2[i], font).setBold());  // Second column
                table.addCell(createCell(card3[i], font).setBold());  // Third column
                table.addCell(createCell(card4[i], font).setBold());
            } else {
                table.addCell(createCell(card1[i], font));  // First column
                table.addCell(createCell(card2[i], font));  // Second column
                table.addCell(createCell(card3[i], font));  // Third column
                table.addCell(createCell(card4[i], font));  // Fourth column
            }
        }

        return table;

    }

    private static void addFourthHeadingToPdf(PdfFont font, Document document) {
        Paragraph sectionC = new Paragraph("Section C: Liability Information")
                .setFont(font)
                .setFontSize(9)
                .setBold()
                .setMarginTop(30)
                .setTextAlignment(TextAlignment.LEFT);
        document.add(sectionC);
    }

    private static Table createThirdTable(PdfFont font, PdfDocument pdf) {
        float pageWidth = pdf.getDefaultPageSize().getWidth();
        float margin = 36;
        float[] columnWidths = {1, 1};
        Table table = new Table(columnWidths);
        table.setTextAlignment(TextAlignment.LEFT);
        table.setWidth(pageWidth - 2 * margin);


        // Add headers for the second table
        table.addCell(createHeaderCell("Month", font));
        table.addCell(createHeaderCell("Amount of Salary", font));

        // Data rows for the second table (4 rows of values)
        String[] months = {
                "08/2024", "09/2024", "10/2024", "11/2024"
        };
        String[] salaries = {
                "$1000", "$1200", "$1100", "$1050"
        };

        // Add data rows to the second table
        for (int i = 0; i < months.length; i++) {
            table.addCell(createCell(months[i], font));  // Month column
            table.addCell(createCell(salaries[i], font)); // Salary column
        }

        return table;
    }

    private static void addThirdHeadingToPdf(PdfFont font, Document document) {
        Paragraph sectionB = new Paragraph("Section B: Income Information")
                .setFont(font)
                .setFontSize(9)
                .setBold()
                .setMarginTop(20)
                .setTextAlignment(TextAlignment.LEFT);

        Paragraph sourceOfSalary = new Paragraph("Source of Salary: AECB")
                .setFont(font)
                .setFontSize(8)
                .setTextAlignment(TextAlignment.LEFT);

        Paragraph dsr = new Paragraph("DSR: 2.5%")
                .setFont(font)
                .setFontSize(8)
                .setTextAlignment(TextAlignment.LEFT);

        Paragraph salaryInf = new Paragraph("Salary Information")
                .setFont(font)
                .setFontSize(8)
                .setTextAlignment(TextAlignment.LEFT);

        document.add(sectionB);
        document.add(sourceOfSalary);
        document.add(dsr);
        document.add(salaryInf);

    }

    private static Paragraph addSecondHeadingToPdf(PdfFont font) {
        return new Paragraph("Section A: Customer's Details")
                .setFont(font)
                .setFontSize(10)
                .setMargin(20)
                .setTextAlignment(TextAlignment.LEFT);
    }

    private static Table createSecondTable(PdfFont font, PdfDocument pdf) {
        float pageWidth = pdf.getDefaultPageSize().getWidth();
        float margin = 36;
        float[] columnWidths = {2, 2, 3, 3}; // Relative widths of columns
        Table table = new Table(columnWidths);
        table.setTextAlignment(TextAlignment.LEFT);
        table.setWidth(pageWidth - 2 * margin);


        // Add headings for the table
        table.addCell(createSpanCell("Personal Information", font, 4));


        // Add data rows for the second table
        // 2nd to 11th rows

        // Data for the first column and second column (Fields and Values)
        String[] fieldsColumn1 = {
                "Full Name", "Arabic Full Name", "Email ID", "Eid Number", "EID Issue Date",
                "EID Expiry Date", "Mobile Number", "Gender", "Nationality Code",
                "Company Name"
        };

        String[] valuesColumn2 = {
                "John Doe", "جون دو", "test@gmail.com", "123456789", "2023-01-01", "2028-01-01",
                "123-456-7890", "Male", "123", "XYZ Corp"
        };

        // Data for the third column and fourth column (Additional Fields and Values)
        String[] fieldsColumn3 = {
                "Month and Year of joining the Employer", "TML", "TML Employer Status",
                "Employment Status", "Marital Status", "Place of Birth", "DOB",
                "Educational Qualification Level", "Field of Study", "Mother's Full Name"
        };

        String[] valuesColumn4 = {
                "January 2023", "TML001", "Active", "Full-time", "Single", "City A",
                "1990-05-01", "Bachelor's Degree", "Computer Science", "Jane Doe"
        };

        // Add the data rows (each row will have 4 columns)
        for (int i = 0; i < 10; i++) {
            table.addCell(createCell(fieldsColumn1[i], font));
            table.addCell(createCell(valuesColumn2[i], font));
            table.addCell(createCell(fieldsColumn3[i], font));
            table.addCell(createCell(valuesColumn4[i], font));
        }

        //Employment Information
        table.addCell(createSpanCell("Employment Information", font, 4));

        String[] fieCol1 = {
                "Company Code", "Office Email ID", "Designation"
        };
        String[] colval2 = {
                "COMP123", "john.doe@xyz.com", "Software Engineer"
        };

        // Fields and values for the second set (Work Permit Status, Contract Start Date)
        String[] fieldcol3 = {
                "Work Permit Status", "Contract Start Date", ""
        };
        String[] valcol4 = {
                "Active", "2023-01-01", ""
        };

        // Add the data rows for the first set (fieldsColumn1 and valuesColumn2)
        for (int i = 0; i < fieCol1.length; i++) {
            table.addCell(createCell(fieCol1[i], font));   // First column
            table.addCell(createCell(colval2[i], font));   // Second column
            table.addCell(createCell(fieldcol3[i], font));   // Third column
            table.addCell(createCell(valcol4[i], font));   // Fourth column
        }
        //VISA & Residency Information
        table.addCell(createSpanCell("VISA & Residency Information", font, 4));

        String[] visa1 = {
                "VISA Status", "VISA Emirates", "VISA Type"
        };
        String[] visa2 = {
                "Active", "Dubai", "Golden visa"
        };

        // Fields and values for the second set (Work Permit Status, Contract Start Date)
        String[] visa3 = {
                "Residency Number", "Residency Expiry Date", ""
        };
        String[] visa4 = {
                "9830", "2023-01-01", ""
        };

        // Add the data rows for the first set (fieldsColumn1 and valuesColumn2)
        for (int i = 0; i < fieCol1.length; i++) {
            table.addCell(createCell(visa1[i], font));   // First column
            table.addCell(createCell(visa2[i], font));   // Second column
            table.addCell(createCell(visa3[i], font));   // Third column
            table.addCell(createCell(visa4[i], font));   // Fourth column
        }
        //Passport Information
        table.addCell(createSpanCell("Passport Information", font, 4));

        String[] pass1 = {
                "Passport Number", "Passport Country Code", "Potential NTC"
        };
        String[] pass2 = {
                "P9459019", "UAE", "Yes"
        };

        // Fields and values for the second set (Work Permit Status, Contract Start Date)
        String[] pass3 = {
                "Passport Issue Date", "Passport Expiry Date", "Home Address"
        };
        String[] pass4 = {
                "01/01/2020", "01/01/2035", "Appartment 704, \n" +
                "The Address, \n " +
                " Boulevard, \n" +
                " DownTown Dubai, \n" +
                " Dubai, \n " +
                "United Arab Emirates."
        };

        // Add the data rows for the first set (fieldsColumn1 and valuesColumn2)
        for (int i = 0; i < fieCol1.length; i++) {
            table.addCell(createCell(pass1[i], font));   // First column
            table.addCell(createCell(pass2[i], font));   // Second column
            table.addCell(createCell(pass3[i], font));   // Third column
            table.addCell(createCell(pass4[i], font));   // Fourth column
        }


        return table;
    }

    private static Cell createSpanCell(String text, PdfFont font, int colspan) {
        // Create the cell that spans 'colspan' columns
        Cell cell = new Cell(1, colspan);

        // Create a paragraph to hold the text, then add it to the cell
        Paragraph paragraph = new Paragraph(text).setFont(font)
                .setTextAlignment(TextAlignment.LEFT)
                .setFontSize(10)
                .setBold();
        cell.add(paragraph);  // Add the paragraph to the cell
        return cell;
    }

    private static Cell createSpanCellGray(String text, PdfFont font, int colspan) {
        // Create the cell that spans 'colspan' columns
        Cell cell = new Cell(1, colspan);
        // Create a paragraph to hold the text, then add it to the cell
        Paragraph paragraph = new Paragraph(text).setFont(font)
                .setTextAlignment(TextAlignment.LEFT)
                .setFontSize(10)
                .setBold()
                .setPadding(5);
        cell.add(paragraph);  // Add the paragraph to the cell
        return cell;
    }

    private static Table addFirstTable(PdfFont font, PdfDocument pdf) {
        float pageWidth = pdf.getDefaultPageSize().getWidth();
        float margin = 36;
        float[] columnWidths = {2, 2, 3, 3}; // Relative widths of columns
        Table table = new Table(columnWidths);

        table.setTextAlignment(TextAlignment.LEFT);
        table.setWidth(pageWidth - 2 * margin); // Subtract margin on both sides

        // Correct usage of DeviceGray

        // Add table headers with bold text inside Paragraph
        table.addCell(createHeaderCell("Application Id", font));
        table.addCell(createHeaderCell("Date", font));
        table.addCell(createHeaderCell("Product Type", font));
        table.addCell(createHeaderCell("Relationship to Bank", font));

        // Add sample rows to the table
        table.addCell(createCell("12345", font));
        table.addCell(createCell("01/01/2024", font));
        table.addCell(createCell("Home Loan", font));
        table.addCell(createCell("Primary Bank", font));


        return table;
    }

    private static Paragraph addHeadingToPdf(PdfFont font) {
        return new Paragraph("CAM CARDS REPORT")
                .setFont(font)
                .setFontSize(13)
                .setTextAlignment(TextAlignment.CENTER)
                .setUnderline();

    }

    private static Cell createHeaderCell(String text, PdfFont font) {
        return new Cell().add(new Paragraph(new Text(text).setFont(font)))
                //.setBackgroundColor(backgroundColor)
                .setFontSize(10) // Reduced font size for header
                .setPadding(1f)
                .setTextAlignment(TextAlignment.LEFT);
    }

    // Custom method to create data cells with reduced padding
    private static Cell createCell(String text, PdfFont font) {
        return new Cell().add(new Paragraph(new Text(text)))
                .setFontSize(10) // Reduced font size for content
                .setPadding(0.5f)  // Reduce padding to make the cell more compact
                .setTextAlignment(TextAlignment.LEFT);
    }

    @AllArgsConstructor
    static class HeaderEventHandler implements IEventHandler {
        private PdfDocument pdfDocument;

        public HeaderEventHandler(PdfDocument pdf) {
            this.pdfDocument = pdf;
        }


        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfPage page = docEvent.getPage();

            // Create the header text "Internal"
            PdfCanvas canvas = new PdfCanvas(page);

            // Set the font for the header (you can change this)
            PdfFont font = null;
            try {
                font = PdfFontFactory.createFont("Helvetica-Bold");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            canvas.setFontAndSize(font, 5);

            // Add the header text at the top-left corner (x=36, y=top-36)
            canvas.beginText()
                    .moveText(36, page.getPageSize().getTop() - 36)
                    .showText("Internal")
                    .endText();
        }
    }
}


