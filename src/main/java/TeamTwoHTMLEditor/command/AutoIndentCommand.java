package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;
import javax.swing.text.BadLocationException;

/**
 * Author:      Grant Kurtz
 */
public class AutoIndentCommand implements Command{

	private JTextArea activePane;

	public AutoIndentCommand(JTextArea activePane){
		this.activePane = activePane;
	}

	@Override
	public void execute(CommandDistributor c){
		String prevLine = EditorFrame.getCurrentLine(activePane);
		int numTabs = EditorFrame.getTabCount(prevLine);
		activePane.insert(EditorFrame.indentTabs(numTabs), activePane.getCaretPosition());
	}
}
