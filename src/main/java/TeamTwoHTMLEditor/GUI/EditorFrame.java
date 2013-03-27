package TeamTwoHTMLEditor.GUI;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.command.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
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
    private JFileChooser fc;
    private int newFileCount = 1;
    private int activePaneIndex = 0;
    private int globalTabSize = 4;
    private JTabbedPane tabPane;

    private ArrayList<JTextArea> editorPanes;

    private JMenuBar menuBar;
    private JMenu menuFile, menuEdit, menuInsert, menuOptions, menuAbout;
    private JMenuItem newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem, closeTabMenuItem, validateMenuItem, quitMenuItem, copyMenuItem, pasteMenuItem, tabWidthMenuItem, aboutUsMenuItem, helpMenuItem;
    private JCheckBoxMenuItem toggleWordWrapMenuItem, toggleAutoIndentMenuItem;


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
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("IntelliHTML - An HTML FileManager from T2");
        setBounds(new Rectangle(0, 0, 500, 530));
        setName("EditorFrame");
        setResizable(true);
        this.setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        tabPane = new JTabbedPane();
        tabPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                changeTabFocus(e);
                if (tabPane.getTabCount() <= 0) {
                    setEverythingFileDependantEnabled(false);
                } else {
                    setEverythingFileDependantEnabled(true);
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
        validateMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK));
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
        menuFile.add(saveAsMenuItem);
        menuFile.add(validateMenuItem);
        menuFile.add(closeTabMenuItem);
        menuFile.add(quitMenuItem);
        menuBar.add(menuFile);

        // BUILD FILE ************************************END****************************//


        //Build the second menu - Edit   ************** BEGIN ************************** //
        //menuEdit.setMnemonic(KeyEvent.VK_E);
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

        //menuBar.add(menuEdit);
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
        tabWidthMenuItem = new JMenuItem("Tab Width");
        toggleWordWrapMenuItem = new JCheckBoxMenuItem("Wrap Text", true);
        toggleAutoIndentMenuItem = new JCheckBoxMenuItem("Auto Indent", true);


        tabWidthMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabWidthActionPerformed(e);
            }
        });

        toggleWordWrapMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleWordWrapActionPerformed(e);
            }
        });


        menuOptions.add(tabWidthMenuItem);
        menuOptions.add(toggleWordWrapMenuItem);
        menuOptions.add(toggleAutoIndentMenuItem);
        menuBar.add(menuOptions);
        // ********************************** END ************************************//


        //Build the fourth menu - About ************** BEGIN ************************** //
        menuAbout.setMnemonic(KeyEvent.VK_A);

        // Adding About MenuItems
        aboutUsMenuItem = new JMenuItem("About the Authors");
        helpMenuItem = new JMenuItem("Help");

        menuAbout.add(aboutUsMenuItem);
        menuAbout.add(helpMenuItem);

        helpMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(helpMenuItem, "To start, open a" +
                        " file or create a new file through the File menu.\nTo" +
                        " contact the authors choose the About Authors option " +
                        "in the About menu.\nCopy - Ctrl+C\nPaste - Ctrl+V");
            }
        });

        aboutUsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(aboutUsMenuItem, "We are the great " +
                        "authors of this HTML editor:\nKocsen Chung - kxc4519@r" +
                        "it.edu\nKeegan Parrotte - kmp3325@rit.edu\nGrant Kurts - " +
                        "grk2929@rit.edu\nShannon Trudeau - smt9020@rit.edu\nCalvin " +
                        "DRosario - cbd2562@rit.edu");
                System.out.println("We are the best EVER!");
            }
        });

        menuBar.add(menuAbout);
        // BUILD EDIT **************************************END**************************//

        setJMenuBar(menuBar);
        add(tabPane, BorderLayout.CENTER);
        if (tabPane.getTabCount() <= 0) {
            setEverythingFileDependantEnabled(false);
        }
    }


    private void setEverythingFileDependantEnabled(boolean b) {
        closeTabMenuItem.setEnabled(b);
        saveMenuItem.setEnabled(b);
        saveAsMenuItem.setEnabled(b);
        validateMenuItem.setEnabled(b);
        menuInsert.setEnabled(b);
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
        pane.getDocument().addDocumentListener(commandDistributor.getFileManager().getFileAt(tabPane.getSelectedIndex()));
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

            new OpenCommand(f, pane, this, tabPane.getTabCount() - 1).execute(commandDistributor);


        }


    }

    public void openFileWithoutFileChooser(File f) {
        JTextArea pane = setupPane();

        JScrollPane sp = new JScrollPane(pane);
        tabPane.addTab(f.getName(), sp);
        tabPane.setSelectedIndex(tabPane.getTabCount() - 1);
        newFileCount++;

        new OpenCommand(f, pane, this, tabPane.getTabCount() - 1).execute(commandDistributor);
    }

    //What to do when a tab is selected
    private void changeTabFocus(ChangeEvent e) {
        activePaneIndex = tabPane.getSelectedIndex();
    }

    //What to do when they click Save in File Menu
    private void saveMenuItemActionPerformed(ActionEvent e) {
        int i = tabPane.getSelectedIndex();
        if (!commandDistributor.getFileManager().needsSaveAsDialog(i)) {
            new SaveCommand(this, getActivePane(), i).execute(commandDistributor);
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
        new ValidateCommand(getActivePane(), commandDistributor.getFileManager().getPathAt(activePaneIndex), this).execute(commandDistributor);
    }


    private void closeTabMenuItemActionPerformed(ActionEvent e) {
        int index = activePaneIndex;
        new CloseTabCommand(index, this).execute(commandDistributor);

    }

    /*
    Close
     */
    public void closeTab() {
        if (newFileCount > 1) {
            int index = activePaneIndex;
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
        InsertTableDialog x = new InsertTableDialog(this);
        x.setLocationRelativeTo(this);
        x.setVisible(true);

        if ((x.getRow() != 0) || (x.getCol() != 0)) { //Making sure 'cancel' wasn't clicked
            new InsertTableCommand(x.getRow(), x.getCol(), getActivePane()).execute(commandDistributor);
        }
    }

    private void insertHeaderActionPerformed(ActionEvent e) {
        new InsertConstructCommand(InsertConstructCommand.Construct.HEADER, getActivePane()).execute(commandDistributor);
    }

    private void insertListActionPerformed(ActionEvent e) {
        SizeOfListDialog x = new SizeOfListDialog(this, true);
        x.setLocationRelativeTo(this);
        x.setVisible(true);
        int sizeOfList = x.getSizeOfList();

        if (sizeOfList != 0) { //Making sure 'cancel' wasn't clicked
            if (e.getSource() == insertNumberList) {
                new InsertListCommand(InsertListCommand.ListType.NUMBERED, sizeOfList, getActivePane()).execute(commandDistributor);
            } else if (e.getSource() == insertBulletList) {
                new InsertListCommand(InsertListCommand.ListType.BULLETED, sizeOfList, getActivePane()).execute(commandDistributor);
            } else if (e.getSource() == insertDictionaryList) {
                new InsertListCommand(InsertListCommand.ListType.DICTIONARY, sizeOfList, getActivePane()).execute(commandDistributor);
            }
        }
    }

    private void fontEmphasisActionPerformed(ActionEvent e) {
        if (e.getSource() == boldMenuItem) {
            new InsertConstructCommand(InsertConstructCommand.Construct.BOLD, getActivePane()).execute(commandDistributor);
        } else if (e.getSource() == italicsMenuItem) {
            new InsertConstructCommand(InsertConstructCommand.Construct.ITALICS, getActivePane()).execute(commandDistributor);
        }
    }


    //******************************** END *******************************************//

    //******************************* Action Performed for Option > X *****************//

    /**
     * Action performed when pressing the word wrap menu item
     *
     * @param e - Action event passed in. not used
     */
    private void toggleWordWrapActionPerformed(ActionEvent e) {
        for (JTextArea textArea : editorPanes) {
            textArea.setLineWrap(toggleWordWrapMenuItem.getState());
        }
    }

    /**
     * Action performed method when pressing the tabWidth button.
     * Launches a dialog for the user to set the dat width.
     *
     * @param e - Action Event passed in. not used
     */
    private void tabWidthActionPerformed(ActionEvent e) {
        TabWidthDialog x = new TabWidthDialog(this, true, globalTabSize);
        x.setLocationRelativeTo(this);
        x.setVisible(true);
        int tabSize = x.getTabWidth();
        if (tabSize != 0) {
            globalTabSize = tabSize;
            for (JTextArea aTextArea : editorPanes) {
                aTextArea.setTabSize(tabSize);
            }
        }
    }
    //******************************** END *******************************************//


    /**
     * Helper function to set up a pane to be inserted by tab pane
     * MUST always be called when making a new representation of a file
     *
     * @return the JTextArea to be shown by the component of the tab pane
     */
    private JTextArea setupPane() {
        JTextArea newEditorPane = new JTextArea();
        newEditorPane.setLineWrap(toggleWordWrapMenuItem.getState());
        newEditorPane.setTabSize(globalTabSize);
        editorPanes.add(newEditorPane);


        // Attach a keylistener to allow for auto-indentation
        newEditorPane.addKeyListener(new KeyListener() {
			// Need this variable for tabs, the selected text is only present on
			// keyPressed and turns to null on keyReleased.
			String selected = "";
			@Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
				// Get selected texts for the keyRelease here.
				if (e.getKeyCode() == KeyEvent.VK_TAB){
					selected = getActivePane().getSelectedText();
				}
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                //System.out.println(keyCode + " " + KeyEvent.VK_ENTER);
                if (keyCode == KeyEvent.VK_ENTER) {
                    try {
                        if (toggleAutoIndentMenuItem.getState()) {
                            new AutoIndentCommand(getActivePane()).execute(commandDistributor);
                        }
                    } catch (ArrayIndexOutOfBoundsException x) {

                    }

                } else if (keyCode == KeyEvent.VK_TAB) {
					if (selected != null){
						new TabSelectedCommand(getActivePane(), selected).execute(commandDistributor);
					}
				}
            }
        });

        //editorPane.setContentType("text/html");
        //editorPane.setEditorKit(new HTMLEditorKit());
        //editorPane.setEditorKitForContentType("text/html", new HTMLEditorKit());
        return newEditorPane;
    }

    private JTextArea getActivePane() {
        if (!(editorPanes.size() <= 0)) {
            return editorPanes.get(activePaneIndex);
        }
        return null;

    }

    public static int getTabCount(String str) {
        int tabCount = 0;
        for (char c : str.toCharArray()) {
            if (c == '\t')
                tabCount++;
            else
                break;
        }

        return tabCount;
    }

    public static String getCurrentLine(JTextArea pane) {
        return getLine(pane, 0);
    }

    public static String getPreviousLine(JTextArea pane) {
        return getLine(pane, -1);
    }

    private static String getLine(JTextArea pane, int offset) {
        int index;
        try {
            index = pane.getLineOfOffset(pane.getCaretPosition());
        } catch (BadLocationException e) {
            // Not sure if we can do much.  Assume that there is no cursor
            // and that we don't need to auto-indent.
            return null;
        }

        String[] content = pane.getText().split("\n");
        if (index + offset < 0 || index + offset >= content.length)
            return "";
        return content[index + offset];
    }

    public static String indentTabs(int tabCount) {
        String tabs = "";
        for (; tabCount > 0; tabCount--) {
            tabs += "\t";
        }
        return tabs;
    }
}

