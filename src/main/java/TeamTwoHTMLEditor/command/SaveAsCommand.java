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
 * To change this template use File | Settings | File Templates.
 */
public class SaveAsCommand implements Command {
    private String filename;
    private File f;
    private JTextArea pane;
    private EditorFrame parent;
    private int index;

    public SaveAsCommand(EditorFrame editorFrame, File saveFile, JTextArea pane, int i) {
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
