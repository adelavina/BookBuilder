package com.globant.kata14.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.globant.kata14.helpers.FileWriterHelper;

public class FileWriterTest {

	@Test
	public void test() {
		String pattern = "this is a test";
		FileWriterHelper.writeToFile(pattern, "/tmp/test.txt");
		String read = FileWriterHelper.readFromFile("/tmp/test.txt");
		assertEquals(read,pattern);
		
	}

}
