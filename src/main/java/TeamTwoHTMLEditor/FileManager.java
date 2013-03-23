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
    public void openFile(File f, JEditorPane p) {
        HTMLFile x = new HTMLFile(f.getPath());
        HTMLFileArray.add(x);
        numOpenFiles = HTMLFileArray.size();
        System.out.println("Opened a file with the name: " + f.getName() + "\nTotal files: " + Integer.toString(numOpenFiles));
        String contents = x.getFileContents();
        System.out.println(contents);
        p.setText(contents);
    }

    public void closeFile(int index) {
        HTMLFileArray.remove(index);
        numOpenFiles = HTMLFileArray.size();
        System.out.println("Total files: " + Integer.toString(numOpenFiles));
    }

    /**
     *
     */
    public void saveFile(File f) {
        //now you need to save the file!
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
        // if ! saved
        ///do you want to save your files?
        ///

        //else close everything
        if (!canQuit()) {

        }
    }
}
