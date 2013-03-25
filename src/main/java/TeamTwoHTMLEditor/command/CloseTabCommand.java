package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Keegan
 * Date: 3/22/13
 * Time: 5:30 PM
 * Class that is instantiated when wanting to close a file/tab
 */
public class CloseTabCommand implements Command {

    private final int index;
    private final EditorFrame parent;

    public CloseTabCommand(int index, EditorFrame p) {
        this.index = index;
        parent = p;
    }

    @Override
    public void execute(CommandDistributor c) {
        if (c.getFileManager().closeFile(index)) {
            System.out.println("Tab # " + (index + 1) + " may close");
            parent.closeTab();
        } else {
            JOptionPane.showMessageDialog(parent, "Please save the file before exiting");
        }
    }

}
