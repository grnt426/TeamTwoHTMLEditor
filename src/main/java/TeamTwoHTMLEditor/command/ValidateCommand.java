package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

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
	private static final Random gen = new Random();

    public ValidateCommand(JTextArea pane, String filename, JFrame parent) {
        this.pane = pane;
        this.filename = filename;
        this.parent = parent;
    }

	private void checkFile(){
		File f = new File(filename);
		if(!f.exists()){
			int i = 8;
			String newFilename = "";
			while(i-- > 0) newFilename += gen.nextInt(9);
			try{
				f = File.createTempFile(newFilename, ".tmp");
				filename = f.getAbsolutePath();
				BufferedWriter bw  = new BufferedWriter(new FileWriter(f));
				bw.write(pane.getText());
				bw.flush();
				bw.close();
			}
			catch(IOException e){
				// TODO can't validate, I guess don't bother?
				JOptionPane x = new JOptionPane();
				x.setName("Error while parsing the HTML");
				x.showMessageDialog(parent, "Unable to create a temporary " +
											"file to validate HTML. No " +
											"validation performed");
				e.printStackTrace();
				return;
			}
		}
	}

    @Override
    public void execute(CommandDistributor c) {
		System.out.println("Validating...");
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
		System.out.println("Done!");
    }
}
