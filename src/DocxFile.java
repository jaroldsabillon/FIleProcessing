import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHyperlink;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

public class DocxFile {



    public DocxFile(String name, String Directory) throws IOException, InvalidFormatException {
        this.fileName = name;
        this.Directory = Directory;

        this.setWordCount();
        this.setPageCount();

    }

    private String Directory;
    private int wordCount;
    private int pageCount;
    private String author;
    private int fileSize;
    private Date dateOfCreation;
    private String fileName;
    //Stores each links response code
    private HashMap<String, Integer> linksInFile = new HashMap<String, Integer>();
    //Stores emails and if they're valid
    private HashMap<String, Integer> emailsInFile = new HashMap<String, Integer>();
    private HashMap<String, String> fileValidation = new HashMap<String, String>(){{
        put("emails","null");
        put("links", "null");
        put("grammar", "null");
    }};

    public void setWordCount() throws IOException {
        XWPFWordExtractor extractor = new XWPFWordExtractor(new XWPFDocument(new FileInputStream(Directory+fileName)));
        String allText = extractor.getText();
        String[] words = allText.split("\\s+");
        int wordCount = words.length;
        //int wordCount = allText.length();
        extractor.close();
        this.wordCount = wordCount;

    }
    public Integer getWordCount(){
        return this.wordCount;
    }
    public void setPageCount() throws IOException {
        XWPFDocument document = new XWPFDocument(new FileInputStream(Directory+fileName));
        int pageCount = document.getProperties().getExtendedProperties().getPages();
        document.close();
        this.pageCount = pageCount;
    }
    public int getPageCount(){
        return this.pageCount;
    }
    public void setAuthor(String name){
        this.author = name;
    }
    public String getAuthor(){
        return this.author;
    }
    public void setFileSize(int size){
        this.fileSize = size;
    }
    public int getFileSize(){
        return this.fileSize;
    }
    public void setDateOfCreation(Date date){
        this.dateOfCreation = date;
    }
    public Date getDateOfCreation(){
        return this.dateOfCreation;
    }
    //returns whether there is a flag in emails, links, or grammar.
    public String getErrorFlag(String validate){
        return this.fileValidation.get(validate);
    }
    public void setErrorFlag(String validate, String error){
        this.fileValidation.put(validate, error);
    }

    //returns the response code for a validating a specific email
    public Integer getEmailResponse(String email){
        return this.emailsInFile.get(email);
    }
    public void setEmailResponse(String email, Integer code){
        this.emailsInFile.put(email, code);
    }

    public String getFileName() {
        return this.fileName = fileName;
    }
    public void setLinksResponseInFile(String link, Integer code){
        this.linksInFile.put(link, code);
    }
    public Integer getLinkResponseCode(String link){
        return this.linksInFile.get(link);
    }

    public void OutPutJson(){

    }



}
