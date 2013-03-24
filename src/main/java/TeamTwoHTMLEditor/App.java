package TeamTwoHTMLEditor;

import TeamTwoHTMLEditor.GUI.EditorFrame;
import TeamTwoHTMLEditor.command.OpenCommand;

import javax.swing.*;
import java.io.File;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        final CommandDistributor distributor = new CommandDistributor(new FileManager());

        // Sets the default look and feel of the UI given OS
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        if (args.length == 1) { // Open the file given as argument
            String filename = args[0];
            final File f = new File(filename);
            System.out.println("Launching Editor with filename: " + filename);
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    JFrame j = new EditorFrame(distributor);
                    j.setVisible(true);

                }
            });


        } else if (args.length == 0) { // Launch GUI normally with blank screen
            System.out.println("Launching Editor");
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    JFrame j = new EditorFrame(distributor);
                    j.setVisible(true);
                }
            });


        } else { // Display error message
            System.err.println("Invalid Number of Arguments\nUsage: App [file_to_open]");
        }

    }
}
