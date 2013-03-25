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
    private JFrame parent;
    private int index;

    public SaveCommand(JFrame editorFrame, File saveFile, JTextArea pane, int i) {
        f = saveFile;
        filename = f.getName();
        this.pane = pane;
        parent = editorFrame;
        index = i;
    }

    @Override
    public void execute(CommandDistributor c) {
        String contents = pane.getText();
        c.getFileManager().saveFile(f, contents, index);
        new ValidateCommand(pane, f.getPath(), parent).execute(c);
    }
}
