package ex02;

public class Program {
    public static void main(String[] args) {
        try {
            ProgramArgumentsWorker.startParsingProgramArguments(args);
            int arrSize = ProgramArgumentsWorker.getArrSize();
            int threadsCount = ProgramArgumentsWorker.getThreadsCount();
            int[] randomArr = GeneratorRandomArray.generateRandomArray(arrSize);
            int[] arr = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
//            ThreadedArraySummator summator = new ThreadedArraySummator(randomArr, threadsCount);
            ThreadedArraySummator summator = new ThreadedArraySummator(arr, threadsCount);
            int arraySum = summator.calculateSum();
            summator.printSumByOneThread();
            System.out.println("Sum by threads: " + arraySum);
        } catch (IllegalProgramArgumentsException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
