package ex03;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        runVisualization();
    }

    public static void runVisualization() {
        Scanner scanner = new Scanner(System.in);
        int countWeek = 1;
        int numberOfWeek = 1;
        long markStorage = 0;
        while (countWeek <= 18) {
            String inputStr = scanner.next();
            if (inputStr.equals("42")) {
                break;
            }
            if (!inputStr.equals("Week")) {
                exitFromProgram();
            } else {
                if (!scanner.hasNextInt()) {
                    exitFromProgram();
                }
            }
            numberOfWeek = scanner.nextInt();
            if (numberOfWeek != countWeek) {
                exitFromProgram();
            }
            int minMarkOnWeek = findMinMark(scanner);
            markStorage *= 10;
            markStorage += minMarkOnWeek;
            ++countWeek;
        }
        markStorage = reverseNumber(markStorage);
        printGraphOfMinimumGrades(markStorage);
    }

    public static long reverseNumber(long number) {
        long reverse_number = 0;

        while (number != 0) {
            long lastDigit = number % 10;
            number /= 10;
            reverse_number = reverse_number * 10 + lastDigit;
        }

        return reverse_number;
    }

    public static void printGraphOfMinimumGrades(long markStorage) {
        int numberOfWeek = 1;
        while (markStorage != 0) {
            System.out.print("Week " + numberOfWeek + " ");
            long minMark = markStorage % 10;
            markStorage /= 10;
            while (minMark != 0) {
                System.out.print("=");
                --minMark;
            }
            System.out.println(">");
            ++numberOfWeek;
        }
    }

    public static void exitFromProgram() {
        System.err.println("IllegalArgument");
        System.exit(-1);
    }

    public static int findMinMark(Scanner scanner) {
        int minMark = 10;
        for (int i = 0; i < 5; ++i) {
            if (!scanner.hasNextInt()) {
                exitFromProgram();
            }
            int currentMark = scanner.nextInt();
            if (currentMark < 0 || currentMark > 9) {
                exitFromProgram();
            }
            if (minMark > currentMark) {
                minMark = currentMark;
            }
        }
        return minMark;
    }
}