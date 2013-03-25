package TeamTwoHTMLEditor;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 3/21/13
 * Time: 10:00 AM
 * This is the model for the editor
 */
public class FileManager {
    private ArrayList<HTMLFile> HTMLFileArray;
    private static int numOpenFiles;
    //private boolean savePending;

    public FileManager() {
        HTMLFileArray = new ArrayList<HTMLFile>();
        numOpenFiles = HTMLFileArray.size();
        //savePending = false;
    }


    /**
     *
     */
    public void createNewFile(String filename) {
        HTMLFile x = new HTMLFile(filename);
        HTMLFileArray.add(x);
        numOpenFiles = HTMLFileArray.size();
        System.out.println("New File was Created with name: " + filename + "\nTotal files: " + Integer.toString(numOpenFiles));
    }

    /**
     *
     */
    public void openFile(File f, JTextArea p) {
        HTMLFile x = new HTMLFile(f.getPath());
        HTMLFileArray.add(x);
        numOpenFiles = HTMLFileArray.size();
        System.out.println("Opened a file with the name: " + f.getName());
        String contents = x.getFileContents();
        System.out.println(contents);
        p.setText(contents);
    }

    public void closeFile(int index) {
        System.out.println("Closing a file with the name: " + HTMLFileArray.get(index).getName());
        HTMLFileArray.remove(index);
        numOpenFiles = HTMLFileArray.size();
    }

    /**
     *
     */
    public void saveFile(File f, String contents, int indexOfFile) {
        HTMLFile fileToSave = new HTMLFile(f.getPath(), false);
        fileToSave.saveFile(contents);
        HTMLFileArray.get(indexOfFile).setSave(true);
    }


    /**
     * @return
     */
    public boolean canQuit() {
        for (HTMLFile aHTMLFileArray : HTMLFileArray) {
            if (aHTMLFileArray.isNeedToSave()) {
                return true;
            }
        }
        return false;
    }

    public boolean canQuit(int index) {
        return HTMLFileArray.get(index).isNeedToSave();
    }

    public int getNumOpenFiles() {
        return numOpenFiles;
    }

    public void shutdown() {
        if (!canQuit()) {

        }
    }

    public void printStatus() {
        System.out.println("**** File Manager Status *****");
        System.out.println("Total number of files open: " + Integer.toString(HTMLFileArray.size()));
        System.out.println("File List:");
        for (HTMLFile aFile : HTMLFileArray) {
            System.out.println("\t" + aFile.getName());
            if (aFile.isNeedToSave()) {
                System.out.println("\t\tSAVED");
            } else {
                System.out.println("\t\tNEEDS SAVE");
            }

        }
    }
}
