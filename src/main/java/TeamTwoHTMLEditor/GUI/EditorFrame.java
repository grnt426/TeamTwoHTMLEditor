package TeamTwoHTMLEditor.GUI;


import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen Chung
 * Date: 3/17/13
 * Time: 7:48 PM
 * This is the main frame for the editor.  Whill be the class that launches (invokes) only once.
 */

public class EditorFrame extends JFrame {
    private static EditorFrame instance;

    static {
        instance = new EditorFrame();
    }

    public static EditorFrame getInstance(){
        return instance;
    }


    public EditorFrame(){
        initComponents();
        this.setLocationRelativeTo(null);

    }

    private void initComponents(){
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("IntelliHTML - An HTML Editor from T2");
        setBounds(new Rectangle(0,0,900,930));
        setName("EditorFrame");
        setResizable(true);
        setLayout(new BorderLayout());

        JTabbedPane tabPane = new JTabbedPane();
        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setEditorKit(new HTMLEditorKit());


        JMenuBar menuBar;
        JMenu menuFile, menuEdit, menuAbout ;
        JMenuItem newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem, copyMenuItem, pasteMenuItem, aboutUsMenuItem, helpMenuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Creating all Menu's
        menuFile = new JMenu("File");
        menuEdit = new JMenu("Edit");
        menuAbout = new JMenu("About");

        //Build the first menu - File    ************** BEGIN ************************** //
        menuFile.setMnemonic(KeyEvent.VK_F);
        menuFile.getAccessibleContext().setAccessibleDescription("This menu allows you to do all of the file handling.");


            //Adding File MenuItems
        newMenuItem = new JMenuItem("New", KeyEvent.VK_N);
        openMenuItem = new JMenuItem("Open", KeyEvent.VK_O);
        saveMenuItem = new JMenuItem("Save", KeyEvent.VK_S);
        saveAsMenuItem= new JMenuItem("Save As", KeyEvent.VK_A);

            //Adding Shortcuts to MenuItems
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

        menuFile.add(newMenuItem);
        menuFile.add(openMenuItem);
        menuFile.add(saveMenuItem);
        menuFile.add(saveAsMenuItem);

        menuBar.add(menuFile);

        // BUILD FILE ************************************END****************************//




        //Build the second menu - Edit   ************** BEGIN ************************** //
        menuEdit.setMnemonic(KeyEvent.VK_E);
        menuEdit.getAccessibleContext().setAccessibleDescription("This menu allows you to edit the file content");

            //Addging Edit MenuItems
        copyMenuItem = new JMenuItem("Copy", KeyEvent.VK_C);
        pasteMenuItem = new JMenuItem("Paste", KeyEvent.VK_V);

            //Shotcuts
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));

        menuEdit.add(copyMenuItem);
        menuEdit.add(pasteMenuItem);

        menuBar.add(menuEdit);
        // BUILD EDIT **************************************END**************************//



        //Build the third menu - About ************** BEGIN ************************** //
        menuAbout.setMnemonic(KeyEvent.VK_A);

            // Adding About MenuItems
        aboutUsMenuItem = new JMenuItem("About the Authors");
        helpMenuItem = new JMenuItem("Help");

        menuAbout.add(aboutUsMenuItem);
        menuAbout.add(helpMenuItem);


        menuBar.add(menuAbout);
        // BUILD EDIT **************************************END**************************//

        setJMenuBar(menuBar);
        add( editorPane , BorderLayout.CENTER );
        add( tabPane, BorderLayout.NORTH) ;


    }

}
