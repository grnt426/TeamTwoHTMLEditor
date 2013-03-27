package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;

/**
 * Handles delegating the task of saving.
 */
public class SaveCommand implements Command {

	private JTextArea pane;
    private EditorFrame parent;
    private int index;

    public SaveCommand(EditorFrame editorFrame, JTextArea pane, int i) {
        this.pane = pane;
        parent = editorFrame;
        index = i;
    }

    @Override
    public void execute(CommandDistributor c) {
        String contents = pane.getText();
        String path = c.getFileManager().getPathAt(index);
        c.getFileManager().quickSaveFile(path, contents, index);
        c.getFileManager().printStatus();
        new ValidateCommand(pane, path, parent, false).execute(c);
        c.getFileManager().printStatus();
    }
}
