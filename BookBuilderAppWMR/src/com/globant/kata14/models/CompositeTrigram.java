package com.globant.kata14.models;

import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class CompositeTrigram {
	private String word1;
	private String word2;
	private ArrayList<String> values;
	
	public CompositeTrigram(String line)
	{
		values = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(line);
		if (st.countTokens() > 2)
		{
			this.word1 = st.nextToken();
			this.word2 = st.nextToken();
			while(st.hasMoreTokens())
				values.add(st.nextToken());
	
		}
	}

	public String getRandomGram()
	{
		if (values.size() == 0)
			return null;
		Random r = new Random();
		int i = r.nextInt(values.size());
		String s = values.get(i);
		values.remove(i);
		return s;
	}
	
	@Override
	public String toString()
	{
		return this.word1+" "+this.word2;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof CompositeTrigram))
			return false;
		CompositeTrigram ct = (CompositeTrigram)obj;
		return (this.word1.equals(ct.word1) && this.word2.equals(ct.word2));
	}

	@Override
	public int hashCode() {
		return (word1+" "+word2).hashCode();
	}

	public ArrayList<String> getList() {
		return this.values;
	}
	
	
}
