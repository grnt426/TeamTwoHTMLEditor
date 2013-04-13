package TeamTwoHTMLEditor.command;

/**
 * Created with IntelliJ IDEA.
 * User: Shannon
 * Date: 4/5/13
 * Time: 3:31 PM
 * Command to insert an image. Gets info from popped up gui and then inserts the string into the active pane.
 */

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;

import javax.swing.*;

public class InsertImageCommand implements Command {

    private final JTextArea activePane;
    private String src;

    public InsertImageCommand(String src, JTextArea activePane) {
        this.src = src;
        this.activePane = activePane;
    }

    public void execute(CommandDistributor c, CommandMediator cmd) {
        String input;

        // If there is no active editor window, then do nothing
        if (activePane == null) {
            return;
        }
        input = "<img src=\"" + src + "\">";
        StringBuilder insertStr = new StringBuilder("");
        insertStr.append(input);

        activePane.insert(insertStr.toString(), activePane.getCaretPosition());
    }
}
