package com.globant.kata14.junit;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.globant.kata14.BookBuilder.BookBuilder;
import com.globant.kata14.helpers.FileWriterHelper;

public class BookBuildingTest {
	public static String path = "book.txt";
	public static String pattern = "The blue house";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		FileWriterHelper.writeToFile(pattern, path);

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		new File(path).delete();
	}

	@Test
	public void test() {
		BookBuilder bb = null;
		try {
			ArrayList<String> files = new ArrayList<String>();
			files.add("book.txt");
			bb = new BookBuilder(files);
		} catch (IOException e) {
			e.printStackTrace();
			fail("IOException");
		}
		String sentence = bb.buildSentence();
		assertEquals(sentence, pattern+".");

		
	}

}
