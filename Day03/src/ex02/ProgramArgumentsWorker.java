package ex02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgramArgumentsWorker {
    private static int arrSize;
    private static int threadsCount;

    private final static List<String> arrayOfArgs = new ArrayList<>();

    public static void startParsingProgramArguments(String[] args) throws IllegalProgramArgumentsException {
        if (!isValidProgramArguments(args)) {
            throw new IllegalProgramArgumentsException("Unknown arguments: " + Arrays.toString(args));
        }
        arrayOfArgs.addAll(Arrays.asList(args));
        fillDataForProgram();
        checkCorrectData();
    }

    private static boolean isValidProgramArguments(String[] args) {
        if (args.length != 2 || !args[0].startsWith("--arraySize") || !args[1].startsWith("--threadsCount")) {
            return false;
        }
        return true;
    }

    private static void fillDataForProgram() {
        try {
            arrSize = Integer.parseInt(arrayOfArgs.get(0).split("=")[1]);
            threadsCount = Integer.parseInt(arrayOfArgs.get(1).split("=")[1]);
        } catch (NumberFormatException e) {
            throw new IllegalProgramArgumentsException("Invalid number format: " + e.getMessage());
        }
    }

    private static void checkCorrectData() {
        if (arrSize <= 0 || threadsCount <= 0) {
            throw new IllegalProgramArgumentsException("The number of count must be a positive integer");
        } else if (threadsCount > arrSize) {
            throw new IllegalProgramArgumentsException("The number of threads must be less than the number of count");
        }
    }

    public static int getArrSize() {
        return arrSize;
    }

    public static int getThreadsCount() {
        return threadsCount;
    }
}
