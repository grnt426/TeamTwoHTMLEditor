package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDispatcher;

/**
 *
 */
public class InsertConstructCommand implements Command {

    public enum Construct {
        HEADER, BOLD, ITALICS, LIST, TABLE
    }

    ;

    Construct construct;
    int row, col;


    public InsertConstructCommand(Construct cn, int r, int c) {
        construct = cn;
        row = r;
        col = c;
    }

    public InsertConstructCommand(Construct cn) {
        this(cn, 0, 0);
    }

    @Override
    public void execute(CommandDispatcher c) {
        switch (construct) {
            case HEADER:
                //TODO Fill out header handling
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
