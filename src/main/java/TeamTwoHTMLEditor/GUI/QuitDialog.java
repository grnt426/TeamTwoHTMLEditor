package TeamTwoHTMLEditor.GUI;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 3/19/13
 * Time: 12:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuitDialog extends JDialog {

    public QuitDialog(){
        int reply = JOptionPane.showConfirmDialog (this,
                "Please Save Your File(s)",
                "Exit Windows", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);
        //Check the User Selection.
        if (reply == JOptionPane.OK_OPTION) {
            this.setVisible(false);	    //Hide the Frame.
            this.dispose();            	//Free the System Resources.
        }
    }
}
