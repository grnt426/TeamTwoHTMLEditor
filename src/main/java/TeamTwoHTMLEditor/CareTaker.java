package TeamTwoHTMLEditor;

import java.util.Stack;

/**
 * Maintains a stack of previous states. The maximum size the CareTaker will
 * allow is two states. As more states are added to the CareTaker, previous
 * states are removed to maintain a stack size no greater than two.
 */
public class CareTaker{

	private final Stack<Memento> undoStack;

	public CareTaker(){
		undoStack = new Stack<Memento>();
	}

	public void storePrevious(Memento previous){

		// We only need to store two previous states, so remove the bottom of
		// the Stack (element 0) to maintain a max size of two.
		if(undoStack.size() == 2)
			undoStack.remove(0);
		undoStack.push(previous);
	}

	public Memento retrievePrevious(){
		// We don't want an exception thrown, so instead we will return null
		if(undoStack.size() == 0)
			return null;
		return undoStack.pop();
	}
}
