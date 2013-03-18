package TeamTwoHTMLEditor;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ){
        System.out.println( "Starting System" );




        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame j = new EditorFrame();
                j.setVisible(true);
            }
        });
    }
}
