package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 3/25/13
 * Time: 11:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class InsertTableCommand implements Command {

    int row, col;

    public InsertTableCommand(int r, int c) {
        row = r;
        col = c;
    }

    @Override
    public void execute(CommandDistributor c) {
        /**
         * Example for table in html (2rows 2cols)
         <table >
         <tr>
         <td>row 1, cell 1</td>
         <td>row 1, cell 2</td>
         </tr>
         <tr>
         <td>row 2, cell 1</td>
         <td>row 2, cell 2</td>
         </tr>
         </table>
         */
        //TODO insert behavoiur
    }
}
