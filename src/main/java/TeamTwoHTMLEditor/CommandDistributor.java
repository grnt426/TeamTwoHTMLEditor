package TeamTwoHTMLEditor;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 3/22/13
 * Time: 2:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommandDistributor {
    private FileManager fileManager;


    public CommandDistributor(FileManager fManager) {
        fileManager = fManager;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

}
