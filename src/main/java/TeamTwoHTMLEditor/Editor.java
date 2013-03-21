package TeamTwoHTMLEditor;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 3/21/13
 * Time: 10:00 AM
 * This is the model for the editor
 */
public class Editor {
    private int numOpenFiles, numUnsavedFiles;
    private boolean savePending;
    private static Editor instance;

    static {
        instance = new Editor();
    }

    public static Editor getInstance() {
        return instance;
    }

    /**
     * Constructor for the Editor Class.
     * Should be a singleton
     */
    public Editor() {

    }


}
