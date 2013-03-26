package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 3/26/13
 * Time: 1:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class InsertListCommand implements Command {

    public enum ListType {NUMBERED, BULLETED, DICTIONARY}

    ;
    int size;
    ListType type;


    public InsertListCommand(ListType l, int size) {
        this.size = size;
        type = l;

    }

    @Override
    public void execute(CommandDistributor c) {
        switch (type) {
            case NUMBERED:
            /*
            Example:
            <ol>
                <li>  text here </li>
                <li>   more text here </li>
            </ol>
             */

            case BULLETED:
            /* Example: (tabs or no tabs after <ul>?)
            <ul>
                <li>text</li>
                <li>text</li>
            </ul>
             */


            case DICTIONARY:
            /*
            <dl>
            <dt>Coffee</dt>
            <dd>black hot drink</dd>
            <dt>Milk</dt>
            <dd>white cold drink</dd>
            </dl>
             */

        }
    }

}
