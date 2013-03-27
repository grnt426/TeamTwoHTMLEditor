package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 3/21/13
 * Time: 10:41 PM
 */
public class NewFileCommand implements Command {

    private String filename;

    public NewFileCommand(String s) {
        filename = s;
    }

    @Override
    public void execute(CommandDistributor c) {
        c.getFileManager().createNewFile(filename);
        c.getFileManager().printStatus();
    }
}
