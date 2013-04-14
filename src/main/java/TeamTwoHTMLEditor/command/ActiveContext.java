package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;

/**
 * Author:      Grant Kurtz
 */
public class ActiveContext{
	private final int index;
	private final JTextArea activeTextArea;
	private final EditorFrame parent;

	public ActiveContext(int index, JTextArea activeTextArea, EditorFrame parent){
		this.index = index;
		this.activeTextArea = activeTextArea;
		this.parent = parent;
	}

	public int getIndex(){
		return index;
	}

	public JTextArea getActiveTextArea(){
		return activeTextArea;
	}

	public EditorFrame getParent(){
		return parent;
	}
}
