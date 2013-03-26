package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;

/**
 * For inserting List elements.
 */
public class InsertListCommand implements Command{

	private final JTextArea activePane;

	public enum ListType{NUMBERED, BULLETED, DICTIONARY}

	int size;
	ListType type;


	public InsertListCommand(ListType l, int size, JTextArea activePane){
		this.size = size;
		type = l;
		this.activePane = activePane;
	}

	@Override
	public void execute(CommandDistributor c){
		StringBuilder listElement = new StringBuilder("");
		int tabCount =
				EditorFrame.getTabCount(EditorFrame.getCurrentLine(activePane));
		String tabs = EditorFrame.indentTabs(tabCount);
		System.out.println(tabCount);

		switch(type){
			case NUMBERED:
				listElement.append("<ol>\n");
				while(size-- > 0){
					listElement.append(tabs).append("\t<li></li>\n");
				}
				listElement.append(tabs).append("</ol>");
				break;

			case BULLETED:
				listElement.append("<ul>\n");
				while(size-- > 0){
					listElement.append(tabs).append("\t<li></li>\n");
				}
				listElement.append(tabs).append("</ul>");
				break;

			case DICTIONARY:
				listElement.append("<dl>\n");
				while(size-- > 0){
					listElement.append(tabs).append("\t<dt></dt>\n");
					listElement.append(tabs).append("\t<dd></dd>\n");
				}
				listElement.append(tabs).append("</dl>");
				break;
		}
		System.out.println(listElement.toString());
		activePane.insert(listElement.toString(), activePane.getCaretPosition());
	}

}
