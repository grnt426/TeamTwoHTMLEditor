package TeamTwoHTMLEditor;

/**
 * Author:      Grant Kurtz
 */
public class Memento{

	private final String previousState;

	public Memento(String previousState){
		this.previousState = previousState;
	}

	public String getPreviousState(){
		return previousState;
	}
}
