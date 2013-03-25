package TeamTwoHTMLEditor.GUI;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.command.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

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
    private int activePane = 0;
    private ArrayList<JTextArea> editorPanes;

    private JMenuBar menuBar;
    private JMenu menuFile, menuEdit, menuInsert, menuOptions, menuAbout;
    private JMenuItem newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem, closeTabMenuItem, validateMenuItem, quitMenuItem, copyMenuItem, pasteMenuItem, aboutUsMenuItem, helpMenuItem;
    private JCheckBoxMenuItem toggleWordWrapMenuItem;


    //headers, font emphasis (bold, italics), list (numbered, bulleted, dictionary), tables.
    private JMenuItem insertHeaderMenuItem, insertTableMenuItem;
    private JMenuItem boldMenuItem, italicsMenuItem;
    private JMenuItem insertNumberList, insertBulletList, insertDictionaryList;
    private JMenu fontEmphasisMenu, insertListMenu;


    public EditorFrame(CommandDistributor cdis) {
        initComponents();
        commandDistributor = cdis;
        fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("HTML Document", "html", "htm"));
        editorPanes = new ArrayList<JTextArea>();

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
        tabPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("State Change");
                changeTabFocus(e);
                if (tabPane.getTabCount() <= 0) {
                    closeTabMenuItem.setEnabled(false);
                    saveMenuItem.setEnabled(false);
                    validateMenuItem.setEnabled(false);
                } else {
                    closeTabMenuItem.setEnabled(true);
                    saveMenuItem.setEnabled(true);
                    validateMenuItem.setEnabled(true);
                }
            }
        });

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Creating all Menu's
        menuFile = new JMenu("File");
        menuEdit = new JMenu("Edit");
        menuAbout = new JMenu("About");
        menuInsert = new JMenu("Insert");
        menuOptions = new JMenu("Options");


        //Build the first menu - File    ************** BEGIN ************************** //
        menuFile.setMnemonic(KeyEvent.VK_F);
        menuFile.getAccessibleContext().setAccessibleDescription("This menu allows you to do all of the file handling.");

        ///Adding File MenuItems
        newMenuItem = new JMenuItem("New", KeyEvent.VK_N);
        openMenuItem = new JMenuItem("Open", KeyEvent.VK_O);
        saveMenuItem = new JMenuItem("Save", KeyEvent.VK_S);
        saveAsMenuItem = new JMenuItem("Save As", KeyEvent.VK_A);
        validateMenuItem = new JMenuItem("Validate", KeyEvent.VK_V);
        closeTabMenuItem = new JMenuItem("Close Tab", KeyEvent.VK_W);
        quitMenuItem = new JMenuItem("Quit", KeyEvent.VK_F4);

        ///Adding Shortcuts to MenuItems
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        validateMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK));
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

        validateMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateActionPerformed(e);
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

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                quitMenuItemActionPerformed(null);
            }
        });

        menuFile.add(newMenuItem);
        menuFile.add(openMenuItem);
        menuFile.add(saveMenuItem);
        menuFile.add(validateMenuItem);
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

        //// Adding action listeners for MenuItems above
        insertHeaderMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertHeaderActionPerformed(e);
            }
        });

        insertTableMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertTableActionPerformed(e);
            }
        });


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

        //// Adding action listeners to menu items above
        boldMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fontEmphasisActionPerformed(e);
            }
        });

        italicsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fontEmphasisActionPerformed(e);
            }
        });

        insertNumberList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertListActionPerformed(e);
            }
        });
        insertDictionaryList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertListActionPerformed(e);
            }
        });

        insertBulletList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertListActionPerformed(e);
            }
        });


        menuInsert.add(insertHeaderMenuItem);
        menuInsert.add(insertTableMenuItem);
        menuInsert.add(fontEmphasisMenu);
        menuInsert.add(insertListMenu);

        /// Handling events


        menuBar.add(menuInsert);
        // ********************************** END ************************************//

        //Build the third menu - Options *****************BEGIN********************//
        menuOptions.setMnemonic(KeyEvent.VK_O);

        ///Adding menuItem
        toggleWordWrapMenuItem = new JCheckBoxMenuItem("Wrap Text", true);
        toggleWordWrapMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleWordWrapActionPerformed(e);
            }
        });

        menuOptions.add(toggleWordWrapMenuItem);
        menuBar.add(menuOptions);
        // ********************************** END ************************************//


        //Build the fourth menu - About ************** BEGIN ************************** //
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
        if (tabPane.getTabCount() <= 0) {
            closeTabMenuItem.setEnabled(false);
            saveMenuItem.setEnabled(false);
            validateMenuItem.setEnabled(false);
        }
    }


    // ********************** Action Performed for File > X *****************************//
    //What to do when they  click New in File Menu
    private void newMenuItemActionPerformed(ActionEvent e) {
        new NewFileCommand("File" + Integer.toString(newFileCount)).execute(commandDistributor);

        JTextArea pane = setupPane();

        JScrollPane editorScrollPane = new JScrollPane(pane);
        tabPane.addTab(
                "File" + Integer.toString(newFileCount), editorScrollPane);
        tabPane.setSelectedIndex(tabPane.getTabCount() - 1);
        newFileCount++;
    }

    //What to do when they click Open in File Menu
    private void openMenuItemActionPerformed(ActionEvent e) {
        System.out.println("Opening Open File Chooser");

        int status = fc.showOpenDialog(this);
        if (status == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();

            JTextArea pane = setupPane();

            JScrollPane editorScrollPane = new JScrollPane(pane);
            tabPane.addTab(f.getName(), editorScrollPane);
            tabPane.setSelectedIndex(tabPane.getTabCount() - 1);
            newFileCount++;

            new OpenCommand(f, pane, this).execute(commandDistributor);

        }


    }

    public void openFileWithoutFileChooser(File f) {
        JTextArea pane = setupPane();

        JScrollPane sp = new JScrollPane(pane);
        tabPane.addTab(f.getName(), sp);
        tabPane.setSelectedIndex(tabPane.getTabCount() - 1);
        newFileCount++;

        new OpenCommand(f, pane, this).execute(commandDistributor);
    }

    //What to do when a tab is selected
    private void changeTabFocus(ChangeEvent e) {
        activePane = tabPane.getSelectedIndex();
    }

    //What to do when they click Save in File Menu
    private void saveMenuItemActionPerformed(ActionEvent e) {
        int i = tabPane.getSelectedIndex();
        if (!commandDistributor.getFileManager().needsSaveAsDialog(i)) {
            new SaveCommand(this, editorPanes.get(i), i);
        } else {
            saveAsMenuItemActionPerformed(null);
        }

    }


    private void saveAsMenuItemActionPerformed(ActionEvent e) {
        System.out.println("Opening Save File Chooser");
        int status = fc.showSaveDialog(this);
        if (status == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();

            JTextArea pane = getActivePane();
            new SaveAsCommand(this, f, pane, tabPane.getSelectedIndex()).execute(commandDistributor);

            tabPane.setTitleAt(tabPane.getSelectedIndex(), fc.getName(f));
        }

    }

    //What to do when clicking Validate in File Menu
    private void validateActionPerformed(ActionEvent e) {
        new ValidateCommand(editorPanes.get(activePane), tabPane.getTitleAt(activePane), this).execute(commandDistributor);
    }


    private void closeTabMenuItemActionPerformed(ActionEvent e) {
        int index = activePane;
        new CloseTabCommand(index, this).execute(commandDistributor);

    }

    public void closeTab() {
        if (newFileCount >= 1) {
            int index = activePane;
            tabPane.remove(index);
            editorPanes.remove(index);
            newFileCount--;
        } else {
            System.err.println("Fatal error closing tab");
        }
    }

    //What to do when they click on Quit in File Menu
    private void quitMenuItemActionPerformed(ActionEvent e) {
        new ShutDownCommand(this).execute(commandDistributor);
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

    //********************** Action Performed for Insert > X *****************************//
    private void insertTableActionPerformed(ActionEvent e) {
        InsertTableDialog x = new InsertTableDialog(this, true);
        x.setLocationRelativeTo(this);
        x.setVisible(true);

        makeTable(x.getRow(), x.getCol());
        //TODO Call command for new table from here
    }

    private static void makeTable(int row, int col) {

    }

    private void insertHeaderActionPerformed(ActionEvent e) {
        //To change body of created methods use File | Settings | File Templates.
    }

    private void insertListActionPerformed(ActionEvent e) {
        if (e.getSource() == insertNumberList) {
            //TODO if numberList
        } else if (e.getSource() == insertBulletList) {
            //TODO if bulletList
        } else if (e.getSource() == insertDictionaryList) {
            //TODO if dictionaryList
        }
    }

    private void fontEmphasisActionPerformed(ActionEvent e) {
        if (e.getSource() == boldMenuItem) {
            //TODO if bold
        } else if (e.getSource() == italicsMenuItem) {
            //TODO if italics
        }
    }


    //******************************** END *******************************************//

    //******************************* Action Perfoemd for Option > X *****************//
    private void toggleWordWrapActionPerformed(ActionEvent e) {
        for (JTextArea textArea : editorPanes) {
            textArea.setLineWrap(toggleWordWrapMenuItem.getState());
        }
    }
    //******************************** END *******************************************//


    private JTextArea setupPane() {
        JTextArea newEditorPane = new JTextArea();
        newEditorPane.setLineWrap(toggleWordWrapMenuItem.getState());
        newEditorPane.setTabSize(4);
        editorPanes.add(newEditorPane);


        // Attach a keylistener to allow for auto-indentation
        newEditorPane.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                //System.out.println(keyCode + " " + KeyEvent.VK_ENTER);
                if (keyCode == KeyEvent.VK_ENTER) {
                    new AutoIndentCommand(getActivePane()).execute(commandDistributor);
                }
            }
        });

        //editorPane.setContentType("text/html");
        //editorPane.setEditorKit(new HTMLEditorKit());
        //editorPane.setEditorKitForContentType("text/html", new HTMLEditorKit());
        return newEditorPane;
    }

    public JTextArea getActivePane() {
        return editorPanes.get(activePane);
    }
}

