package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.FileManager;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 3/22/13
 * Time: 2:16 PM
 */
public class ShutDownCommand implements Command {


    private ShutDownCommand() {

    }

    @Override
    public void execute(CommandDistributor c) {
        c.getFileManager().shutdown();
    }
}
