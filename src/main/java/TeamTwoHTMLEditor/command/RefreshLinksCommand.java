package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.GUI.TabFrame;

import java.util.ArrayList;

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
    public void execute(CommandDistributor c) {
        ArrayList<String> links = c.getFileManager().getLinksAt(index);
        System.out.println("LINKS: " + links.toString());
        f.setList(links);
    }
}
