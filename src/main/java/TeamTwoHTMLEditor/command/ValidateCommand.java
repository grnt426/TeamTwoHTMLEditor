package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;
import TeamTwoHTMLEditor.GUI.EditorFrame;
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
public class ValidateCommand implements Command{
	private JTextArea pane;
	private String filename;
	private EditorFrame parent;
	private static final Random gen = new Random();
	private boolean manuallyClicked;


	/**
	 * The validate command runs a Java XML parser. The parser starts to parse
	 * the file and if a special exception is shown, it means that the tags are
	 * not complete or that there is a premature end of file etc.
	 * <p/>
	 * This allows for greater flexibility on the tags that are included within
	 * the html.
	 *
	 * @param pane
	 * @param filename
	 * @param parent
	 * @param manuallyClicked - If the validation was done when saving/opening
	 *                        or manually
	 */
	public ValidateCommand(JTextArea pane, String filename, EditorFrame parent,
						   boolean manuallyClicked){
		this.pane = pane;
		this.filename = filename;
		this.parent = parent;
		this.manuallyClicked = manuallyClicked;
	}

	/**
	 * Special case if the file is new or has been changed after save, the
	 * editor makes a new temporary file to be parsed by the parser so that the
	 * user can validate whenever.
	 *
	 * @param c - command distributor instance to get a hold of the FileManager
	 *          reference
	 */
	private void checkFile(CommandDistributor c){
		File f = new File(filename);
		if(!f.exists()
		   || !c.getFileManager().canQuitAt(parent.getActivePaneIndex())){
			int i = 8;
			String newFilename = "";
			while(i-- > 0){
				newFilename += gen.nextInt(9);
			}
			try{
				f = File.createTempFile(newFilename, ".tmp");
				filename = f.getAbsolutePath();
				BufferedWriter bw = new BufferedWriter(new FileWriter(f));
				bw.write(pane.getText());
				bw.flush();
				bw.close();
			}
			catch(IOException e){
				// TODO can't validate, I guess don't bother?
				JOptionPane x = new JOptionPane();
				x.setName("Error while parsing the HTML");
				JOptionPane.showMessageDialog(parent,
											  "Unable to create a temporary " +
											  "file to validate HTML. No " +
											  "validation performed");
				e.printStackTrace();
				return;
			}
		}
	}

	@Override
	public void execute(CommandDistributor c, CommandMediator cmd){
		checkFile(c);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		DocumentBuilder db = null;
		try{
			db = dbf.newDocumentBuilder();
			Document dom = db.parse(filename);
			// If the validate option was accessed from the menu, give feedback when successful.
			if(manuallyClicked){
				JOptionPane.showMessageDialog(parent, "This HTML file is valid.", "Success", JOptionPane.PLAIN_MESSAGE);
			}
		}
		catch(ParserConfigurationException ignored){
		}
		catch(SAXException e){
			dbf.setIgnoringComments(true);
			JOptionPane x = new JOptionPane();
			x.setName("Warning while parsing the HTML");
			JOptionPane.showMessageDialog(parent, e.getMessage(), "Syntax Error with HTML", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(IOException ignored){
		}
	}
}
