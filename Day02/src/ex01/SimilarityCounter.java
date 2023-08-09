package ex01;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SimilarityCounter {

    static public int countSimilarity(List<String> wordsFromTextA, List<String> wordsFromTextB, Set<String> dictionary) {
        int[] wordFrequencyVectorA = calculateWordFrequencyVector(wordsFromTextA, dictionary);
        int[] wordFrequencyVectorB = calculateWordFrequencyVector(wordsFromTextB, dictionary);
        for(int el : wordFrequencyVectorA) {
            System.out.print(el + " ");
        }
        System.out.println();
        for(int el : wordFrequencyVectorB) {
            System.out.print(el + " ");
        }
        return 0;
    }

    static private int[] calculateWordFrequencyVector(List<String> wordsFromText, Set<String> dictionary) {
        int[] wordFrequencyVector = new int[dictionary.size()];
        int arrIndex = 0;
        for (String word : dictionary) {
            for (String s : wordsFromText) {
                if (word.equals(s)) {
                    wordFrequencyVector[arrIndex] += 1;
                }
            }
            ++arrIndex;
        }
        return wordFrequencyVector;
    }
}
