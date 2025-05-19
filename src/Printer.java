import java.io.*;
import java.util.Scanner;

public class Printer {
    
    private Scanner in = new Scanner(System.in);
    private Scanner fileInput;
    private File file;

    public void openFile(String filename) throws FileNotFoundException {
        file = new File(filename);
        fileInput = new Scanner(file);
    }

    /**
     * Save changes made to decks to a file
     * @param filename
     * @throws IOException
     */
    public void saveToFile(String filename, String textToSave) throws IOException { 
        FileWriter myWriter = new FileWriter(filename);
        myWriter.write(textToSave);
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
    }

    public void closeFile() {
        fileInput.close();
        file = null;
        fileInput = null;
    }

    public boolean fileHasNextLine() {
        if (fileInput == null) {
            return false;
        }else {
            return fileInput.hasNextLine();
        }
    }

    public String getNextLine() {
        if (fileHasNextLine()) {
            return fileInput.nextLine();
        }else {
            return "";
        }      
    }

    public void output(String s) {
        System.out.println(s);
    }

    public String input() {
        return in.nextLine();
    }

}