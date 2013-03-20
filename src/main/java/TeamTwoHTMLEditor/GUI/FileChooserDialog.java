package TeamTwoHTMLEditor.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 3/19/13
 * Time: 12:15 PM
 * This class is the dialog frame that pops up when the user must chose a file for openning or saving.
 */
public class FileChooserDialog extends JDialog {

    public enum Type {
        OPEN, SAVE
    }

    public FileChooserDialog(Frame parent, boolean modal, Type type) {
        super(parent, modal);
        initComponents(type);

    }

    private void initComponents(Type type) {
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setModal(true);
        setBounds(new Rectangle(0, 0, 300, 300));
        setResizable(false);
        setLocationRelativeTo(null);



        if (type == Type.OPEN) {
            initComponentsOpen();
        } else {
            initComponentsClose();
        }





    }

    private void initComponentsOpen() {
        setTitle("Open File - IntelliHTML");
        setName("Open File");

    }

    private void initComponentsClose() {
        setTitle("Save File - IntelliHTML");
        setName("Save File");

    }


}
