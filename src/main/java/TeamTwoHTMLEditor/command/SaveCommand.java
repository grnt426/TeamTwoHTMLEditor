package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;

import javax.swing.*;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 3/21/13
 * Time: 9:39 PM
 * To change this template use File | Settings | File Templates.
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
