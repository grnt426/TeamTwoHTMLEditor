package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;

/**
 * Author:      Grant Kurtz
 * Class that runs the auto indent
 */
public class AutoIndentCommand implements Command{

	private JTextArea activePane;

	public AutoIndentCommand(JTextArea activePane){
		this.activePane = activePane;
	}

	@Override
	public void execute(CommandDistributor c){
		String prevLine = EditorFrame.getPreviousLine(activePane);
		int numTabs = EditorFrame.getTabCount(prevLine);
		activePane.insert(EditorFrame.indentTabs(numTabs), activePane.getCaretPosition());
	}
}
