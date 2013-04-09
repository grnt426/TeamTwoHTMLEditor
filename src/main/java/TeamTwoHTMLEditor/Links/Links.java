package TeamTwoHTMLEditor.Links;

import TeamTwoHTMLEditor.HTMLFile;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA. User: Shannon Date: 4/6/13 Time: 10:35 PM To
 * change this template use File | Settings | File Templates.
 */
public class Links{

	private HTMLFile file;
	private ArrayList<String> links;

	public Links(HTMLFile f){
		file = f;
		links = new ArrayList<String>();
		this.refresh();
	}

	public void refresh(){
		String contents;

		contents = file.getFileContents();
		if(contents != ""){
			parseContents(contents);
		}
	}

	/**
	 * @param contents - the contents of the file that you want to find the
	 *                 links of
	 */
	private void parseContents(String contents){
		int currIndex = -1;
		currIndex = contents.indexOf("href");
		while(currIndex < contents.length()){
			if(currIndex == -1){
				break;
			}
			currIndex = contents.indexOf("\"", currIndex);
			links.add(contents.substring(
					currIndex + 1, contents.indexOf("\"", currIndex + 1)));
			currIndex = contents.indexOf("href", currIndex + 1);
		}
	}

	public ArrayList<String> display(LinkStrategy s){
		return s.display((ArrayList<String>) links.clone());
	}

	public ArrayList<String> getLinks(){
		return links;
	}

}
