package ex03;

import java.util.Scanner;

public class Program {
    private static final int WEEKS_IN_SEMESTER = 18;
    private static final int NUMBER_OF_GRADES = 5;
    private static final int MAX_GRADE = 9;

    public static void main(String[] args) {
        runVisualization();
    }

    private static void runVisualization() {
        Scanner scanner = new Scanner(System.in);
        int currentWeek = 1;
        long gradeStorage = 0;

        while (currentWeek <= WEEKS_IN_SEMESTER) {
            String inputStr = scanner.next();
            if (inputStr.equals("42")) {
                break;
            } else if (!inputStr.equals("Week")) {
                exitFromProgram();
            } else if (!scanner.hasNextInt()) {
                exitFromProgram();
            }

            int weekNumber = scanner.nextInt();
            if (weekNumber != currentWeek) {
                exitFromProgram();
            }

            int minMarkOnWeek = findMinMark(scanner);
            gradeStorage = (gradeStorage * 10) + minMarkOnWeek;
            ++currentWeek;
        }

        gradeStorage = reverseNumber(gradeStorage);
        printGraphOfMinimumGrades(gradeStorage);
    }

    private static long reverseNumber(long number) {
        long reverse_number = 0;

        while (number != 0) {
            long lastDigit = number % 10;
            number /= 10;
            reverse_number = reverse_number * 10 + lastDigit;
        }

        return reverse_number;
    }

    private static void printGraphOfMinimumGrades(long gradeStorage) {
        int numberOfWeek = 1;
        while (gradeStorage != 0) {
            System.out.print("Week " + numberOfWeek + " ");
            long minMark = gradeStorage % 10L;
            gradeStorage /= 10;

            while (minMark != 0) {
                System.out.print("=");
                --minMark;
            }

            System.out.println(">");
            ++numberOfWeek;
        }
    }

    private static void exitFromProgram() {
        System.err.println("IllegalArgument");
        System.exit(-1);
    }

    private static int findMinMark(Scanner scanner) {
        int minMark = MAX_GRADE;
        for (int i = 0; i < NUMBER_OF_GRADES; ++i) {
            if (!scanner.hasNextInt()) {
                exitFromProgram();
            }

            int currentMark = scanner.nextInt();
            if (currentMark <= 0 || currentMark > MAX_GRADE) {
                exitFromProgram();
            }

            if (minMark >= currentMark) {
                minMark = currentMark;
            }
        }

        return minMark;
    }
}