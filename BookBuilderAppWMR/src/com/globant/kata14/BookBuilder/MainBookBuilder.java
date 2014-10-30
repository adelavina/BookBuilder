package com.globant.kata14.BookBuilder;

import java.io.File;

import org.apache.log4j.Logger;

import com.globant.kata14.computation.ComputationController;
import com.globant.kata14.helpers.FileWriterHelper;

/*
 * Main application class, will launch a book parsing computation
 * and pass the output files into the book builder.
 * @author adelavina
 */
public class MainBookBuilder {
	public static Logger logger = Logger.getRootLogger();
	public static Properties properties = new Properties();

	public static void main(String[] args) throws
			InterruptedException {
		if (args[0].equals("full") || args[0].equals("more")) {
			
			BookBuilder bb;
			
			if (args[0].equals("full"))
			{
				try {
					ComputationController.launchComputation(args[1],
							properties.getReduceOutput());
				} catch (Exception e) {
					logger.fatal("Computation Exception");
					e.printStackTrace();
					System.exit(1);
				}
			}
			
			
			try {
				
				bb = new BookBuilder(
						ComputationController.listFilesForFolder(new File(
								properties.getReduceOutput())));
				String para = bb.buildBook();

				if (args[0].equals("full")) {
					FileWriterHelper.writeToFile(para, args[2]);
				} else if (args[0].equals("more")) {
					FileWriterHelper.writeToFile(para, args[1]);
				}
				
			} catch (Exception e) {
				logger.fatal("Processing Exception");
				e.printStackTrace();
				System.exit(1);
			}
	
		} else
			logger.fatal("Bad Parameters.");

		
	}
}
