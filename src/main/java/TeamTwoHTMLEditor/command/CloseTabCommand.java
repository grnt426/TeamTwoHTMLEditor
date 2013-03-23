package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;

/**
 * Created with IntelliJ IDEA.
 * User: Keegan
 * Date: 3/22/13
 * Time: 5:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class CloseTabCommand implements Command {

    private int index;

    public CloseTabCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(CommandDistributor c) {
        c.getFileManager().closeFile(index);
    }

}
