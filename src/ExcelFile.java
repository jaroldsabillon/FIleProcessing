import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.openxml4j.opc.PackageProperties;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class ExcelFile {


    private String name;
    private String Directory;


    public ExcelFile(String name, String dir) throws IOException {
        this.name = name;
        this.Directory = dir;
        FileInputStream fis = new FileInputStream(new File(dir+name));
        Workbook workbook = WorkbookFactory.create(fis);
        getFileInformation(dir, workbook);
    }


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

}