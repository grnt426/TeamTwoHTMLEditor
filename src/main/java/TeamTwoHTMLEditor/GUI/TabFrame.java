package TeamTwoHTMLEditor.GUI;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA. User: Kocsen Date: 4/6/13 Time: 3:49 PM To change
 * this template use File | Settings | File Templates.
 */
public class TabFrame extends JPanel{

	private boolean wordWrap;
	private int tabSize;
	private JScrollPane linkListScrollPane, editorScrollPane;
	private JTextArea editorTextArea;
	private JList linkList;


	public TabFrame(EditorFrame editorFrame){

		this.wordWrap = editorFrame.getWordWrapBoolean();
		tabSize = editorFrame.getGlobalTabSize();
		initComponents();
		//By Default, the links view will be set to false
		linkListScrollPane.setVisible(true);

	}

	private void initComponents(){
		editorTextArea = setupTextArea();
		editorScrollPane = new JScrollPane(editorTextArea);

		// SAMPLE LISt
		ArrayList<String> ary = new ArrayList<String>();
		ary.add("https://super.duper.yolo.com/teamTwoHTMLEditor/Rocks.html");
		//
		linkList = setupLinkList(ary);
		linkListScrollPane = new JScrollPane(linkList);

		GroupLayout layout = new GroupLayout(this);

		//Setting layout
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
														.addContainerGap()
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(editorScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
																		.addComponent(linkListScrollPane))
														.addContainerGap()));

		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					  .addGroup(layout.createSequentialGroup()
									  .addContainerGap()
									  .addComponent(editorScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
									  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
									  .addComponent(linkListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
									  .addContainerGap()));


		setLayout(layout);


	}


	/**
	 * Helper function to set up a pane to be inserted by tab pane MUST always
	 * be called when making a new representation of a file
	 *
	 * @return the JTextArea to be shown by the component of the tab pane
	 */
	private JTextArea setupTextArea(){
		JTextArea newEditorPane = new JTextArea();
		newEditorPane.setLineWrap(wordWrap);
		newEditorPane.setTabSize(tabSize);
		//TODO REMINDER TO add new tabFrame to the tabFrames array in EditorFrame

		return newEditorPane;
	}

	private JList setupLinkList(ArrayList array){
		JList list = new JList(new LinksListModel(array));
		return list;
	}

	public void setLinkViewVisible(boolean b){
		this.linkListScrollPane.setVisible(b);
	}

	public JTextArea getTextPane(){
		return this.editorTextArea;
	}

}
