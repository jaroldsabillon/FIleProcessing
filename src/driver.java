import java.util.ArrayList;

public class driver {


    public static void main(ArrayList<String> docxarray){

        System.out.print("Running second program\n");
        FileObjectCreation createobj = new FileObjectCreation();

        createobj.createDocxObjects(docxarray);

        for(DocxFile docs: createobj.getListOfDocxObjects()){
            System.out.println(docs.getFileName()+"\n");
        }

    }
}
