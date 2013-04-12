package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;

/**
 * @author Keegan Parrotte - kmp3325@rit.edu Class that handles the closing of a
 *         tab and an implicit file
 */
public class CloseTabCommand implements Command{

	private final int index;
	private final EditorFrame parent;

	public CloseTabCommand(int index, EditorFrame p){
		this.index = index;
		parent = p;
	}

	/**
	 * This is the execute command and it checks wheather you can close a file
	 * (which is simply if the file has been saved) And then if you can close
	 * the file then it will close the tab and then call filemanager to close
	 * the file. Otherwise, if the file is not saved, an option pane is shown
	 * from the editor frame, in which the user can chose to close the file or
	 * to interrupt the close to save manually.
	 *
	 * @param c - Command distributor who has a reference access to the
	 *          FileManager
	 */
	@Override
	public void execute(CommandDistributor c, CommandMediator cmd){
		if(c.getFileManager().canQuitAt(index)){
			parent.closeTab();
			c.getFileManager().closeFile(index);
		}
		else{
			int n = JOptionPane.showConfirmDialog(
					parent,
					"This Tab has not been saved, would you like to quit anyway?",
					"Unsaved Tab",
					JOptionPane.YES_NO_OPTION);
			if(n == JOptionPane.YES_OPTION){
				parent.closeTab();
				c.getFileManager().closeFile(index);
			}
			else{

			}
		}

		c.getFileManager().printStatus();
	}

}
