import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.IOException;

public class DownloadPdf {

    public static void main(String[] args) throws IOException {
        // Create a PdfWriter to write to a file
        String dest = "output.pdf";
        PdfWriter writer = new PdfWriter(dest);

        // Create a PdfDocument object
        PdfDocument pdf = new PdfDocument(writer);

        // Create a Document object
        Document document = new Document(pdf);

        // Add a paragraph to the document
        document.add(new Paragraph("Hello, iText!"));

        // Close the document (this writes the content to the file)
        document.close();

        System.out.println("PDF created successfully at " + dest);
    }
}
