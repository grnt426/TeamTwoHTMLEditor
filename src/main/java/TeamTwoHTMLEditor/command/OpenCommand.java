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

    public OpenCommand(File openFile, JTextArea p) {
        f = openFile;
        String filename = f.getName();
        pane = p;
    }

    @Override
    public void execute(CommandDistributor c) {

        c.getFileManager().openFile(f, pane);
    }

}
