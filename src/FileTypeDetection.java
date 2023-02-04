import java.io.FileFilter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileTypeDetection {

    public final static String DIRECTORY = "./src/FileInput/";//absolute path to input

    public String getDirectory(){
        return DIRECTORY;
    }

    //getting file counts --> convert counts to hashmaps "name / count" easier to access and less commands.

    private static HashMap<String, Integer> fileCount = new HashMap<>(){{
        put("pptx",0);
        put("docx", 0);
        put("pdf", 0);
        put("other", 0);
    }};



    //data structures to store names of files in dependent on file type
    public static ArrayList<String> pptxNames = new ArrayList<>();
    public static ArrayList<String> docxNames= new ArrayList<>();
    public static ArrayList<String> pdfNames = new ArrayList<>();
    public static ArrayList<String> unknownNames = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        fileCount.put("England", 0);
        fileCount.put("England", 0);
        fileCount.put("England", 0);

        System.out.println("\n\t Traversing files in directory: " + DIRECTORY + "\n");
        //replace file detection with https://www.programiz.com/java-programming/examples/get-file-extension#:~:text=If%20you%20are%20using%20the,%22%3B%20String%20extension%20%3D%20Files.

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
