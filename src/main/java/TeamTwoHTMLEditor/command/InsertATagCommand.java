package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Shannon
 * Date: 4/6/13
 * Time: 11:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class InsertATagCommand implements Command {
    private final JTextArea activePane;
    private String href;
    private String name;

    public InsertATagCommand(String href, JTextArea activePane) {
        this.href = href;
        this.activePane = activePane;
    }

    public void execute(CommandDistributor c) {
        String input;

        // If there is no active editor window, then do nothing
        if (activePane == null)
            return;

        input = "<a href=\"" + href + "\"> " + "</a>";
        StringBuilder insertStr = new StringBuilder("");
        insertStr.append(input);

        activePane.insert(insertStr.toString(), activePane.getCaretPosition());
    }
}

