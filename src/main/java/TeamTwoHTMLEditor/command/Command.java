package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 3/21/13
 * Time: 9:37 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Command {

    void execute(CommandDistributor c);


}
