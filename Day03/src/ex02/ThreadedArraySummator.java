package ex02;

public class ThreadedArraySummator {
    private int[] array;
    private int numberOfThreads;
    private int result;
    int[] sumByOneThread;


    public ThreadedArraySummator(int[] array, int numberOfThreads) {
        this.array = array;
        this.numberOfThreads = numberOfThreads;
        this.result = 0;
        sumByOneThread = new int[numberOfThreads];
    }

    public int calculateSum() throws InterruptedException {
        Thread[] threads = new Thread[numberOfThreads];
        int chunkSize = countChunkSize();
        for (int i = 0; i < numberOfThreads; i++) {
            final int start = i * chunkSize;
            final int end = (i == numberOfThreads - 1) ? array.length : start + chunkSize;
            int finalI = i;
            threads[i] = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    this.sumByOneThread[finalI] += array[j];
                }
                synchronized (this) {
                    result += this.sumByOneThread[finalI];
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        return result;
    }

    public void printSumByOneThread() {
        for (int i = 0; i < sumByOneThread.length; i++) {
            System.out.println("Thread " + i + ": " + sumByOneThread[i]);
        }
    }

    private int countChunkSize() {
        int compare = (int) Math.ceil((double) array.length / numberOfThreads);
        if ((double) array.length / compare > (double) numberOfThreads - 1) {
            return (int) Math.ceil((double) array.length / numberOfThreads);
        } else {
            return (int) Math.floor((double) array.length / numberOfThreads);
        }
    }
}
