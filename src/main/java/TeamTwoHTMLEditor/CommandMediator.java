package TeamTwoHTMLEditor;

import TeamTwoHTMLEditor.GUI.EditorFrame;
import TeamTwoHTMLEditor.GUI.TabFrame;
import TeamTwoHTMLEditor.command.ActiveContext;
import TeamTwoHTMLEditor.command.RefreshLinksCommand;
import TeamTwoHTMLEditor.command.ValidateCommand;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Keegan
 * Date: 4/11/13
 * Time: 3:25 PM
 * The main purpose of this class is to decouple the commands.  Many of the
 * commands call one another inside their execution.  Yet, the CommandMediator
 * will be able to call these methods as needed whenever a command is executed
 * so that individual commands don't need to know about one another.
 */
public class CommandMediator {

    public CommandMediator() {

    }

    /**
     * Called whenever an open Command is successfully called.
     *
     * @param filePath        - The path of the file.
     * @param manuallyClicked - Whether or not the button "Validate" was clicked.
     * @param c               - The CommandDistributor
     */
    public void openCommandExecuted(String filePath, boolean manuallyClicked,
                                    CommandDistributor c, ActiveContext context) {
        new ValidateCommand(filePath, manuallyClicked, context).execute(c, this);
        new RefreshLinksCommand(context).execute(c, this);
    }

    /**
     * Called whenever a save as Command is successfully called.
     *
     * @param filePath        - The path of the file.
     * @param manuallyClicked - Whether or not the button "Validate" was clicked.
     * @param c               - The CommandDistributor
     */
    public void saveAsCommandExecuted(String filePath, boolean manuallyClicked,
                                      CommandDistributor c, ActiveContext context) {
        new ValidateCommand(filePath, manuallyClicked, context).execute(c, this);
        new RefreshLinksCommand(context).execute(c, this);
    }

    /**
     * Called whenever a save Command is successfully called.
     *
     * @param filePath        - The path of the file.
     * @param manuallyClicked - Whether or not the button "Validate" was clicked.
     * @param c               - The CommandDistributor
     */
    public void saveCommandExecuted(String filePath, boolean manuallyClicked,
                                    CommandDistributor c, ActiveContext context) {
        new ValidateCommand(filePath, manuallyClicked, context).execute(c, this);
        new RefreshLinksCommand(context).execute(c, this);
    }

    public void insertCommandExecuted(ActiveContext context) {
        new RefreshLinksCommand(context);
    }

}
