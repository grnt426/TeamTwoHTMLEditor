package TeamTwoHTMLEditor;

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
    private int numOpenFiles, numUnsavedFiles;
    private boolean savePending;

    public void FileManager() {
        HTMLFileArray = new ArrayList<>();
        numOpenFiles = HTMLFileArray.size();
        numUnsavedFiles = 0;
        savePending = false;

    }


    public boolean canQuit() {
        for (int i = 0; i < HTMLFileArray.size(); i++) {
            if (HTMLFileArray.get(i).needToSave) {
                return true;
            }
        }
        return false;
    }

    public int getNumOpenFiles() {
        return numOpenFiles;
    }
}
