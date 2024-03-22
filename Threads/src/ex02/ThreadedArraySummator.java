package ex02;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadedArraySummator {
    private int[] array;
    private int numberOfThreads;
    private AtomicInteger result;
    private OneThreadSummator[] threads;


    public ThreadedArraySummator(int[] array, int numberOfThreads) {
        if (array == null || numberOfThreads <= 0) {
            throw new IllegalArgumentException("Invalid input parameters");
        }
        this.array = array;
        this.numberOfThreads = numberOfThreads;
        this.result = new AtomicInteger(0);
        this.threads = new OneThreadSummator[numberOfThreads];
    }

    public int calculateSum() throws InterruptedException {
        int chunkSize = countChunkSize();
        for (int i = 0; i < numberOfThreads; ++i) {
            int startIndex = i * chunkSize;
            int endIndex = (i == numberOfThreads - 1) ? array.length : startIndex + chunkSize;
            this.threads[i] = new OneThreadSummator(startIndex, endIndex);
            this.threads[i].start();
        }
        for (Thread thread : this.threads) {
            thread.join();
        }
        return result.get();
    }

    public void printSumByOneThread() {
        StringBuilder printLine = new StringBuilder();
        for (int i = 0; i < numberOfThreads; ++i) {
            printLine.append("Thread ").append(i + 1).append(": from ").
                    append(threads[i].startIndex).append(" to ").append(threads[i].endIndex - 1).
                    append(" sum is ").append(threads[i].sumResult).append("\n");
        }
        System.out.print(printLine);
    }

    private int countChunkSize() {
        int compare = (int) Math.ceil((double) array.length / numberOfThreads);
        if ((double) array.length / compare > (double) numberOfThreads - 1) {
            return (int) Math.ceil((double) array.length / numberOfThreads);
        } else {
            return (int) Math.floor((double) array.length / numberOfThreads);
        }
    }

    private class OneThreadSummator extends Thread {
        int startIndex;
        int endIndex;
        int sumResult;

        private OneThreadSummator(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.sumResult = 0;
        }

        @Override
        public void run() {
            for (int j = startIndex; j < endIndex; j++) {
                sumResult += array[j];
            }
            result.addAndGet(sumResult);
        }
    }
}
