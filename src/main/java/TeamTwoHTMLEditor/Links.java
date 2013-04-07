package TeamTwoHTMLEditor;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Shannon
 * Date: 4/6/13
 * Time: 10:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Links {

    HTMLFile file;
    ArrayList<String> links;

    public Links(HTMLFile f) {
        file = f;
        links = new ArrayList<String>();
    }

    public void setList() {
        String contents;

        contents = file.getFileContents();

        contents.indexOf("href");
    }
}
