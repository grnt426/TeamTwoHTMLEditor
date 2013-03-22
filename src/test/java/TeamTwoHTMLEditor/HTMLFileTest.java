package TeamTwoHTMLEditor;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.io.File;
import java.util.Scanner;

/**
 * Author:      Grant Kurtz
 */
public class HTMLFileTest extends TestCase{

	private final String readFileTest = "src/test/resources/readFileTest.txt";
	private final String writeFileTest = "src/test/resources/writeFileTest.txt";

	private final String readTestResults = "I am a file\n" +
										   "I have been read in correctly if both lines appear.";

	public void testLoadFile() throws Exception{

		HTMLFile htmlFile = new HTMLFile(readFileTest);
		System.out.println(htmlFile.getFileContents());
		Assert.assertTrue(htmlFile.getFileContents().equals(readTestResults));
	}

	public void testSaveFile() throws Exception{

	}
}
