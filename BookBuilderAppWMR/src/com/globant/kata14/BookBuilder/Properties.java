package com.globant.kata14.BookBuilder;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;


public class Properties {
	private int sentenceMinLength;
	private int sentenceMaxLength;
	private int paragraphMinLength;
	private int paragraphMaxLength;
	private int paragraphMaxCant;
	private int paragraphMinCant;
	private String reduceOutput;

	public int getSentenceMinLength() {
		return sentenceMinLength;
	}

	public int getSentenceMaxLength() {
		return sentenceMaxLength;
	}

	public int getParagraphMinLength() {
		return paragraphMinLength;
	}

	public int getParagraphMaxLength() {
		return paragraphMaxLength;
	}

	public Properties()
	{
		PropertiesConfiguration appConf;
		try {
			appConf = new PropertiesConfiguration("conf.properties");
			this.sentenceMinLength = appConf.getInt("sentence.minlength");
			this.sentenceMaxLength = appConf.getInt("sentence.maxlength");
			
			this.paragraphMinLength = appConf.getInt("paragraph.minlength");
			this.paragraphMaxLength = appConf.getInt("paragraph.maxlength");
			
			this.paragraphMaxCant = appConf.getInt("paragraph.maxcant");
			this.paragraphMinCant = appConf.getInt("paragraph.mincant");
			
			this.reduceOutput = appConf.getString("reduce.output");
			
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	
	}

	public int getParagraphMaxCant() {
		return paragraphMaxCant;
	}

	public int getParagraphMinCant() {
		return paragraphMinCant;
	}
	
	public String getReduceOutput() {
		return this.reduceOutput;
	}
}
