import com.google.gson.Gson;
import org.apache.poi.EmptyFileException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.openxml4j.opc.PackageProperties;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class ExcelFile {


    private String name;
    private String Directory;
    private String allData;

    /**
     * Constructor
     * @param name name of the file
     * @param dir directory of the file
     * @throws IOException throws exception if file is not found
     * @throws InvalidFormatException throws invalidformatexception if the file is not of the correct type
     */
    public ExcelFile(String name, String dir) throws IOException{
        this.name = name;
        this.Directory = dir;

        FileInputStream fis = new FileInputStream(new File(dir+name));
        System.out.println(dir+name);
        try{

            Workbook workbook = WorkbookFactory.create(fis);
            getFileInformation(dir, workbook);

        }catch(EmptyFileException e){
            System.out.println("File not applicable");
            return;
        }
        this.createJSON();
    }

    /**
     * Retrieves file informationa and stores it within the object
     * @param filePath the path of the file
     * @param workbook a workbook object created using apache poi
     * @throws IOException thrown is file is not found
     */
    private void getFileInformation(String filePath, Workbook workbook) throws IOException {
        Path path = new File(filePath).toPath();
        BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);


        setFileName(this.name);

        try {
            PackageProperties properties = ((XSSFWorkbook) workbook).getPackage().getPackageProperties();
            setAuthor(properties.getCreatorProperty().orElse("Unknown"));
        } catch (InvalidFormatException e) {
            setAuthor("Unknown");
            e.printStackTrace();
        }

        int wordCount = 0;
        int rowCount = 0;

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            for (Row row : sheet) {
                wordCount += row.getPhysicalNumberOfCells();
            }
            rowCount += sheet.getPhysicalNumberOfRows();
        }

        setWordCount(wordCount);
        setRowCount(rowCount);
        setFileSize(attrs.size());
        setCreationTime(attrs.creationTime().toString());


    }


        private String fileName;
        private String author;
        private int wordCount;
        private long fileSize;
        private int rowCount;
        private String creationTime;

        // Getters
        public String getFileName() {
            return fileName;
        }

        public String getAuthor() {
            return author;
        }

        public int getWordCount() {
            return wordCount;
        }

        public long getFileSize() {
            return fileSize;
        }

        public int getRowCount() {
            return rowCount;
        }

        public String getCreationTime() {
            return creationTime;
        }

        // Setters
        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setWordCount(int wordCount) {
            this.wordCount = wordCount;
        }

        public void setFileSize(long fileSize) {
            this.fileSize = fileSize;
        }

        public void setRowCount(int rowCount) {
            this.rowCount = rowCount;
        }

        public void setCreationTime(String creationTime) {
            this.creationTime = creationTime;
        }


    public void createJSON() {
        this.allData = "{'name': '" + getFileName() + "',\n 'author': '" + getAuthor() + "',\n 'pagecount': " + getRowCount() +
                ",\n 'filesize': " + getFileSize() + ",\n 'wordcount': " + getWordCount() + ",\n 'created': '" + getCreationTime() + "'}";

        Gson gson = new Gson();

        // Convert the input string to a JSON object
        Object jsonObject = gson.fromJson(this.allData, Object.class);

        //change this directory to
        String outputFilePath = "./FileProcessing/src/FileOutput/";

        // Write the JSON object to a file
        try (FileWriter fileWriter = new FileWriter(outputFilePath + getFileName() + ".json")) {
            gson.toJson(jsonObject, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}