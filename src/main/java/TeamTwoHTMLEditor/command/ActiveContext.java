package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.GUI.EditorFrame;
import TeamTwoHTMLEditor.GUI.TabFrame;

import javax.swing.*;

/**
 * Author:      Grant Kurtz
 */
public class ActiveContext{
	private final int index;
	private final JTextArea activeTextArea;
	private final EditorFrame parent;
    private final TabFrame tabFrame;

	public ActiveContext(int index, JTextArea activeTextArea, EditorFrame parent, TabFrame tabFrame){
		this.index = index;
		this.activeTextArea = activeTextArea;
		this.parent = parent;
        this.tabFrame = tabFrame;
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

    public TabFrame getTabFrame() {
        return tabFrame;
    }
}
