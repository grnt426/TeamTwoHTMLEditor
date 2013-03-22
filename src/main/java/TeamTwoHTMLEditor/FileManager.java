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
    private static ArrayList<HTMLFile> HTMLFileArray;   //This is a list of currently open files
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
    public static void createNewFile(String filename) {
        HTMLFile x = new HTMLFile(filename);
        HTMLFileArray.add(x);
        numOpenFiles = HTMLFileArray.size();
        System.out.println("New File was Created with name: " + filename + "\nTotal files: " + Integer.toString(numOpenFiles));
    }

    /**
     *
     */
    public static void openFile(File f) {
        //Right now I've got the correct file (f), I just need to be able to add it's content to the HTML File
        //which isn't done yet, so I'm gonna go ahead and push
        HTMLFile x = new HTMLFile(f.getName());
        HTMLFileArray.add(x);
        numOpenFiles = HTMLFileArray.size();
        System.out.println("File was opened with name: " + f.getName() + "\nTotal files: " + Integer.toString(numOpenFiles));
    }

    /**
     *
     */
    public static void saveFile() {

    }


    /**
     * @return
     */
    public static boolean canQuit() {
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

    public static void shutdown() {
        if (canQuit()) {

        }
    }
}
