package TeamTwoHTMLEditor;

import TeamTwoHTMLEditor.Links.Links;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.io.*;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA. User: Kocsen Date: 3/21/13 Time: 2:31 PM To
 * change this template use File | Settings | File Templates.
 */
public class HTMLFile implements DocumentListener {
    private boolean needToSave, needSaveAs;
    private String filename;
    private BufferedWriter writer;
    private Scanner reader;
    private StringBuilder fileContents;
    private Links linksList;
    private CareTaker history;

    /**
     * Overloaded constructor
     *
     * @param path - the pathname of the document
     */
    public HTMLFile(String path) {
        this(path, true);
    }

    /**
     * Constructor for HTMLFile
     *
     * @param path     - The name of the file/the path
     * @param loadFile - True if it needs to be loaded
     */
    public HTMLFile(String path, boolean loadFile) {
        filename = path;
        needToSave = false;
        needSaveAs = true;
        if (loadFile) {
            loadFile();
        }
        linksList = new Links(this);
        history = new CareTaker();
    }

    /**
     * @return the name of the file which is the pathname
     */
    public String getName() {
        return filename;
    }

    void loadFile() {
        // We want to read in the whole file, so we need to recreate the Scanner
        // instance each time to reset the read-head to the start of the file.
        try {
            reader = new Scanner(new File(filename));

            // We also don't care about the previous file buffer contents, so
            // create a new object to clear out everything.
            fileContents = new StringBuilder();
            while (reader.hasNextLine()) {
                fileContents.append(reader.nextLine());
                if (reader.hasNext()) {
                    fileContents.append("\n");
                }
            }
        } catch (FileNotFoundException e) {
            // TODO: Report error to user?
            //e.printStackTrace();
            //System.out.print("YOLO: NEW FILE");
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * Main method to call for saving a file
     *
     * @param newContent - The string contents of the new file to save.
     */
    public void saveFile(String newContent) {
        setFileContents(newContent);
        linksList.refresh();
        saveFile();
    }

    /**
     * Actual save file method that uses buffers to do so
     */
    void saveFile() {
        this.needToSave = false;
        System.out.println("SAVED FILE: " + filename);
        try {
            // BufferedWriter will automatically clear the contents of the
            // file for us.
            writer = new BufferedWriter(new FileWriter(filename));

            // Just write everything
            writer.write(fileContents.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                // TODO: if we get here, something really annoying happened.
                e.printStackTrace();
            }
        }
    }

    /**
     * @return - String format of the file of the contents
     */
    public String getFileContents() {
        if (fileContents != null) {
            return fileContents.toString();
        } else {
            return "";
        }
    }


    public Links getLinksList() {
        return linksList;
    }

    /**
     * Write to the file given the fileContents (used for savintg
     *
     * @param fileContents - the contents to save
     */
    void setFileContents(String fileContents) {
        needToSave = true;
        this.fileContents = new StringBuilder(fileContents);
    }


    /**
     * Setter for need To save
     *
     * @param b - the new set of whether the documetn isntance needs to save or
     *          not
     */
    public void setNeedToSave(boolean b) {
        this.needToSave = b;
    }

    /**
     * Checks if the given HTMLFile needs a save
     *
     * @return - True if the document needs to save, false otherwise.
     */
    public boolean isNeedToSave() {
        return needToSave;
    }

    /**
     * Setter for needSaveAs
     *
     * @param b - true if the document has not ever been saved. false
     *          otherwise.
     */
    public void setNeedSaveAs(boolean b) {
        this.needSaveAs = b;
    }

    /**
     * getter for the needSaveAS
     *
     * @return - true if the document has not ever been saved. false otherwise.
     */
    public boolean getNeedSaveAs() {
        return needSaveAs;
    }

    /**
     * @return the String of the path of the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Stores the current state of this file so that an Undo operation can
     * be done later.
     */
    public void createMemento() {
        history.storePrevious(new Memento(fileContents.toString()));
    }

    /**
     * Restores the previous state of this file.
     */
    public void restoreState() {
        Memento previous = history.retrievePrevious();
        if (previous == null)
            return;
        setFileContents(previous.getPreviousState());
    }

    /**
     * All the overriding methods below are methods that are implemented by the
     * DocumentListener. They all trigger when the document has been altered in
     * any of the following ways. Insertion Deletion Or change (copy/paste) This
     * ensures that whenever the document has been changed, the state of the
     * document changes to being unsaved
     *
     * @param e - The event that triggers the methods . Is unused
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        // Type of e.getDocument() is Javax.swing.text.PlainDocument
        Document doc = e.getDocument();
        try {
            System.out.println(doc.getText(0, doc.getLength()));
        } catch (BadLocationException e1) {
        }
        System.out.println("yolo1");
        this.needToSave = true;
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        System.out.println("yolo2");
        this.needToSave = true;
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        System.out.println("yolo3");
        this.needToSave = true;
    }
}
