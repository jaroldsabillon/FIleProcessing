import java.util.ArrayList;

public class FileObjectCreation {


    private ArrayList<DocxFile> listOfDocxObjects = new ArrayList<>();
    private ArrayList<DocxFile> listOfPptxObjects = new ArrayList<>();
    private ArrayList<DocxFile> listOfPdfObjects = new ArrayList<>();


    public ArrayList<DocxFile> getListOfDocxObjects() {
        return this.listOfDocxObjects;
    }
    public void addDocxObject(DocxFile file){
        this.listOfDocxObjects.add(file);
    }
    public void createDocxObjects(ArrayList<String> docx){
        for(String file: docx){
            addDocxObject(new DocxFile(file));
        }
    }



    public static void main(String[] args){


    }

}
