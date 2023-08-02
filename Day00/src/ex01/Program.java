package ex01;

import java.util.Scanner;

public class Program {
private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        if (scanner.hasNextInt()) {
            isPrimeNumber(scanner.nextInt());
        } else {
            exitFromProgram();
        }
        scanner.close();
    }

    private static void isPrimeNumber(int number) {
        if (!isCorrectInput(number)) {
            exitFromProgram();
        }
        int iterationCount = 1;
        boolean isPrime = true;
        for (int i = 2; i <= Math.sqrt(number); ++i, ++iterationCount) {
            if ((number % i) == 0) {
                isPrime = false;
                break;
            }
        }
        if (isPrime) {
            System.out.println("true " + iterationCount);
        } else {
            System.out.println("false " + iterationCount);
        }
    }

    private static boolean isCorrectInput(int number) {
        return (number > 0) && (number != 1);
    }

    private static void exitFromProgram() {
        System.err.println("Illegal Argument");
        scanner.close();
        System.exit(-1);
    }
}
