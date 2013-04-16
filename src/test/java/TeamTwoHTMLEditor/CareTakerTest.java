package TeamTwoHTMLEditor;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Author:      Grant Kurtz
 */
public class CareTakerTest extends TestCase{

	private final Memento FIRST = new Memento("FIRST");
	private final Memento SECOND = new Memento("SECOND");
	private final Memento THIRD = new Memento("THIRD");
	private final Memento FOURTH = new Memento("FOURTH");

	public void testRetrievePrevious() throws Exception{
		CareTaker careTaker = new CareTaker();
		careTaker.storePrevious(FIRST);
		Assert.assertEquals(FIRST, careTaker.retrievePrevious());
		Assert.assertNull(careTaker.retrievePrevious());
	}

	public void testRetrievePreviousTwo() throws Exception{
		CareTaker careTaker = new CareTaker();
		careTaker.storePrevious(FIRST);
		careTaker.storePrevious(SECOND);
		Assert.assertEquals(SECOND, careTaker.retrievePrevious());
		Assert.assertEquals(FIRST, careTaker.retrievePrevious());
		Assert.assertNull(careTaker.retrievePrevious());
	}

	public void testRetrievePreviousAndRedo() throws Exception{
		CareTaker careTaker = new CareTaker();
		careTaker.storePrevious(FIRST);
		careTaker.storePrevious(SECOND);
		careTaker.retrievePrevious();
		Assert.assertEquals(SECOND, careTaker.retrieveNext());
		Assert.assertEquals(null, careTaker.retrieveNext());
		Assert.assertEquals(SECOND, careTaker.retrievePrevious());
		Assert.assertEquals(FIRST, careTaker.retrievePrevious());
		Assert.assertEquals(null, careTaker.retrievePrevious());
		Assert.assertEquals(FIRST, careTaker.retrieveNext());
		Assert.assertEquals(SECOND, careTaker.retrieveNext());
		Assert.assertEquals(null, careTaker.retrieveNext());
	}

	public void testInsertNewUndoStateInMiddleOfStack() throws Exception{
		CareTaker careTaker = new CareTaker();
		careTaker.storePrevious(FIRST);
		careTaker.storePrevious(SECOND);
		careTaker.storePrevious(THIRD);
		careTaker.retrievePrevious();
		careTaker.storePrevious(FOURTH);
		Assert.assertEquals(FOURTH, careTaker.retrievePrevious());
		Assert.assertEquals(SECOND, careTaker.retrievePrevious());
		Assert.assertEquals(FIRST, careTaker.retrievePrevious());
		Assert.assertEquals(FIRST, careTaker.retrieveNext());
		Assert.assertEquals(SECOND, careTaker.retrieveNext());
		Assert.assertEquals(FOURTH, careTaker.retrieveNext());
		Assert.assertEquals(null, careTaker.retrieveNext());
	}

	public void testRetrieveRedo() throws Exception{
		CareTaker careTaker = new CareTaker();
		careTaker.storePrevious(FIRST);
		careTaker.storePrevious(SECOND);
		Assert.assertEquals(null, careTaker.retrieveNext());
	}
}
