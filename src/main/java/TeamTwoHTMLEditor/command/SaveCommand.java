package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDispatcher;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;

/**
 * Handles delegating the task of saving.
 */
public class SaveCommand implements Command {
    private String filename;

    private JTextArea pane;
    private EditorFrame parent;
    private int index;

    public SaveCommand(EditorFrame editorFrame, JTextArea pane, int i) {
        this.pane = pane;
        parent = editorFrame;
        index = i;
    }

    @Override
    public void execute(CommandDispatcher c) {
        String contents = pane.getText();
        String path = c.getFileManager().getPathAt(index);
        c.getFileManager().quickSaveFile(path, contents, index);
        new ValidateCommand(pane, path, parent).execute(c);
    }
}
