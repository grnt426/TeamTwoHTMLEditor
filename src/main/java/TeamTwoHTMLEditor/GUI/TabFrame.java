package TeamTwoHTMLEditor.GUI;

import com.sun.xml.internal.bind.v2.TODO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 4/6/13
 * Time: 3:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class TabFrame extends JPanel {

    private boolean wordWrap;
    private int tabSize;
    JScrollPane linkListScrollPane;
    JTextArea editorTextArea;


    public TabFrame(EditorFrame editorFrame) {
        this.wordWrap = editorFrame.getWordWrapBoolean();
        tabSize = editorFrame.getGlobalTabSize();
        initComponents();

    }

    private void initComponents() {
        JPanel displayPanel = new JPanel();

        editorTextArea = setupTextArea();
        JScrollPane editorScrollPane = new JScrollPane(editorTextArea);

        //
        ArrayList<String> ary = new ArrayList<String>();
        ary.add("HTTPS://github.com/grn2524/TeamTwoHTMLEditor");
        //

        linkListScrollPane = new JScrollPane(new JList<String>(new LinksListModel(ary)));

        GroupLayout layout = new GroupLayout(displayPanel);

        //Setting layout
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(editorScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                .addComponent(linkListScrollPane))
                        .addContainerGap()));
        displayPanel.setLayout(new BorderLayout());

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(editorScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(linkListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));


        // END BUILD Link List view;

        displayPanel.setLayout(layout);

    }


    /**
     * Helper function to set up a pane to be inserted by tab pane
     * MUST always be called when making a new representation of a file
     *
     * @return the JTextArea to be shown by the component of the tab pane
     */
    private JTextArea setupTextArea() {
        JTextArea newEditorPane = new JTextArea();
        newEditorPane.setLineWrap(wordWrap);
        newEditorPane.setTabSize(tabSize);
        //TODO REMINDER TO add new tabFrame to the tabFrames array in EditorFrame

        return newEditorPane;
    }


    public void setLinkViewVisible(boolean b) {
        this.linkListScrollPane.setVisible(b);
    }

    public JTextArea getTextPane() {
        return this.editorTextArea;
    }

}
