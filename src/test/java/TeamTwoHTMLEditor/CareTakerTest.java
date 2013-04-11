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

	public void testRetrievePreviousThree() throws Exception{
		CareTaker careTaker = new CareTaker();
		careTaker.storePrevious(FIRST);
		careTaker.storePrevious(SECOND);
		careTaker.storePrevious(THIRD);
		Assert.assertEquals(THIRD, careTaker.retrievePrevious());
		Assert.assertEquals(SECOND, careTaker.retrievePrevious());
		Assert.assertNull(careTaker.retrievePrevious());
	}
}
