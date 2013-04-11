package TeamTwoHTMLEditor;

import TeamTwoHTMLEditor.GUI.TabFrame;

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
    public void openFile(File f, JTextArea pane) {
        HTMLFile x = new HTMLFile(f.getPath());
        x.setNeedSaveAs(false);
        x.setNeedToSave(false);
        HTMLFileArray.add(x);
        numOpenFiles = HTMLFileArray.size();
        printStatus();
        String contents = x.getFileContents();
        pane.setText(contents);
    }

    public ArrayList<String> getLinksAt(int index) {
         return HTMLFileArray.get(index).getLinksList().getLinks();
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
        System.out.println("Total number of files open: " + 
						   Integer.toString(HTMLFileArray.size()));
        System.out.println("File List:");
        if (HTMLFileArray.isEmpty()) System.out.println(" NONE");
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

    /**
     * Method to see if a file needs the SaveAs dialog.
     *
     * @param index - the index of where in the tabs and where in the file array the file to deal with is
     * @return - True if the file needs a saveAs, false otherwise
     */
    public boolean needsSaveAsDialog(int index) {
        return HTMLFileArray.get(index).getNeedSaveAs();
    }

    /**
     * Get the name (which is the path) of the file requested
     *
     * @param index - The file requested at this index
     * @return - Returns a string of the filename == pathname
     */
    public String getPathAt(int index) {
        return HTMLFileArray.get(index).getFilename();
    }

    /**
     * This is in order for every pane that is in the view to have the actual back end file object (HTMLFile instance)
     * Listen to it for changes.
     *
     * @param activePaneIndex - the index of the location of the file
     * @return The document (HTML Document extend DocumentListener)
     */
    public DocumentListener getFileAt(int activePaneIndex) {
        return HTMLFileArray.get(activePaneIndex);
    }

}
