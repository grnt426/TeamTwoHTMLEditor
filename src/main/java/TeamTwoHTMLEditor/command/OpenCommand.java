package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;
import TeamTwoHTMLEditor.GUI.EditorFrame;
import TeamTwoHTMLEditor.GUI.TabFrame;

import javax.swing.*;
import java.io.File;

/**
 * Created with IntelliJ IDEA. User: Kocsen Date: 3/21/13 Time: 9:39 PM Command
 * Class for opning a class. During the execute, the command adds a document
 * observer to be notified of this documents change
 */
public class OpenCommand implements Command{
	private File f;
	private int index;
	private JTextArea pane;
	private final EditorFrame parent;

	public OpenCommand(File openFile, JTextArea pane, EditorFrame parent, int i){
		f = openFile;
		this.pane = pane;
		this.parent = parent;
		index = i;
	}

	@Override
	public void execute(CommandDistributor c, CommandMediator cmd){
		c.getFileManager().openFile(f, pane);
		pane.getDocument().addDocumentListener(c.getFileManager().getFileAt(index));
		cmd.openCommandExecuted(pane, f.getPath(), parent, false, c, index);
		c.getFileManager().printStatus();
	}

}
