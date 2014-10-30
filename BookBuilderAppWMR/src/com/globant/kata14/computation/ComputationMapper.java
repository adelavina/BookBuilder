package com.globant.kata14.computation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.globant.kata14.helpers.StringUtilsHelper;

/*
 * adelavina
 */

public class ComputationMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	protected void map(LongWritable key, Text line,
			Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		
		String s = line.toString();
		StringTokenizer tokenizer = new StringTokenizer(s);
		ArrayList<String> tokens = new ArrayList<String>();
		int i;
		
		while (tokenizer.hasMoreElements())
			tokens.add(tokenizer.nextToken().toLowerCase());
		
		for(i=0; i<tokens.size(); i++)
			if(i>=2)
				context.write(new Text(StringUtilsHelper.clean(tokens.get(i-2).trim())+" "+StringUtilsHelper.clean(tokens.get(i-1)).trim()), new Text(StringUtilsHelper.clean(tokens.get(i))));
	}



}
