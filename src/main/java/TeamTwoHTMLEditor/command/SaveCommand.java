package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;

import javax.swing.*;
import java.io.File;

/**
 * Handles delegating the task of saving.
 */
public class SaveCommand implements Command {
    private String filename;
    private File f;
	private JTextArea pane;

    public SaveCommand(File saveFile, JTextArea pane) {
        f = saveFile;
        filename = f.getName();
		this.pane = pane;
    }

    @Override
    public void execute(CommandDistributor c) {
		String contents = pane.getText();
        c.getFileManager().saveFile(f, contents);
    }
}
