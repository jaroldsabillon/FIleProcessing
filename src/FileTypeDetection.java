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

    private static int totalCount = 0;

    public static int updateTotalCount(){
        return totalCount = totalCount+1;
    }
    public static int getTotalCount(){
        return totalCount;
    }



    //data structures to store names of files in dependent on file type
    public static ArrayList<String> pptxNames = new ArrayList<>();
    public static ArrayList<String> docxNames= new ArrayList<>();
    public static ArrayList<String> pdfNames = new ArrayList<>();
    public static ArrayList<String> unknownNames = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        fileCount.put("pdf", 0);
        fileCount.put("docx", 0);
        fileCount.put("pptx", 0);

        System.out.println("\n\t Traversing files in directory: " + DIRECTORY + "\n");
        //replace file detection with https://www.programiz.com/java-programming/examples/get-file-extension#:~:text=If%20you%20are%20using%20the,%22%3B%20String%20extension%20%3D%20Files.

        for(File f: new File(DIRECTORY).listFiles()) {//traverses all files in INPUT directory

            String fileName = f.toString();

            int index = fileName.lastIndexOf('.');
            if(index > 0) {
                String extension = fileName.substring(index + 1);
                if (fileCount.get(extension) == null) {
                    fileCount.put(extension, 0);
                } else {

                    fileCount.put(extension, fileCount.get(extension) + 1);
                    System.out.println("File name: " + fileName);
                    System.out.println(fileName + "\t" + extension);

                }
            }
        }

        System.out.println("\nAmount of docx files: " + fileCount.get("docx"));
        System.out.println("Amount of pdf files: " + fileCount.get("pptx"));
        System.out.println("Amount of ppt files: " + fileCount.get("pdf"));
        System.out.println("Total amount of files in folder: " + getTotalCount());

        for(String s: docxNames){
            System.out.println(s);
        }

        FileObj.main(new String[0], docxNames);


    }



}
