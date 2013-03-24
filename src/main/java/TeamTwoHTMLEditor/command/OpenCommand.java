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
public class OpenCommand implements Command {
    private File f;
    private JTextArea pane;
	private final JFrame parent;

	public OpenCommand(File openFile, JTextArea p, JFrame parent) {
        f = openFile;
        pane = p;
		this.parent = parent;
    }

    @Override
    public void execute(CommandDistributor c) {
        c.getFileManager().openFile(f, pane);
		new ValidateCommand(pane, f.getPath(), parent).execute(c);
    }

}
