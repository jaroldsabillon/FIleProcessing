import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.ArrayList;

public class driver {

    //change to accept an array of arrays and create a for loop to enter each to make it.
    public static void main(ArrayList<String> pdfArray, ArrayList<String> docxArray, ArrayList<String> pptxArray, String dir) throws IOException, InvalidFormatException {

        System.out.print("Running second program\n");
        FileObjectCreation createobj = new FileObjectCreation();

        createobj.createDocxObjects(docxArray, dir);
        createobj.createPdfObjects(pdfArray, dir);
        createobj.createPptxObjects(pptxArray,dir);
        System.out.println("\n\n-------------\n\nword documents\n\n-------------");
        for(DocxFile docs: createobj.getListOfDocxObjects()){
            System.out.println("name of file: "+docs.getFileName()+"\n");
            System.out.println("name of author: "+docs.getAuthor()+"\n");
            System.out.println("Word count: "+docs.getWordCount()+"\n");
            System.out.println("file size: " + docs.getFileSize()+"\n");
            System.out.println("Page count: "+docs.getPageCount()+"\n");
            System.out.println("Page count: "+docs.getDateOfCreation()+"\n");

            System.out.println("\n\n-------------------");
            docs.createJSON();
        }
        System.out.println("\n\nPrinting pdf file data \n\n-----------------");
        for(PdfFile pdf: createobj.getListOfPdfObjects()){


            System.out.println("name of file: " + pdf.getFileName()+"\n");
            System.out.println("name of author: " + pdf.getAuthor()+"\n");
            System.out.println("page count: " + pdf.getPageCount()+"\n");
            System.out.println("file size: " + pdf.getFileSize()+"\n");
            System.out.println("word count: " + pdf.getWordCount()+"\n");
            System.out.println("date created: " + pdf.getFileMonth()+"/"+pdf.getFileDay()+
                    "/"+pdf.getFileYear()+" " +pdf.getFileHour()+":"+pdf.getFileMinute()+":"
                    +pdf.getFileSecond()+"\n");
            System.out.println("\n\n-------------------");
            pdf.createJSON();
        }
        System.out.println("\n\nPowerpoint files \n\n---------------");
        for(PptxFile pptx: createobj.getListOfPptxObjects()){
            System.out.println("name of file: "+pptx.getFileName()+"\n");
            System.out.println("name of author: "+ pptx.getAuthor()+"\n");
            System.out.println("Word count: "+ pptx.getWordCount()+"\n");
            System.out.println("file size: " + pptx.getFileSize()+"\n");
            System.out.println("Page count: "+ pptx.getNumberOfSlides()+"\n");
            System.out.println("date created: " + pptx.getCreationDate());
            System.out.println("\n\n-------------------");
            pptx.createJSON();
        }

    }
}
