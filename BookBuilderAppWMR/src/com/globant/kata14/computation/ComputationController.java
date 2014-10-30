package com.globant.kata14.computation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import com.globant.kata14.BookBuilder.MainBookBuilder;

public class ComputationController {
	public static boolean launchComputation(String input, String output) throws IOException, InterruptedException
	{
		Configuration conf = new Configuration();
		Job job = new Job(conf, "kata14Computation");
		job.setJarByClass(MainBookBuilder.class);
		job.setMapperClass(ComputationMapper.class);
		job.setReducerClass(ComputationReducer.class);
		job.setOutputKeyClass(Text.class);

		job.setOutputValueClass(Text.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		ArrayList<String> inputFiles = listFilesForFolder(new File(input));
		
		for(String inputFile : inputFiles)
			FileInputFormat.addInputPath(job, new Path(inputFile));
		 

		deleteTemporals(new File(output));
		
		FileOutputFormat.setOutputPath(job, new Path(output));
		
		try {
			return job.waitForCompletion(true);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static ArrayList<String> listFilesForFolder(File folder) {
		ArrayList<String> files = new ArrayList<String>();
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            files.add(fileEntry.getAbsolutePath());
	        }
	    }
	    return files;
	}
	
	public static boolean deleteTemporals(File f) {
		if (!f.exists())
			return false;
		if (f.isDirectory())
		{
			for (final File fileEntry : f.listFiles())
				deleteTemporals(fileEntry);
			f.delete();
		}
		else
			f.delete();
		return true;
	}
}