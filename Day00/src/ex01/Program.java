package ex01;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        isPrimeNumber(scanner.nextInt());
        scanner.close();
    }

    private static void isPrimeNumber(int number) {
        if (!isCorrectInput(number)) {
            System.err.println("Illegal Argument");
            System.exit(-1);
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

}
