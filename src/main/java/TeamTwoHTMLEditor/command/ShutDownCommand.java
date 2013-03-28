package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 3/22/13
 * Time: 2:16 PM
 */
public class ShutDownCommand implements Command {
    private EditorFrame eFrame;

    public ShutDownCommand(EditorFrame frame) {
        eFrame = frame;

    }

    /**
     * Checks if the editor can quit based on the save state of the files.
     * If all files are saved, the editor quit, else an interruption option pane is shown to prevent the shutdown operation.
     *
     * @param c - Command distributor who has a reference access to the FileManager
     */
    @Override
    public void execute(CommandDistributor c) {
        if (c.getFileManager().canQuit()) {
            eFrame.dispose();
            System.out.println("Shutting Down System");
        } else {
            int n = JOptionPane.showConfirmDialog(
                    eFrame,
                    "There are some unsaved files, would you like to quit anyway?",
                    "Unsaved Files",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                eFrame.dispose();
                System.out.println("Shutting Down System");
            } else {

            }

        }
        c.getFileManager().printStatus();
    }
}
