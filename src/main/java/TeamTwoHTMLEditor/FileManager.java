package TeamTwoHTMLEditor;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 3/21/13
 * Time: 10:00 AM
 * This is the model for the editor
 */
public class FileManager {
    private int numOpenFiles, numUnsavedFiles;
    private boolean savePending;

    private static FileManager instance;

    static {
        instance = new FileManager();
    }

    public static FileManager getInstance() {
        return instance;
    }

    /**
     * Constructor for the FileManager Class.
     * Should be a singleton
     */
    public FileManager() {

    }


}
