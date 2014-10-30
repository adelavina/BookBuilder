package com.globant.kata14.helpers;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.globant.kata14.BookBuilder.MainBookBuilder;

public class FileWriterHelper {
	/*
	 * Simple method that writes to a file using commons-io
	 */
	public static Boolean writeToFile(String text, String path) {
		File file = new File(path);
		try {
			FileUtils.writeStringToFile(file, text);
		} catch (IOException e) {
			MainBookBuilder.logger.fatal("Can't write file.");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/*
	 * Simple method that reads from a file using commons-io
	 */
	public static String readFromFile(String file) {
		try {
			return FileUtils.readFileToString(new File(file));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
