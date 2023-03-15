import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.ArrayList;

public class driver {

    //change to accept an array of arrays and create a for loop to enter each to make it.
    public static void main(ArrayList<String> pdfArray, ArrayList<String> docxArray, String dir) throws IOException, InvalidFormatException {

        System.out.print("Running second program\n");
        FileObjectCreation createobj = new FileObjectCreation();

        createobj.createDocxObjects(docxArray, dir);
        createobj.createPdfObjects(pdfArray, dir);
        System.out.println("\n\n-------------------");
        for(DocxFile docs: createobj.getListOfDocxObjects()){
            System.out.println("name of file: "+docs.getFileName()+"\n");
            System.out.println("Word count: "+docs.getWordCount()+"\n");
            System.out.println("Page count: "+docs.getPageCount()+"\n");
            System.out.println("\n\n-------------------");
        }
        System.out.println("Printing pdf file data \n\n");
        for(PdfFile pdf: createobj.getListOfPdfObjects()){


            System.out.println("name of file: " + pdf.getFileName()+"\n");
            System.out.println("name of author: " + pdf.getAuthor()+"\n");
            System.out.println("page count: " + pdf.getPageCount()+"\n");
            System.out.println("file size: " + pdf.getFileSize()+"\n");
            System.out.println("word count: " + pdf.getWordCount()+"\n");
            System.out.println("date created: " + pdf.getDateOfCreation()+"\n\n-------------------");
            pdf.createJSON();
        }

    }
}
