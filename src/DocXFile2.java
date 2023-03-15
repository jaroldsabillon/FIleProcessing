import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHyperlink;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFProperties;

public class DocxFileInfoExtractor {
    private String filePath;

    public DocxFileInfoExtractor(String filePath) {
        this.filePath = filePath;
    }

    public int getWordCount() throws InvalidFormatException, IOException {
        XWPFWordExtractor extractor = new XWPFWordExtractor(new XWPFDocument(new FileInputStream(filePath)));
        int wordCount = extractor.getWordCount();
        extractor.close();
        return wordCount;
    }

    public int getPageCount() throws InvalidFormatException, IOException {
        XWPFWordExtractor extractor = new XWPFWordExtractor(new XWPFDocument(new FileInputStream(filePath)));
        int pageCount = extractor.getPageCount();
        extractor.close();
        return pageCount;
    }

    public String getAuthor() throws InvalidFormatException, IOException {
        XWPFDocument document = new XWPFDocument(new FileInputStream(filePath));
        XWPFProperties properties = document.getProperties();
        String author = properties.getCoreProperties().getCreator();
        document.close();
        return author;
    }

    public Date getDateOfCreation() throws InvalidFormatException, IOException {
        XWPFDocument document = new XWPFDocument(new FileInputStream(filePath));
        XWPFProperties properties = document.getProperties();
        Date dateOfCreation = properties.getCoreProperties().getCreated();
        document.close();
        return dateOfCreation;
    }

    public long getFileSize() {
        File file = new File(filePath);
        return file.length();
    }

    public String getFileName() {
        File file = new File(filePath);
        return file.getName();
    }

    public HashMap<String, Integer> getLinksInFile() throws InvalidFormatException, IOException {
        HashMap<String, Integer> linksInFile = new HashMap<String, Integer>();
        XWPFDocument document = new XWPFDocument(new FileInputStream(filePath));
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            for (XWPFHyperlink hyperlink : paragraph.getHyperlinks()) {
                linksInFile.put(hyperlink.getURL(), hyperlink.getCTHyperlink().sizeOfRArray());
            }
        }
        document.close();
        return linksInFile;
    }

    public HashMap<String, Integer> getEmailsInFile() throws InvalidFormatException, IOException {
        HashMap<String, Integer> emailsInFile = new HashMap<String, Integer>();
        XWPFDocument document = new XWPFDocument(new FileInputStream(filePath));
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            String text = paragraph.getText();
            String[] words = text.split("\\s+");
            for (String word : words) {
                if (word.contains("@")) {
                    emailsInFile.put(word, -1);
                }
            }
        }
        document.close();
        return emailsInFile;
    }

    public HashMap<String, String> getFileValidation() {
        HashMap<String, String> fileValidation = new HashMap<String, String>();
        fileValidation.put("emails", "null");
        fileValidation.put("links", "null");
        fileValidation.put("grammar", "null");
        return fileValidation;
    }
}
