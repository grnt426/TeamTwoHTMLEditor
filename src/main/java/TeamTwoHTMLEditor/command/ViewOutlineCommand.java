package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;
import TeamTwoHTMLEditor.XMLEditorKit.XMLEditorKit;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Keegan
 * Date: 4/16/13
 * Time: 2:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewOutlineCommand implements Command{

	private final ActiveContext context;

	public ViewOutlineCommand(ActiveContext context){
		this.context = context;

	}

	@Override
	public void execute(CommandDistributor c, CommandMediator cmd){
		JDialog previewFrame = new JDialog(context.getParent(), true);
		previewFrame.setSize(context.getParent().getSize());
		previewFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		previewFrame.setTitle("Outline mode for HTML File");

		JEditorPane preview = new JEditorPane();
		preview.setEditorKit(new XMLEditorKit());
		preview.setText(context.getActiveTextArea().getText());
		preview.setEditable(false);

		previewFrame.add(new JScrollPane(preview));
		previewFrame.setLocationRelativeTo(context.getParent());

		previewFrame.setVisible(true);

	}
}
