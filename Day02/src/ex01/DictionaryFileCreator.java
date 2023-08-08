package ex01;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DictionaryFileCreator {
    static public void createDictionaryFile(File fileA, File fileB, String dictionaryName) throws IOException {
        Set<String> wordsFromFileA = readWordsFromFile(fileA);
        Set<String> wordsFromFileB = readWordsFromFile(fileB);
        Set<String> dictionary = new TreeSet<>(wordsFromFileA);
        dictionary.addAll(wordsFromFileB);
        writeDictionaryIntoFile(dictionary, dictionaryName);
    }

    static private Set<String> readWordsFromFile(File file) throws IOException {
        Set<String> wordsFromFile = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file.getPath()))) {
            Pattern pattern = Pattern.compile("\\b[a-zA-Z]+\\b");
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    wordsFromFile.add(matcher.group());
                }
            }
        }
        return wordsFromFile;
    }

    static private void writeDictionaryIntoFile(Set<String> dictionary, String dictionaryName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dictionaryName))) {
            StringBuilder sb = new StringBuilder();
            int counterWordsInLine = 0;
            for (String word : dictionary) {
                sb.append(word).append(", ");
                ++counterWordsInLine;
                if (counterWordsInLine == 10) {
                    sb.append("\n");
                    counterWordsInLine = 0;
                }
            }
            writer.write(sb.substring(0, sb.length() - 2));
        }
    }
}
