package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;

/**
 *
 */
public class InsertConstructCommand implements Command {

    public enum Construct {
        HEADER, BOLD, ITALICS, LIST, TABLE
    }

    private Construct construct;
    private int row;
    private int col;


    private InsertConstructCommand(Construct cn, int r) {
        construct = cn;
        row = 0;
        col = 0;
    }

    public InsertConstructCommand(Construct cn) {
        this(cn, 0);
    }

    @Override
    public void execute(CommandDistributor c) {
        switch (construct) {
            case HEADER:
                //insert header: <header> \n </header>


            case BOLD:
                //TODO fill out bold heandling
                //insert bold: <b> </b>

            case ITALICS:
                //TODO fill out italics handling
                //insert italics: <i></i>

            case LIST:
                //TODO fill out list handling
                //call make list command?

            case TABLE:
                //TODO fill out taable handling
                //call make table command?

        }

    }
}
