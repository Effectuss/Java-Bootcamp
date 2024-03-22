package edu.school21.numbers;

import edu.school21.numbers.exception.IllegalNumberException;

public class NumberWorker {
    private static final String INVALID_NUMBER_FOR_PRIME =
            "This number %d cannot be verified for prime!\n" +
                    "Number must be greater than 1 !";

    public boolean isPrime(int number) {
        if (number <= 1) {
            throw new IllegalNumberException(String.format(INVALID_NUMBER_FOR_PRIME, number));
        }

        for (int i = 2; i <= Math.sqrt(number); ++i) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    public int digitSum(int number) {
        if (number < 0) {
            number *= -1;
        }

        int digitSum = 0;

        while (number > 0) {
            digitSum += number % 10;
            number /= 10;
        }

        return digitSum;
    }
}
