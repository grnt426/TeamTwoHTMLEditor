package TeamTwoHTMLEditor.GUI;


import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen Chung
 * Date: 3/17/13
 * Time: 7:48 PM
 * This is the main frame for the editor.  Whill be the class that launches (invokes) only once.
 */

public class EditorFrame {
    private static EditorFrame instance;

    static {
        instance = new EditorFrame();
    }

    public EditorFrame(){
        initComponents();
        this.setLocationRelativeTo(null);

    }

    private void initComponents(){
        JMenuBar menuBar;
        JMenu menuFile, menuEdit, menuAbout ;
        JMenuItem newFile, openFile, saveFile, saveAsFile, copyMenuItem, pasteMenuItem;
        JRadioButtonMenuItem rbMenuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Creating all Menu's
        menuFile = new JMenu("File");
        menuEdit = new JMenu("Edit");
        menuAbout = new JMenu("About");

        //Build the first menu - File    ************** BEGIN ************************** //
        menuFile.setMnemonic(KeyEvent.VK_F);
        menuFile.getAccessibleContext().setAccessibleDescription("This menu allows you to do all of the file handling.");
        menuBar.add(menuFile);

        //Adding File MenuItems
        menuItem

        // BUILD FILE ************************************END****************************//




        //Build the second menu - Edit   ************** BEGIN ************************** //
        SpinnerNumberModel.setMnemonic(KeyEvent.VK_E);
        menuEdit.getAccessibleContext().setAccessibleDescription("This menu allows you to edit the file content");
        menuBar.add(menuEdit);
        // BUILD EDIT **************************************END**************************//



        //Build the third menu - About ************** BEGIN ************************** //
        SpinnerNumberModel.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menuAbout);
        // BUILD EDIT **************************************END**************************//




    }





    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //TODO: Frame name.setvisible(true);
            }
        });
    }

}
