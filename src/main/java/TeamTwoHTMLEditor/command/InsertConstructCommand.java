package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;

/**
 *
 */
public class InsertConstructCommand implements Command {

    public enum Construct {
        HEADER, BOLD, ITALICS, LIST
    }

    private Construct construct;


    public InsertConstructCommand(Construct cn) {
        construct = cn;

    }

    @Override
    public void execute(CommandDistributor c) {
        switch (construct) {
            case HEADER:
                //TODO fill out header
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

        }

    }
}
