import java.io.FileFilter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileTypeDetection {

    public final static String DIRECTORY = "./src/FileInput/";//absolute path to input

    public String getDirectory(){
        return DIRECTORY;
    }

    //getting file counts --> convert counts to hashmaps "name / count" easier to access and less commands.
    private static int totalCount = 0;
    private static int docxCount = 0;
    private static int pdfCount = 0;
    private static int pptxCount = 0;
    private static int unknownCount = 0;

    public static void updateTotalCount(){
        totalCount = totalCount+1;
    }
    public static int getTotalCount(){
        return totalCount;
    }
    public static void updateUnknownCount(){
        unknownCount = unknownCount+1;
    }
    public static int getUnknownCount(){
        return unknownCount;
    }

    public static void updateDocxCount(){
        docxCount = docxCount+1;
    }
    public static int getDocxCount(){
        return docxCount;
    }
    public static void updatePdfCount(){
        pdfCount = pdfCount +1;
    }
    public static int getPdfCount(){
        return pdfCount;
    }
    public static void updatePptxCount(){
        pptxCount = pptxCount+1;
    }
    public static int getPptxCount(){
        return pptxCount;
    }


    //data structures to store names of files in dependent on file type
    public static ArrayList<String> pptxNames = new ArrayList<>();
    public static ArrayList<String> docxNames= new ArrayList<>();
    public static ArrayList<String> pdfNames = new ArrayList<>();
    public static ArrayList<String> unknownNames = new ArrayList<>();

    public static void main(String[] args) throws IOException {


        System.out.println("\n\t Traversing files in directory: " + DIRECTORY + "\n");

        for(File f: new File(DIRECTORY).listFiles()) {//traverses all files in INPUT directory
            updateTotalCount();

            //Counting and adding file names to appropriate data structure
            if (f.getName().endsWith(".docx")) {

                updateDocxCount();

                String inputFileName = f.getName();
                docxNames.add(inputFileName);
                System.out.println("File name: " +inputFileName);

            }
            else if(f.getName().endsWith(".pdf")){
                updatePdfCount();
                String inputFileName = f.getName();
                pdfNames.add(inputFileName);
                System.out.println("File name: " +inputFileName);

            }
            else if(f.getName().endsWith(".pptx")){
                updatePptxCount();
                String inputFileName = f.getName();
                pptxNames.add(inputFileName);
                System.out.println("File name: " +inputFileName);
            }
            else{
                updateUnknownCount();
                String inputFileName = f.getName();
                unknownNames.add(inputFileName);
                System.out.println("File name: " +inputFileName);
            }

        }

        System.out.println("\nAmount of docx files: " + getDocxCount());
        System.out.println("Amount of pdf files: " + getPdfCount());
        System.out.println("Amount of ppt files: " + getPptxCount());
        System.out.println("Amount of unknown files: " + getUnknownCount());
        System.out.println("Total amount of files in folder: " + getTotalCount());

        for(String s: docxNames){
            System.out.println(s);
        }

        FileObj.main(new String[0], docxNames);


    }



}
