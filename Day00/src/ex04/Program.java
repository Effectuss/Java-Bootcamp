package ex04;

import java.rmi.MarshalException;
import java.util.Scanner;

import static java.util.Collections.swap;

public class Program {
    private static final int MAX_CHARACTER = 65535;
    private static final int MAX_INPUT_LENGTH = 999;
    private static final int[] arrUnicode = new int[MAX_CHARACTER];
    private static final int[] arrCountCharacter = new int[MAX_CHARACTER];
    private static final int[] arrUniqueUnicode = new int[MAX_INPUT_LENGTH];
    private static final int[] arrCountUniqueChar = new int[MAX_INPUT_LENGTH];
    private static int amountUniqueCharacter = 0;

    public static void main(String[] args) {
        runFrequencyAnalysis(readInputData());
    }

    private static char[] readInputData() {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        char[] arrInputData = inputString.toCharArray();
        if (arrInputData.length > MAX_INPUT_LENGTH) {
            exitFromProgram("IllegalArgument");
        } else if (arrInputData.length == 0) {
            exitFromProgram("Empty input");
        }
        scanner.close();
        return arrInputData;
    }

    private static void runFrequencyAnalysis(char[] arrInputData) {
        for (char c : arrInputData) {
            if (arrUnicode[c] != c) {
                arrUnicode[c] = c;
                arrUniqueUnicode[amountUniqueCharacter] = c;
                ++amountUniqueCharacter;
            }
            arrCountCharacter[c]++;
        }
        for (int i = 0; i < amountUniqueCharacter; ++i) {
            arrCountUniqueChar[i] = arrCountCharacter[arrUniqueUnicode[i]];
        }
        createFinalData();
        printDataDiagram();
    }

    private static void createFinalData() {
        for (int i = 0; i < amountUniqueCharacter; ++i) {
            for (int j = 0; j < amountUniqueCharacter - i; ++j) {
                if (arrCountUniqueChar[j + 1] >= arrCountUniqueChar[j]) {
                    swap(arrCountUniqueChar, j, j + 1);
                    swap(arrUniqueUnicode, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void printDataDiagram() {
        double maxElement = arrCountUniqueChar[0];
        int numberIteration = Math.min(10, amountUniqueCharacter);
        System.out.printf("%3d\n", arrCountUniqueChar[0]);
        for (int i = 10; i > 0; i--) {
            for (int j = 0; j < numberIteration; j++) {
                int amountHashtag = (int) (arrCountUniqueChar[j] / maxElement * 10.0);
                if (amountHashtag >= i) {
                    System.out.printf("%3c", '#');
                } else if (amountHashtag == i - 1) {
                    System.out.printf("%3d", arrCountUniqueChar[j]);
                } else {
                    System.out.printf("%3c", ' ');
                }
            }
            System.out.println();
        }
        for (int i = 0; i < numberIteration; i++) {
            System.out.printf("%3c", arrUniqueUnicode[i]);
        }
    }

    private static void exitFromProgram(String exitMessage) {
        System.err.println(exitMessage);
        System.exit(-1);
    }
}
