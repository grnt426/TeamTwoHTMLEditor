package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 3/21/13
 * Time: 9:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class OpenCommand implements Command {
    private String filename;
    private File f;

    public OpenCommand(File openFile) {
        f = openFile;
        filename = f.getName();
    }

    @Override
    public void execute(CommandDistributor c) {

        c.getFileManager().openFile(f);
    }

}
