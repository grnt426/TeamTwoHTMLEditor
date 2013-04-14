package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;
import TeamTwoHTMLEditor.GUI.TabFrame;
import TeamTwoHTMLEditor.Links.Links;

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
        links.refresh();

		context.getTabFrame().setList(links.getLinks());
    }
}
