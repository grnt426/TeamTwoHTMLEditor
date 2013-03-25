package TeamTwoHTMLEditor;

import java.io.*;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA. User: Kocsen Date: 3/21/13 Time: 2:31 PM To
 * change this template use File | Settings | File Templates.
 */
class HTMLFile {
    private boolean needToSave;
    private String filename;
    private BufferedWriter writer;
    private Scanner reader;
    private StringBuilder fileContents;

    public HTMLFile(String path) {
        this(path, true);
    }

    public HTMLFile(String path, boolean loadFile) {
        filename = path;
        needToSave = false;
        if (loadFile)
            loadFile();
    }

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
                if (reader.hasNext())
                    fileContents.append("\n");
            }
        } catch (FileNotFoundException e) {
            // TODO: Report error to user?
            //e.printStackTrace();
            //System.out.print("YOLO: NEW FILE");
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    public void saveFile(String newContent) {
        setFileContents(newContent);
        saveFile();
    }

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

    public String getFileContents() {
        return fileContents.toString();
    }

    public boolean isNeedToSave() {
        return needToSave;
    }

    void setFileContents(String fileContents) {
        needToSave = true;
        this.fileContents = new StringBuilder(fileContents);
    }

    public void setSave(boolean b) {
        this.needToSave = b;
    }
}
