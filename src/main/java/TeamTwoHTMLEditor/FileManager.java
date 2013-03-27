package TeamTwoHTMLEditor;

import javax.swing.*;
import javax.swing.event.DocumentListener;
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

    public FileManager() {
        HTMLFileArray = new ArrayList<HTMLFile>();
        numOpenFiles = HTMLFileArray.size();
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
        x.setNeedSaveAs(false);
        x.setNeedToSave(false);
        HTMLFileArray.add(x);
        numOpenFiles = HTMLFileArray.size();
        printStatus();
        String contents = x.getFileContents();
        p.setText(contents);
    }

    /**
     * @param index - the index of where to close the file (backend)
     */
    public void closeFile(int index) {
        HTMLFileArray.remove(index);
        numOpenFiles = HTMLFileArray.size();

    }

    /**
     *
     */
    public void saveFile(File f, String contents, int indexOfFile) {
        HTMLFile fileToSave = new HTMLFile(f.getPath(), false);
        fileToSave.saveFile(contents);
        HTMLFileArray.get(indexOfFile).setNeedToSave(false);
        HTMLFileArray.get(indexOfFile).setNeedSaveAs(false);
    }

    public void quickSaveFile(String path, String contents, int indexOfFile) {
        System.out.println("Quick Saved file: " + path);
        HTMLFile fileToSave = new HTMLFile(path, false);
        fileToSave.saveFile(contents);
        HTMLFileArray.get(indexOfFile).setNeedToSave(false);
        HTMLFileArray.get(indexOfFile).setNeedSaveAs(false);
    }


    /**
     * Runs through the array of files to check if they have all been saved
     *
     * @return true if the system is allowed to quit
     */
    public boolean canQuit() {
        if (HTMLFileArray.isEmpty()) {
            return true;
        }
        for (HTMLFile aHTMLFileArray : HTMLFileArray) {
            if (aHTMLFileArray.isNeedToSave()) {
                return false;
            }
        }
        return true;
    }

    public boolean canQuitAt(int index) {
        return !(HTMLFileArray.get(index).isNeedToSave());
    }


    public void printStatus() {
        System.out.println("**** File Manager Status *****");
        System.out.println("Total number of files open: " + Integer.toString(HTMLFileArray.size()));
        System.out.println("File List:");
        for (HTMLFile aFile : HTMLFileArray) {
            System.out.println("\t" + aFile.getName());
            if (aFile.isNeedToSave()) {
                System.out.println("\t\tNEEDS SAVE");
            } else {
                System.out.println("\t\tSAVED");
            }

        }
        System.out.println("******************************END");
    }

    public boolean needsSaveAsDialog(int index) {
        return HTMLFileArray.get(index).getNeedSaveAs();
    }

    public String getPathAt(int index) {
        return HTMLFileArray.get(index).getFilename();
    }

    public DocumentListener getFileAt(int activePaneIndex) {
        return HTMLFileArray.get(activePaneIndex);
    }
}
