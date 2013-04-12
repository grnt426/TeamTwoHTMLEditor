package TeamTwoHTMLEditor;

import TeamTwoHTMLEditor.GUI.EditorFrame;
import TeamTwoHTMLEditor.command.Command;
import TeamTwoHTMLEditor.command.ValidateCommand;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Keegan
 * Date: 4/11/13
 * Time: 3:25 PM
 * The main purpose of this class is to decouple the commands.  Many of the
 * commands call one another inside their execution.  Yet, the CommandMediator
 * will be able to call these methods as needed whenever a command is executed
 * so that individual commands don't need to know about one another.
 */
public class CommandMediator {

	public CommandMediator(){

	}

	/**
	 * Called whenever an open Command is successfully called.
	 * @param pane - The JTextArea
	 * @param filePath - The path of the file.
	 * @param parent - The Editor Frame
	 * @param manuallyClicked - Whether or not the button "Validate" was clicked.
	 * @param c - The CommandDistributor
	 */
	public void openCommandExecuted(JTextArea pane, String filePath,
									EditorFrame parent, boolean manuallyClicked,
									CommandDistributor c){
		new ValidateCommand(pane, filePath, parent, manuallyClicked).execute(c, this);
	}

	/**
	 * Called whenever a save as Command is successfully called.
	 * @param pane - The JTextArea
	 * @param filePath - The path of the file.
	 * @param parent - The Editor Frame
	 * @param manuallyClicked - Whether or not the button "Validate" was clicked.
	 * @param c - The CommandDistributor
	 */
	public void saveAsCommandExecuted(JTextArea pane, String filePath,
									  EditorFrame parent, boolean manuallyClicked,
									  CommandDistributor c){
		new ValidateCommand(pane, filePath, parent, manuallyClicked).execute(c, this);
	}

	/**
	 * Called whenever a save Command is successfully called.
	 * @param pane - The JTextArea
	 * @param filePath - The path of the file.
	 * @param parent - The Editor Frame
	 * @param manuallyClicked - Whether or not the button "Validate" was clicked.
	 * @param c - The CommandDistributor
	 */
	public void saveCommandExecuted(JTextArea pane, String filePath,
									  EditorFrame parent, boolean manuallyClicked,
									  CommandDistributor c){
		new ValidateCommand(pane, filePath, parent, manuallyClicked).execute(c, this);
	}

}
