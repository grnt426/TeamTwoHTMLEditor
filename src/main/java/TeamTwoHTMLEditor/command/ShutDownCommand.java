package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDispatcher;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 3/22/13
 * Time: 2:16 PM
 */
public class ShutDownCommand implements Command {
    EditorFrame eFrame;

    public ShutDownCommand(EditorFrame frame) {
        eFrame = frame;

    }

    @Override
    public void execute(CommandDispatcher c) {
        if (c.getFileManager().canQuit()) {
            eFrame.dispose();
            System.out.println("Shutting Down System");
        } else {
            JOptionPane.showMessageDialog(eFrame, "Please Save all your files ");

        }
    }
}
