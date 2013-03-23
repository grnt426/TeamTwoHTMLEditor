package TeamTwoHTMLEditor.GUI;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.command.CloseTabCommand;
import TeamTwoHTMLEditor.command.NewFileCommand;
import TeamTwoHTMLEditor.command.OpenCommand;
import TeamTwoHTMLEditor.command.SaveCommand;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen Chung
 * Date: 3/17/13
 * Time: 7:48 PM
 * This is the main frame for the editor.  Will be the class that launches (invokes) only once.
 */

public class EditorFrame extends JFrame {
    private CommandDistributor commandDistributor;
    private QuitDialog quitDialog;
    private JFileChooser fc;
    private int newFileCount = 1;
    private int filesOpen = 0;
    private JTabbedPane tabPane;
    private JEditorPane editorPane;


    public EditorFrame(CommandDistributor cdis) {
        initComponents();
        commandDistributor = cdis;
        fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("HTML Document", "html", "htm"));


    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("IntelliHTML - An HTML FileManager from T2");
        setBounds(new Rectangle(0, 0, 500, 530));
        setName("EditorFrame");
        setResizable(true);
        this.setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        quitDialog = new QuitDialog();
        quitDialog.setVisible(false);

        tabPane = new JTabbedPane();


        JMenuBar menuBar;
        JMenu menuFile, menuEdit, menuInsert, menuAbout;
        JMenuItem newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem, closeTabMenuItem, quitMenuItem, copyMenuItem, pasteMenuItem, aboutUsMenuItem, helpMenuItem;

        //headers, font emphasis (bold, italics), list (numbered, bulleted, dictionary), tables.
        JMenuItem insertHeaderMenuItem, insertTableMenuItem;
        JMenuItem boldMenuItem, italicsMenuItem;
        JMenuItem insertNumberList, insertBulletList, insertDictionaryList;
        JMenu fontEmphasisMenu, insertListMenu;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Creating all Menu's
        menuFile = new JMenu("File");
        menuEdit = new JMenu("Edit");
        menuAbout = new JMenu("About");
        menuInsert = new JMenu("Insert");


        //Build the first menu - File    ************** BEGIN ************************** //
        menuFile.setMnemonic(KeyEvent.VK_F);
        menuFile.getAccessibleContext().setAccessibleDescription("This menu allows you to do all of the file handling.");

        ///Adding File MenuItems
        newMenuItem = new JMenuItem("New", KeyEvent.VK_N);
        openMenuItem = new JMenuItem("Open", KeyEvent.VK_O);
        saveMenuItem = new JMenuItem("Save", KeyEvent.VK_S);
        saveAsMenuItem = new JMenuItem("Save As", KeyEvent.VK_A);
        closeTabMenuItem = new JMenuItem("Close Tab", KeyEvent.VK_W);
        quitMenuItem = new JMenuItem("Quit", KeyEvent.VK_F4);

        ///Adding Shortcuts to MenuItems
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        //saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        closeTabMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));

        ///Adding listeners
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

        closeTabMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeTabMenuItemActionPerformed(e);
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
        menuFile.add(closeTabMenuItem);
        menuFile.add(quitMenuItem);

        menuBar.add(menuFile);

        // BUILD FILE ************************************END****************************//


        //Build the second menu - Edit   ************** BEGIN ************************** //
        menuEdit.setMnemonic(KeyEvent.VK_E);
        menuEdit.getAccessibleContext().setAccessibleDescription("This menu allows you to edit the file content");

        ///Adding Edit MenuItems
        copyMenuItem = new JMenuItem("Copy", KeyEvent.VK_C);
        pasteMenuItem = new JMenuItem("Paste", KeyEvent.VK_V);

        ///Shortcuts
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));

        ///Listeners for Menu Items
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


        //Build the Insert Menu *********************** BEGIN ************************ //

        menuInsert.setMnemonic(KeyEvent.VK_I);
        menuInsert.getAccessibleContext().setAccessibleDescription("Allows you to insert HTML tags");

        /// Adding MenuItems
        insertHeaderMenuItem = new JMenuItem("Header", KeyEvent.VK_H);
        insertTableMenuItem = new JMenuItem("Table", KeyEvent.VK_T);

        /// Adding submenus and menu items
        fontEmphasisMenu = new JMenu("Font Emphasis");
        boldMenuItem = new JMenuItem("Bold", KeyEvent.VK_B);
        italicsMenuItem = new JMenuItem("Italics", KeyEvent.VK_I);
        fontEmphasisMenu.add(boldMenuItem);
        fontEmphasisMenu.add(italicsMenuItem);

        insertListMenu = new JMenu("List");
        insertNumberList = new JMenuItem("Number List");
        insertDictionaryList = new JMenuItem("Dictionary List");
        insertBulletList = new JMenuItem("Bullet List");
        insertListMenu.add(insertNumberList);
        insertListMenu.add(insertDictionaryList);
        insertListMenu.add(insertBulletList);

        menuInsert.add(insertHeaderMenuItem);
        menuInsert.add(insertTableMenuItem);
        menuInsert.add(fontEmphasisMenu);
        menuInsert.add(insertListMenu);

        /// Handling events


        menuBar.add(menuInsert);
        // ********************************** END ************************************//

        //Build the third menu - About ************** BEGIN ************************** //
        menuAbout.setMnemonic(KeyEvent.VK_A);

        // Adding About MenuItems
        aboutUsMenuItem = new JMenuItem("About the Authors");
        helpMenuItem = new JMenuItem("Help");

        menuAbout.add(aboutUsMenuItem);
        menuAbout.add(helpMenuItem);
        aboutUsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("We are the best EVER!");
            }
        });

        menuBar.add(menuAbout);
        // BUILD EDIT **************************************END**************************//

        setJMenuBar(menuBar);
        add(tabPane, BorderLayout.CENTER);
    }


    // ********************** Action Performed for File > X *****************************//
    //What to do when they  click New in File Menu
    private void newMenuItemActionPerformed(ActionEvent e) {
        new NewFileCommand("File" + Integer.toString(newFileCount)).execute(commandDistributor);

        editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setEditorKit(new HTMLEditorKit());
        //editorPane.setEditorKitForContentType("text/html", new HTMLEditorKit());
        editorPane.setContentType("text/html");
        JScrollPane editorScrollPane = new JScrollPane(editorPane);
        tabPane.addTab("File" + Integer.toString(newFileCount), editorScrollPane);
        newFileCount++;
    }

    //What to do when they click Open in File Menu
    private void openMenuItemActionPerformed(ActionEvent e) {
        System.out.println("Opening Open File Chooser");

        fc.showOpenDialog(this);
        File f = fc.getSelectedFile();

        editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setEditorKit(new HTMLEditorKit());
        //editorPane.setEditorKitForContentType("text/html", new HTMLEditorKit());
        editorPane.setContentType("text/html");
        JScrollPane editorScrollPane = new JScrollPane(editorPane);
        tabPane.addTab(f.getName(), editorScrollPane);
        newFileCount++;

        new OpenCommand(f, editorPane).execute(commandDistributor);
    }

    //What to do when they click Save in File Menu
    private void saveMenuItemActionPerformed(ActionEvent e) {
        System.out.println("Opening Save File Chooser");
        fc.showSaveDialog(this);

        File f = fc.getSelectedFile();
        new SaveCommand(f).execute(commandDistributor);
        System.out.println(f.getName());
    }

    //What to do when they click Save As in File Menu
    private void saveAsMenuItemActionPerformed(ActionEvent e) {
        System.out.println("Opening Save File Chooser");
        fc.showSaveDialog(this);
    }

    private void closeTabMenuItemActionPerformed(ActionEvent e) {
        System.out.println("Closing tab and file");
        if (newFileCount > 1) {
            int index = tabPane.getSelectedIndex();
            tabPane.remove(index);
            newFileCount--;

            new CloseTabCommand(index).execute(commandDistributor);
        }
    }

    //What to do when they click on Quit in File Menu
    private void quitMenuItemActionPerformed(ActionEvent e) {
        if (commandDistributor.getFileManager().canQuit()) {

            this.dispose();
            System.out.println("Shutting Down System");
        } else {
            quitDialog.setVisible(true);
        }


        //Check if all files are saved and safe

        //this.dispose();

    }
    // ******************************************** END ********************************//


    //********************** Action Performed for Edit > X *****************************//
    //What to do when copy or paste
    private void copyMenuItemActionPerformed(ActionEvent e) {
        //Grab current file edited
    }

    private void pasteMenuItemActionPerformed(ActionEvent e) {
    }
    //******************************** END *******************************************//


    //********************** Action Performed for About > X *****************************//
    //*********************************************** END 8******************************//


}

