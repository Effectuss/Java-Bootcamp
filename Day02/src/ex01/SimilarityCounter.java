package ex01;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SimilarityCounter {

    static private final double EPS = 0.000001;

    static public double countSimilarity(List<String> wordsFromTextA, List<String> wordsFromTextB, Set<String> dictionary) {
        int[] wordFrequencyVectorA = calculateWordFrequencyVector(wordsFromTextA, dictionary);
        int[] wordFrequencyVectorB = calculateWordFrequencyVector(wordsFromTextB, dictionary);
        int numerator = countNumerator(wordFrequencyVectorA, wordFrequencyVectorB);
        double denominator = countDenominator(wordFrequencyVectorA, wordFrequencyVectorB);
        if (Math.abs(denominator - 0) < EPS) {
            return 0.0;
        }
        return numerator / denominator;
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

    static private int countNumerator(int[] vectorA, int[] vectorB) {
        int result = 0;
        for (int i = 0; i < vectorA.length; ++i) {
            result += vectorA[i] * vectorB[i];
        }
        return result;
    }

    static private double countDenominator(int[] vectorA, int[] vectorB) {
        return Math.sqrt(squareAndSumVector(vectorA) * squareAndSumVector(vectorB));
    }

    static private int squareAndSumVector(int[] vector) {
        int sum = 0;
        for (int el : vector) {
            sum += el * el;
        }
        return sum;
    }
}
