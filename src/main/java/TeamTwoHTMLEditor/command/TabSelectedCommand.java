package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;

import javax.swing.*;

/**
 * @author Keegan Parrotte - kmp3325@rit.edu
 */
public class TabSelectedCommand implements Command{
	private JTextArea activePane;
	private String selectedText;

	public TabSelectedCommand(JTextArea activePane, String selectedText){
		this.activePane = activePane;
		this.selectedText = selectedText;
	}

	/**
	 * This execute method is used for when pressing the tab key when text is
	 * highlighted.
	 *
	 * @param c - Command distributor who has a reference access to the
	 *          FileManager
	 */
	@Override
	public void execute(CommandDistributor c){
		String[] split = selectedText.split("\n");
		String newSelected = "";
		for(String s : split){
			newSelected += ("\t" + s + "\n");
		}
		// Substring gets rid of the first tab that is still
		// registered for the original tab keyPress and
		// the last extra newline
		activePane.replaceSelection(newSelected.substring(1
				, newSelected.length() - 1));
	}


}
