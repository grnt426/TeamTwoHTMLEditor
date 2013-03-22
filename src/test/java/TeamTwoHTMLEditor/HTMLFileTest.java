package TeamTwoHTMLEditor;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Author:      Grant Kurtz
 */
public class HTMLFileTest extends TestCase{

	private final String readFileTest = "src/test/resources/readFileTest.txt";
	private final String writeFileTest = "src/test/resources/writeFileTest.txt";

	private final String readTestResults = "I am a file\n" +
										   "I have been read in correctly if " +
										   "both lines appear.";
	private final String writeTestContent = "I am a file\n" +
											"I have been written correctly " +
											"if both lines appear.";

	/**
	 * Ensures that HTMLFile can correctly read in all characters of a saved
	 * file.
	 */
	public void testLoadFile() throws Exception{
		HTMLFile htmlFile = new HTMLFile(readFileTest);
		Assert.assertTrue(htmlFile.getFileContents().equals(readTestResults));
	}

	/**
	 * Tests that HTMLFile can correctly write to a file, and then later
	 * re-read the file.
	 */
	public void testSaveFile() throws Exception{

		// Clear the file to make sure we are actually writing to the file and
		// not seeing the previous tests' work.
		BufferedWriter writer = new BufferedWriter(
				new FileWriter(writeFileTest));
		writer.write("");
		writer.flush();

		// Now actually write the new content to the file
		HTMLFile htmlFile = new HTMLFile(writeFileTest);
		htmlFile.saveFile(writeTestContent);
		HTMLFile readResult = new HTMLFile(writeFileTest);
		Assert.assertTrue(readResult.getFileContents().
				equals(writeTestContent));
	}
}
