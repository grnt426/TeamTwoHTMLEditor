package TeamTwoHTMLEditor;

import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
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

        if (args.length == 1) { // Oepn the file given as argument
            String filename = args[0];


        } else if (args.length == 0) { // Launch GUI normally with blank screen
            final CommandDistributor distributor = new CommandDistributor(new FileManager());
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    JFrame j = new EditorFrame(distributor);
                    j.setVisible(true);
                }
            });


        } else { // Display error message
            System.err.println("Invalid Number of Arguments");
        }


        System.out.println("Starting System");

    }
}
