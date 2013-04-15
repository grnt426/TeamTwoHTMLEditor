package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;
import TeamTwoHTMLEditor.GUI.EditorFrame;
import TeamTwoHTMLEditor.Links.AlphaStrategy;
import TeamTwoHTMLEditor.Links.ConsecutiveOrderStrategy;
import TeamTwoHTMLEditor.Links.Links;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA. User: Shannon Date: 4/4/13 Time: 2:40 PM
 */
public class RefreshLinksCommand implements Command {
    private final ActiveContext context;

    public RefreshLinksCommand(ActiveContext context) {
        this.context = context;
    }

    @Override
    public void execute(CommandDistributor c, CommandMediator cmd) {
        Links links = c.getFileManager().getLinksAt(context.getIndex());
        links.refresh(context.getActiveTextArea().getText());
        if (context.getListType() == EditorFrame.ListType.ALPHABETICAL) {
            ArrayList displayList = new AlphaStrategy().display(links.getLinks());
            context.getTabFrame().setList(displayList);
        } else {
            ArrayList displayList = new ConsecutiveOrderStrategy().display(links.getLinks());
            context.getTabFrame().setList(displayList);
        }
    }
}
