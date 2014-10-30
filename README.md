BookBuilder
===========

This application aims to solve the problem described here:
http://codekata.com/kata/kata14-tom-swift-under-the-milkwood/

The problem is initially splitted into two and thus solved as two diferent solutions tied together sequentially.

The first problem: BookReading
==============================
The first problem forces the application to read through a large amount of text data in order to build a map as shown by the description of the main problem.
I aim to obtain a list of trigrams used by the books sampled and merge them into a consistent data structure. 
Provided that this structure is sorted somehow, the solution will be deterministic, in this case, this means that for any given set of books the solution may run N times and the resulting map will be equivalent every time.

The first problem is solved with a MapReduce implementation. The Mapping class will read each word on every book and emit a <K>,<V> line where each <K> will be a BiGram and each <V1> will be the third word that completes the Trigram. The Reducer instances will, for each <K>, emit a a <K> <V2> row, where the <V2> will be the concatenation of every <V1> provided by the Map implementation. 


The second problem: BookWriting
===============================
The second problem consists on writing text structures (Sentences/Paragraphs) from a generated random BiGram seed and for each iteration pick a word that could follow that BiGram seed. That means that every time a BiGram is generated the TriGram is pseudo-randomly completed with one of the possible following words. This problem is NOT deterministic, for any given TriGram map, the solution will be one of N, randomly selected.

The second problem is solved by an in-memory implementation of a HashMap<Bigram,List<Word>> that will list for each combination of two words a list of possible words that could follow. A random map initial point is taken and then each iteration on the BuildSentence method will pick a one of the available List of words on the HashMap. Sentences and parapraphs are ended when there are no further possibilities on the HashMap to continue the Trigram builds, or when the amount of words per sentence or sentences per paragraphs, or paragraphs per book is reached.

The implementation
==================

The application allows the user to scan the books and generate the in-file map only once and then call the solution to the second problem N times to generate possibly N different books. Saving Map-Reduce time when possible.
