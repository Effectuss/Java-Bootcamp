package edu.school21;

import edu.school21.numbers.NumberWorker;
import edu.school21.numbers.exception.IllegalNumberException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class NumberWorkerTest {
    private static final NumberWorker numberWorker = new NumberWorker();

    @ParameterizedTest
    @ValueSource(ints = {2, 7, 11, 13})
    void isPrimeForPrimes(int primeNumber) {
        assertTrue(numberWorker.isPrime(primeNumber));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 10})
    void isPrimeForNotPrimes(int notPrimeNumber) {
        assertFalse(numberWorker.isPrime(notPrimeNumber));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -10})
    void isPrimeForIncorrectNumbers(int incorrectNumber) {
        assertThrows(IllegalNumberException.class, () ->
                numberWorker.isPrime(incorrectNumber));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    void testDigitSum(int number, int sum) {
        assertEquals(sum, numberWorker.digitSum(number));
    }
}
