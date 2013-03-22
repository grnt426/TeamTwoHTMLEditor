package TeamTwoHTMLEditor.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Keegan
 * Date: 3/22/13
 * Time: 4:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class CloseTabButton extends JButton implements ActionListener {

	private JTabbedPane pane;

    public CloseTabButton(JTabbedPane pane) {
		this.pane = pane;
        int size = 17;
        setPreferredSize(new Dimension(size, size));
		setContentAreaFilled(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int i = pane.indexOfTabComponent(CloseTabButton.this);
		if (i != -1){
			pane.remove(i);
		}
    }


}
