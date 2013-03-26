package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA. User: Kocsen Date: 3/21/13 Time: 9:41 PM
 */
public class ValidateCommand implements Command {
    // As an idea, we can make validate use a parser of the file, and it has to expect the correct HTML tags.
    // if the parser finishes then its all ok, but if it doesent find what it wants (fails) then itll throw
    // an exception and then catching it will imply a fail in the parsing of the file.

    private JTextArea pane;
    private String filename;
    private JFrame parent;

    public ValidateCommand(JTextArea pane, String filename, JFrame parent) {
        this.pane = pane;
        this.filename = filename;
        this.parent = parent;
    }


    @Override
    public void execute(CommandDistributor c) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setIgnoringComments(true);
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
            Document dom = db.parse(filename);
        } catch (ParserConfigurationException ignored) {
        } catch (SAXException e) {
            JOptionPane x = new JOptionPane();
            x.setName("Warning while parsing the HTML");
            x.showMessageDialog(parent, e.getMessage());
        } catch (IOException ignored) {
        }
    }
}
