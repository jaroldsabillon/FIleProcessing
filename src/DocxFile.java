import java.util.Date;
import java.util.HashMap;

public class DocxFile {


    private int wordCount;

    private int pages;

    private String Author;

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


    //returns whether there is a flag in emails, links, or grammar.
    public String getErrorFlags(String validate){
        return this.fileValidation.get(validate);
    }

    //returns the response code for a validating a specific email
    public Integer getEmailResponse(String email){


        return emailsInFile.get(email);
    }

    public String DocXFile(String fileName) {
        return this.fileName = fileName;
    }

    public void getLinks(){

    }








}
