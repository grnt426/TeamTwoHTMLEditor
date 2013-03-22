package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Keegan
 * Date: 3/22/13
 * Time: 5:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class CloseTabCommand implements Command{

	private File f;

	public CloseTabCommand(File f){
		this.f = f;
	}

	@Override
	public void execute(CommandDistributor c) {
		//c.getFileManager().
	}

}
