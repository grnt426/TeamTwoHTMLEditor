package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;

import javax.swing.*;

/**
 * @author Keegan Parrotte - kmp3325@rit.edu
 */
public class TabSelectedCommand implements Command{
		private JTextArea activePane;
		private String selected;

		public TabSelectedCommand(JTextArea activePane, String selected){
			this.activePane = activePane;
			this.selected = selected;
		}

		@Override
		public void execute(CommandDistributor c){
			String[] split = selected.split("\n");
			String newSelected = "";
			for (String s : split) newSelected += ("\t"+s+"\n");
			// Substring gets rid of the first tab that is still
			// registered for the original tab keyPress and
			// the last extra newline
			activePane.replaceSelection(newSelected.substring(1
					, newSelected.length()-1));
		}


}
