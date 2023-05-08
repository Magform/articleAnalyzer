import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Outputter {
    private boolean toConsole;
    private boolean toFile;
    private String file;

    public Outputter() {
        this.toConsole = false;
        this.toFile = false;
        this.file = "";
    }

    public Outputter(boolean toConsole, boolean toFile) {
        this.toConsole = toConsole;
        this.toFile = toFile;
        this.file = "";
    }

    public Outputter(boolean toConsole, boolean toFile, String file) {
        this(toConsole, toFile);
        this.file = file;
    }

    public void setFile(String file){
        this.file = file;
    }

    public String getFile(){
        return this.file;
    }

    public void setToConsole(boolean toSet){
        this.toConsole = toSet;
    }

    public void setToFile(boolean toSet){
        this.toFile = toSet;
    }

    public boolean getToConsole(){
        return this.toConsole;       
    }

    public boolean getToFile(){
        return this.toFile;
    }

    //Funzioni di print di una stringa (le funzioni sono private per scelta implementativa)
    public void print(String toPrint){
        if(this.toConsole){
            this.printToConsole(toPrint);
        }
        if(this.toFile){
            this.printToFile(toPrint);
        }
    }

    private void printToConsole(String toPrint){
        System.out.println(toPrint)
    }

    private void printToFile(String toPrint) throws IOException{
        if(this.file==""){
            throw new IOException("File were to print not setted");
        }
        try {
            File file = new File(this.file);
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(this.file, true);
            fileWriter.write(toPrint);
            fileWriter.close();
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + this.file, e);
        }
    }


}
