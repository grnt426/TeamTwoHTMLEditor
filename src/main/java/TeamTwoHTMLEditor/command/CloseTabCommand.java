package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;

/**
 * @author Keegan Parrotte - kmp3325@rit.edu
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

        c.getFileManager().printStatus();
    }

}
