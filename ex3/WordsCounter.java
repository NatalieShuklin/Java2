import java.io.*;
import java.util.ArrayList;
import java.io.FileWriter;

import java.util.List;
import java.util.Set;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Words counter class
 * counts words occurrences in given txt files
 */
public class WordsCounter {
     ConcurrentHashMap<String, AtomicInteger> wordCounter;

    /**
     * Constructor, initializes the counter map
     */
    public WordsCounter() {
        wordCounter = new ConcurrentHashMap<>();
    }

    /**
     * Opens given files, and performs word counting in parallel with threads per file,
     * saves the counts in concurrent map attribute
     * @param files input files names in strings
     * @throws InterruptedException exception for join function
     */
    public void load(String... files) throws InterruptedException {
        List<Counter> threads = new ArrayList<>();
        for (String file : files) {
            File open = new File(file);
            Counter counter= new Counter(open);
            threads.add(counter);
            counter.start();
        }
        for(String file : files) {
            threads.remove(threads.size()-1).join();
        }
    }

    /**
     * displays in output file the words counts that performed on files
     * @throws IOException for errors with out file
     */
    public void displayStatus() throws IOException {
        File fileOut = new File("3EX-out.txt");
        BufferedWriter out = new BufferedWriter(new FileWriter(fileOut));
        Set<String> words = wordCounter.keySet();
        // sort the concurrent hash map by keys
        ArrayList<String> wordsArr = new ArrayList<>(words);
        Collections.sort(wordsArr);
        int sumOfCounts = 0;

        for (String wordKey: wordsArr) {
            if (wordKey.equals("")) {
                continue;
            }
            out.write(wordKey + "  " + wordCounter.get(wordKey) +"\n");
            sumOfCounts += wordCounter.get(wordKey).intValue();
        }
        out.write("\n" + "** total: " + sumOfCounts);
        out.close();
    }

    /**
     * main method, creates instance of the word counter and starts counting words
     * in parallel from given files. After finished, outputs a file with the updated
     * words counts from these files.
     * @param args non given
     * @throws IOException files errors
     * @throws InterruptedException threads errors
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        WordsCounter wc = new WordsCounter();
        wc.load("File1.txt", "File2.txt", "File3.txt");
        wc.displayStatus();
    }

    /**
     * inner class, a thread for each file processing - reading and counting words in it
     */
    class Counter extends Thread {
        File sourceFile;

        /**
         * constructor
         * @param source given source file to read from
         */
        public Counter(File source) {
            this.sourceFile = source;
        }

        /**
         * the running method of a Thread, perform the file process, count words in the file
         * and update the atomic counter in the hash map
         */
        @Override
        public void run() {
            try {
                FileReader sourceReader = new FileReader(sourceFile);
                BufferedReader buffered = new BufferedReader(sourceReader);
                String readLine;
                String[] splitLine;

                while ((readLine = buffered.readLine()) != null) {
                    splitLine = readLine.trim().split("\\s+");

                    for (String s : splitLine) {
                        wordCounter.putIfAbsent(s, new AtomicInteger(0));
                        wordCounter.get(s).incrementAndGet();
                    }
                }
                buffered.close();
                sourceReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


