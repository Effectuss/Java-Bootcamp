package ex02;

import java.util.Random;

public class GeneratorRandomArray {
    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random randomizer = new Random();
        for (int i = 0; i < size; i++) {
//            array[i] = randomizer.nextInt(2001) - 1000;
            array[i] = randomizer.nextInt(10);
        }
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        return array;
    }
}
