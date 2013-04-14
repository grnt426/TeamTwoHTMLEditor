package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA. User: Shannon Date: 4/6/13 Time: 11:28 PM To
 * change this template use File | Settings | File Templates.
 */
public class InsertATagCommand implements Command {
    private final JTextArea activePane;
    private final EditorFrame parent;
    private int index;
    private String href;
    private String name;

    public InsertATagCommand(String href, String name, EditorFrame parent) {
        this.href = href;
        this.name = name;
        this.parent = parent;
        this.activePane = parent.getActiveTabFrame().getTextPane();
        index = parent.getActivePaneIndex();
    }

    public void execute(CommandDistributor c, CommandMediator cmd) {
        String input;

        // If there is no active editor window, then do nothing
        if (activePane == null) {
            return;
        }

        input = "<a href=\"" + href + "\">" + name + "</a>";
        StringBuilder insertStr = new StringBuilder("");
        insertStr.append(input);

        activePane.insert(insertStr.toString(), activePane.getCaretPosition());

        cmd.insertCommandExecuted(parent.getActiveTabFrame(), index);
    }
}

