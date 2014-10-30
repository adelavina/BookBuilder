package com.globant.kata14.computation;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;



public class ComputationReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	protected void reduce(Text key, Iterable<Text> values,
			Reducer<Text, Text, Text, Text>.Context cont) throws IOException,
			InterruptedException {
		
		HashSet<String> s = new HashSet<String>();
		String value = "";
		
		/* Removes tokens duplicates */
		for (Text tvalue : values)
			s.add(tvalue.toString());
		
		Iterator<String> ite = s.iterator();
		
		/* Concats new Test */
		while (ite.hasNext())
			value += ite.next()+" ";
		
		cont.write(key,new Text(value.trim()));
		
	}

}
