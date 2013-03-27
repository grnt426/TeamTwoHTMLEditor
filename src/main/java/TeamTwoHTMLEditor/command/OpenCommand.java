package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 3/21/13
 * Time: 9:39 PM
 * Command Class for opning a class.
 * During the execute, the command adds a document observer
 * to be notified of this documents change
 */
public class OpenCommand implements Command {
    private File f;
    private int index;
    private JTextArea pane;
    private final EditorFrame parent;

    public OpenCommand(File openFile, JTextArea p, EditorFrame parent, int i) {
        f = openFile;
        pane = p;
        this.parent = parent;
        index = i;
    }

    @Override
    public void execute(CommandDistributor c) {
        c.getFileManager().openFile(f, pane);
        pane.getDocument().addDocumentListener(c.getFileManager().getFileAt(index));
        new ValidateCommand(pane, f.getPath(), parent).execute(c);
        c.getFileManager().printStatus();
    }

}
