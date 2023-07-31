package ex02;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int count = countOfCoffeeRequest();
        System.out.println("Count of coffee-request - " + count);
    }

    public static int countOfCoffeeRequest() {
        Scanner scanner = new Scanner(System.in);
        int countCoffeeRequest = 0;

        while (true) {
            int elementOfSequence = scanner.nextInt();
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

    public static boolean isPrimeNumber(int number) {
        boolean isPrime = true;

        for (int i = 2; i <= Math.sqrt(number); ++i) {
            if ((number % i) == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    public static int sumDigitsNumber(int number) {
        int sum = 0;

        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}
