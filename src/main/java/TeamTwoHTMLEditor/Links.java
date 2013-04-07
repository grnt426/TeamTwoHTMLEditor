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

    private HTMLFile file;
    private ArrayList<String> links;

    public Links(HTMLFile f) {
        file = f;
        links = new ArrayList<String>();
        this.setList();
    }

    public void setList() {
        String contents;
        int currIndex = -1;

        contents = file.getFileContents();

        currIndex = contents.indexOf("http");
        while (currIndex < contents.length()) {
            if (currIndex == -1) {
                break;
            }
            links.add(contents.substring(currIndex, contents.indexOf("</a>", currIndex)));
            currIndex = contents.indexOf("href", currIndex + 1);
        }
        System.out.println(links.toString());
    }

}
