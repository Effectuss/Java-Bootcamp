package ex02;

public class Program {
    private static final int ONE = 1;

    public static void main(String[] args) {
        try {
            ProgramArgumentsWorker.startParsingProgramArguments(args);

            int arrSize = ProgramArgumentsWorker.getArrSize();
            int threadsCount = ProgramArgumentsWorker.getThreadsCount();
            int[] randomArr = GeneratorRandomArray.generateRandomArray(arrSize);

            ThreadedArraySummator oneThreadSummator = new ThreadedArraySummator(randomArr, ONE);
            ThreadedArraySummator multiThreadSummator = new ThreadedArraySummator(randomArr, threadsCount);

            int oneThreadSum = oneThreadSummator.calculateSum();

            System.out.println("Sum: " + oneThreadSum);

            int multiThreadSum = multiThreadSummator.calculateSum();

            multiThreadSummator.printSumByOneThread();
            System.out.println("Sum by threads: " + multiThreadSum);

        } catch (IllegalProgramArgumentsException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
