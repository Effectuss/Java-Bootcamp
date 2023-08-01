package ex04;

import java.util.Scanner;

public class Program {
    private static final int MAX_CHARACTER = 65535;
    private static char[] arrInputData = new char[999];
    private static final char[] arrUnicode = new char[MAX_CHARACTER];
    private static final int[] arrCountCharacter = new int[MAX_CHARACTER];
    private static final char[] arrUniqueUnicode = new char[999];
    private static final int[] arrCountUniqueChar = new int[999];
    private static int amountUniqueCharacter = 0;

    public static void main(String[] args) {
        readInputData();
        runFrequencyAnalysis();
        printDataDiagram();
    }

    private static void readInputData() {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        arrInputData = inputString.toCharArray();
        if (arrInputData.length > 999) {
            exitFromProgram();
        }
    }

    private static void runFrequencyAnalysis() {
        for (char c : arrInputData) {
            if (arrUnicode[c] != c) {
                arrUnicode[c] = c;
                arrUniqueUnicode[amountUniqueCharacter] = c;
                ++amountUniqueCharacter;
            }
            arrCountCharacter[c] += 1;
        }
        for (int i = 0; i < amountUniqueCharacter; ++i) {
            arrCountUniqueChar[i] = arrCountCharacter[arrUniqueUnicode[i]];
        }
    }


    private static void printDataDiagram() {
        for (int i = 0; i < amountUniqueCharacter; ++i) {
            System.out.print(arrCountUniqueChar[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < amountUniqueCharacter; ++i) {
            System.out.print(arrUniqueUnicode[i] + " ");
        }
        System.out.println();

    }

    private static void exitFromProgram() {
        System.err.println("IllegalArgument");
        System.exit(-1);
    }

    private static void sortArray() {

    }
}
