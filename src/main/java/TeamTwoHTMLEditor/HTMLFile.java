package TeamTwoHTMLEditor;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 3/21/13
 * Time: 2:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class HTMLFile {
    boolean needToSave;
    String filename;

    public HTMLFile(String name) {
        filename = name;
        needToSave = false;
    }

    public String getName() {
        return filename;
    }


}
