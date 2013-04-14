package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;
import TeamTwoHTMLEditor.GUI.TabFrame;
import TeamTwoHTMLEditor.Links.Links;

/**
 * Created with IntelliJ IDEA. User: Shannon Date: 4/4/13 Time: 2:40 PM
 */
public class RefreshLinksCommand implements Command {
    TabFrame f;
    int index;

    public RefreshLinksCommand(TabFrame f, int x) {
        this.f = f;
        index = x;
    }

    @Override
    public void execute(CommandDistributor c, CommandMediator cmd) {
        Links links = c.getFileManager().getLinksAt(index);
        links.refresh();
        f.setList(links.getLinks());
    }
}
