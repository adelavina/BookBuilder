package com.globant.kata14.BookBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.log4j.Logger;

import com.globant.kata14.models.CompositeTrigram;

public class BookBuilder {
	private final String WHITESPACE = " ";
	private HashMap<String, CompositeTrigram> ctList;
	private ArrayList<String> book = new ArrayList<String>();
	private boolean canKeepBuilding;
	private static Logger logger = MainBookBuilder.logger;
	private static Properties properties = MainBookBuilder.properties;

	/*
	 * Class constructor, parses a set of files with the base map in text format
	 */
	public BookBuilder(ArrayList<String> dataSources) throws IOException {
		logger.info("HashMap building.");
		ctList = new HashMap<String, CompositeTrigram>();
		Iterator<String> ite = dataSources.iterator();
		String dataSource;

		while (ite.hasNext()) {
			dataSource = ite.next();
			LineIterator it = FileUtils.lineIterator(new File(dataSource),
					"UTF-8");
			CompositeTrigram ct;
			try {
				while (it.hasNext()) {
					ct = new CompositeTrigram(it.nextLine());
					ctList.put(ct.toString(), ct);
				}
			} finally {
				LineIterator.closeQuietly(it);
			}
		}
		logger.info("HashMap built completely.");
		this.canKeepBuilding = true;
	}

	/*
	 * Takes from the map a random seed to start a new sentence.
	 */
	
	private String getStartingSeed() {
		Random rnd = new Random();
		int startint = rnd.nextInt(this.ctList.size());
		String start = (String) this.ctList.keySet().toArray()[startint];
		logger.info("Starting seed: " + this.ctList.get(start).toString());
		return this.ctList.get(start).toString() + WHITESPACE;

	}
	
	/*
	 * Public endpoint to generate a new book.
	 */
	
	public String buildBook() {
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder();
		int cantParagraph = rnd.nextInt(properties.getParagraphMaxCant()
				- properties.getParagraphMinCant())
				+ properties.getParagraphMinCant();

		while (book.size() < cantParagraph) {
			buildParagraph();
			logger.info("Can Keep Building after this paragraph? "
					+ this.canKeepBuilding);
		}

		Iterator<String> ite;
		ite = this.book.iterator();

		while (ite.hasNext()) {
			sb.append(ite.next());
		}
		
		return sb.toString();
	}
	
	/*
	 * Builds a whole new paragraph.
	 */
	
	private void buildParagraph() {

		String paragraph;
		StringBuilder sb = new StringBuilder();

		Random rnd = new Random();
		int size = 0;
		int maxSizeParagraph = rnd.nextInt(properties.getParagraphMaxLength()
				- properties.getParagraphMinLength())
				+ properties.getParagraphMinLength();

		while (size < maxSizeParagraph) {
			if (size == 0)
				sb.append(buildSentence());
			else
				sb.append(WHITESPACE+buildSentence());
			
			logger.info("Can keep building after this line? "
					+ this.canKeepBuilding);
			size++;
		}
		paragraph = sb.toString();
		this.book.add(paragraph.trim() + "\n");
		 
	}
	
	/*
	 * Reads the map to build new stream of words from the TriGram map.
	 */
	
	public String buildSentence() {
		int size = 2;
		String str = getStartingSeed();
		String nextWord;
		String word1 = str.split(WHITESPACE)[0];
		String word2 = str.split(WHITESPACE)[1];
		Random rnd = new Random();

		int maxSizeSentece = rnd.nextInt(properties.getSentenceMaxLength()
				- properties.getSentenceMinLength())
				+ properties.getSentenceMinLength();

		StringBuilder sb = new StringBuilder(str);

		while (canKeepBuilding && size < maxSizeSentece) {
			nextWord = this.getRandomNextWord(word1, word2);
			logger.info("Next word will be: " + nextWord);
			if (nextWord != null) {
				sb.append(nextWord + WHITESPACE);
				logger.info("Last str legated: " + word1 + WHITESPACE + word2);
				logger.info("Last word legated: " + word2);
				word1 = word2;
				word2 = nextWord;
				size++;
			} else
				canKeepBuilding = false; // End due to data stream end
		}
		canKeepBuilding = true;
		return sb.toString().trim().substring(0, 1).toUpperCase()
				+ sb.toString().trim().substring(1)+".";
	}

	/*
	 * From a given base biGram queries the map for a random word from the associated list.
	 */
	
	private String getRandomNextWord(String word1, String word2) {
		logger.info("Querying map for biGram: " + word1.trim() + WHITESPACE
				+ word2.trim());
		CompositeTrigram ct = this.ctList
				.get(word1.trim() + WHITESPACE + word2.trim());
		if (ct != null) {
			logger.info("Options are: ");
			Iterator<String> ite = ct.getList().iterator();
			while (ite.hasNext())
				logger.info("Option: " + ite.next());
		} else
			logger.info("No entry in map for this biGram");

		String nextGram;
		if (ct == null)
			return null;
		else {
			nextGram = ct.getRandomGram();
			return nextGram;
		}

	}

}
