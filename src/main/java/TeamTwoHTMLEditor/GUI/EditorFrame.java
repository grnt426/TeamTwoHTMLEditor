package TeamTwoHTMLEditor.GUI;


import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    protected JFileChooser fc;

    static {
        instance = new EditorFrame();
    }

    // Getter method for the singleton
    public static EditorFrame getInstance() {
        return instance;
    }


    public EditorFrame() {
        initComponents();
        fc = new JFileChooser();


    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("IntelliHTML - An HTML Editor from T2");
        setBounds(new Rectangle(0, 0, 500, 530));
        setName("EditorFrame");
        setResizable(true);
        this.setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JTabbedPane tabPane = new JTabbedPane();
        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setEditorKit(new HTMLEditorKit());


        JMenuBar menuBar;
        JMenu menuFile, menuEdit, menuAbout;
        JMenuItem newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem, quitMenuItem, copyMenuItem, pasteMenuItem, aboutUsMenuItem, helpMenuItem;

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
        saveAsMenuItem = new JMenuItem("Save As", KeyEvent.VK_A);
        quitMenuItem = new JMenuItem("Quit", KeyEvent.VK_F4);

        //Setting icons
        //newMenuItem.setIcon(new ImageIcon("/icons/New.ico");

        //Adding Shortcuts to MenuItems
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        //saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));

        //Adding listeners
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newMenuItemActionPerformed(e);
            }
        });

        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMenuItemActionPerformed(e);
            }
        });

        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMenuItemActionPerformed(e);
            }
        });

        saveAsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAsMenuItemActionPerformed(e);
            }
        });

        quitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitMenuItemActionPerformed(e);
            }
        });

        menuFile.add(newMenuItem);
        menuFile.add(openMenuItem);
        menuFile.add(saveMenuItem);
        menuFile.add(saveAsMenuItem);
        menuFile.add(quitMenuItem);

        menuBar.add(menuFile);

        // BUILD FILE ************************************END****************************//


        //Build the second menu - Edit   ************** BEGIN ************************** //
        menuEdit.setMnemonic(KeyEvent.VK_E);
        menuEdit.getAccessibleContext().setAccessibleDescription("This menu allows you to edit the file content");

        //Adding Edit MenuItems
        copyMenuItem = new JMenuItem("Copy", KeyEvent.VK_C);
        pasteMenuItem = new JMenuItem("Paste", KeyEvent.VK_V);

        //Shotcuts
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));

        //Listeners for Menu Items
        copyMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copyMenuItemActionPerformed(e);
            }
        });

        pasteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pasteMenuItemActionPerformed(e);
            }
        });

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
        //add(editorPane, BorderLayout.CENTER);
        tabPane.addTab("File1", editorPane);
        add(tabPane, BorderLayout.CENTER);
        //tabPane.insertTab("New" + (tabPane.getTabCount()+1), null, editorPane, "TAB", tabPane.getTabCount()+1 );
    }


    // ********************** Action Performed for File > X *****************************//
    //What to do when they  click New in File Menu
    private void newMenuItemActionPerformed(ActionEvent e) {
        System.out.println("YOLO");
        System.out.println("IMA commit");
    }

    //What to do when they click Open in File Menu
    private void openMenuItemActionPerformed(ActionEvent e) {
        System.out.println("Openning Open File Chooser");
        fc.showOpenDialog(this);
    }

    //What to do when they click Save in File Menu
    private void saveMenuItemActionPerformed(ActionEvent e) {
        System.out.println("Openning Save File Chooser");
        fc.showSaveDialog(this);
    }

    //What to do when they click Save As in File Menu
    private void saveAsMenuItemActionPerformed(ActionEvent e) {
        System.out.println("Openning Save File Chooser");
        fc.showSaveDialog(this);
    }

    //What to do when they click on Quit in File Menu
    private void quitMenuItemActionPerformed(ActionEvent e) {
        this.dispose();
        System.out.println("Shutting Down System");
    }
    // ******************************************** END ********************************//


    //********************** Action Performed for Edit > X *****************************//
    //What to do when copy or paste
    private void copyMenuItemActionPerformed(ActionEvent e) {
    }

    private void pasteMenuItemActionPerformed(ActionEvent e) {
    }
    //******************************** END *******************************************//


    //********************** Action Performed for About > X *****************************//
    //*********************************************** END 8******************************//


}

