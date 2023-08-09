package ex01;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DictionaryFileCreator {

    private static Set<String> dictionary;
    private static List<String> listWordsFileA;
    private static List<String> listWordsFileB;

    static public void createDictionaryFile(File fileA, File fileB, String dictionaryName) throws IOException {
        listWordsFileA = readWordsFromFile(fileA);
        listWordsFileB = readWordsFromFile(fileB);
        System.out.println(listWordsFileA);
        System.out.println(listWordsFileB);
        dictionary = new TreeSet<>(listWordsFileA);
        dictionary.addAll(listWordsFileB);
        writeDictionaryIntoFile(dictionary, dictionaryName);
    }

    static private List<String> readWordsFromFile(File file) throws IOException {
        List<String> wordsFromFile = new LinkedList<>();
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

    static public Set<String> getDictionary() {
        return dictionary;
    }

    static public List<String> getListWordsFileA() {
        return listWordsFileA;
    }

    static public List<String> getListWordsFileB() {
        return listWordsFileB;
    }
}
