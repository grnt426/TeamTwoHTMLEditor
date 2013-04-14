package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;
import TeamTwoHTMLEditor.GUI.EditorFrame;
import TeamTwoHTMLEditor.GUI.TabFrame;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA. User: Shannon Date: 4/6/13 Time: 11:28 PM To
 * change this template use File | Settings | File Templates.
 */
public class InsertATagCommand implements Command {
	private final ActiveContext context;
	private int index;
    private String href;
    private String name;

    public InsertATagCommand(String href, String name, ActiveContext context) {
        this.href = href;
        this.name = name;
        this.context = context;
    }

    public void execute(CommandDistributor c, CommandMediator cmd) {
        System.out.println("HELLO");
        String input;

        // If there is no active editor window, then do nothing
        if (context.getActiveTextArea() == null) {
            return;
        }

        input = "<a href=\"" + href + "\">" + name + "</a>";
        StringBuilder insertStr = new StringBuilder("");
        insertStr.append(input);

        context.getActiveTextArea().insert(insertStr.toString(),
										   context.getActiveTextArea().getCaretPosition());

        // ADD cmd
    }
}

