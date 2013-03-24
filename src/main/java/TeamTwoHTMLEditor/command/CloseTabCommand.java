package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;

/**
 * Created with IntelliJ IDEA.
 * User: Keegan
 * Date: 3/22/13
 * Time: 5:30 PM
 * Class that is instantiated when wanting to close a file/tab
 */
public class CloseTabCommand implements Command {

    private final int index;

    public CloseTabCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(CommandDistributor c) {
        c.getFileManager().closeFile(index);
    }

}
