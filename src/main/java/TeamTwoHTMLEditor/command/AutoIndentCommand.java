package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;

/**
 * Author:      Grant Kurtz
 */
public class AutoIndentCommand implements Command{


	private JTextArea activePane;

	public AutoIndentCommand(JTextArea activePane){
		this.activePane = activePane;
	}

	@Override
	public void execute(CommandDistributor c){
		int index = 0;
		try{
			index = activePane.getLineOfOffset(activePane.getCaretPosition());
		}
		catch(BadLocationException e){
			// Not sure if we can do much.  Assume that there is no cursor
			// and that we don't need to auto-indent.
		}

		if(index == 0){
			// We don't need to do auto-indentation if we are on the first line
			return;
		}

		String[] content = activePane.getText().split("\n");
		String prevLine = content[index-1];
		int numTabs = getTabCount(prevLine);
		String newLine = "";
		for(; numTabs > 0; numTabs--){
			 newLine += "\t";
		}
		activePane.insert(newLine, activePane.getCaretPosition());
	}

	private int getTabCount(String str){
		int tabCount = 0;
		for(char c : str.toCharArray()){
			if(c == '\t')
				tabCount++;
			else
				break;
		}

		return tabCount;
	}
}
