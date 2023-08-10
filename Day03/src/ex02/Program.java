package ex02;

public class Program {
    public static void main(String[] args) {
        try {
            ProgramArgumentsWorker.startParsingProgramArguments(args);
            int arrSize = ProgramArgumentsWorker.getArrSize();
            int threadsCount = ProgramArgumentsWorker.getThreadsCount();

        } catch (IllegalProgramArgumentsException e) {
            System.err.println(e.getMessage());
        }
    }
}
