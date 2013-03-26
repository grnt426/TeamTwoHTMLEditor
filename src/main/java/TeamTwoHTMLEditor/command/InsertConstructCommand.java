package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;

import javax.swing.*;

/**
 *
 */
public class InsertConstructCommand implements Command {

    public enum Construct {
        HEADER, BOLD, ITALICS, LIST
    }

    private Construct construct;
    private final JTextArea activePane;


    public InsertConstructCommand(Construct cn, JTextArea activePane) {
        construct = cn;
        this.activePane = activePane;
    }

    @Override
    public void execute(CommandDistributor c) {

        // If there is no active editor window, then do nothing
        if (activePane == null)
            return;

        StringBuilder insertStr = new StringBuilder("");
//		String tabs = "";
//		String curLine = EditorFrame.getCurrentLine(activePane);
//
//		// If the current line isn't a fresh line, then move down a line before
//		// starting the insert of the construct.
//		System.out.println("INSERT HEADER:\n'" + curLine + "'\n" + curLine.matches("\t+(?!.)"));
//		if(curLine.matches("\t*.")){
//			tabs = EditorFrame.indentTabs(EditorFrame.getTabCount(curLine));
//			insertStr.append("\n" + tabs);
//		}
//
//		// Otherwise we can just use the current number of tabs on this line
//		// making the assumption it has already been indented correctly.
//		else{
//			tabs = curLine;
//		}

        String input = null;
        switch (construct) {
            case HEADER:
                input = "<header></header>";
                insertStr.append(input);
                break;

            case BOLD:
                input = "<b></b>";
                insertStr.append(input);
                break;

            case ITALICS:
                input = "<i></i>";
                insertStr.append(input);
                break;

        }

        activePane.insert(insertStr.toString(), activePane.getCaretPosition());
        activePane.setCaretPosition(activePane.getCaretPosition() - (input.length() / 2) - 1);

    }
}
