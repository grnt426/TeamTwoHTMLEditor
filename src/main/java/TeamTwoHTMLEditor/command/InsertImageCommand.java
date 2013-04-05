package TeamTwoHTMLEditor.command;

/**
 * Created with IntelliJ IDEA.
 * User: Shannon
 * Date: 4/5/13
 * Time: 3:31 PM
 * To change this template use File | Settings | File Templates.
 */

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.GUI.EditorFrame;
import javax.swing.*;

public class InsertImageCommand implements Command{

    private final JTextArea activePane;
    private String src;
    private String alt;
    private String width;
    private String height;

    public InsertImageCommand(String src, String alt, String height, String width, JTextArea activePane) {
        this.src = src;
        this.alt = alt;
        this.width = width;
        this.height = height;
        this.activePane = activePane;
    }

    public void execute(CommandDistributor c){
        String input;

        // If there is no active editor window, then do nothing
        if (activePane == null)
            return;
        input = "<img src=\"" + src + "\" alt=\"" + alt + "\" height=\"" + height + "\" width=\"" + width + "\">";
        StringBuilder insertStr = new StringBuilder("");
        insertStr.append(input);

        activePane.insert(insertStr.toString(), activePane.getCaretPosition());
    }
}
