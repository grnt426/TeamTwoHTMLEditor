package TeamTwoHTMLEditor;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 3/22/13
 * Time: 2:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommandDispatcher {
    private FileManager fileManager;


    public CommandDispatcher(FileManager fManager) {
        fileManager = fManager;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

}
