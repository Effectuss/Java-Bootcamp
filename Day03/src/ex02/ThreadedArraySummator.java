package ex02;

public class ThreadedArraySummator {
    private int[] array;
    private int numberOfThreads;

    public ThreadedArraySummator(int[] array, int numberOfThreads) {
        this.array = array;
        this.numberOfThreads = numberOfThreads;
    }
}
