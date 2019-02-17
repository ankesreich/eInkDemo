package de.ankesreich.imagegeneration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class MessageDisplayPanelTest {

	@Test
	public void testShortText() throws IOException
	{
		assertEquals("testFileShort.bmp", ImageGenerator.writeImageToFile("testFileShort", "Questions !?").getName());
		assertTrue(new File("testFileShort.bmp").exists());
	}
	@Test
	public void testLongText() throws IOException
	{
		assertEquals("testFileLong.bmp", ImageGenerator.writeImageToFile("testFileLong", 
				"Nicht vergessen e-ink Display in sleep Modus zu setzen..").getName());
		assertTrue(new File("testFileLong.bmp").exists());
	}
	@Test
	public void testMediumText() throws IOException
	{
		assertEquals("testFileMedium.bmp", ImageGenerator.writeImageToFile("testFileMedium", "..ja und dann hab").getName());
		assertTrue(new File("testFileMedium.bmp").exists());
	}

	
}
