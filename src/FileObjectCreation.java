
import java.io.IOException;
import java.util.ArrayList;

public class FileObjectCreation {

    private static String DIRECTORY ;
    private ArrayList<DocxFile> listOfDocxObjects = new ArrayList<>();
    private ArrayList<PptxFile> listOfPptxObjects = new ArrayList<>();
    private ArrayList<PdfFile> listOfPdfObjects = new ArrayList<>();


    public ArrayList<DocxFile> getListOfDocxObjects() {
        return this.listOfDocxObjects;
    }
    public ArrayList<PdfFile> getListOfPdfObjects(){ return this.listOfPdfObjects;}
    public ArrayList<PptxFile> getListOfPptxObjects(){ return this.listOfPptxObjects;}
    public void addDocxObject(DocxFile file){
        this.listOfDocxObjects.add(file);
    }
    public void addPdfObject(PdfFile file){ this.listOfPdfObjects.add(file);};
    public void addPptxObject(PptxFile file){ this.listOfPptxObjects.add(file);};

    public void createDocxObjects(ArrayList<String> docx, String dir) throws IOException{
        DIRECTORY = dir;
        for(String file: docx){
            addDocxObject(new DocxFile(file,this.DIRECTORY));
        }
    }
    public void createPptxObjects(ArrayList<String> pptx, String dir) throws IOException {
        DIRECTORY = dir;
        for(String file: pptx){
            addPptxObject(new PptxFile(file,this.DIRECTORY));
        }
    }
    public void createPdfObjects(ArrayList<String> pdf, String dir) throws IOException {
        DIRECTORY = dir;
        for(String file:pdf) {
            addPdfObject(new PdfFile(file, this.DIRECTORY));
        }
    }

}
