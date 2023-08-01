package ex04;

import java.rmi.MarshalException;
import java.util.Scanner;

public class Program {
    private static final int MAX_CHARACTER = 65535;
    private static final char[] arrUnicode = new char[MAX_CHARACTER];
    private static final int[] arrCountCharacter = new int[MAX_CHARACTER];
    private static final char[] arrUniqueUnicode = new char[999];
    private static final int[] arrCountUniqueChar = new int[999];
    private static int amountUniqueCharacter = 0;

    public static void main(String[] args) {
        runFrequencyAnalysis(readInputData());
        printDataDiagram();
    }

    private static char[] readInputData() {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        char[] arrInputData = inputString.toCharArray();
        if (arrInputData.length > 999) {
            exitFromProgram("IllegalArgument");
        } else if (arrInputData.length == 0) {
            exitFromProgram("Empty input");
        }
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
    }

    private static void createFinalData() {
        for (int i = 0; i < amountUniqueCharacter; ++i) {
            for (int j = 0; j < amountUniqueCharacter - i; ++j) {
                if (arrCountUniqueChar[j + 1] >= arrCountUniqueChar[j]) {
                    int temp = arrCountUniqueChar[j];
                    arrCountUniqueChar[j] = arrCountUniqueChar[j + 1];
                    arrCountUniqueChar[j + 1] = temp;
                    temp = arrUniqueUnicode[j];
                    arrUniqueUnicode[j] = arrUniqueUnicode[j + 1];
                    arrUniqueUnicode[j + 1] = (char) temp;
                }
            }
        }
    }

    private static void printDataDiagram() {
        double maxElement = arrCountUniqueChar[0];
        int numberIteration = Math.min(10, amountUniqueCharacter);
        System.out.printf("%3d\n", arrCountUniqueChar[0]);
        for (int i = 10; i > 0; i--) {
            for (int j = 0; j < numberIteration; j++) {
                int amountHashtag = (int) (arrCountUniqueChar[j] / maxElement * 10.0);
                if (amountHashtag >= i) {
                    System.out.print("  #");
                } else if (amountHashtag == i - 1) {
                    System.out.printf("%3d", arrCountUniqueChar[j]);
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
        for (int i = 0; i < numberIteration; i++) {
            System.out.print("  " + arrUniqueUnicode[i]);
        }
        System.out.println();
    }


    private static void exitFromProgram(String exitMessage) {
        System.err.println(exitMessage);
        System.exit(-1);
    }
}
