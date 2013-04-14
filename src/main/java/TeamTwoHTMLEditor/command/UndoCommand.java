package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;

/**
 *
 */
public class UndoCommand implements Command{

	private JTextArea pane;
	private EditorFrame parent;
	private int index;

	public UndoCommand(JTextArea pane, EditorFrame parent, int index){
		this.pane = pane;
		this.parent = parent;
		this.index = index;
	}

	@Override
	public void execute(CommandDistributor c, CommandMediator cmd){
		c.getFileManager().undoChange(index);
	}
}
