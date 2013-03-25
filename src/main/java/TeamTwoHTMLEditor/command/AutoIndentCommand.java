package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDispatcher;

import javax.swing.*;
import javax.swing.text.BadLocationException;

/**
 * Author:      Grant Kurtz
 */
public class AutoIndentCommand implements Command{

	private JTextArea activePane;

	public AutoIndentCommand(JTextArea activePane){
		this.activePane = activePane;
	}

	@Override
	public void execute(CommandDispatcher c){
		String prevLine = getCurrentLine(activePane);
		int numTabs = getTabCount(prevLine);
		activePane.insert(indentTabs(numTabs), activePane.getCaretPosition());
	}

	public static int getTabCount(String str){
		int tabCount = 0;
		for(char c : str.toCharArray()){
			if(c == '\t')
				tabCount++;
			else
				break;
		}

		return tabCount;
	}

	public static String getCurrentLine(JTextArea pane){
		int index = 0;
		try{
			index = pane.getLineOfOffset(pane.getCaretPosition());
		}
		catch(BadLocationException e){
			// Not sure if we can do much.  Assume that there is no cursor
			// and that we don't need to auto-indent.
			return null;
		}

		String[] content = pane.getText().split("\n");
		return content[index-1];
	}

	public static String indentTabs(int tabCount){
		String tabs = "";
		for(; tabCount > 0; tabCount--){
			 tabs += "\t";
		}
		return tabs;
	}
}
