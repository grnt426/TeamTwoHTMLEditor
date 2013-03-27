package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 3/25/13
 * Time: 3:38 PM
 * Command for Saving As.
 * the execute method simply runs a save file given a path from the file chooser
 */
public class SaveAsCommand implements Command {
	private File f;
    private JTextArea pane;
    private EditorFrame parent;
    private int index;

    public SaveAsCommand(EditorFrame editorFrame, File saveFile, JTextArea pane, int i) {
        f = saveFile;
		this.pane = pane;
        parent = editorFrame;
        index = i;
    }

    @Override
    public void execute(CommandDistributor c) {
        String contents = pane.getText();
        c.getFileManager().saveFile(f, contents, index);
        c.getFileManager().printStatus();
        new ValidateCommand(pane, f.getPath(), parent).execute(c);
        c.getFileManager().printStatus();
    }
}
