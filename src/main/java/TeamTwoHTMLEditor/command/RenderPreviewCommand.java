package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;

/**
 * Created with IntelliJ IDEA.
 * User: Shannon
 * Date: 4/4/13
 * Time: 2:43 PM
 */
public class RenderPreviewCommand implements Command {


    private final EditorFrame editorFrame;
    private final JTextArea textArea;

    public RenderPreviewCommand(EditorFrame frame, JTextArea textArea) {
        editorFrame = frame;
        this.textArea = textArea;

    }

    @Override
    public void execute(CommandDistributor c) {
        JDialog previewFrame = new JDialog(editorFrame, true);
        previewFrame.setSize(editorFrame.getSize());
        previewFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        previewFrame.setTitle("Preview of HTML File");

        JEditorPane preview = new JEditorPane();
        preview.setEditorKit(new HTMLEditorKit());
        preview.setText(textArea.getText());
        preview.setContentType("text/html");
        preview.setEditable(false);

        previewFrame.add(new JScrollPane(preview));
        previewFrame.setLocationRelativeTo(editorFrame);

        previewFrame.setVisible(true);

    }
}
