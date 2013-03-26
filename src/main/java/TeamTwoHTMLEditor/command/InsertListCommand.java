package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;

/**
 * For inserting List elements.
 */
public class InsertListCommand implements Command {

	private final JTextArea activePane;

	public enum ListType {NUMBERED, BULLETED, DICTIONARY}

	int size;
    ListType type;


    public InsertListCommand(ListType l, int size, JTextArea activePane) {
        this.size = size;
        type = l;
		this.activePane = activePane;
	}

    @Override
    public void execute(CommandDistributor c) {
		StringBuilder listElement = new StringBuilder("");
		int tabCount = EditorFrame.getTabCount(EditorFrame.getCurrentLine(activePane));
		String tabs = EditorFrame.indentTabs(tabCount);
		System.out.println(tabCount);

        switch (type) {
            case NUMBERED:
            /*
            Example:
            <ol>
                <li>  text here </li>
                <li>   more text here </li>
            </ol>
             */
			listElement.append("<ol>\n");
			while(size-- > 0) listElement.append(tabs).append("\t<li></li>\n");
			listElement.append(tabs).append("</ol>");
			break;

            case BULLETED:
            /* Example: (tabs or no tabs after <ul>?)
            <ul>
                <li>text</li>
                <li>text</li>
            </ul>
             */


            case DICTIONARY:
            /*
            <dl>
            <dt>Coffee</dt>
            <dd>black hot drink</dd>
            <dt>Milk</dt>
            <dd>white cold drink</dd>
            </dl>
             */

        }
		System.out.println(listElement.toString());
		activePane.insert(listElement.toString(), activePane.getCaretPosition());
    }

}
