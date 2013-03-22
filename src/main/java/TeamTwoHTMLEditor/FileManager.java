package TeamTwoHTMLEditor;

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
    private boolean savePending;

    public FileManager() {
        HTMLFileArray = new ArrayList<HTMLFile>();
        numOpenFiles = HTMLFileArray.size();
        savePending = false;
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
    public void openFile(File f) {
        HTMLFile x = new HTMLFile(f.getName());
        HTMLFileArray.add(x);
        numOpenFiles = HTMLFileArray.size();
        System.out.println("Opened a file with the name: " + f.getName() + "\nTotal files: " + Integer.toString(numOpenFiles));
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
        for (int i = 0; i < HTMLFileArray.size(); i++) {
            if (HTMLFileArray.get(i).isNeedToSave()) {
                return true;
            }
        }
        return false;
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
