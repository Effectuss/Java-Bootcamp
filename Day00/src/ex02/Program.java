package ex02;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int count = countOfCoffeeRequest();
        System.out.println("Count of coffee-request - " + count);
    }

    private static int countOfCoffeeRequest() {
        Scanner scanner = new Scanner(System.in);
        int countCoffeeRequest = 0;

        while (true) {
            if (!scanner.hasNextLong()) {
                exitFromProgram(scanner);
            }
            long elementOfSequence = scanner.nextLong();
            if (elementOfSequence == 42) {
                break;
            }

            if (isPrimeNumber(sumDigitsNumber(elementOfSequence))) {
                ++countCoffeeRequest;
            }
        }

        scanner.close();
        return countCoffeeRequest;
    }

    private static boolean isPrimeNumber(long number) {
        boolean isPrime = true;

        for (int i = 2; i <= Math.sqrt(number); ++i) {
            if ((number % i) == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    private static long sumDigitsNumber(long number) {
        long sum = 0;

        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    private static void exitFromProgram(Scanner scanner) {
        System.err.println("Illegal Argument");
        scanner.close();
        System.exit(-1);
    }
}


