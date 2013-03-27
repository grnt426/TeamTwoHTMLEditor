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
        if (c.getFileManager().canQuitAt(index)) {
            parent.closeTab();
            c.getFileManager().closeFile(index);
        } else {
            int n = JOptionPane.showConfirmDialog(
                    parent,
                    "This Tab has not been saved, would you like to quit anyway?",
                    "Unsaved Tab",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                parent.closeTab();
                c.getFileManager().closeFile(index);
            } else {

            }
        }
    }

}
